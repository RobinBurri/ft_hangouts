package com.example.ft_hangouts.Models;

public class Message {
    private final String message;
    private final String date;
    private final int senderId;
    private final int receiverId;

    public Message(String message, String date, int senderId, int receiverId) {
        this.message = message;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }
}
