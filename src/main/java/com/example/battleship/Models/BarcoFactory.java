package com.example.battleship.Models;

/**
 * This class uses the Factory Method pattern that centralizes the creation of objects like a factory
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Aqui se esta aplicando el patron Factory Method, centraliza la creacion de objetos, como una fabrica,
// ya no se necesitaria usar new muchas veces y en diferentes partes
public class BarcoFactory {

    /**
     * Static method to create a ship based on its name
     *
     * @param tipo
     * @return
     */
    // Este metodo es estatico para no necesitar crear un objeto BarcoFactory para usarlo
    public static Barco crearBarco(String tipo, String rutaImagen){
        int tamano; // Variable para el tamaño del barco
        switch (tipo){
            // Dependiendo el nombre le asigna un tamaño
            case "fragata" -> tamano = 1;
            case "destructor" -> tamano = 2;
            case "submarino" -> tamano = 3;
            case "portaaviones" -> tamano = 4;
            default -> throw new IllegalArgumentException("Tipo de barco desconocido: " + tipo);
        }

        Barco barco = new Barco(tipo, tamano); // Crea un objeto tipo Barco
        barco.setRutaImagen(rutaImagen); // Le asigna la ruta que recibe como parametro
        barco.setVerical(true); // Define por defecto la orientacion que es vertical
        return barco; // Devuelve el objeto Barco que acabo de crear para usarlo
    }
}
