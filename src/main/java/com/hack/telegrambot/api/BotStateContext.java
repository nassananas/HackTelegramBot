package com.hack.telegrambot.api;

import com.hack.telegrambot.HackTelegramBot;
import com.hack.telegrambot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotStateContext {
    private final HackTelegramBot bot;
    private static User user;

    @Autowired
    public BotStateContext(HackTelegramBot bot) {
        this.bot = bot;
    }

    public BotStateContext(HackTelegramBot bot, User user) {
        this.bot = bot;
        this.user = user;
    }

    public HackTelegramBot getBot() {
        return bot;
    }

    public User getUser() {
        return user;
    }


}
