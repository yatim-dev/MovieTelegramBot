package Database.UserInfo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

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
        return userRepo.find(eq("chatId", chatId)).first();
    }

    public void updateAndAddUser(long chatId) {
        //var resultCreateIndex = userRepo.createIndex(Indexes.ascending("chatId"));
        Bson filter = eq("chatId", chatId);
        Bson sort = Sorts.ascending("chatId");
        Bson projection = fields(include("chatId"), excludeId());
        FindIterable<Chat> cursor = userRepo.find(filter).sort(sort).projection(projection);

        var chat = userRepo.find(eq("chatId", chatId)).first();
        if (chat != null)
            userRepo.deleteOne(new Document("chatId", chatId));
        userRepo.insertOne(new Chat(chatId, ChatState.START));
    }

    public void update(Chat user)
    {
        var mongoUser = userRepo.find(eq("chatId", user.getChatId())).first();
        if (mongoUser != null)
            userRepo.deleteOne(new Document("chatId", user.getChatId()));
        userRepo.insertOne(user);
    }

}
