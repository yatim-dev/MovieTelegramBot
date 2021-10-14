import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args)
    {
        var bot = new BotLogic();
        var telegramWrapper = new TelegramApiWrapper(bot);
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramWrapper);
    }
}
