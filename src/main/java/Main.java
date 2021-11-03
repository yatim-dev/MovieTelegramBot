import Database.MovieRepository.MovieRepo;
import Database.UserInfo.UserRepo;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args)
    {
        UserRepo userRepo = new UserRepo(System.getenv("MONGO_URI"));
        MovieRepo movieRepo = new MovieRepo();
        var bot = new BotLogic();
        var telegramWrapper = new TelegramApi(bot, userRepo, movieRepo, System.getenv("TelegramBotToken"));
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramWrapper);
    }
}
