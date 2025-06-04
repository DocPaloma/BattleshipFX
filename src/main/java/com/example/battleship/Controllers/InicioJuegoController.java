package com.example.battleship.Controllers;

import com.example.battleship.Models.AlertaIniciarJuego;
import com.example.battleship.Models.JugadorPersona;
import com.example.battleship.Views.JuegoBatallaNavalView;
import com.example.battleship.Views.PantallaJugadorView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InicioJuegoController {

    @FXML
    private TextField textFieldNombre;

    @FXML
    private Label labelMensaje;

    @FXML
    private VBox vBoxPrincipal;


    public void initialize(){
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        vBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");
    }

    @FXML
    void onActionBotonIniciar(ActionEvent actionEvent) throws IOException {
        String nombre = textFieldNombre.getText().trim();

        if(nombre.isEmpty()){
            labelMensaje.setText("El nombre es obligatorio");
            return;
        }
        labelMensaje.setText("");

        JugadorPersona jugadorPersona = new JugadorPersona(nombre);


        //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        AlertaIniciarJuego alert = new AlertaIniciarJuego();

        boolean confirmacion = alert.mostrarAlertaDeConfirmacion
                ("Alerta de iniciar juego", "Esta es una ventana de alerta", "Deseas iniciar el juego?");
        if(confirmacion){
            PantallaJugadorView pantallaJugadorView = PantallaJugadorView.getInstance();
            pantallaJugadorView.getController().setJugador(jugadorPersona);
            pantallaJugadorView.getController().mostrarNombreJugador();
            pantallaJugadorView.show();
        } else{
            labelMensaje.setText("Decidiste no iniciar el juego");
        }
    }
}
