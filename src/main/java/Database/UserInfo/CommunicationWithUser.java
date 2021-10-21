package Database.UserInfo;


import Database.MovieRepository.DatabaseOfMovieRepo;

public class CommunicationWithUser {

    public String communication(Long chatId, DatabaseOfUserInfo userInfo, DatabaseOfMovieRepo movieRepo, String text){
        User user = userInfo.getUser(userInfo, chatId);
        if(user.getChatState() == ChatState.START){
            user.setChatState(ChatState.CHOICE_GENRE);
            userInfo.setChatState(user);
            user.setGenre(text);
            userInfo.setGenre(user);
            return "Выберите страну";
        }
        if(user.getChatState() == ChatState.CHOICE_GENRE){
            user.setChatState(ChatState.CHOICE_COUNTRY);
            userInfo.setChatState(user);
            user.setCountry(text);
            userInfo.setCountry(user);
            user.setChatState(ChatState.RESULT);
            userInfo.setChatState(user);

        }
        if(user.getChatState() == ChatState.RESULT){
            return movieRepo.findMovie(user);
        }


        return "";
    }
}


