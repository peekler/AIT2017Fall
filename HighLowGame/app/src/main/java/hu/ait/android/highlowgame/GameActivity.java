package hu.ait.android.highlowgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int generated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        final TextView tvStatus = (TextView) findViewById(R.id.tvStatus);

        Button btnGuess = (Button) findViewById(R.id.btnGuess);
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guess = Integer.parseInt(etGuess.getText().toString());

                if (guess < generated) {
                    tvStatus.setText("The number is greater");
                } else if (guess > generated) {
                    tvStatus.setText("The number is smaller");
                } else {
                    tvStatus.setText("You have won! YEEEE");
                }

            }
        });

        generateNewRandom();
    }

    private void generateNewRandom() {
        generated = new Random(System.currentTimeMillis()).nextInt(100);
    }
}
