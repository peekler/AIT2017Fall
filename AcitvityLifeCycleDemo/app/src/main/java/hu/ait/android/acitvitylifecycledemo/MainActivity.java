package hu.ait.android.acitvitylifecycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LIFE = "TAG_LIFE";
    private static final String KEY_SCORE = "KEY_SCORE";

    int score = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        Log.d(TAG_LIFE, "onCreate called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SCORE, score);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG_LIFE, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG_LIFE, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG_LIFE, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG_LIFE, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG_LIFE, "onDestroy called");
    }
}
