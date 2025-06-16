package com.example.battleship.Models;

/**
 * This class represents the relationship between a ship and the board where it is located
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase representa la relacion entre barco y tablero en el que se encuentra ubicado
public class PosicionBarco {
    private Barco barco;
    private Tablero tablero;

    /**
     * Constructor that receives a ship and the board to be used
     *
     * @param barco
     * @param tablero
     */
    // Constructor el cual se le pasa un barco y el tablero que se desea usar
    public PosicionBarco(Barco barco, Tablero tablero){
        this.barco = barco;
        this.tablero = tablero;
    }

    /**
     * Method that reads and returns the ship object
     *
     * @return barco
     */
    public Barco getBarco(){
        return barco;
    }

    /**
     * Method that reads and returns the whole board
     *
     * @return tablero
     */
    public Tablero getTablero(){
        return tablero;
    }
}
