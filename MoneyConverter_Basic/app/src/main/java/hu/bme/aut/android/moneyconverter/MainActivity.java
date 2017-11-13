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
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import hu.bme.aut.android.moneyconverter.data.MoneyResult;


public class MainActivity extends AppCompatActivity {

    private final String URL_BASE =
            "http://api.fixer.io/latest?base=USD";

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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onEvent(MoneyResult moneyResult) {
        tvResult.setText(
                    moneyResult.getRates().gethUF() + "\n" +
                            moneyResult.getRates().geteUR());
        btnGetRate.setProgress(100);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnGetRate.setProgress(0);
            }
        }, 1500);
    }


}
