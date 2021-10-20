package Database.UserInfo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.logging.Filter;
//import org.telegram.telegrambots.meta.api.objects.User;


public class DatabaseOfUserInfo {

    public void initialization(){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(User.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        MongoCollection<User> collection = new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("UserInfo", User.class);

        collection.insertOne(new User(33, ChatState.CHOICE_CATEGORY, "Аниме", null, null,
                null, null, null, null));

        collection.updateOne(new Document("chatId", 33),
                new Document("$set", new Document("currentMessage", "Portable Space Ball")));

    }
}
