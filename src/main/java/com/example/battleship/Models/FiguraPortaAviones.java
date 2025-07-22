package com.example.battleship.Models;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FiguraPortaAviones implements BarcoVisual{

    private boolean vertical = true;

    @Override
    public List<Node> crearFiguras(){
        List<Node> figuras = new ArrayList<>();

        // Figura base o principal del barco (45 x 180)
        Rectangle base = new Rectangle(45, 180);
        base.setArcWidth(40);
        base.setArcHeight(40);
        base.setFill(Color.GRAY);
        base.setStroke(Color.BLACK);

        /**
        if (vertical) {
            base.setTranslateY(0);
        } else {
            base.setRotate(90);  // gira visualmente
            base.setTranslateX(0);
        }
         **/

        figuras.add(base);

        // Helipuertos y textos: en vez de usar setX/Y (absoluto), usamos setTranslate
        Rectangle[] helipuertos = new Rectangle[3];
        Text[] textos = new Text[3];
        int[] offsets = {50, 95, 135};

        for (int i = 0; i < 3; i++) {
            Rectangle h = new Rectangle(30, 30);
            h.setFill(Color.GRAY);
            h.setStroke(Color.BLACK);
            h.setTranslateX(7); // centrado
            h.setTranslateY(offsets[i]);

            Text t = new Text("H");
            t.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
            t.setTranslateX(15);
            t.setTranslateY(offsets[i] + 21); // centrado dentro del recuadro

            figuras.add(h);
            figuras.add(t);
        }

        // Helipuerto transparente
        Rectangle helipuertoT = new Rectangle(30, 30);
        helipuertoT.setFill(Color.TRANSPARENT);
        helipuertoT.setStroke(Color.BLACK);
        helipuertoT.setTranslateX(7);
        helipuertoT.setTranslateY(135);
        figuras.add(helipuertoT);

        // Cañón trasero
        Rectangle baseCanon = new Rectangle(10, 10);
        baseCanon.setFill(Color.DARKGRAY);
        baseCanon.setStroke(Color.BLACK);
        baseCanon.setTranslateX(15);
        baseCanon.setTranslateY(17);

        Rectangle tuboCanon = new Rectangle(5, 10);
        tuboCanon.setFill(Color.BLACK);
        tuboCanon.setTranslateX(17);
        tuboCanon.setTranslateY(6);

        figuras.add(baseCanon);
        figuras.add(tuboCanon);

        return figuras;
    }

    @Override
    // Este PortaAviones ocupa 4 espacios
    public int getTamano() {
        return 4;
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
