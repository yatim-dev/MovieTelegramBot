package Database.UserInfo;

import com.truedev.kinoposk.api.model.Result;
import com.truedev.kinoposk.api.model.movie.Film;
import com.truedev.kinoposk.api.model.search.movie.keyword.SearchResult;
import com.truedev.kinoposk.api.service.KinopoiskApiService;

import java.util.ArrayList;
import java.util.Objects;

public class KinopoiskAPI {
    public void searchMovie(String searchString){
        KinopoiskApiService kinopoiskApiService = new KinopoiskApiService(System.getenv("KINOPOISK_TOKEN"),15000);
        Result<SearchResult> film = kinopoiskApiService.searchByKeyword(searchString, 1);
        var nameRu = film.getOrThrowException().getFilms().get(0).component2();
        var descriptor = film.getOrThrowException().getFilms().get(0).component6();
        var posterUri = film.getOrThrowException().getFilms().get(0).component12();
    }
}
