package hu.autsoft.android.moneyexchangeretrofit.network;

import hu.autsoft.android.moneyexchangeretrofit.data.MoneyResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Peter on 2017. 11. 13..
 */

public interface MoneyAPI {
    @GET("latest")
    Call<MoneyResult> getRatesToUSD(@Query("base") String base);
}

// http://api.fixer.io/latest?base=USD
