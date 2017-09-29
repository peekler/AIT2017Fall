package hu.ait.android.multiactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hu.ait.android.multiactivitydemo.data.DataManager;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String data = DataManager.getInstance().getData();
        /*if (getIntent().hasExtra(MainActivity.KEY_DATA)) {
            data = getIntent().getStringExtra(MainActivity.KEY_DATA);
        }*/

        TextView tvData = (TextView) findViewById(R.id.tvData);
        tvData.setText(data);

    }
}
