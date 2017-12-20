package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/MainWindow.fxml"));
            fxmlLoader.setController(new MainWindowController());
            Scene scene = new Scene((Parent) fxmlLoader.load(), 750, 400);
            primaryStage.setTitle("Chart Generator");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
