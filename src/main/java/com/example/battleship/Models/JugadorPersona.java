package com.example.battleship.Models;

/**
 * This class represents the jugadorPersona and extends from Jugador. It has a name, a board,
 * can shoot at the enemy, and can know if it has no ships left
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase representa al jugadorPersona y se extiende de Jugador, cuenta con un nombre, un tablero
// puede disparar al enemigo y puede darse cuenta si se queda sin barcos
public class JugadorPersona extends  Jugador {

    /**
     * Constructor that calls the parent class constructor using super
     *
     * @param nombre
     */
    //
    public JugadorPersona(String nombre) {
        super(nombre);
    }

    /**
     * his method allows the jugadorPersona to shoot at the enemy board (jugadorMaquina)
     *
     * @param enemigo
     * @param fila
     * @param columna
     * @return
     */
    @Override
    public boolean disparar(Tablero enemigo, int fila, int columna) {
        Barco resultado = enemigo.recibirDisparo(fila, columna);
        return resultado != null;
    }

    /**
     * This method checks if the jugadorPersona has no ships left
     *
     * @return true or false
     */
    public boolean zeroBarcos(){
        return getTablero().zeroBarcos();
    }
}
