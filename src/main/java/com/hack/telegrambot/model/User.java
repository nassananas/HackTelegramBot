package com.hack.telegrambot.model;

import com.hack.telegrambot.api.BotState;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    private long chatId;
    private BotState state;

    public User(long chatId, BotState state) {
        this.chatId = chatId;
        this.state = state;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public BotState getState() {
        return state;
    }

    public void setState(BotState state) {
        this.state = state;
    }


}
