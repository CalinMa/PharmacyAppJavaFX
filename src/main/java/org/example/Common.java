package org.example;

import javafx.scene.control.Alert;

public class Common {
    public static void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Validation error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
