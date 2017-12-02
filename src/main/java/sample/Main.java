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
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ChartObject Generator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
        DataReader bufferedReader = new DataReader(2, new File("C:\\Users\\sojer\\Desktop\\file.txt"));
        bufferedReader.loadData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
