package com.example.battleship.Models;

public class BarcoVisualFactory {

    public static BarcoVisual crearBarco(String tipo) {
        return switch (tipo.toLowerCase()){
            case "fragata" -> new FiguraFragata();
            case "destructor" -> new FiguraDesctructor();
            case "submarino" -> new FiguraSubmarino();
            case "portaaviones" -> new FiguraPortaAviones();
            default -> throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        };
    }
}
