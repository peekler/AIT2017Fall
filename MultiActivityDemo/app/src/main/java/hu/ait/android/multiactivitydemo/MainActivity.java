package hu.ait.android.multiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.ait.android.multiactivitydemo.data.DataManager;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText etData = (EditText) findViewById(R.id.etData);
        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetails = new Intent();
                intentDetails.setClass(MainActivity.this, DetailsActivity.class);

                intentDetails.putExtra(KEY_DATA, etData.getText().toString());


                DataManager.getInstance().setData(etData.getText().toString());


                startActivity(intentDetails);

                //finish();

            }
        });

    }
}
