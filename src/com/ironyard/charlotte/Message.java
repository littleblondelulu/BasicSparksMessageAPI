package com.ironyard.charlotte;

/**
 * Created by lindseyringwald on 8/27/16.
 */
public class Message {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public Message(String message) {
        this.message = message;
    }

}

