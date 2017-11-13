package hu.bme.aut.android.moneyconverter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.bme.aut.android.moneyconverter.data.MoneyResult;


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

        HttpURLConnection hc = null;
        InputStream is = null;
        try {
            URL url = new URL(params[0]);
            hc = (HttpURLConnection)url.openConnection();
            hc.setConnectTimeout(10000);
            hc.setReadTimeout(10000);
            is = hc.getInputStream();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                bos.write(ch);
            }

            result = new String(bos.toByteArray());

        } catch (Exception e) {
            result = e.getMessage();
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (hc != null) {
                hc.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        MoneyResult moneyResult = new Gson().fromJson(result, MoneyResult.class);

        EventBus.getDefault().post(moneyResult);
    }
}
