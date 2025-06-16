package com.example.battleship.Views;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is in charge of loading the start screen, it extends Stage and loads the FXML file
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase se encarga de cargar la pantalla inicial, se extiende de Stage y carga en archivo FXML
public class InicioJuegoView extends Stage {

    /**
     * Loads the corresponding FXML file and sets the scene
     *
     * @throws IOException
     */
    // Carga el archivo FXML que le corresponde y configura la escena
    public InicioJuegoView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/battleship/InicioJuego.fxml"));
        Scene scene = new Scene(loader.load());
        this.setTitle("Inicio Juego Batalla Naval!!!");
        this.setScene(scene);
    }


    /**
     * Returns the only instance so that infinite screens are not created
     *
     * @return InicioJuegoView.InicioJuegoViewHolder.INSTANCE
     * @throws IOException
     */
    // Devuelve la unica instancia para que no se creen pantalla infinitas
    public static InicioJuegoView getInstance() throws IOException {
        if(InicioJuegoView.InicioJuegoViewHolder.INSTANCE == null) {
            InicioJuegoView.InicioJuegoViewHolder.INSTANCE = new InicioJuegoView();
            return  InicioJuegoView.InicioJuegoViewHolder.INSTANCE;
        } else  {
            return InicioJuegoView.InicioJuegoViewHolder.INSTANCE;
        }
    }

    /**
     * Static inner class that holds the single instance
     */
    // Clase interna estatica que mantiene la instancia unica
    public static class InicioJuegoViewHolder{
        private static InicioJuegoView INSTANCE;
    }
}
