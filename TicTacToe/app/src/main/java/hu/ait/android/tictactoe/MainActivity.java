package hu.ait.android.tictactoe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import hu.ait.android.tictactoe.view.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

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

        ShimmerFrameLayout shimmerView = findViewById(R.id.shimmer_view);
        shimmerView.startShimmerAnimation();
    }

    public void showNextPlayer(String nextPlayer) {
        tvStatus.setText(getString(R.string.text_next_player, nextPlayer));

        Snackbar.make(layoutContent, "Next turn", Snackbar.LENGTH_LONG).setAction(
                "Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                }
        ).show();
    }

}
