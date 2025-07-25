package com.example.battleship.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class InstruccionesController {

    @FXML
    private VBox vBoxPrincipal;

    public void initialize(){
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        vBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");
    }


}
