package Database;

import Enums.ChatState;

public class User
{
    private long chatId;
    private ChatState chatState;
    private String lastMessage;
    private String currentMessage;

    public User(long chatId, ChatState chatState, String lastMessage, String currentMessage) {
        this.chatId = chatId;
        this.chatState = chatState;
        this.lastMessage = lastMessage;
        this.currentMessage = currentMessage;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public ChatState getChatState() {
        return chatState;
    }

    public void setChatState(ChatState chatState) {
        this.chatState = chatState;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }
}