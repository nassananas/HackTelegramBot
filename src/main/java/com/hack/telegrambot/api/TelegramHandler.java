package com.hack.telegrambot.api;
import com.hack.telegrambot.model.User;
import com.hack.telegrambot.service.GenerationService;
import com.hack.telegrambot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramHandler {
    private BotStateContext context;
    private UserService userService;
    private GenerationService generationService;

    public TelegramHandler(BotStateContext botStateContext, UserService userService, GenerationService generationService) {
        this.userService = userService;
        this.context = botStateContext;
        this.generationService = generationService;
    }

    public void handleInputMessage(Update update) throws TelegramApiException {
        String inputMessage = update.getMessage().getText();
        User user = context.getUser();
        SendMessage replyMessage = null;
        Long chatId = user.getChatId();

        switch (inputMessage){
            case "/start":
                //старт чата
                user.setState(BotState.START.name());
                replyMessage = new SendMessage(chatId, "Привет! \nЯ могу помочь тебе отслеживать курс криптовалюты! \n" +
                        "Отправь мне цифру, соответствующую следующим действиям: \n" +
                        "1 - Подписка на BTC \n" +
                        "2 - Подписка на ETH \n" +
                        "3 - Подписка на BNB \n" +
                        "4 - Подписка на DOGE \n" +
                        "5 - Подписка на DOT \n" +
                        "6 - Подписка на ADA \n" +
                        "0 - Отписаться от уведомлений"
                );
                break;
            case "1":
                //подписка на BTC
                user.setState(BotState.SUBSCRIPTION_BTC.name());
                replyMessage = new SendMessage(chatId, "Ты подписался на обновления BTC!");
                break;
            case "2":
                //подписка на ETH
                user.setState(BotState.SUBSCRIPTION_ETH.name());
                replyMessage = new SendMessage(chatId, "Ты подписался на обновления ETH!");
                break;
            case "3":
                //подписка на BNB
                user.setState(BotState.SUBSCRIPTION_BNB.name());
                replyMessage = new SendMessage(chatId, "Ты подписался на обновления BNB!");
                break;
            case "4":
                //подписка на DOGE
                user.setState(BotState.SUBSCRIPTION_DOGE.name());
                replyMessage = new SendMessage(chatId, "Ты подписался на обновления DOGE!");
                break;
            case "5":
                //подписка на DOT
                user.setState(BotState.SUBSCRIPTION_DOT.name());
                replyMessage = new SendMessage(chatId, "Ты подписался на обновления DOT!");
                break;
            case "6":
                //подписка на ADA
                user.setState(BotState.SUBSCRIPTION_ADA.name());
                replyMessage = new SendMessage(chatId, "Ты подписался на обновления ADA!");
                break;
            case "0":
                //отписка
                user.setState(BotState.START.name());
                replyMessage = new SendMessage(chatId, "Ты отписался на уведомлений!");
                break;
            default:
                replyMessage = new SendMessage(chatId, "Не понимаю тебя");
                context.getBot().execute(replyMessage);
                return;
        }
        context.getBot().execute(replyMessage);
        userService.updateUser(user);
        generationService.sendCoin();

    }


}
