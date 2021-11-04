package Database.MovieRepository;

import Database.UserInfo.Chat;
import Database.UserInfo.SearchCriteria;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

public class MovieRepo {

    MongoCollection<Movie> movieRepo;

    public MovieRepo() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(Movie.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        movieRepo =
                new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("MovieRepository", Movie.class);

        String resultCreateIndex = movieRepo.createIndex(Indexes.ascending("genre", "country", "title"));
    }

    public Movie findMovie(SearchCriteria searchCriteria) {
        Bson filter = and(eq("genre", searchCriteria.getGenre()), eq("country", searchCriteria.getCountry()));
        Bson sort = Sorts.ascending("title");
        FindIterable<Movie> cursor = movieRepo.find(filter).sort(sort);

        try {
            return cursor.first();
        }catch (NullPointerException e){
            return null;
        }


    }
}
