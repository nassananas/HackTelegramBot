package com.hack.telegrambot;

import com.hack.telegrambot.api.BotState;
import com.hack.telegrambot.api.BotStateContext;
import com.hack.telegrambot.api.TelegramHandler;
import com.hack.telegrambot.common.Figi;
import com.hack.telegrambot.model.Candle;
import com.hack.telegrambot.model.User;
import com.hack.telegrambot.service.CandleService;
import com.hack.telegrambot.service.GenerationService;
import com.hack.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.GeneratedValue;

@Component
@PropertySource("classpath:application.properties")
public class HackTelegramBot extends TelegramWebhookBot {

    @Value("${telegrambot.botUserName}")
    String botUserName;
    @Value("${telegrambot.botToken}")
    String botToken;
    @Value("${telegrambot.webHookPath}")
    String webHookPath;


    private final UserService userService;
    private final CandleService candleService;
    private GenerationService generationService;

    private BotStateContext context;

    public HackTelegramBot(UserService userService, CandleService candleService) {
        this.userService = userService;
        this.candleService = candleService;
        this.generationService = new GenerationService(this, candleService, userService);
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }
    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @SneakyThrows
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update){
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            User user = userService.findByChatId(chat_id);

            BotState state;

            String text = update.getMessage().getText();

            if (user == null) {
                state = BotState.START;
                user = new User (chat_id, state);
                userService.addUser(user);
                context = new BotStateContext(this, user, text);
            }
            else {
                context = new BotStateContext(this, user, text);
                //state = user.getState();

            }
            TelegramHandler telegramHandler = new TelegramHandler(context, userService, generationService);
            SendMessage sendMessage = telegramHandler.handleInputMessage();

//            try {
//                //execute(sendMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }

        return null;
    }



}
