package com.example.battleship.Models;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * This Barco class allows creating ships with a size, direction, and position
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase Barco permite crear barcos con un tamaño, sentido y posicion
public class Barco implements Serializable {

    private static final long serialVersionUID = 1L;

    private int tamano; // Para saber cuantas celdas ocupa {1,2,3,4}
    private boolean vertical; // Para saber si su orientacion es horizontal o vertical
    private int fila;
    private int columna;
    private String nombre;
    private int impactos = 0;
    //private Image image;
    private String rutaImagen;
    private int filaInicio;
    private int columnaInicio;
    private BarcoVisual figura;


    /**
     * This constructor was mainly created for when JugadorPersona adds ships to their board
     *
     * @param nombre
     * @param tamano
     */
    // Este constructor fue creado principalmente para cuando JugadorPersona añade barcos a su tablero
    // Parametro tamano para saber cuantas casillas ocupa un barco creado
    public Barco(String nombre, int tamano){
        this.nombre = nombre;
        this.tamano = tamano;
        this.impactos = 0;
        this.vertical = true; // Se toma por defecto que el barco se pone horizontal
        this.fila = -1; // el -1 lo puse para saber que el barco aun no se ha puesto
        this.columna = -1;
    }

    /**
     * A second constructor was created mainly for JugadorMaquina when it creates its strategy randomly
     *
     * @param nombre
     * @param tamano
     * @param figura
     */
    // Se creo un segundo constructor principalmente para JugadorMaquina cuando crea su estrategia de manera aleatoria
    public Barco(String nombre, int tamano, BarcoVisual figura) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.figura =  figura;
    }

    /**
     * This method allows reading the name of the ship
     *
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * This method allows reading the size of the ship
     *
     * @return int tamano
     */
    // Getter del tamaño para capturarlo
    public int getTamano(){
        return tamano;
    }

    /**
     * This method allows checking with a boolean if the ship is vertical by default or horizontal
     *
     * @return true or false
     */
    // Para saber si el barco se puso horizontalmente o no
    public boolean esVerical(){
        return vertical;
    }

    /**
     * This method allows changing the orientation of the ship
     *
     * @param vertical
     */
    // Sirve para cambiar la orientacion
    public void setVerical(boolean vertical){
        this.vertical = vertical;
    }

    /**
     * This method allows reading the row where the ship is located
     *
     * @return int fila
     */
    // Obtener la fila donde empiza el barco
    public int getFila(){
        return fila;
    }

    /**
     * This method allows reading the column where the ship is located
     *
     * @return columna
     */
    // Obtener la columna donde empieza el barco
    public int getColumna(){
        return columna;
    }

    // Sirve para establecer la ubicacion del barco
    public void setPosicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * This method adds up how many hits a ship has taken to later compare it with its size (hundido)
     **/
    public void recibirImpacto(){
        impactos++;
    }

    /**
     * This method allows checking if the number of hits on a ship is equal to its size, so we know if it
     * is sunk or not
     *
     * @return true or false
     */
    public boolean barcoHundido(){
        System.out.println("Impactos: " + impactos + " Tamano: " + tamano);
        return impactos >= tamano;
    }

    /**
    public void setImage(Image image){
        this.image = image;
    }
     **/

    /**
    public Image getImage(){
        return image;
    }
     **/

    /**
     * his method changes the image path that the ship will have
     *
     * @param rutaImagen
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * This method allows reading the image path of the ship
     *
     * @return String rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }


    public void setPosicionInicio(int fila, int columna) {
        this.filaInicio = fila;
        this.columnaInicio = columna;
    }


    public boolean esInicio(int fila, int columna) {
        return this.filaInicio == fila && this.columnaInicio == columna;
    }



    public void setFigura(BarcoVisual figura){
        this.figura = figura;
    }

    public BarcoVisual getFigura(){
        return figura;
    }
}
