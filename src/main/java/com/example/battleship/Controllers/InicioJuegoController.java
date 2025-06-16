package com.example.battleship.Controllers;

import com.example.battleship.Models.AlertaIniciarJuego;
import com.example.battleship.Models.JugadorPersona;
import com.example.battleship.Views.InstruccionesView;
import com.example.battleship.Views.PantallaJugadorView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * This class handles the interaction and initialization of the start interface
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
public class InicioJuegoController {

    @FXML
    private TextField textFieldNombre;

    @FXML
    private Label labelMensaje;

    @FXML
    private VBox vBoxPrincipal;

    /**
     * This method is the first to be executed in this interface, it adds a background image
     */
    public void initialize(){
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        vBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");
    }

    /**
     * When the button is pressed, this event saves the entered name, triggers an alert,
     * and loads the PantallaJugadorView
     *
     * @param actionEvent
     * @throws IOException
     */
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

    @FXML
    void onActionBotonInstrucciones(ActionEvent event) throws IOException {
        InstruccionesView instruccionesView = InstruccionesView.getInstance();
        instruccionesView.show();
    }
}
