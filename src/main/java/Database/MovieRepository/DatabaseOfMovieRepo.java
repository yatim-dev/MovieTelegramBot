package Database.MovieRepository;

import Database.UserInfo.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

public class DatabaseOfMovieRepo {

    MongoCollection<MovieRepositorySetting> collectionOfMovie;

    public void initialization(){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(MovieRepositorySetting.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        collectionOfMovie =
                new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("MovieRepository", MovieRepositorySetting.class);
    }

    public String findMovie(User user){
        var filter = Filters.and(Filters.eq("genre", user.getGenre()), Filters.eq("country", user.getCountry()));
        if(collectionOfMovie.find(filter).first() == null){
            return "Такого нету...((( Начни поиск сначала /new_round";
        }else{
            return collectionOfMovie.find(filter).first().getTitle();
        }

    }
}
