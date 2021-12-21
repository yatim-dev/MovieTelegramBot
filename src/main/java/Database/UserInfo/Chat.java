package Database.UserInfo;

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

}