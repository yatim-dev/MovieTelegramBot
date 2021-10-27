package Database.MovieRepository;

import Database.UserInfo.Chat;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MovieRepo {

    MongoCollection<Movie> movieRepo;

    public MovieRepo(){
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

    }

    public String findMovie(Chat searchCriteria){ //////?????
        var filter = Filters.and(Filters.eq("genre", searchCriteria.getGenre()),
                Filters.eq("country", searchCriteria.getCountry()));
        try {
            return movieRepo.find(filter).first().getTitle();
        }catch (Exception exception){
            return "Такого нет...((( Начни поиск сначала /new_round";
        }


    }
}
