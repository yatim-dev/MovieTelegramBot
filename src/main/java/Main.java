import Database.Database;
import com.mongodb.MongoClientURI;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.mongodb.MongoClient;

public class Main {
    @SneakyThrows
    public static void main(String[] args)
    {
        var bot = new BotLogic();
        var telegramWrapper = new TelegramApi(bot);
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramWrapper);
        var database = new Database();
        database.initialization();
    }
}
