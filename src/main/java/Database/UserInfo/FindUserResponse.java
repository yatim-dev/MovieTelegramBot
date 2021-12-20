
package Database.UserInfo;

import Database.MovieRepository.MovieRepo;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;

import java.util.List;

public class FindUserResponse {

    public String dialogue(
            Long chatId, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer,
            UserRepo userRepo, MovieRepo movieRepo, String userInput
    )
    {
        Chat chat = userRepo.getChat(chatId);
        switch (chat.searchCriteria.getChatState()) {
            case START:
                List<String> words = List.of(userInput.split(" "));
                Recognizer recognizer = new Recognizer(words, firstRecognizer,
                        secondRecognizer, thirdRecognizer
                );

            case RESULT:
                return movieRepo.findMovie(chat.searchCriteria).getTitle();
            default:
                return "Some exception";
        }

    }
}