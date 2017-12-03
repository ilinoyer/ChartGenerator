package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdvancedSettingsController implements Initializable{

    private ChartObject chartObject;
    private SimpleBooleanProperty isChanged;

    public AdvancedSettingsController(ChartObject chartObject)
    {
        this.chartObject = chartObject;
        isChanged = new SimpleBooleanProperty(false);
    }

    @FXML
    private ListView<DataSeries> seriesListView;
    private ObservableList<DataSeries> seriesObservableList = FXCollections.observableArrayList();


    @FXML
    private TextField xAxisLabel;

    @FXML
    private TextField yAxisLabel;

    @FXML
    private TextField xAxisMaxValue;

    @FXML
    private TextField yAxisMaxValue;

    @FXML
    private TextField xAxixMinUnit;

    @FXML
    private TextField yAxisMinUnit;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button generateButton;

    public void initSeriesListView()
    {
        for(int i = 0; i < chartObject.getSeriesNumber(); i++){
            seriesObservableList.add(chartObject.getDataSeriesByIndex(i));
        }
        seriesListView.setItems(seriesObservableList);
    }

    private void openModifyWindow(DataSeries dataSeriesToModify)
    {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ModifyWindow.fxml"));
            fxmlLoader.setController(new ModifyWindowController(dataSeriesToModify, isChanged));
            Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Modify DataSeries");
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    isChanged.set(true);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSeriesListView();

        isChanged.addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                seriesListView.refresh();
                isChanged.set(false);

            }
        });

        seriesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        DataSeries dataSeriesToModify = seriesListView.getSelectionModel().getSelectedItem();
                        openModifyWindow(dataSeriesToModify);
                    }
                }
            }
        });

        previousPageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/MainWindow.fxml"));
                    fxmlLoader.setController(new MainWindowController(chartObject));
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 750, 400);
                    Stage thisStage = (Stage)previousPageButton.getScene().getWindow();
                    thisStage.setResizable(false);
                    thisStage.setScene(scene);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ChartWindow.fxml"));
                    fxmlLoader.setController(new ChartWindowController(chartObject));
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 800, 600);
                    Stage stage = new Stage();
                    stage.setTitle("Chart");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
