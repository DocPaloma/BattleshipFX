package com.example.battleship.Views;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioJuegoView extends Stage {

    public InicioJuegoView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/battleship/InicioJuego.fxml"));
        Scene scene = new Scene(loader.load());
        this.setTitle("Inicio Juego Batalla Naval!!!");
        this.setScene(scene);
    }



    public static InicioJuegoView getInstance() throws IOException {
        if(InicioJuegoView.InicioJuegoViewHolder.INSTANCE == null) {
            InicioJuegoView.InicioJuegoViewHolder.INSTANCE = new InicioJuegoView();
            return  InicioJuegoView.InicioJuegoViewHolder.INSTANCE;
        } else  {
            return InicioJuegoView.InicioJuegoViewHolder.INSTANCE;
        }
    }

    public static class InicioJuegoViewHolder{
        private static InicioJuegoView INSTANCE;
    }
}
