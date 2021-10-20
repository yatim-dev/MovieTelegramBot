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
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator


public class DatabaseOfUserInfo {

    MongoCollection<User> collection;

    public void initialization(){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                //ClassModel.builder(AbstractUser.class).enableDiscriminator(false).build(),
                                ClassModel.builder(User.class).enableDiscriminator(false).build()
                        ).automatic(true)
                        .build()
                )
        );

        collection = new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("UserInfo", User.class);

        //collection.insertOne(new User(33, ChatState.CHOICE_CATEGORY, "Аниме", null, null,
        //       null, null, null, null));

        //collection.updateOne(new Document("chatId", 33),
        //        new Document("$set", new Document("genre", "Онимэ")));

        //AbstractUser user = collection.find(Filters.eq("chatId", 32)).first();
        //if (user != null) {
        //    System.out.println(user);
        //} else {
            //collection.insertOne(new User(32, null, null, null, null, null, null, null, null));
        //}
    }

    public void setId(long chatId){
        var user = collection.find(Filters.eq("chatId", chatId)).first();
        if(user != null)
            collection.deleteOne(new Document("chatId", chatId));
        collection.insertOne(new User(chatId, ChatState.START, null, null, null,
                null, null, null, null));
    }

    public AbstractUser getUser(Long chatId) {
        AbstractUser user = collection.find(Filters.eq("chatId", chatId)).first();
        return user;
    }
}
