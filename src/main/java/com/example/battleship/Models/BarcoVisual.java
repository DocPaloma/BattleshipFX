package com.example.battleship.Models;

import javafx.scene.Node;
import java.util.List;

public interface BarcoVisual {
    List<Node> crearFiguras();
    int getTamano();
    void setVertical(boolean vertical);
    boolean esVertical();
}
