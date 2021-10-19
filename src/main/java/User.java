

public class User
{
    private long chatId;
    private ChatState chatState;
    private String lastMessage;
    private String currentMessage;


    public long getChatId()
    {
        return chatId;
    }

    public void setId(long chatId)
    {
        this.chatId = chatId;
    }

    public void setChatState(ChatState chatState)
    {
        this.chatState = chatState;
    }

    public ChatState getChatState()
    {
        return chatState;
    }

    public String getLastMessage()
    {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage)
    {
        this.lastMessage = lastMessage;
    }

    public String getCurrentMessage()
    {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage)
    {
        this.currentMessage = currentMessage;
    }

}