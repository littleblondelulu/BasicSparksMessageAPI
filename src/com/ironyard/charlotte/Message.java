package com.ironyard.charlotte;

/**
 * Created by lindseyringwald on 8/27/16.
 */
public class Message extends User{
 //don't really need userMessage variable --
 // used trial/error deciding to make Message it's own Class or store it a local Class in User
 //private String userMessage;

    String editMessage;
    String editedMessages;
    String deleteMessage;
    int accessMessageNumber;

    public Message(String editMessage, String editedMessages, String deleteMessage, int accessMessageNumber) {
        this.editMessage = editMessage;
        this.editedMessages = editedMessages;
        this.deleteMessage = deleteMessage;
        this.accessMessageNumber = accessMessageNumber;
    }

    public String getEditMessage() {
        return editMessage;
    }

    public void setEditMessage(String editMessage) {
        this.editMessage = editMessage;
    }

    public String getEditedMessages() {
        return editedMessages;
    }

    public void setEditedMessages(String editedMessages) {
        this.editedMessages = editedMessages;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public int getAccessMessageNumber() {
        return accessMessageNumber;
    }

    public void setAccessMessageNumber(int accessMessageNumber) {
        this.accessMessageNumber = accessMessageNumber;
    }


}

//OPTION: CAN MAKE CLASS METHOD FOR IF/ELSE (FOR EDIT AND DELETE) HERE
// DECIDE IF WANT TO INCLUDE THE "DO SOMETHING TO MESSAGE#" METHOD HERE OR IN MAIN
