package com.example.peter.realmdemowithcats;

import io.realm.RealmObject;

/**
 * Created by Peter on 2017. 10. 30..
 */

public class Cat extends RealmObject {

    private String name;
    private int age;

    public Cat(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
