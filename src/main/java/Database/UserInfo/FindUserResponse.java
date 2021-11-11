package Database.UserInfo;


import Database.MovieRepository.MovieRepo;

public class FindUserResponse {

    public String dialogue(Long chatId, UserRepo chatInfo, MovieRepo movieRepo, String text){
        Chat chat = chatInfo.getChat(chatId);
        switch (chat.searchCriteria.getChatState()) {
            case START:
                chat.searchCriteria.setChatState(ChatState.CHOICE_GENRE);
                chat.searchCriteria.setGenre(text);
                chatInfo.update(chat);
                return "Выберите страну";
            case CHOICE_GENRE:
                chat.searchCriteria.setChatState(ChatState.CHOICE_COUNTRY);
                chat.searchCriteria.setCountry(text);
                chat.searchCriteria.setChatState(ChatState.RESULT); //end of search
                chatInfo.update(chat);
            case RESULT:
                try {
                    return movieRepo.findMovie(chat.searchCriteria).getTitle();
                }catch (NullPointerException ex){
                    return null;
                }
            default:
                return "Some exception";
        }
    }
}


