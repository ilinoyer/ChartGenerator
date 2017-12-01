package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    final FileChooser fileChooser = new FileChooser();

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
                File file = fileChooser.showOpenDialog(seriesAmountBox.getScene().getWindow());
            }
        });


    }
}
