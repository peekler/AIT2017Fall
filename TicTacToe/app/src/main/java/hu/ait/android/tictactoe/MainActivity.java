package hu.ait.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);

        final TicTacToeView ticTacToeView = (TicTacToeView) findViewById(R.id.ticTacToeView);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacToeView.clearBoard();
                showNextPlayer("O");
            }
        });
    }

    public void showNextPlayer(String nextPlayer) {
        tvStatus.setText(getString(R.string.text_next_player, nextPlayer));


    }

}
