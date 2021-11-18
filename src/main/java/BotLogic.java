
import Database.MovieRepository.MovieRepo;
import Database.UserInfo.*;

public class BotLogic {

    FindUserResponse findUserResponse;

    public BotLogic(FindUserResponse findUserResponse) {
        this.findUserResponse = findUserResponse;
    }

    public String formResponse(long chatId, String text, UserRepo userRepo, MovieRepo movieRepo) {
        switch (text) {
            case "/start":
                userRepo.update(new Chat(chatId, ChatState.START));
                return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                        " Чтобы узнать больше информации напишите /help";
            case "/help":
                return "Напишите, какой фильм хотите найти";
            case "/new_round":
                userRepo.update(new Chat(chatId, ChatState.START));
                return "ну погнали сначала";
            case "страна":

            default:
                try {
                    return findUserResponse.dialogue(chatId, userRepo, movieRepo, text);
                } catch (NullPointerException ex) {
                    return "Такого нет...((( Начни поиск сначала /new_round";
                }
        }
    }
}