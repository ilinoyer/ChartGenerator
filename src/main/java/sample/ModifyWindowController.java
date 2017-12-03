package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyWindowController implements Initializable{

    private DataSeries dataSeriesToModify;
    private SimpleBooleanProperty isChanged;

    public ModifyWindowController(DataSeries dataSeriesToModify, SimpleBooleanProperty isChanged)
    {
        this.isChanged = isChanged;
        this.dataSeriesToModify = dataSeriesToModify;
    }

    @FXML
    private TextField dataSeriesNumber;

    @FXML
    private TextField dataSeriesLabel;

    @FXML
    private Button saveButton;


    private void loadDataSeries()
    {
        dataSeriesNumber.setText(Integer.toString(dataSeriesToModify.getNumber()));
        dataSeriesLabel.setText(dataSeriesToModify.getLabel());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDataSeries();

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dataSeriesToModify.setLabel(dataSeriesLabel.getText());

                Alert alert = new Alert(Alert.AlertType.NONE, "Data Series Modified.", ButtonType.OK);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }

                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
                isChanged.setValue(true);
            }
        });
        {

        }

    }
}
