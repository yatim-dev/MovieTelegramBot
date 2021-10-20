import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

class TelegramApi extends TelegramLongPollingBot {

    private BotLogic bot;

    public TelegramApi(BotLogic bot)
    {
        this.bot = bot;
    }

    @Override
    public String getBotUsername() {
        return "MovieBot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("TelegramBotToken");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var currentChatId = message.getChatId();
        var response = bot.formResponse(currentChatId, message.getText());
        sendResponse(currentChatId, response);
    }

    @SneakyThrows
    private void sendResponse(Long chatId, String outText) {
        execute(SendMessage.builder()
                .chatId(chatId.toString())
                .text(outText)
                .build()
        );
    }


}
