package hu.ait.android.recylerviewdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Peter on 2017. 11. 02..
 */

public class TodoApplication extends Application {

    private Realm realmTodo;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }


    public void openRealm() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realmTodo = Realm.getInstance(config);
    }

    public void closeRealm() {
        realmTodo.close();
    }

    public Realm getRealmTodo() {
        return realmTodo;
    }

}
