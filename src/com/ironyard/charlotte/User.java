package com.ironyard.charlotte;

import java.util.ArrayList;

/**
 * Created by lindseyringwald on 8/27/16.
 */
public class User {
    private String name;
    private String password;

    public static ArrayList<Message> messages = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static ArrayList<Message> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




