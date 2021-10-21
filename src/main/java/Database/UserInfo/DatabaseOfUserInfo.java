package Database.UserInfo;

import Database.MovieRepository.DatabaseOfMovieRepo;
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

public class DatabaseOfUserInfo {

    MongoCollection<User> collection;

    public void initialization(){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(AbstractUser.class).enableDiscriminator(true).build(),
                                ClassModel.builder(User.class).enableDiscriminator(true).build()
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
        collection.insertOne(new User(chatId, ChatState.START));
    }

    public User getUser(DatabaseOfUserInfo userInfo, Long chatId) {
        User user = collection.find(Filters.eq("chatId", chatId)).first();
        return user;
    }

    public void setGenre(User user){
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("genre", user.getGenre())));
    }

    public void setCountry(User user){
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("country", user.getCountry())));
    }

    public void setChatState(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("chatState", user.getChatState().toString())));
    }



}
