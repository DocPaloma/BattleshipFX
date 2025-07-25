package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FiguraSubmarino implements BarcoVisual, Serializable {

    private boolean vertical = true;

    @Override
    public List<Node> crearFiguras() {
        List <Node> figuras = new ArrayList<>();

        Ellipse base  = new Ellipse(22.5, 77.5, 20.5, 77.5);
        base.setFill(Color.YELLOWGREEN);


        Circle circleCentroGrande = new Circle(22.5, 77.5, 17.5);
        circleCentroGrande.setFill(Color.DARKGRAY);
        circleCentroGrande.setStroke(Color.BLACK);

        Circle circleCentroPequeno  = new Circle(22.5, 77.5, 15.5);
        circleCentroPequeno.setFill(Color.DARKGRAY);
        circleCentroPequeno.setStroke(Color.BLACK);

        Line line1 = new Line(14, 24, 32, 42);

        Line line2 = new Line(32, 24, 14, 42);

        Line line2_1 = new Line(14, 20, 32, 20);

        Line line3 = new Line(14, 118, 32, 136);

        Line line4 = new Line(32, 118, 14, 136);



        //Rectangle base = new Rectangle(45, 135);


        figuras.add(base);
        figuras.add(circleCentroGrande);
        figuras.add(circleCentroPequeno);
        figuras.add(line1);
        figuras.add(line2);
        figuras.add(line3);
        figuras.add(line4);
        figuras.add(line2_1);

        return figuras;
    }

    @Override
    public int getTamano() {
        return 3;
    }

    @Override
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    @Override
    public boolean esVertical() {
        return vertical;
    }
}
