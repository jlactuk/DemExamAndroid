package com.example.dead;

public class Chat {
    private int chatId;
    private String name;

    public Chat(int chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
