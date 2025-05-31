package com.example.battleship.Controllers;

import com.example.battleship.Models.JugadorPersona;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class JuegoBatallaNavalController {

    @FXML
    private GridPane gridPaneTableroJugador2;

    private JugadorPersona jugador;

    public void setJugador(JugadorPersona jugador) {
        this.jugador = jugador;

        // Aquí puedes mostrar el tablero, por ejemplo:
        // pintarTablero(jugador.getTablero());
    }
}
