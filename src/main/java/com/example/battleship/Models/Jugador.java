package com.example.battleship.Models;

import java.io.Serializable;

/**
 * This class represents the players, who have a name, a board, and can shoot
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase representa a los jugadores los cuales tienen un nombre, tablero y disparan
public abstract class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private Tablero tablero;
    private int barcosHundidos;

    /**
     * Constructor of the Player class where the name received is assigned as an attribute and
     * a board is created for that player
     *
     * @param nombre
     */
    // Contructor de la clase Jugador en donde se asigna el nombre que se recibe como atributo y crea un
    // tablero para ese jugador
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tablero = new Tablero(); // Cada jugador tiene su propio tablero
        //this.barcosHundidos = 0;
    }

    /**
     * Method that reads and returns the player's name
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method that reads and returns the player's entire board
     *
     * @return tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
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
     **/

    /**
     * An abstract method is created so that each player has their own version of shooting
     *
     * @param enemigo
     * @param fila
     * @param columna
     * @return true or false
     */
    // Se crea un metodo aabstracto para que cada jugador tenga su propia version de disparar
    public abstract boolean disparar(Tablero enemigo, int fila, int columna);

}
