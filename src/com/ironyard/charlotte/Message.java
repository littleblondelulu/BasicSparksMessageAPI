package com.ironyard.charlotte;

/**
 * Created by lindseyringwald on 8/27/16.
 */
public class Message extends User{
 //Make class fields private
    String message;
    String editMessage;
    String editedMessage;
    String deleteMessage;



//Make construcor var's public
    public String Message(String message, String editMessage, String editedMessage, String deleteMessage) {
        this.message = message;
        this.editMessage = editMessage;
        this.editedMessage = editedMessage;
        this.deleteMessage = deleteMessage;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEditMessage() {
        return editMessage;
    }

    public void setEditMessage(String editMessage) {
        this.editMessage = editMessage;
    }

    public String getEditedMessage() {
        this.editedMessage = editedMessage;
    }

    public void setEditedMessage() {
        return editedMessage;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

}
