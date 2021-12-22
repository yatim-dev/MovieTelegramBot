
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
                //Tests test = new Tests();
                //test.ReadOutput();
                //test.ReadInput();
                //userInput = test.GetTestWord();
                List<String> words = List.of(userInput.split(" "));
                for(String word : words) {
                    Recognizer recognizer = new Recognizer(word, firstRecognizer, secondRecognizer, thirdRecognizer);
                  //  test.Calculate(recognizer.output);

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
                KinopoiskApiService kinopoiskApiService = new KinopoiskApiService("419a7163-777a-4f3a-bd32-10b5109fa940",15000);
                var film1 = kinopoiskApiService.searchByKeyword(chat.searchCriteria.getCountry()
                       // + chat.searchCriteria.getGenre() + chat.searchCriteria.getCategory() + chat.searchCriteria.getDirector() +
                        //chat.searchCriteria.getRating() + chat.searchCriteria.getYearOfIssue()
                        ,1);
                return movieRepo.findMovie(chat.searchCriteria).getTitle();
            default:
                return "Some exception";
        }

    }
}