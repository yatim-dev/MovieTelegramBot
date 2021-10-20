package Database.MovieRepository;

public class MovieRepositorySetting {
    private String title;
    private String genre;
    private String category;
    private Integer yearOfIssue;
    private String country;
    private Double rating;
    private String director;

    public MovieRepositorySetting(String title, String genre, String category,
                                  Integer yearOfIssue, String country, Double rating, String director) {
        this.title = title;
        this.genre = genre;
        this.category = category;
        this.yearOfIssue = yearOfIssue;
        this.country = country;
        this.rating = rating;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
