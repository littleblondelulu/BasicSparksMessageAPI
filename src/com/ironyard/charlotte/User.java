package com.ironyard.charlotte;

import java.util.ArrayList;

/**
 * Created by lindseyringwald on 8/27/16.
 */
public class User {
    String name;
    String password;

    //messages type changed <Message> to <String> -- not sure how/why it changed -- be aware of in case changes again or ends up being a bug somewhere
    ArrayList<Message> messages = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password= password;
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

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}

