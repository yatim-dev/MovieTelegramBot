package Database.UserInfo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public interface AbstractUser {
    long getChatId();
}
