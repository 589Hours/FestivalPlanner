package Application;


import data.CellDataForTableView;
import data.FestivalPlan;
import data.Performance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class FestivalTableview extends TableView {

    private FestivalPlan festivalPlan;
    private TableColumn beginTimeColumn;
    private TableColumn endTimeColumn;
    private TableColumn stageColumn;
    private TableColumn artistColumn;
    private TableColumn popularityColumn;
    private TableColumn genreColumn;


    public FestivalTableview(FestivalPlan festivalPlan){
        this.festivalPlan = festivalPlan;
        setUpTable();
    }
    public void setUpTable(){
        beginTimeColumn = new TableColumn("Begin time");
        endTimeColumn = new TableColumn("End time");
        stageColumn = new TableColumn("Stage");
        artistColumn = new TableColumn("Artist");
        popularityColumn = new TableColumn("Popularity");
        genreColumn = new TableColumn("Genre");

        /*Hiermee set je de sizes naar 1/6 van de totale lengte.
        Hierdoor worden alle stukjes even groot */
        setColumnsWidth();

        //add columns to the table
        getColumns().addAll(beginTimeColumn,endTimeColumn,stageColumn,artistColumn,popularityColumn,genreColumn);

        //set the values for the cells according to the CellData... object
        setCellValue(beginTimeColumn, "beginTime");
        setCellValue(endTimeColumn, "endTime");
        setCellValue(stageColumn, "stage");
        setCellValue(artistColumn, "artistName");
        setCellValue(popularityColumn, "popularity");
        setCellValue(genreColumn, "genre");

        /* per performance naar stringfactory object en tenslotte toevoegen
        aan Arraylist die uiteindelijk observable word */

        ArrayList<CellDataForTableView> data = new ArrayList<>();
        for (Performance performance : festivalPlan.getPerformances()) {
            CellDataForTableView cellDataForTableView = new CellDataForTableView(performance);
            data.add(cellDataForTableView);
        }

        ObservableList<CellDataForTableView> dataToShow = FXCollections.observableArrayList(data);

        setItems(dataToShow);
    }

    public void setColumnsWidth() {
        beginTimeColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        endTimeColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        stageColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        artistColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        popularityColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
        genreColumn.prefWidthProperty().bind(this.widthProperty().divide(6));
    }

    public void setCellValue(TableColumn tableColumn, String parameter){
        tableColumn.setCellValueFactory(new PropertyValueFactory<CellDataForTableView, String>(parameter));
    }


   /* public boolean addPerformance(FestivalPlan festivalPlan, Performance performance) {
        if (festivalPlan.canAddPerformance(performance)) {
            festivalPlan.addPerformance(performance);
            festivalPlan.showSuccessAlert();
            return true;
        } else {
            System.err.println("Error: Performance overlaps with existing performances.");
            festivalPlan.showErrorAlert("Performance could not be added due to overlapping performances.");
            return false;
        }
    } */
    public void addPerformance(Performance performance) {
        if (festivalPlan.canAddPerformance(performance)) {
            festivalPlan.addPerformance(performance);
            festivalPlan.clearInputFields();
            festivalPlan.showSuccessAlert();
        } else {
            festivalPlan.showErrorAlert("Fout a klakel");
        }
    }
}
