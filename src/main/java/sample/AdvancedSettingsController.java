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
import javafx.scene.control.*;
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

    @FXML
    private CheckBox autoRanging;

    private void initSeriesListView()
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


    private SimpleBooleanProperty loadChartParameters()
    {
        SimpleBooleanProperty parametersCorrectness = new SimpleBooleanProperty(true);
        chartObject.setYAxisAutoRanging(true);
        chartObject.setXAxisAutoRanging(true);

        if(xAxisLabel.getText() != null && !xAxisLabel.getText().isEmpty())
            chartObject.setXLabel(xAxisLabel.getText());

        if(yAxisLabel.getText() != null && !yAxisLabel.getText().isEmpty())
            chartObject.setYLabel(yAxisLabel.getText());

        if(!autoRanging.isSelected()) {

            chartObject.setYAxisAutoRanging(false);
            chartObject.setXAxisAutoRanging(false);

            if (yAxisMaxValue.getText() != null && !yAxisMaxValue.getText().isEmpty()) {

                try {
                    Double maxValue = Double.parseDouble(yAxisMaxValue.getText());
                    chartObject.setYAxisMaxValue(maxValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    AlertDialog.showAlert("Enter valid yAxisMaxValue ! ", Alert.AlertType.ERROR);
                    parametersCorrectness.setValue(false);

                }
            }

            if (xAxisMaxValue.getText() != null && !xAxisMaxValue.getText().isEmpty()) {

                try {
                    Double maxValue = Double.parseDouble(xAxisMaxValue.getText());
                    chartObject.setXAxisMaxValue(maxValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    AlertDialog.showAlert("Enter valid xAxisMaxValue ! ", Alert.AlertType.ERROR);
                    parametersCorrectness.setValue(false);
                }
            }

            if (yAxisMinUnit.getText() != null && !yAxisMinUnit.getText().isEmpty()) {

                try {
                    Double minUnit = Double.parseDouble(yAxisMinUnit.getText());
                    chartObject.setYAxisMinUnit(minUnit);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    AlertDialog.showAlert("Enter valid yAxisMinUnit ! ", Alert.AlertType.ERROR);
                    parametersCorrectness.setValue(false);
                }
            }

            if (xAxixMinUnit.getText() != null && !xAxixMinUnit.getText().isEmpty()) {

                try {
                    Double minUnit = Double.parseDouble(xAxixMinUnit.getText());
                    chartObject.setXAxisMinUnit(minUnit);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    AlertDialog.showAlert("Enter valid xAxisMinUnit ! ", Alert.AlertType.ERROR);
                    parametersCorrectness.setValue(false);
                }
            }
        }

        return parametersCorrectness;
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

                if( loadChartParameters().getValue()) {

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
            }
        });

        autoRanging.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(autoRanging.isSelected())
                {
                    xAxisMaxValue.setEditable(false);
                    yAxisMaxValue.setEditable(false);
                    xAxixMinUnit.setEditable(false);
                    yAxisMinUnit.setEditable(false);
                }
                else if(!autoRanging.isSelected())
                {
                    xAxisMaxValue.setEditable(true);
                    yAxisMaxValue.setEditable(true);
                    xAxixMinUnit.setEditable(true);
                    yAxisMinUnit.setEditable(true);
                }
            }
        });
    }
}
