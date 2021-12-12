package Database.MovieRepository;

import Database.UserInfo.SearchCriteria;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MovieRepo {

    MongoCollection<Movie> movieRepo;

    public MovieRepo(MongoCollection<Movie> movieRepo) {
        this.movieRepo = movieRepo;
        String resultCreateIndex = movieRepo.createIndex(Indexes.ascending("genre", "country", "title"));
    }

    public Movie findMovie(SearchCriteria searchCriteria) {
        Bson filter = and(eq("genre", searchCriteria.getGenre()), eq("country", searchCriteria.getCountry()));
        Bson sort = Sorts.ascending("title");
        FindIterable<Movie> cursor = movieRepo.find(filter).sort(sort);
        return cursor.first();
    }
}
