import com.truedev.kinoposk.api.model.Result;
import com.truedev.kinoposk.api.model.movie.Film;
import com.truedev.kinoposk.api.model.search.movie.keyword.SearchResult;
import com.truedev.kinoposk.api.service.KinopoiskApiService;

import java.util.ArrayList;

public class KinopoiskAPI {
    KinopoiskApiService kinopoiskApiService = new KinopoiskApiService("419a7163-777a-4f3a-bd32-10b5109fa940",15000);
    // if success it will return value otherwise it will be null
    Film film = kinopoiskApiService.getFilm(301,new ArrayList<>()).getOrNull();
    Result<SearchResult> film1 = kinopoiskApiService.searchByKeyword("франция фантастика", 1);
}
