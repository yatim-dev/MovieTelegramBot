import Database.MovieRepository.MovieRepo;
import Database.UserInfo.UserRepo;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class TelegramApi extends TelegramLongPollingBot {

    private final BotLogic bot;
    private final String telegramBotToken;
    UserRepo userRepo;
    MovieRepo movieRepo;
    ReplyKeyboardMarkup replyKeyboardMarkup;

    public TelegramApi(BotLogic bot, UserRepo userRepo, MovieRepo movieRepo, String telegramBotToken)
    {
        this.bot = bot;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.telegramBotToken = telegramBotToken;
        this.replyKeyboardMarkup = new ReplyKeyboardMarkup();
        getMessage();
    }

    @Override
    public String getBotUsername() {
        return "MovieBot";
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var currentChatId = message.getChatId();
        var response = bot.formResponse(currentChatId, message.getText(), userRepo, movieRepo);
        sendResponse(currentChatId, response);
    }

    @SneakyThrows
    private void sendResponse(Long chatId, String outText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(outText);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        execute(sendMessage);
    }

    public void getMessage() {
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        var firstButtonsNameArray = new String[]{"Жанр", "Год", "Категория"};
        var secondButtonsNameArray = new String[]{"Страна", "Режиссер", "Рейтинг"};
        firstRow.addAll(Arrays.stream(firstButtonsNameArray).toList());
        secondRow.addAll(Arrays.stream(secondButtonsNameArray).toList());
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}