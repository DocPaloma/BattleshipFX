package com.example.battleship.Models;

public class JugadorPersona extends  Jugador {
    public JugadorPersona(String nombre) {
        super(nombre);
    }

    @Override
    public boolean disparar(Jugador enemigo, int fila, int columna) {
        return false;
    }
}
