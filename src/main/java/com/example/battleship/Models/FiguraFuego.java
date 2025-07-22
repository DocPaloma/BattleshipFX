package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class FiguraFuego implements ObjetosVisualesInterface{
    @Override
    public List<Node> crearObjeto() {
        List <Node> figuras = new ArrayList<>();

        // Base invisible para mantener tamaño uniforme
        Rectangle base = new Rectangle(35, 35);
        base.setFill(Color.TRANSPARENT); // o semi-transparente para pruebas
        base.setStroke(Color.TRANSPARENT);


        Polygon llamaExterior = new Polygon(
                22.5, 0.0,    // punta
                30.0, 10.0,
                35.0, 22.0,
                28.0, 36.0,
                22.5, 45.0,   // base
                17.0, 36.0,
                10.0, 22.0,
                15.0, 10.0
        );
        llamaExterior.setFill(Color.ORANGERED);
        llamaExterior.setStroke(Color.DARKRED);
        llamaExterior.setStrokeWidth(2);

        //Llama interior
        Polygon llamaInterior = new Polygon(
                22.5, 8.0,
                26.0, 16.0,
                24.0, 28.0,
                22.5, 37.0,
                21.0, 28.0,
                19.0, 16.0
        );
        llamaInterior.setFill(Color.GOLD);
        llamaInterior.setStroke(Color.GOLDENROD);
        llamaInterior.setStrokeWidth(1);

        figuras.add(llamaExterior);
        figuras.add(llamaInterior);
        figuras.add(base);

        return figuras;
    }
}
