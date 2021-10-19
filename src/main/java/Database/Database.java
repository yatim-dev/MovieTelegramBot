package Database;

import Enums.ChatState;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
//import org.telegram.telegrambots.meta.api.objects.User;

import static Enums.ChatState.START;

public class Database {

    public void initialization(){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(Userr.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        MongoCollection<Userr> collection = new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("UserInfo", Userr.class);

        collection.insertOne(new Userr(33, START, "a", "b"));
    }
}
