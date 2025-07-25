package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FiguraBomba implements  ObjetosVisualesInterface, Serializable {
    @Override
    public List<Node> crearObjeto() {
        List <Node> figuras = new ArrayList<>();

        // Base invisible para mantener tamaño uniforme
        Rectangle base = new Rectangle(35, 35);
        base.setFill(Color.TRANSPARENT); // o semi-transparente para pruebas
        base.setStroke(Color.TRANSPARENT);


        Circle baseCirculo = new Circle(22.5, 27, 13);
        baseCirculo.setFill(Color.DARKSLATEGRAY);

        Rectangle mecha = new Rectangle(20, 10, 5, 8);
        mecha.setFill(Color.GRAY);
        mecha.setArcWidth(2);
        mecha.setArcHeight(2);

        Polygon llamaExterna = new Polygon(
                38.0, 4.0,
                34.0, 8.0,
        36.0, 12.0,
                31.0, 10.0,
        28.0, 14.0,
                27.0, 9.0,
                22.0, 8.0,
                26.0, 5.0,
                24.0, 0.0,
                30.0, 2.0,
                33.0, -2.0,
                34.0, 2.0
        );
        llamaExterna.setFill(Color.ORANGERED);
        llamaExterna.setStroke(Color.DARKRED);





        figuras.add(baseCirculo);
        figuras.add(mecha);
        figuras.add(llamaExterna);
        figuras.add(base);

        return figuras;
    }
}
