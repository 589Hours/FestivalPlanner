package data;

import java.io.Serializable;
import java.util.ArrayList;

public class FestivalPlan implements Serializable {
    private ArrayList<Performance> performances;
    private ArrayList<Artist> artists;
    private ArrayList<Stage> stages;

    public FestivalPlan() {
        this.performances = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.stages = new ArrayList<>();
//        addTestData();
    }

    private void addTestData() {
        // Artiesten
        String standardArtistInfo = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad\nminim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit\nin voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia\ndeserunt mollit anim id est laborum.";

        Artist artist1 = new Artist("Ed Sheeran", 95, "Pop");
        artist1.setArtistInfo(standardArtistInfo);

        Artist artist2 = new Artist("Gorillaz", 30, "Indie");
        artist2.setArtistInfo(standardArtistInfo);

        Artist artist3 = new Artist("Suzan en Freek", 70, "Rock");
        artist3.setArtistInfo(standardArtistInfo);

        Artist artist4 = new Artist("Kraantje Pappie", 10, "Rap");
        artist4.setArtistInfo(standardArtistInfo);

        Artist artist5 = new Artist("Bizzey", 0, "Country");
        artist5.setArtistInfo(standardArtistInfo);

        Artist artist6 = new Artist("The Weekend", 65, "Rock");
        artist6.setArtistInfo(standardArtistInfo);

        Artist artist7 = new Artist("Snelle", 50, "Pop");
        artist7.setArtistInfo(standardArtistInfo);

        Artist artist8 = new Artist("Armin van Buuren", 100, "Metal");
        artist8.setArtistInfo(standardArtistInfo);

        artists.add(artist1);
        artists.add(artist2);
        artists.add(artist3);
        artists.add(artist4);
        artists.add(artist5);
        artists.add(artist6);
        artists.add(artist8);
        artists.add(artist7);

        // Podiums
        Stage stage = new Stage("Hoofdpodium", 1);
        Stage stage2 = new Stage("Testpodium1", 2);
        Stage stage3 = new Stage("Testpodium2", 3);
        Stage stage4 = new Stage("Testpodium3", 4);
        Stage stage5 = new Stage("Testpodium4", 5);
        stages.add(stage);
        stages.add(stage2);
        stages.add(stage3);
        stages.add(stage4);
        stages.add(stage5);

        // Performances
        Performance performance = new Performance(artist1, stage, 12, 0, 13,0);
        Performance performance1 = new Performance(artist2, stage2, 12, 30, 15, 0);
        Performance performance2 = new Performance(artist3, stage, 13, 30, 16, 30);
        Performance performance3 = new Performance(artist4, stage3, 14, 30, 16, 0);
        Performance performance4 = new Performance(artist5, stage4, 14, 0, 17, 0);
        Performance performance5 = new Performance(artist6, stage5, 12, 0, 14, 30);
        Performance performance6 = new Performance(artist7, stage2, 16, 0, 19, 0);
        Performance performance7 = new Performance(artist8, stage, 20, 0, 24, 0);
        Performance performance8 = new Performance(artist1, stage3, 20, 0, 23, 0);
        Performance performance9 = new Performance(artist2, stage4, 22, 0, 2, 0);


        performances.add(performance);
        performances.add(performance1);
        performances.add(performance2);
        performances.add(performance3);
        performances.add(performance4);
        performances.add(performance5);
        performances.add(performance6);
        performances.add(performance7);
        performances.add(performance8);
        performances.add(performance9);
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }


    public ArrayList<Artist> getArtists() {
        return artists;
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

    public void deleteStage(Stage stage) {
        stages.remove(stage);
    }
    public void deleteArtist(Artist artist) {
        artists.remove(artist);
    }
    public void deletePerformance(Performance performance) {
        performances.remove(performance);
    }
}
