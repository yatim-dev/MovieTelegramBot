import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Random;

public class BotLogic {

    public String formResponse(String text) {
       // User user = new User();
        if(text.equals("/start")) {
            return "Здравствуйте, вы попали к нам в бот, который поможет вам найти фильм на вечер." +
                    " Чтобы узнать больше информации напишите /help";
        }else if(text.equals("/help")){
            return "Напишите, какой фильм хотите найти";
        }
        //var a = new MovieRepositorySetting();
        //a.setDirector(text);

        return "продолжай";
    }
}
