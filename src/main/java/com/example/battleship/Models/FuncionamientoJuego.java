package com.example.battleship.Models;

import java.io.Serializable;

/**
 * This class contains the game logic like turns, shots, ending, and winner
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase contiene el funcionamiento del juego como los turnos, los disparos, finalizacion y ganador
public class FuncionamientoJuego implements Serializable {

    private static final long serialVersionUID = 1L;

    private JugadorPersona jugadorPersona;
    private JugadorMaquina jugadorMaquina;
    private boolean turnoJugador; // true (jugadorPersona), false (jugadorMaquina)
    //private Tablero ultimoTableroJugado;
    private int barcosP = 10;
    private int barcosM = 10;


    /**
     * Constructor receives two objects which are both players, and says that the first turn is for jugadorPersona
     *
     * @param jugadorPersona
     * @param jugadorMaquina
     */
    // Constructor recibe dos objetos que son ambos jugadores, indica que el primer turno es de jugadorPersona
    public FuncionamientoJuego(JugadorPersona jugadorPersona, JugadorMaquina jugadorMaquina) {
        this.jugadorPersona = jugadorPersona;
        this.jugadorMaquina = jugadorMaquina;
        this.turnoJugador = true; // Siempre va a empezar jugador jugadorPersona
    }


    /**
     * This method makes the shot based on the player's turn and returns the coordinates where
     * the shot was made
     *
     * @param fila
     * @param columna
     * @return int[fila][columna]
     */
    // Este metodo realiza en disparo segun el turno del jugador y retorna las coordenada en donde se disparo
    // Los parametros son las coordenadas donde el jugador quiere disparar
    public int[] disparoSegunTurno(int fila, int columna) {
        boolean aciertoDisparo; // Para saber si acerto, de ser asi para que pueda seguir disparando

        // Disparo cuando es el turno del jugadorPersona
        if (turnoJugador) {
            aciertoDisparo = jugadorPersona.disparar(jugadorMaquina.getTablero(), fila, columna);
            //ultimoTableroJugado = jugadorMaquina.getTablero();

            // Si falla entonces debe de cambiar turno
            if (!aciertoDisparo) {
                cambiarTurno();
            }

            return new int[] {fila, columna};

            // Disparo cuando es el turno de jugadorMaquina
        } else {
            int[] coor; // Variable en donde se guardan las coordenadas escogidas por la maquina aleatoriamente
            // Se usa un Do-while para que la maquina dispare hasta que falle
            do {
                coor = jugadorMaquina.elegirCoordenadasAleatorias(); // Escoge las coordenadas aleatoriamente
                System.out.println("Coor ale escogidas: " + coor[0] + " " + coor[1]);
                aciertoDisparo = jugadorMaquina.disparar(jugadorPersona.getTablero(), coor[0], coor[1]);

                System.out.println("Jugador Máquina disparó en: (" +
                        coor[0] + ", " +
                        coor[1] + ")");

                //ultimoTableroJugado = jugadorPersona.getTablero();
            } while (aciertoDisparo);
            cambiarTurno(); // Cambia de turno cuando falla
            return coor;
        }
    }

    /**
     * This method returns the current value of turnoJugador to use it in other classes
     *
     * @return true or false
     */
    // Este metodo devuelve el valor actual del turnoJugador para poder usarlo en otras clases
    public boolean getTurnoJugador() {
        return this.turnoJugador;
    }

    /**
     * This method changes the value so the next player can play
     */
    // Este metodo cambia el valor para que juegue el proximo jugador
    public void cambiarTurno() {
        turnoJugador = !turnoJugador;

    }

    /**
     * This method checks if there is already a winner
     *
     * @return true or false
     */
    // Este metodo determina si ya hay un ganador
    public boolean juegoTerminado() {
        return jugadorPersona.zeroBarcos() || jugadorMaquina.zeroBarcos();
    }

    /**
     * This method returns the winner player's name when the opponent has 0 ships left
     *
     * @return String nombre
     */
    // Este metodo retorna el nombre del jugador ganador cuando el oponente queda con 0 barcos
    public String ganador() {
        if (jugadorMaquina.zeroBarcos()) return jugadorPersona.getNombre();
        if (jugadorPersona.zeroBarcos()) return "Maquina";

        return null;
    }


    /**
     * It subtracts from the initial number of ships (10) when jugadorMaquina sinks a ship
     *
     * @param barco
     * @return int barcosP
     */
    // Resta la cantidad de barcos iniciales (10) cuando jugadorMaquina hunde un barco
    public int cantBarcosPersona(Barco barco){
        if(barco != null && barco.barcoHundido()){
            System.out.println("Barco hundido de persona detectado.");
            barcosP--;
        }
        return barcosP;
    }

    /**
     * It subtracts from the initial number of ships (10) when jugadorPersona sinks a ship
     *
     * @param barco
     * @return int barcosM
     */
    // Resta la cantidad de barcos iniciales (10) cuando jugadorPersona hunde un barco
    public int cantBarcosMaquina(Barco barco){
        if(barco != null && barco.barcoHundido()){
            barcosM--;
        }
        return barcosM;
    }


    public JugadorMaquina getCPUPlayer(){
        return jugadorMaquina;
    }

    public JugadorPersona getPersonaPlayer(){
        return jugadorPersona;
    }

    /**
     * method that resets the game data
     */
    public void restoreGameData(){
        this.jugadorPersona = new JugadorPersona("Player");
        this.jugadorMaquina = new JugadorMaquina("Maquina");
    }

}
