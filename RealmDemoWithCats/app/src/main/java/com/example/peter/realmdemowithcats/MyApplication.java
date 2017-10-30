package com.example.peter.realmdemowithcats;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Peter on 2017. 10. 30..
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
