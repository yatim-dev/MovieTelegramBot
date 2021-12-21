package Database.UserInfo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

public class UserRepo {

    MongoCollection<Chat> userRepo;

    public UserRepo(MongoCollection<Chat> userRepo) {
        this.userRepo = userRepo;
    }

    public Chat getChat(Long chatId) {
        return userRepo.find(Filters.eq("chatId", chatId)).first();
    }

    public void update(Chat chat) {
        Bson filter = Filters.eq("chatId", chat.getChatId());
        Bson update = new Document("$set", chat);
        UpdateOptions options = new UpdateOptions().upsert(true);
        userRepo.updateOne(filter, update, options);
    }
}
