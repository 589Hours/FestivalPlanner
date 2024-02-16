package data;

import java.util.ArrayList;

public class FestivalPlan {
    private ArrayList<Performance> performances;
    private ArrayList<Artist> artists;
    private ArrayList<Stage> stages;

    public FestivalPlan() {
        this.performances = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.stages = new ArrayList<>();
        addTestData();
    }

    private void addTestData() {
        ArrayList<Artist> artist1 = new ArrayList<>();
        artist1.add(new Artist("Ed Sheeran", 100, "Pop"));

        Stage stage = new Stage("Hoofdpodium");
        Stage stage2 = new Stage("Anderpodium1");
        Stage stage3 = new Stage("Anderpodium2");
        Stage stage4 = new Stage("Anderpodium3");
        stages.add(stage);
        stages.add(stage2);
        stages.add(stage3);
        stages.add(stage4);

        Performance performance = new Performance(artist1, stage, 8, 0, 9,0);
        performances.add(performance);
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public void addPerformance(Performance performance){
        this.performances.add(performance);
    }
    public void addArtist(Artist artist){
        this.artists.add(artist);
    }
    public void addStage(Stage stage){
        this.stages.add(stage);
    }
}
