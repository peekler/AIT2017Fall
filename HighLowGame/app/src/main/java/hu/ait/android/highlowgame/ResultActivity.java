package hu.ait.android.highlowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvResult = (TextView) findViewById(R.id.tvResult);

        if (getIntent().hasExtra(GameActivity.KEY_NUMBER)) {
            int winnerNumber = getIntent().getIntExtra(GameActivity.KEY_NUMBER, 0);
            tvResult.setText("Congratulations, the winner number is: "+winnerNumber);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intentMenu = new Intent();
        intentMenu.setClass(ResultActivity.this, MainActivity.class);
        intentMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intentMenu);

        finish();
    }
}
