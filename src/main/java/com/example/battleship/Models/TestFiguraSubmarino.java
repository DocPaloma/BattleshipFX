package com.example.battleship.Models;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestFiguraSubmarino extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FiguraSubmarino visual = new FiguraSubmarino();
        Pane root = new Pane();

        for(Node figura : visual.crearFiguras()){
            root.getChildren().add(figura);
        }

        Scene scene = new Scene(root, 80, 220); // Tamaño ajustado al portaaviones
        stage.setTitle("Vista previa del Submarino");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
