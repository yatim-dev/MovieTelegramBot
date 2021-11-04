package Database.UserInfo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

public class UserRepo {

    MongoCollection<Chat> userRepo;

    public UserRepo(String mongoUri) {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(Chat.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        userRepo = new MongoClient(new MongoClientURI(mongoUri))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("UserInfo", Chat.class);

    }

    public Chat getChat(Long chatId) {
        return userRepo.find(Filters.eq("chatId", chatId)).first();
    }

    public void update(Chat chat) {
        Bson filter = Filters.eq("chatId", chat.getChatId());
        Bson update =  new Document("$set", chat);
        UpdateOptions options = new UpdateOptions().upsert(true);
        userRepo.updateOne(filter, update, options);
    }
}
