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
    }

    private void addTestData() {
    }
}
