package hu.ait.android.highlowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String KEY_RANDOM = "KEY_RANDOM";
    public static final String KEY_LAST_MESSAGE = "KEY_LAST_MESSAGE";
    public static final String KEY_NUMBER = "KEY_NUMBER";
    private int generated = 0;

    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        Button btnGuess = (Button) findViewById(R.id.btnGuess);
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (!etGuess.getText().toString().equals("") )
                try {
                    if (!TextUtils.isEmpty(etGuess.getText())) {
                        int guess = Integer.parseInt(etGuess.getText().toString());

                        if (guess < generated) {
                            tvStatus.setText("The number is greater");
                        } else if (guess > generated) {
                            tvStatus.setText("The number is smaller");
                        } else {
                            tvStatus.setText("You have won! YEEEE");

                            Intent intentResult = new Intent();
                            intentResult.setClass(GameActivity.this, ResultActivity.class);

                            intentResult.putExtra(KEY_NUMBER, generated);

                            startActivity(intentResult);

                        }
                    } else {
                        etGuess.setError("This field can not be empty");
                    }
                } catch (NumberFormatException nf) {
                    etGuess.setError("Wrong content");
                    nf.printStackTrace();
                }

            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_RANDOM)) {
            generated = savedInstanceState.getInt(KEY_RANDOM, 0);

            tvStatus.setText(savedInstanceState.getString(KEY_LAST_MESSAGE, ""));

        } else {
            generateNewRandom();
        }
    }

    private void generateNewRandom() {
        generated = new Random(System.currentTimeMillis()).nextInt(3);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_RANDOM, generated);
        outState.putString(KEY_LAST_MESSAGE, tvStatus.getText().toString());

    }
}
