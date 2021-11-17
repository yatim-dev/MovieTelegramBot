
package Database.UserInfo;

import Database.MovieRepository.MovieRepo;

public class FindUserResponse {

    public String dialogue(Long chatId, UserRepo userRepo, MovieRepo movieRepo, String text){
        Chat chat = userRepo.getChat(chatId);
        switch (chat.searchCriteria.getChatState()) {
            case START:
                chat.searchCriteria.setChatState(ChatState.CHOICE_GENRE);
                chat.searchCriteria.setGenre(text);
                userRepo.update(chat);
                //break return "Выберите страну";
            case CHOICE_GENRE:
                chat.searchCriteria.setChatState(ChatState.CHOICE_COUNTRY);
                chat.searchCriteria.setCountry(text);
                chat.searchCriteria.setChatState(ChatState.RESULT); //end of search
                userRepo.update(chat);
            case RESULT:
                return movieRepo.findMovie(chat.searchCriteria).getTitle();
            default:
                return "Some exception";
        }
    }
}