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

public class UserRepo {

    MongoCollection<Chat> userRepo;

    public UserRepo() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(Chat.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        userRepo = new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("UserInfo", Chat.class);

    }

    public Chat getChat(Long chatId) {
        return userRepo.find(Filters.eq("chatId", chatId)).first();
    }

    public void updateOrAddUser(long chatId) {
       userRepo.deleteOne(new Document("chatId", chatId));
       userRepo.insertOne(new Chat(chatId, ChatState.START));
    }

    public void update(Chat user)
    {
        userRepo.deleteOne(new Document("chatId", user.getChatId()));
        userRepo.insertOne(user);
    }
}
