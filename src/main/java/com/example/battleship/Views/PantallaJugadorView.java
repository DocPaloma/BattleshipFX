package com.example.battleship.Views;

import com.example.battleship.Controllers.PantallaJugadorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class loads the screen where the playerPersona creates their strategy. It extends Stage and
 * loads the FXML file
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase se encarga de cargar la pantalla donde el jugadorPersona crea su estrategia, se extiende de stage
// y carga el archivo FXML
public class PantallaJugadorView extends Stage {

    private PantallaJugadorController controller;

    /**
     * It loads the corresponding FXML file and sets the scene
     *
     * @throws IOException
     */
    // Carga el archivo FXML que le corresponde y carga la escena
    public PantallaJugadorView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/com/example/battleship/PantallaJugador.fxml"));
        Scene scene = new Scene(loader.load());
        this.controller = loader.getController();
        this.setTitle("Game Uno");
        this.setScene(scene);
    }

    /**
     * Returns the controller linked to this game view
     *
     * @return controller
     */
    // Devuelve el controlador asociado a esta vista del juego
    public PantallaJugadorController getController() {
        return controller;
    }


    /**
     * Returns the only instance so that infinite windows are not created
     *
     * @return PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE
     * @throws IOException
     */
    // Devueve la unica instancia para que no se creen pantallas infinitas
    public static PantallaJugadorView getInstance() throws IOException{
        if(PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE == null){
            PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE = new PantallaJugadorView();
            return PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE;
        } else {
            return PantallaJugadorView.PantallaJugadorViewHolder.INSTANCE;
        }
    }

    /**
     * Static inner class that returns the only instance
     */
    // Clase Interna estatica que devuelve la unica instancia
    public static class PantallaJugadorViewHolder{
        private static PantallaJugadorView INSTANCE; //Clase interna, contiene la unica instancia
    }
}
