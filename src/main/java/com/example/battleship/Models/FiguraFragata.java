package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class FiguraFragata implements BarcoVisual{

    private boolean vertical = true;

    @Override
    public List<Node> crearFiguras() {
        List<Node> figuras = new ArrayList<Node>();

        //Rectangle base =  new Rectangle(45,45);

        Polygon base = new Polygon();
        base.getPoints().addAll(new Double[]{
                16.875, 0.0,
                28.125, 0.0,
                33.75, 11.25,
                11.25, 11.25
        });
        base.setFill(Color.BLACK);

        Rectangle baseRectangle = new Rectangle(22.5, 33.75);
        baseRectangle.setFill(Color.BLACK);
        baseRectangle.setTranslateX(11.25);
        baseRectangle.setTranslateY(11.25);

        Polygon aletaIzquierda = new Polygon();
        aletaIzquierda.getPoints().addAll(new Double[]{
                11.25, 28.125,
                11.25, 45.0,
                0.0, 39.375,
                0.0, 33.75
        });
        aletaIzquierda.setFill(Color.BLACK);

        Polygon aletaDerecha = new Polygon();
        aletaDerecha.getPoints().addAll(new Double[]{
                33.75, 28.125,
                45.0, 33.75,
                45.0, 39.375,
                33.75, 45.0
        });
        aletaDerecha.setFill(Color.BLACK);

        Rectangle rectanguloCentro = new Rectangle(11.25, 16.875);
        rectanguloCentro.setFill(Color.RED);
        rectanguloCentro.setTranslateX(16.875);
        rectanguloCentro.setTranslateY(11.25);
        rectanguloCentro.setArcWidth(15);
        rectanguloCentro.setArcHeight(15);

        Circle circleCentro = new Circle(22.5, 19.6875, 2.8125);
        circleCentro.setFill(Color.BLACK);

        figuras.add(base);
        figuras.add(baseRectangle);
        figuras.add(aletaIzquierda);
        figuras.add(aletaDerecha);
        figuras.add(rectanguloCentro);
        figuras.add(circleCentro);

        return figuras;
    }

    @Override
    public int getTamano() {
        return 1;
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
