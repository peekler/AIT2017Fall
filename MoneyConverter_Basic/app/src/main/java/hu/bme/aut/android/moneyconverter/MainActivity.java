package hu.bme.aut.android.moneyconverter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dd.CircularProgressButton;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private final String URL_BASE =
            "http://api.fixer.io/latest?base=USD&symbols=HUF";

    private CircularProgressButton btnGetRate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);

        btnGetRate = (CircularProgressButton) findViewById(R.id.btnGetRate);
        btnGetRate.setIndeterminateProgressMode(true);

        btnGetRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGetRate.setProgress(50);
                String query = URL_BASE;
                new HttpGetTask(getApplicationContext()).
                        execute(query);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                brWeatherReceiver,
                new IntentFilter(HttpGetTask.FILTER_RESULT)
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(
                this).unregisterReceiver(brWeatherReceiver);
    }

    private BroadcastReceiver brWeatherReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String rawResult = intent.getStringExtra(
                    HttpGetTask.KEY_RESULT);

            try {
                // TODO: parse JSON here

                tvResult.setText(rawResult);
            } catch (Exception e) {
                e.printStackTrace();
                btnGetRate.setProgress(-1);
            }

            btnGetRate.setProgress(100);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnGetRate.setProgress(0);
                }
            }, 1500);
        }
    };


}
