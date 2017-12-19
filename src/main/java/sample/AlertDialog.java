package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertDialog {
    public static void showAlert(String alertMessage, Alert.AlertType alertType)
    {
        Alert alert = new Alert(alertType, alertMessage, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
}
