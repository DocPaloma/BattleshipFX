package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class FiguraDesctructor implements BarcoVisual{

    private boolean vertical = true;

    @Override
    public List<Node> crearFiguras() {
        List <Node> figuras = new ArrayList<>();

        // Base del barco con poligono
        Polygon basePico = new Polygon(
                22.5, 0.0, // punto del centro
                33.75, 5.25,
                45.0, 22.5, // punto derecho
                0.0, 22.5, // punto izquierdo
                11.25, 5.25 // punto izquiero
                //33.75, 11.25, // punto derecho
                //0.0, 30.5, // punto izquierdo
                //45.0, 30.5 // punto derecho
                //0.0, 90.0,
                //45.0, 90.0
                //100.0, 280.0,
                //70.0, 260.0,
                //70.0, 40.0
        );
        basePico.setFill(Color.GRAY);
        basePico.setStroke(Color.BLACK);

        Rectangle base = new Rectangle(45.0, 68.5);
        base.setFill(Color.GRAY);
        base.setStroke(Color.BLACK);
        base.setTranslateY(16.5);
        base.setArcWidth(20);
        base.setArcHeight(20);



        Rectangle ventana1  = new Rectangle(12, 12);
        ventana1.setFill(Color.DARKGRAY);
        ventana1.setStroke(Color.BLACK);
        ventana1.setTranslateX(15);
        ventana1.setTranslateY(33);

        Rectangle ventana2  = new Rectangle(20, 8);
        ventana2.setFill(Color.DARKGRAY);
        ventana2.setStroke(Color.BLACK);
        ventana2.setTranslateX(11);
        ventana2.setTranslateY(50);


        Rectangle ventana3  = new Rectangle(12, 12);
        ventana3.setFill(Color.DARKGRAY);
        ventana3.setStroke(Color.BLACK);
        ventana3.setTranslateX(15);
        ventana3.setTranslateY(64);


        Rectangle baseCanon  = new Rectangle(10.0, 10.0);
        baseCanon.setFill(Color.DARKGRAY);
        baseCanon.setStroke(Color.BLACK);
        baseCanon.setTranslateX(17.5);
        baseCanon.setTranslateY(11.0);

        Rectangle tuboCanon  = new Rectangle(3.0, 10.0);
        tuboCanon.setFill(Color.BLACK);
        tuboCanon.setTranslateX(21);
        tuboCanon.setTranslateY(2.0);


        figuras.add(basePico);
        figuras.add(base);
        figuras.add(ventana1);
        figuras.add(ventana2);
        figuras.add(ventana3);
        figuras.add(baseCanon);
        figuras.add(tuboCanon);

        return figuras;
    }

    @Override
    public int getTamano() {
        return 2;
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
