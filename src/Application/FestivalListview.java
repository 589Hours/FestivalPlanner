package Application;


import data.FestivalPlan;
import javafx.scene.control.ListView;

public class FestivalListview extends ListView {

    private FestivalPlan festivalPlan;

    public FestivalListview(FestivalPlan festivalPlan){
        this.festivalPlan = festivalPlan;
    }

}
