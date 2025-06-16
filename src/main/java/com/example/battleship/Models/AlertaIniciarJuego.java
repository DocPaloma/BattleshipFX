package com.example.battleship.Models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * This class creates a game start alert and lets the user confirm whether to start the game or not.
 * It also implements the AlertaInterface interface
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase crea una alerta de inicio del juego y permite que el usuario confirme iniciar el juego o no
// y tambien implementa la interface AlertaInterface
public class AlertaIniciarJuego implements AlertaInterface {

    /**
     * Method implemented from AlertaInterface. It shows the alert window with a title, header, content,
     * and an interactive button
     *
     * @param tittle
     * @param header
     * @param content
     * @return true if botton is pressed
     */
    // Metodo implementado por AlertaInterface, muestra la ventana de alerta con titulo, encabezado, contenido
    // y un boton interactivo
    @Override
    public boolean mostrarAlertaDeConfirmacion(String tittle, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait(); //Muestra la alerta y espera la respuesta

        return result.isPresent() && result.get() == ButtonType.OK; //Retorna true si presiona OK
    }

}
