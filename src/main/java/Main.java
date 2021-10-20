import Database.MovieRepository.DatabaseOfMovieRepo;
import Database.UserInfo.DatabaseOfUserInfo;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args)
    {
        var bot = new BotLogic();
        var telegramWrapper = new TelegramApi(bot);
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramWrapper);
        var databaseOfUserInfo = new DatabaseOfUserInfo();
        databaseOfUserInfo.initialization();
        var databaseOfMovieRepo = new DatabaseOfMovieRepo();
        databaseOfMovieRepo.initialization();
    }
}
