package com.example.battleship.Models;

public class JugadorMaquina extends Jugador {
    public JugadorMaquina(String nombre) {
        super("Maquina");
    }

    @Override
    public boolean disparar(Jugador enemigo, int fila, int columna) {
        return false;
    }
}
