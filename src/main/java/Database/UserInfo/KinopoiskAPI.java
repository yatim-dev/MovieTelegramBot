package Database.UserInfo;

import com.truedev.kinoposk.api.model.Result;
import com.truedev.kinoposk.api.model.search.movie.keyword.SearchItem;
import com.truedev.kinoposk.api.model.search.movie.keyword.SearchResult;
import com.truedev.kinoposk.api.service.KinopoiskApiService;

import java.util.List;

public class KinopoiskAPI {
    public String searchMovie(String searchString){
        KinopoiskApiService kinopoiskApiService = new KinopoiskApiService(System.getenv("KINOPOISK_TOKEN"),15000);
        List<SearchItem> film = kinopoiskApiService.searchByKeyword(searchString, 1).getOrThrowException().getFilms();

        //var nameRu = film.getOrThrowException().getFilms().get(0).component2();
        //var descriptor = film.getOrThrowException().getFilms().get(0).component6();
        //var posterUri = film.getOrThrowException().getFilms().get(0).component12();
        return "a";//nameRu + " " + posterUri + "\n" + descriptor;
    }
}