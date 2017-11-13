package hu.autsoft.android.moneyexchangeretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hu.autsoft.android.moneyexchangeretrofit.data.MoneyResult;
import hu.autsoft.android.moneyexchangeretrofit.network.MoneyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvData = findViewById(R.id.tvData);
        Button btnGet = findViewById(R.id.btnGet);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final MoneyAPI moneyAPI = retrofit.create(MoneyAPI.class);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MoneyResult> call
                        = moneyAPI.getRatesToUSD("USD");

                call.enqueue(new Callback<MoneyResult>() {
                    @Override
                    public void onResponse(Call<MoneyResult> call, Response<MoneyResult> response) {
                        tvData.setText(""+response.body().getRates().gethUF());
                    }

                    @Override
                    public void onFailure(Call<MoneyResult> call, Throwable t) {
                        tvData.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
