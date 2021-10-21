import Database.UserInfo.CommunicationWithUser;
import Database.UserInfo.DatabaseOfUserInfo;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Random;

public class BotLogic {

    public String formResponse(long chatId, String text) {
        DatabaseOfUserInfo userInfo = new DatabaseOfUserInfo();
        CommunicationWithUser communicationWithUser = new CommunicationWithUser();
        userInfo.initialization();
        switch (text){
            case "/start":
                userInfo.setId(chatId);
                return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                        " Чтобы узнать больше информации напишите /help";
            case "/help":
                return "Напишите, какой фильм хотите найти";
            case "/new_round":

                break;
            default:
                return communicationWithUser.communication(chatId, userInfo, text);
        }
        return "uncorrent input";
    }
}
