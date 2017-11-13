package hu.bme.aut.android.moneyconverter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpGetTask extends AsyncTask<String, Void, String> {

    public static final String FILTER_RESULT = "FILTER_RESULT";
    public static final String KEY_RESULT = "KEY_RESULT";
    private Context ctx;

    public HttpGetTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        // Todo: network communication

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intentBrResult = new Intent(FILTER_RESULT);
        intentBrResult.putExtra(KEY_RESULT,result);

        LocalBroadcastManager.getInstance(
            ctx).sendBroadcast(intentBrResult);
    }
}
