package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {

    private ChartObject chartObject;

    @FXML
    private AnchorPane anchorPane;

    public ChartController(ChartObject chartObject)
    {
        this.chartObject = chartObject;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            anchorPane.getChildren().add(chartObject.getChart());
            anchorPane.setTopAnchor(chartObject.getChart(),0.0);
            anchorPane.setBottomAnchor(chartObject.getChart(),0.0);
            anchorPane.setLeftAnchor(chartObject.getChart(),0.0);
            anchorPane.setRightAnchor(chartObject.getChart(),0.0);

    }
}
