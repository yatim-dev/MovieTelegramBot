package Database.UserInfo;


import Database.MovieRepository.MovieRepo;

public class FindUserResponse {

    public String dialogue(Long chatId, UserRepo chatInfo, MovieRepo movieRepo, String text){
        Chat searchCriteria = chatInfo.getChat(chatId);
        switch (searchCriteria.getChatState()){
            case START:
                searchCriteria.setChatState(ChatState.CHOICE_GENRE);
                searchCriteria.setGenre(text);
                chatInfo.update(searchCriteria);
                return "Выберите страну";
            case CHOICE_GENRE:
                searchCriteria.setChatState(ChatState.CHOICE_COUNTRY);
                searchCriteria.setCountry(text);
                searchCriteria.setChatState(ChatState.RESULT); //end of search
                chatInfo.update(searchCriteria);
            case RESULT:
                chatInfo.update(searchCriteria);
                return movieRepo.findMovie(searchCriteria);
            default:
                return "Some exception";

        }
    }
}


