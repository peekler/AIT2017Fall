package hu.ait.android.aittime;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDateUI();
    }

    private void initDateUI() {
        Button btnTime = (Button) findViewById(R.id.btnTime);
        final TextView tvTime = (TextView) findViewById(R.id.tvTime);

        final EditText etName = (EditText) findViewById(R.id.etUser);

        final LinearLayout layoutContent = findViewById(R.id.layoutContent);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG_MAIN", "btnTime button was pressed");


                String date =
                        etName.getText().toString()+ " " +
                        getString(R.string.header_date)+
                        new Date(System.currentTimeMillis());

                Toast.makeText(MainActivity.this,
                    date,
                    Toast.LENGTH_LONG
                ).show();

                tvTime.setText(date);

                Snackbar.make(layoutContent, date, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
