package hu.ait.android.threadtimerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    private boolean enabled = false;

    private Timer timerMain;

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvData.append("$");
                }
            });
        }
    }


    private class MyThread extends Thread {
        public void run() {
            while (enabled) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvData.append("#");
                    }
                });

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enabled = true;
                new MyThread().start();

                if (timerMain != null) {
                    timerMain.cancel();
                }

                timerMain = new Timer();
                timerMain.schedule(new MyTimerTask(), 0, 2000);

                tvData.setText("HEY");

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        enabled = false;

        if (timerMain != null) {
            timerMain.cancel();
        }
    }
}
