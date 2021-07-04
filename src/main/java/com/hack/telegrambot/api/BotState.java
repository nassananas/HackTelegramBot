package com.hack.telegrambot.api;

//Возможные состояния бота

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public enum BotState {
//    START {
//        @Override
//        public void enter(BotStateContext context){
//            sendMessage(context, "Hello");
//        }
//
//        @Override
//        public BotState nextState(){
//            return CHOOSE_FIGI;
//        }
//    },
//    CHOOSE_FIGI {
//        @Override
//        public void enter(BotStateContext context){
//            sendMessage(context, "Выбор");
//        }
//
//        @Override
//        public BotState nextState(){
//            return SUBSCRIPTION;
//        }
//    },
//    SUBSCRIPTION{
//        @Override
//        public void enter(BotStateContext context){
//            sendMessage(context, "");
//        }
//
//        @Override
//        public BotState nextState(){
//            return SUBSCRIPTION;
//        }
//    },
    START,
    CHOOSE,
    SUBSCRIPTION_BTC,
    SUBSCRIPTION_ETH,
    SUBSCRIPTION_BNB,
    SUBSCRIPTION_DOGE,
    SUBSCRIPTION_DOT,
    SUBSCRIPTION_ADA,
    COMPLETION
//        @Override
//        public void enter(BotStateContext context){
//            sendMessage(context, "");
//        }
//
//        @Override
//        public BotState nextState(){
//            return CHOOSE_FIGI;
//        }
//    };

//    public abstract void enter(BotStateContext context);
//    public abstract BotState nextState();

//    public static BotState getInitialState(){
//        return BotState.START;
//    }
//
//    public void sendMessage(BotStateContext context, String text){
//        SendMessage message = new SendMessage()
//                .setChatId((long) context.getUser().getChatId())
//                .setText(text);
//        try{
//            context.getBot().execute(message);
//        } catch (TelegramApiException e){
//            e.printStackTrace();
//        }
//    }
}
