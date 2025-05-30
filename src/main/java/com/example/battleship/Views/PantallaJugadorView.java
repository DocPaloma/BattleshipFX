package com.example.battleship.Views;

import com.example.battleship.Controllers.PantallaJugadorController;
import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaJugadorView extends Stage {

    private PantallaJugadorController controller;

    public PantallaJugadorView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/com/example/battleship/PantallaJugador.fxml"));
        Scene scene = new Scene(loader.load());
        this.controller = loader.getController();
        this.setTitle("Game Uno");
        this.setScene(scene);
    }

    public PantallaJugadorController getController() {
        return controller;
    }


    public static PantallaJugadorView getInstance() throws IOException{
        if(PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE == null){
            PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE = new PantallaJugadorView();
            return PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE;
        } else {
            return PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE;
        }
    }

    public static class PantallaJugadorViewHolder{
        private static PantallaJugadorView INSTANCE; //Clase interna, contiene la unica instancia
    }
}
