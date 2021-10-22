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

    public void initialization() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(User.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );

        collection = new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")))
                .getDatabase("TelegramBotBD")
                .withCodecRegistry(codecRegistry).getCollection("UserInfo", User.class);
    }

    public User getUser(DatabaseOfUserInfo userInfo, Long chatId) {
        return collection.find(Filters.eq("chatId", chatId)).first();
    }

    public void setId(long chatId) {
        var user = collection.find(Filters.eq("chatId", chatId)).first();
        if (user != null)
            collection.deleteOne(new Document("chatId", chatId));
        collection.insertOne(new User(chatId, ChatState.START));
    }

    public void setChatState(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("chatState", user.getChatState().toString())));
    }

    public void setGenre(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("genre", user.getGenre())));
    }

    public void setCategory(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("category", user.getCategory())));
    }

    public void setYearOfIssue(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("yearOfIssue", user.getYearOfIssue().toString())));
    }

    public void setCountry(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("country", user.getCountry())));
    }

    public void setRating(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("rating", user.getRating().toString())));
    }

    public void setDirector(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("director", user.getDirector())));
    }
    /*
    CurrentMessage - поле отсутствует у класса User, но если нужно будет, то допилим.
    public void setCurrentMessage(User user) {
        collection.updateOne(new Document("chatId", user.getChatId()),
                new Document("$set", new Document("currentMessage", user.getCurrentMessage())));
    }
     */
}
