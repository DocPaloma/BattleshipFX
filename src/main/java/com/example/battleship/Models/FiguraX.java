package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FiguraX implements ObjetosVisualesInterface, Serializable {
    @Override
    public List<Node> crearObjeto() {
        List<Node> figuras = new ArrayList<>();

        // Base invisible para mantener tamaño uniforme
        Rectangle base = new Rectangle(35, 35);
        base.setFill(Color.TRANSPARENT); // o semi-transparente para pruebas
        base.setStroke(Color.TRANSPARENT);


        Line linea1 = new Line(8, 5, 35, 35);
        linea1.setStrokeWidth(6);
        linea1.setStroke(Color.DARKRED);
        //linea1.setStartX(-45);
        //linea1.setStartY(-45);
        //linea1.setEndX(45);
        //linea1.setEndY(45);

        Line linea2 = new Line(35, 5, 8, 35);
        linea2.setStrokeWidth(6);
        linea2.setStroke(Color.DARKRED);
        //linea2.setStartX(45);
        //linea2.setStartY(45);
        //linea2.setEndX(-45);
        //linea2.setEndY(-45);

        figuras.add(linea1);
        figuras.add(linea2);
        figuras.add(base);

        return figuras;

    }
}
