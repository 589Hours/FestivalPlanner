package data;

import javafx.beans.property.SimpleStringProperty;

public class CellDataForTableView {
    private final SimpleStringProperty artistName;
    private final SimpleStringProperty beginTime;
    private final SimpleStringProperty endTime;
    private final SimpleStringProperty stage;
    private final SimpleStringProperty popularity;
    private final SimpleStringProperty genre;

    public CellDataForTableView(Performance performance){
        //TODO KIEZEN OF WE TOCH 1 ARTIST GAAN DOEN
        Artist artist = performance.getArtist();
        this.artistName = new SimpleStringProperty(artist.getName());
        
        //TODO TIME FOR TABLE moeten nog overleggen hoe we de tijd gaan doen.
        this.beginTime = new SimpleStringProperty(performance.getBeginTime());
        this.endTime = new SimpleStringProperty(performance.getEndTime());
        
        this.stage = new SimpleStringProperty(performance.getStage().getName());
        this.popularity = new SimpleStringProperty(String.valueOf(artist.getPopularity()));
        this.genre = new SimpleStringProperty(artist.getGenre());
    }

    public String getArtistName() {
        return artistName.get();
    }
    public SimpleStringProperty artistNameProperty() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName.set(artistName);
    }
    public String getBeginTime() {
        return beginTime.get();
    }
    public SimpleStringProperty beginTimeProperty() {
        return beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime.set(beginTime);
    }
    public String getEndTime() {
        return endTime.get();
    }
    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }
    public String getStage() {
        return stage.get();
    }
    public SimpleStringProperty stageProperty() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage.set(stage);
    }
    public String getPopularity() {
        return popularity.get();
    }
    public SimpleStringProperty popularityProperty() {
        return popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity.set(popularity);
    }
    public String getGenre() {
        return genre.get();
    }
    public SimpleStringProperty genreProperty() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre.set(genre);
    }
}
