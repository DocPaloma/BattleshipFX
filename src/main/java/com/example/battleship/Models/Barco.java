package com.example.battleship.Models;

import javafx.scene.image.Image;

public class Barco {

    private int tamano; // Para saber cuantas celdas ocupa {1,2,3,4}
    private boolean vertical; // Para saber si su orientacion es horizontal o vertical
    private int fila;
    private int columna;
    private String nombre;
    private int impactos = 0;
    private Image image;


    // Parametro tamaño para saber cuantas casillas ocupa un barco creado
    public Barco(String nombre, int tamano){
        this.nombre = nombre;
        this.tamano = tamano;
        this.impactos = 0;
        this.vertical = true; // Se toma por defecto que el barco se pone horizontal
        this.fila = -1; // el -1 lo puse para saber que el barco aun no se ha puesto
        this.columna = -1;
    }

    public String getNombre() {
        return nombre;
    }

    // Getter del tamaño para capturarlo
    public int getTamano(){
        return tamano;
    }

    // Para saber si el barco se puso horizontalmente o no
    public boolean esVerical(){
        return vertical;
    }

    // Sirve para cambiar la orientacion
    public void setVerical(boolean vertical){
        this.vertical = vertical;
    }

    // Obtener la fila donde empiza el barco
    public int getFila(){
        return fila;
    }

    // Obtener la columna donde empieza el barco
    public int getColumna(){
        return columna;
    }

    // Sirve para establecer la ubicacion del barco
    public void setPosicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public void recibirImpacto(){
        impactos++;
    }

    public boolean estaHundido(){
        return impactos >= tamano;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public Image getImage(){
        return image;
    }
}
