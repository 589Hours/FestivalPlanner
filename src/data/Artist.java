package data;

public class Artist {
    private String name;
    private int popularity;
    private String genre;

    public Artist(String name, int popularity, String genre) {
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
