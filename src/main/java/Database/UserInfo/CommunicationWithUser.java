package Database.UserInfo;

public class CommunicationWithUser {

    public String communication(Long chatId, DatabaseOfUserInfo userInfo, String text){
        User user = userInfo.getUser(userInfo, chatId);
        if(user.getChatState() == ChatState.START){
            user.setChatState(ChatState.CHOICE_CATEGORY);

        }
        return "";
    }
}
