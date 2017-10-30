package com.example.peter.realmdemowithcats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNew = findViewById(R.id.btnNew);
        Button btnQuery = findViewById(R.id.btnQuery);
        final TextView tvData = findViewById(R.id.tvData);



        RealmConfiguration config = new RealmConfiguration.Builder().
                deleteRealmIfMigrationNeeded().
                build();
        final Realm realm = Realm.getInstance(config);


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                Cat newCat = realm.createObject(Cat.class);
                newCat.setName("Garfield"+System.currentTimeMillis());
                newCat.setAge(9);
                realm.commitTransaction();
            }
        });


        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Cat> cats = realm.where(Cat.class).findAll();
                tvData.setText("");
                for (Cat cat : cats) {
                    tvData.append(cat.getName()+" "+cat.getAge()+"\n");
                }
            }
        });

    }
}
