package com.example.battleship.Models;

// Aqui se esta aplicando el patron Factory Method
public class BarcoFactory {
    public static Barco crearBarco(String tipo, String rutaImagen){
        int tamano;
        switch (tipo){
            case "fragata1", "fragata2",  "fragata3", "fragata4" -> tamano = 1;
            case "destructor1", "destructor2", "destructor3" -> tamano = 2;
            case "submarino1", "submarino2" -> tamano = 3;
            case "portaAviones" -> tamano = 4;
            default -> throw new IllegalArgumentException("Tipo de barco desconocido: " + tipo);
        }

        Barco barco = new Barco(tipo, tamano);
        barco.setRutaImagen(rutaImagen);
        barco.setVerical(true);
        return barco;
    }
}
