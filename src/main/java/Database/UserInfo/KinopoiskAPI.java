package Database.UserInfo;

import com.truedev.kinoposk.api.model.Result;
import com.truedev.kinoposk.api.model.movie.Film;
import com.truedev.kinoposk.api.model.search.movie.keyword.SearchResult;
import com.truedev.kinoposk.api.service.KinopoiskApiService;

import java.util.ArrayList;

public class KinopoiskAPI {
    public void searchMovie(String searchString){
        KinopoiskApiService kinopoiskApiService = new KinopoiskApiService(System.getenv("KINOPOISK_TOKEN"),15000);
        Result<SearchResult> film1 = kinopoiskApiService.searchByKeyword(searchString, 1);
    }
}
