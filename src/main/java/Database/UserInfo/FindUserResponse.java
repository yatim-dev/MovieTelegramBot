
package Database.UserInfo;

import Database.MovieRepository.MovieRepo;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;
import Tests.Tests;
import com.truedev.kinoposk.api.model.Result;
import com.truedev.kinoposk.api.model.search.movie.keyword.SearchResult;
import com.truedev.kinoposk.api.service.KinopoiskApiService;
import lombok.SneakyThrows;

import java.util.List;

public class FindUserResponse {

    @SneakyThrows
    public String dialogue(
            Long chatId, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer,
            UserRepo userRepo, MovieRepo movieRepo, String userInput
    ) {
        Chat chat = userRepo.getChat(chatId);
        switch (chat.searchCriteria.getChatState()) {
            case START:
                
                List<String> words = List.of(userInput.split(" "));
                for(String word : words) {
                    Recognizer recognizer = new Recognizer(word, firstRecognizer, secondRecognizer, thirdRecognizer);

                    switch (recognizer.indexArray) {
                        case 0 -> chat.searchCriteria.setCategory(recognizer.output);
                        case 1 -> chat.searchCriteria.setCountry(recognizer.output);
                        case 2 -> chat.searchCriteria.setGenre(recognizer.output);
                        case 3 -> chat.searchCriteria.setDirector(recognizer.output);
                        case 4 -> chat.searchCriteria.setRating(Double.valueOf(recognizer.output)); //отдельная обработка
                        case 5 -> chat.searchCriteria.setYearOfIssue(Integer.valueOf(recognizer.output)); //отдельная обработка
                    }
                }
            case RESULT:
                KinopoiskAPI kinopoiskAPI = new KinopoiskAPI();
                kinopoiskAPI.searchMovie(chat.getRealParameter());
                //return movieRepo.findMovie(chat.searchCriteria).getTitle();
            default:
                return "Some exception";
        }

    }
}