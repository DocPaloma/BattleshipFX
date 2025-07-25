package com.example.battleship.Models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class LeaveGameAlert implements AlertaInterface{
    @Override
    public boolean mostrarAlertaDeConfirmacion(String tittle, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType yesBttn = new ButtonType("Sí");
        ButtonType noBttn = new ButtonType("No");

        alert.getButtonTypes().setAll(yesBttn, noBttn);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
