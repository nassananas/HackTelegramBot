package com.hack.telegrambot.service;

import com.hack.telegrambot.HackTelegramBot;
import com.hack.telegrambot.common.FigiProperty;
import com.hack.telegrambot.model.Candle;
import com.hack.telegrambot.common.Figi;
import com.hack.telegrambot.model.User;
import lombok.Data;
import java.sql.Timestamp;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.List;

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
            candle.setFigi(figi);
            FigiProperty fp = new FigiProperty(figi);

            min = fp.getFigiMin();
            max = fp.getFigiMax();
            candle.setOpen(rnd(min,max));
            candle.setClose(rnd(min, max));
            candle.setLow(rnd(min, Math.max(candle.getOpen(), candle.getClose())));
            candle.setHigh(rnd(Math.max(candle.getOpen(), candle.getClose()), max));
            candle.setOpenTime(new Timestamp(System.currentTimeMillis()));

            candleService.addCandle(candle);
        }
    }

    public static double rnd(double min, double max)
    {
        max -= min;
        return (double) (Math.random() * ++max) + min;
    }

    public void printCoin(Candle candle, long chatId) throws TelegramApiException {
        String str = "Low: " + candle.getLow() + " \n"
                + "High:" + candle.getHigh() + " \n"
                + "Open: " + candle.getOpen() + " \n"
                + "Close: " + candle.getClose() + " \n"
                + "Open time:" + candle.getOpenTime();
            hackTelegramBot.execute(new SendMessage(chatId, "Свеча " + candle.getFigi() + ": \n" + str));
    }

    @Scheduled(fixedDelay = 15000)
    public void sendCoin() throws TelegramApiException {
        generateValues();
        Candle candle = new Candle();
        List<Candle> candleList = candleService.findAllCandles();
        List<User> userList = userService.searchByStateLike("SUBSCRIPTION");
        for (User user:userList) {
            switch (user.getState()){
                case "SUBSCRIPTION_BTC":
                    candle = candleList.stream().filter(a -> a.getFigi().equals(Figi.BTC)).findFirst().orElse(null);
                    break;
                case "SUBSCRIPTION_ETH":
                    candle = candleList.stream().filter(a -> a.getFigi().equals(Figi.ETH)).findFirst().orElse(null);
                    break;
                case "SUBSCRIPTION_BNB":
                    candle = candleList.stream().filter(a -> a.getFigi().equals(Figi.BNB)).findFirst().orElse(null);
                    break;
                case "SUBSCRIPTION_DOGE":
                    candle = candleList.stream().filter(a -> a.getFigi().equals(Figi.DOGE)).findFirst().orElse(null);
                    break;
                case "SUBSCRIPTION_DOT":
                    candle = candleList.stream().filter(a -> a.getFigi().equals(Figi.DOT)).findFirst().orElse(null);
                    break;
                case "SUBSCRIPTION_ADA":
                    candle = candleList.stream().filter(a -> a.getFigi().equals(Figi.ADA)).findFirst().orElse(null);
                    break;
                default:
                    return;
            }
            printCoin(candle, user.getChatId());

        }
    }







}
