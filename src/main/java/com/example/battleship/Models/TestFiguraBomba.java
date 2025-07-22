package com.example.battleship.Models;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestFiguraBomba extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FiguraBomba visual = new FiguraBomba();
        Pane root = new Pane();

        for(Node figura : visual.crearObjeto()){
            root.getChildren().add(figura);
        }

        Scene scene = new Scene(root, 80, 220);
        stage.setTitle("Test Figura Bomba");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
