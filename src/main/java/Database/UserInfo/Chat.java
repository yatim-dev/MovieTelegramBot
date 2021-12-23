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
        StringBuilder request = null;
        var parameters = new ArrayList<String>();
        parameters.add(searchCriteria.getGenre());
        parameters.add(searchCriteria.getCountry());
        parameters.add(searchCriteria.getYearOfIssue().toString());
        parameters.add(searchCriteria.getCategory());
        parameters.add(searchCriteria.getDirector());
        parameters.add(searchCriteria.getRating().toString());
        for (String parameter : parameters)
            if (parameter != null)
                request.append(parameter).append(" ");

        return request.toString();
    }
}