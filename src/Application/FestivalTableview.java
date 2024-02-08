package Application;


import data.FestivalPlan;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class FestivalTableview extends TableView {

    private FestivalPlan festivalPlan;

    public FestivalTableview(){
        //TODO receive data for the table
        setUpTable();
    }
    public void setUpTable(){
        TableColumn beginTimeColumn = new TableColumn("Begin time");
        TableColumn endTimeColumn = new TableColumn("End time");
        TableColumn stageColumn = new TableColumn("Stage");
        TableColumn artistColumn = new TableColumn("Artist");
        TableColumn popularityColumn = new TableColumn("Popularity");
        TableColumn genre = new TableColumn("Genre");

        int width = 725/6; //TODO auto resize vinden voor de columns
        beginTimeColumn.setPrefWidth(width);
        endTimeColumn.setPrefWidth(width);
        stageColumn.setPrefWidth(width);
        artistColumn.setPrefWidth(width);
        popularityColumn.setPrefWidth(width);
        genre.setPrefWidth(width);

        getColumns().addAll(beginTimeColumn,endTimeColumn,stageColumn,artistColumn,popularityColumn,genre);
    }
}
