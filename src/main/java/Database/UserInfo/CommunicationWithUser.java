package Database.UserInfo;

public class CommunicationWithUser {

    public String communication(Long chatId, String text){
        var userInfo = new DatabaseOfUserInfo();
        AbstractUser user = userInfo.getUser(chatId);

        return "";
    }
}
