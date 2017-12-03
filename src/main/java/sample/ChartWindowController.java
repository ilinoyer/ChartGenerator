package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartWindowController implements Initializable {

    private ChartObject chartObject;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuItem saveAs;

    public ChartWindowController(ChartObject chartObject)
    {
        this.chartObject = chartObject;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            anchorPane.getChildren().add(chartObject.getChart());
            anchorPane.setTopAnchor(chartObject.getChart(),25.0);
            anchorPane.setBottomAnchor(chartObject.getChart(),0.0);
            anchorPane.setLeftAnchor(chartObject.getChart(),0.0);
            anchorPane.setRightAnchor(chartObject.getChart(),0.0);


            saveAs.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    WritableImage image = chartObject.getChart().snapshot(new SnapshotParameters(), null);
                    File file = new File("C:\\Users\\sojer\\Desktop\\chart.png");
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

    }
}
