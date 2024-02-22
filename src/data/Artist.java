package data;

public class Artist {
    private String name;
    private int popularity;
    private String genre;
    private String artistInfo;

    public Artist(String name, int popularity, String genre) {
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
        this.artistInfo = "";
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

    public String getArtistInfo() {
        return artistInfo;
    }
    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    @Override
    public String toString() {
        return getName();
    }
}
