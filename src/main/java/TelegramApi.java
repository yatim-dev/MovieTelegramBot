import Database.MovieRepository.MovieRepo;
import Database.UserInfo.UserRepo;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TelegramApi extends TelegramLongPollingBot {

    private final BotLogic bot;
    private final String telegramBotToken;
    UserRepo userRepo;
    MovieRepo movieRepo;
    FirstRecognizer firstRecognizer;
    SecondRecognizer secondRecognizer;
    ThirdRecognizer thirdRecognizer;
    ReplyKeyboardMarkup replyKeyboardMarkup;

    public TelegramApi(
            BotLogic bot, UserRepo userRepo, MovieRepo movieRepo,
            FirstRecognizer firstRecognizer, SecondRecognizer secondRecognizer,
            ThirdRecognizer thirdRecognizer, String telegramBotToken
    )
    {
        this.bot = bot;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.firstRecognizer = firstRecognizer;
        this.secondRecognizer = secondRecognizer;
        this.thirdRecognizer = thirdRecognizer;
        this.telegramBotToken = telegramBotToken;
        this.replyKeyboardMarkup = new ReplyKeyboardMarkup();
        createButton();
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
        var response = bot.formResponse(
                currentChatId, message.getText(),
                firstRecognizer, secondRecognizer, thirdRecognizer,
                userRepo, movieRepo
        );
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

    public void createButton() {
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