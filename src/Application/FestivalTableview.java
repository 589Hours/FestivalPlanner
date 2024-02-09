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


        /*Hiermee set je de sizes naar 1/6 van de totale lengte.
        Hierdoor worden alle stukjes even groot */
        beginTimeColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        endTimeColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        stageColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        artistColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        popularityColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        genre.prefWidthProperty().bind(this.widthProperty().divide(6));


        getColumns().addAll(beginTimeColumn,endTimeColumn,stageColumn,artistColumn,popularityColumn,genre);
    }

}
