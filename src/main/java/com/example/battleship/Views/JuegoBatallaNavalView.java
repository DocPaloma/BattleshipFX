package com.example.battleship.Views;

import com.example.battleship.Controllers.JuegoBatallaNavalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class JuegoBatallaNavalView extends Stage {

    private JuegoBatallaNavalController controller;

    public JuegoBatallaNavalView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/com/example/battleship/JuegoBatallaNaval.fxml"));
        Scene scene = new Scene(loader.load());
        this.controller = loader.getController();
        this.setTitle("Game Uno");
        this.setScene(scene);
    }

    public JuegoBatallaNavalController getController() {
        return controller;
    }

    public static JuegoBatallaNavalView getInstance() throws IOException{
        if(JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE == null){
            JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE = new JuegoBatallaNavalView();
            return JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE;
        } else {
            return JuegoBatallaNavalView.JuegoBatallaNavalViewHolder.INSTANCE;
        }
    }

    public static class JuegoBatallaNavalViewHolder{
        private static JuegoBatallaNavalView INSTANCE; //Clase interna, contiene la unica instancia
    }
}
