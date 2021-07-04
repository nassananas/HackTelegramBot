package com.hack.telegrambot.service;

import com.hack.telegrambot.HackTelegramBot;
import com.hack.telegrambot.common.FigiProperty;
import com.hack.telegrambot.model.Candle;
import com.hack.telegrambot.common.Figi;
import com.hack.telegrambot.model.User;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Data
@EnableScheduling
public class GenerationService {

    private final HackTelegramBot hackTelegramBot;
    private final CandleService candleService;
    private final UserService userService;
    public static double min;
    public static double max;

    public GenerationService(HackTelegramBot hackTelegramBot, CandleService candleService, UserService userService) {
        this.hackTelegramBot = hackTelegramBot;
        this.candleService = candleService;
        this.userService = userService;
    }

    public void generateValues(){

        for (Figi figi:Figi.values()) {
            Candle candle = new Candle();
            candle.setFigi(figi.name());
            FigiProperty fp = new FigiProperty(figi);

            min = fp.getFigiMin();
            max = fp.getFigiMax();
            candle.setOpen(rnd(min,max));
            candle.setClose(rnd(min, max));
            candle.setLow(rnd(min, Math.max(candle.getOpen(), candle.getClose())));
            candle.setHigh(rnd(Math.max(candle.getOpen(), candle.getClose()), max));

            candleService.addCandle(candle);

        }

    }

    public static double rnd(double min, double max)
    {
        max -= min;
        return (double) (Math.random() * ++max) + min;
    }

    public void printCoin(Candle candle, long chatId) throws TelegramApiException {

        //Candle candle = candleService.findByFigi(figi.name());
        String str = "low: " + candle.getLow() + " \n"
                + "high:" + candle.getHigh() + " \n"
                + "open: " + candle.getOpen() + " \n"
                + "close: " + candle.getClose() + " \n";
            //+ "open time: " + dateFormat.format(new Date(candle.getOpenTime()));

            hackTelegramBot.execute(new SendMessage(chatId, "свеча " + candle.getFigi() + ": \n" + str));
    }

    @Scheduled(fixedDelay = 5000)
    public void sendCoin() throws TelegramApiException {
        generateValues();
        List<Candle> candleList = candleService.findAllCandles();
        List<User> listUsers = userService.findAll();
        for (User user:
             listUsers) {
            switch (user.getState()){
                case SUBSCRIPTION_BTC:
                    Candle candle = candleList.stream().filter(a -> user.getState().name().equals(a.getFigi())).findFirst().orElse(null);;
                    printCoin(candle, user.getChatId());
                    break;
                case SUBSCRIPTION_ETH:

                case SUBSCRIPTION_BNB:

                case SUBSCRIPTION_DOGE:

            }

        }
    }







}
