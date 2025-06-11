package com.example.battleship.Models;

public class JugadorPersona extends  Jugador {
    public JugadorPersona(String nombre) {
        super(nombre);
    }

    @Override
    public boolean disparar(Tablero enemigo, int fila, int columna) {
        Barco resultado = enemigo.recibirDisparo(fila, columna);
        return resultado != null;
    }

    public boolean zeroBarcos(){
        return getTablero().zeroBarcos();
    }
}
