package com.example.battleship.Models;

public class PosicionBarco {
    private Barco barco;
    private Tablero tablero;

    public PosicionBarco(Barco barco, Tablero tablero){
        this.barco = barco;
        this.tablero = tablero;
    }

    public Barco getBarco(){
        return barco;
    }

    public Tablero getTablero(){
        return tablero;
    }
}
