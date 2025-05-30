package com.example.battleship.Models;

public abstract class Jugador {

    private String nombre;
    private Tablero tablero;
    private int barcosHundidos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tablero = new Tablero(); // Cada jugador tiene su propio tablero
        this.barcosHundidos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getBarcosHundidos() {
        return barcosHundidos;
    }

    public void incrementarBarcosHundidos(){
        this.barcosHundidos++;
    }

    // Este metodo lo que hace es devolver la cantidad de barcos que le quedan al jugador
    public int barcosRestantes(){
        return tablero.getBarcosColocados().size() - barcosHundidos;
    }

    // Se crea un metodo aabstracto para que cada jugador tenga su propia version de disparar
    public abstract boolean disparar(Jugador enemigo, int fila, int columna);

}
