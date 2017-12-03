package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    private  FileChooser fileChooser;

    private ChartObject chartObject;
    private File dataFile;

    @FXML
    private ComboBox<String> chartTypeBox;

    @FXML
    private ComboBox<String> seriesAmountBox;

    @FXML
    private Button generateButton;

    @FXML
    private Button addFileButton;

    public MainWindowController()
    {
        fileChooser = new FileChooser();
        chartObject = new ChartObject();
    }

    public MainWindowController(ChartObject chartObject)
    {
        this.fileChooser = new FileChooser();
        this.chartObject = chartObject;
        this.dataFile = chartObject.getDataFile();
    }

    private void initChartTypeBox()
    {
        chartTypeBox.getItems().addAll("Line Chart", "Area Chart", "Bubble Chart", "Scatter Chart");
        chartTypeBox.setValue("Line Chart");
    }

    private void initSeriesAmountBox()
    {
        for(int i = 1; i < 11; ++i)
            seriesAmountBox.getItems().add(Integer.toString(i));
        seriesAmountBox.setValue("1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChartTypeBox();
        initSeriesAmountBox();

        addFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file: *.txt", "*.txt"));
                dataFile = fileChooser.showOpenDialog(seriesAmountBox.getScene().getWindow());
            }
        });


        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(dataFile != null)
                {
                    chartObject.initialize(chartTypeBox.getValue(), dataFile, Integer.parseInt(seriesAmountBox.getValue()));

                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/AdvancedSettingsWindow.fxml"));
                        fxmlLoader.setController(new AdvancedSettingsController(chartObject));
                        Scene scene = new Scene((Parent) fxmlLoader.load(), 750, 400);
                        Stage thisStage = (Stage)generateButton.getScene().getWindow();
                        thisStage.setResizable(false);
                        thisStage.setScene(scene);

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Select Data File!", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }

            }
        });

    }
}
