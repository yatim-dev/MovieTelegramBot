package Database.UserInfo;


import Database.MovieRepository.MovieRepo;

public class CommunicationWithUser {

    public String communication(Long chatId, UserRepo chatInfo, MovieRepo movieRepo, String text){
        Chat chat = chatInfo.getUser(chatInfo, chatId);
        switch (chat.getChatState()){
            case START:
                chat.setChatState(ChatState.CHOICE_GENRE);
                chat.setGenre(text);
                chatInfo.update(chat);
                return "Выберите страну";
            case CHOICE_GENRE:
                chat.setChatState(ChatState.CHOICE_COUNTRY);
                chat.setCountry(text);
                chat.setChatState(ChatState.RESULT); //end of search
                chatInfo.update(chat);
            case RESULT:
                chatInfo.update(chat);
                return movieRepo.findMovie(chat); //метод гавно
            default:
                return "Some exception";

        }
    }
}


