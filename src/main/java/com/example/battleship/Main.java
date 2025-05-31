package com.example.battleship;

import com.example.battleship.Views.InicioJuegoView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        InicioJuegoView inicioJuegoView = InicioJuegoView.getInstance();
        inicioJuegoView.show();
    }

    public static void main(String[] args) {
        launch();
    }
}