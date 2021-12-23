package com.example.dead;

public class Message {
    private int chatId;
    private int messageId;
    private String creationDateTime;
    private String firstName;
    private String secondName;
    private String avatar;
    private String text;

    public Message(int chatId, int messageId, String creationDateTime, String firstName, String secondName, String avatar, String text) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.creationDateTime = creationDateTime;
        this.firstName = firstName;
        this.secondName = secondName;
        this.avatar = avatar;
        this.text = text;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
