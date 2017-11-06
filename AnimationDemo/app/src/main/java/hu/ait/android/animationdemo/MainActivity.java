package hu.ait.android.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btnPlayAnim = findViewById(R.id.btnPlayAnim);
        final LinearLayout layoutContent = findViewById(R.id.layoutContent);


        final Animation anim = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.show_anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {

                btnPlayAnim.setEnabled(true);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        btnPlayAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnPlayAnim.setEnabled(false);
                btnPlayAnim.startAnimation(anim);

                //layoutContent.startAnimation(anim);
            }
        });


    }
}
