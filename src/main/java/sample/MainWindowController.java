package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    final FileChooser fileChooser = new FileChooser();

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

    @FXML
    private Button settingsButton;

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
                dataFile = fileChooser.showOpenDialog(seriesAmountBox.getScene().getWindow());
            }
        });

        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/AdvancedSettingsWindow.fxml"));
                    fxmlLoader.setController(new AdvancedSettingsController());
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 750, 400);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Settings");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chartObject = new ChartObject(chartTypeBox.getValue(), Integer.parseInt(seriesAmountBox.getValue()), dataFile);

                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ChartWindow.fxml"));
                    fxmlLoader.setController(new ChartController(chartObject));
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 1100, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Chart Window");
                    stage.setScene(scene);
                    stage.show();

                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }
}
