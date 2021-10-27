import Database.MovieRepository.MovieRepo;
import Database.UserInfo.FindUserResponse;
import Database.UserInfo.UserRepo;

public class BotLogic {

    UserRepo userInfo = new UserRepo();
    MovieRepo movieRepo = new MovieRepo();

    public String formResponse(long chatId, String text) {

        FindUserResponse findUserResponse = new FindUserResponse();

        switch (text){
            case "/start":
                userInfo.updateAndAddUser(chatId);
                return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                        " Чтобы узнать больше информации напишите /help, либо введите жанр"; //костыли ебаные
            case "/help":
                return "Напишите, какой фильм хотите найти";
            case "/new_round":
                userInfo.updateAndAddUser(chatId);
                return "ну погнали сначала";
            default:
                return findUserResponse.dialogue(chatId, userInfo, movieRepo, text);
        }
    }
}
