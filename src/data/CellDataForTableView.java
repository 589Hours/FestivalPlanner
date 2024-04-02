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
        Artist artist = performance.getArtist();
        this.artistName = new SimpleStringProperty(artist.getName());

        this.beginTime = new SimpleStringProperty(performance.getBeginTime());
        this.endTime = new SimpleStringProperty(performance.getEndTime());
        
        this.stage = new SimpleStringProperty(performance.getStage().getName());
        this.popularity = new SimpleStringProperty(String.valueOf(artist.getPopularity()));
        this.genre = new SimpleStringProperty(artist.getGenre());
    }
}
