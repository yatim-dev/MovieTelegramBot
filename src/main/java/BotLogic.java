
import Database.MovieRepository.MovieRepo;
import Database.UserInfo.*;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;

public class BotLogic {

    FindUserResponse findUserResponse;

    public BotLogic(FindUserResponse findUserResponse) {
        this.findUserResponse = findUserResponse;
    }

    public String formResponse(
            long chatId, String userInput, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer,
            UserRepo userRepo, MovieRepo movieRepo
    )
    {
        switch (userInput) {
            case "/start":
                userRepo.update(new Chat(chatId, ChatState.START));
                return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                        " Чтобы узнать больше информации напишите /help";
            case "/help":
                return "Напишите, какой фильм хотите найти";
            case "/new_round":
                userRepo.update(new Chat(chatId, ChatState.START));
                return "ну погнали сначала";
            default:
                try {
                    return findUserResponse.dialogue(
                            chatId, firstRecognizer, secondRecognizer,
                            thirdRecognizer, userRepo, movieRepo, userInput
                    );
                } catch (NullPointerException ex) {
                    return "Такого нет...((( Начни поиск сначала /new_round";
                }
        }
    }
}