import Database.MovieRepository.Movie;
import Database.MovieRepository.MovieRepo;
import Database.UserInfo.Chat;
import Database.UserInfo.FindUserResponse;
import Database.UserInfo.UserRepo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import lombok.SneakyThrows;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(Movie.class).enableDiscriminator(true).build(),
                                ClassModel.builder(Chat.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        UserRepo userRepo =
                new UserRepo(
                        new MongoClient(
                                new MongoClientURI(System.getenv("MONGO_URI")))
                                .getDatabase("TelegramBotBD")
                                .withCodecRegistry(codecRegistry)
                                .getCollection("UserInfo", Chat.class)
                );

        MovieRepo movieRepo =
                new MovieRepo(
                        new MongoClient(
                                new MongoClientURI(System.getenv("MONGO_URI")))
                                .getDatabase("TelegramBotBD")
                                .withCodecRegistry(codecRegistry)
                                .getCollection("MovieRepository", Movie.class)

                );

        var bot = new BotLogic(new FindUserResponse());
        var telegramWrapper = new TelegramApi(bot, userRepo, movieRepo, System.getenv("TelegramBotToken"));
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramWrapper);
    }
}