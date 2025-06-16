package com.example.battleship.Views;

import com.example.battleship.Controllers.JuegoBatallaNavalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class loads the game, extends Stage, and loads an FXML file
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase se encarga de cargar el juego, se extiende de Stage y carga el archivo FXML
public class JuegoBatallaNavalView extends Stage {

    private JuegoBatallaNavalController controller;

    /**
     * Loads the corresponding FXML file and sets the scene
     *
     * @throws IOException
     */
    // Carga el archivo FXML que le corresponde y carga la escena
    public JuegoBatallaNavalView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/com/example/battleship/JuegoBatallaNaval.fxml"));
        Scene scene = new Scene(loader.load());
        this.controller = loader.getController();
        this.setTitle("Game Uno");
        this.setScene(scene);
    }

    /**
     * Returns the controller associated with this game view
     *
     * @return controller
     */
    // Devuelve el controllador asociado a esta vista del juego
    public JuegoBatallaNavalController getController() {
        return controller;
    }

    /**
     * Returns the single instance to avoid creating multiple windows
     *
     * @return JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE
     * @throws IOException
     */
    // Devuelve la unica instancia para que no se creen pantalla infinitas
    public static JuegoBatallaNavalView getInstance() throws IOException{
        if(JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE == null){
            JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE = new JuegoBatallaNavalView();
            return JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE;
        } else {
            return JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE;
        }
    }

    /**
     * Static inner class that holds the only instance
     */
    // Clase interna estatica que mantiene la unica instancia
    public static class JuegoBatallaNavalViewHolder{
        private static JuegoBatallaNavalView INSTANCE; //Clase interna, contiene la unica instancia
    }
}
