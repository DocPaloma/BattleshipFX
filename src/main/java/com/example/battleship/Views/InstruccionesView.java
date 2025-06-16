package com.example.battleship.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * his class is responsible for loading the instructions screen, it extends Stage and loads the FXML file
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase se encarga de cargar la pantalla de instrucciones, se extiende de Stage y carga el archivo FXML
public class InstruccionesView extends Stage {

    /**
     * It loads the corresponding FXML file and sets up the scene
     *
     * @throws IOException
     */
    // Carga el archivo FXML que le corresponde y configura la escena
    public InstruccionesView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/battleship/Instrucciones.fxml"));
        Scene scene = new Scene(loader.load());
        this.setTitle("Instrciones juego Batalla Naval!!!");
        this.setScene(scene);
    }


    /**
     * Returns the only instance so infinite screens are not created
     *
     * @return InstruccionesView.InstrccionesViewHolder.INSTANCE
     * @throws IOException
     */
    // Devueve la unica instancia para que no se creen pantallas infinitas
    public static InstruccionesView getInstance() throws IOException {
        if(InstruccionesView.InstrccionesViewHolder.INSTANCE == null) {
            InstruccionesView.InstrccionesViewHolder.INSTANCE = new InstruccionesView();
            return InstruccionesView.InstrccionesViewHolder.INSTANCE;
        } else{
            return InstruccionesView.InstrccionesViewHolder.INSTANCE;
        }
    }


    /**
     * Static inner class that holds the only instance
     */
    // Clase interna estatica que mantiene la unica instancia
    public static class InstrccionesViewHolder{
        private static InstruccionesView INSTANCE;
    }


}
