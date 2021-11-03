import Database.MovieRepository.MovieRepo;
import Database.UserInfo.FindUserResponse;
import Database.UserInfo.UserRepo;

public class BotLogic {

    public String formResponse(long chatId, String text, UserRepo userRepo, MovieRepo movieRepo) {

        FindUserResponse findUserResponse = new FindUserResponse();

        switch (text){
            case "/start":
                userRepo.updateOrAddUser(chatId);
                return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                        " Чтобы узнать больше информации напишите /help, либо введите жанр";
            case "/help":
                return "Напишите, какой фильм хотите найти";
            case "/new_round":
                userRepo.updateOrAddUser(chatId);
                return "ну погнали сначала";
            default:
                return findUserResponse.dialogue(chatId, userRepo, movieRepo, text);
        }
    }
}
