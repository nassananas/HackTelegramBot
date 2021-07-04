package com.hack.telegrambot.api;

import com.hack.telegrambot.model.Candle;
import com.hack.telegrambot.model.User;
import com.hack.telegrambot.service.GenerationService;
import com.hack.telegrambot.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramHandler {
    private BotStateContext context;
    private UserService userService;
    private GenerationService generationService;
    //ConcurrentHashMap<Integer, BotState> chatState = new ConcurrentHashMap<Integer, BotState>();
    //private UserDataCache userDataCache;

    public TelegramHandler(BotStateContext botStateContext, UserService userService, GenerationService generationService) {
        this.userService = userService;
        this.context = botStateContext;
        this.generationService = generationService;
    }


/*    public SendMessage handleUpdate(Update update){
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if(message != null){
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }*/

    public SendMessage handleInputMessage() throws TelegramApiException {
        String inputMessage = context.getInput();
        User user = context.getUser();
        //String inputMessage = message.getText();
        //int chatId = message.getFrom().getId();
        //long chatId = context.getUser().getChatId();
        //BotState botState;
        SendMessage replyMessage = null;

        switch (inputMessage){
            case "/start":
                //старт чата
                user.setState(BotState.START);
                replyMessage = new SendMessage(user.getChatId(), "Привет! \nЯ могу помочь тебе отслеживать криптовалюту! \n" +
                        "Отправь мне цифру, соответствующую следующим действиям: \n" +
                        "1 - Подписка на BTC \n" +
                        "2 - Подписка на ETH \n" +
                        "3 - Подписка на BNB \n" +
                        "4 - Подписка на DOGE \n" +
                        "5 - Подписка на DOT \n" +
                        "6 - Подписка на ADA \n" +
                        ""
                );

                break;
            case "1":
                //подписка на BTC
                user.setState(BotState.SUBSCRIPTION_BTC);
                replyMessage = new SendMessage(user.getChatId(), userService.findByChatId(user.getChatId()).getState().name());
                userService.updateUser(user);

                //botState = BotState.SUBSCRIPTION_BTC;
                break;
            case "2":
                //подписка на ETH

                generationService.sendCoin();


                break;
            case "3":
                //подписка на BNB
//                context.getUser().setState(BotState.SUBSCRIPTION_BNB);
//
//                botState = BotState.SUBSCRIPTION_BNB;

                break;
//            case "4":
//                //подписка на DOGE
//                context.getUser().setState(BotState.SUBSCRIPTION_BTC);
//
//                botState = BotState.SUBSCRIPTION_DOGE;
//                break;
//            case "5":
//                //подписка на DOT
//                context.getUser().setState(BotState.SUBSCRIPTION_BTC);
//
//                botState = BotState.SUBSCRIPTION_DOT;
//                break;
//            case "6":
//                //подписка на ADA
//                context.getUser().setState(BotState.SUBSCRIPTION_BTC);
//
//                botState = BotState.SUBSCRIPTION_ADA;
//                break;
//            case "1":
//            case "2":
//            case "3":
//            case "4":
//            case "5":
//            case "6":
//                createSubscription();

            case "0":
                //отписка
                context.getUser().setState(BotState.SUBSCRIPTION_BTC);
                break;

            default:
                //context.getUser().setState();
                replyMessage = new SendMessage(context.getUser().getChatId(), "Не понимаю тебя");
                //botState = chatState.get(chatId);
                break;
                //botState = getCurrentBotState(chatId);

        }
        //context.getUser().setState(botState);
        //replyMessage = context.analizeState(inputMessage);
        return replyMessage;
    }

    public void analizeState(){

    }


    public void createSubscription(){


    }


}
