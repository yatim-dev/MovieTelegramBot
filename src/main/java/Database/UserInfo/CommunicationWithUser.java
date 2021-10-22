package Database.UserInfo;


import Database.MovieRepository.DatabaseOfMovieRepo;

public class CommunicationWithUser {

    public String communication(Long chatId, DatabaseOfUserInfo userInfo, DatabaseOfMovieRepo movieRepo, String text){
        User user = userInfo.getUser(userInfo, chatId);
        switch (user.getChatState()){
            case START:
                user.setChatState(ChatState.CHOICE_GENRE);
                userInfo.setChatState(user);
                user.setGenre(text);
                userInfo.setGenre(user);
                return "Выберите страну";
            case CHOICE_GENRE:
                user.setChatState(ChatState.CHOICE_COUNTRY);
                userInfo.setChatState(user);
                user.setCountry(text);
                userInfo.setCountry(user);
                user.setChatState(ChatState.RESULT); //end of search
                userInfo.setChatState(user);
            case RESULT:
                return movieRepo.findMovie(user); //метод гавно
            default:
                return "Some exception";

        }
    }
}


