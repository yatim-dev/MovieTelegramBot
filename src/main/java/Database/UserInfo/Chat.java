package Database.UserInfo;

import java.util.ArrayList;

public class Chat {
    private long chatId;
    public SearchCriteria searchCriteria = new SearchCriteria();

    public Chat(long chatId, ChatState chatState) {
        this.chatId = chatId;
        searchCriteria.setChatState(chatState);
    }

    public Chat() {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getRealParameter() {
        String request = "";
        var parameters = new ArrayList<String>();
        if (searchCriteria.getYearOfIssue() != null)
            parameters.add(String.valueOf(searchCriteria.getYearOfIssue().intValue()));
        if (searchCriteria.getRating() != null)
            parameters.add(String.valueOf(searchCriteria.getRating().doubleValue()));
        parameters.add(searchCriteria.getGenre());
        parameters.add(searchCriteria.getCountry());
        parameters.add(searchCriteria.getCategory());
        parameters.add(searchCriteria.getDirector());
        for (String parameter : parameters)
            if (parameter != null)
                request += parameter + " ";

        return request;
    }
}