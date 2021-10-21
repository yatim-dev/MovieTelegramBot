import Database.MovieRepository.DatabaseOfMovieRepo;
import Database.UserInfo.CommunicationWithUser;
import Database.UserInfo.DatabaseOfUserInfo;

public class BotLogic {

    public String formResponse(long chatId, String text) {
        DatabaseOfUserInfo userInfo = new DatabaseOfUserInfo();
        CommunicationWithUser communicationWithUser = new CommunicationWithUser();
        DatabaseOfMovieRepo MovieRepo = new DatabaseOfMovieRepo();
        MovieRepo.initialization();
        userInfo.initialization();
        switch (text){
            case "/start":
                userInfo.setId(chatId);
                return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                        " Чтобы узнать больше информации напишите /help, либо введите жанр"; //костыли ебаные
            case "/help":
                return "Напишите, какой фильм хотите найти";
            case "/new_round":
                userInfo.setId(chatId);
                return "ну погнали сначала";
            default:
                return communicationWithUser.communication(chatId, userInfo, MovieRepo,text);
        }
    }
}
