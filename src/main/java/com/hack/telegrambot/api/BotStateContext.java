package com.hack.telegrambot.api;

import com.hack.telegrambot.HackTelegramBot;
import com.hack.telegrambot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
@Component
public class BotStateContext {
//    private ConcurrentHashMap<BotState, MessageHandler> messageHandlers = new ConcurrentHashMap<BotState, MessageHandler>();
    private final HackTelegramBot bot;
    private static User user;
    private static String input;

    @Autowired
    public BotStateContext(HackTelegramBot bot) {
        this.bot = bot;
    }

    public BotStateContext(HackTelegramBot bot, User user, String input) {
        this.bot = bot;
        this.user = user;
        this.input = input;
    }

    public HackTelegramBot getBot() {
        return bot;
    }

    public User getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }


}
