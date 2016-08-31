package com.ironyard.charlotte;

import java.util.ArrayList;

/**
 * Created by lindseyringwald on 8/27/16.
 */
public class User {
     public String name;
     public String password;

    static ArrayList<Message> messages = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}




