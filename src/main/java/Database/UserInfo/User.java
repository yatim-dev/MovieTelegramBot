package Database.UserInfo;

public class User implements AbstractUser {
    private long chatId;
    private ChatState chatState;
    private String currentMessage;
    private String genre;
    private String category;
    private Integer yearOfIssue;
    private String country;
    private Double rating;
    private String director;

    public User(long chatId, ChatState chatState,
                String currentMessage, String genre, String category,
                Integer yearOfIssue, String country, Double rating, String director) {
        this.chatId = chatId;
        this.chatState = chatState;
        this.currentMessage = currentMessage;
        this.genre = genre;
        this.category = category;
        this.yearOfIssue = yearOfIssue;
        this.country = country;
        this.rating = rating;
        this.director = director;
    }

    public User(Long chatId){
        this.chatId = chatId;
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

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}