package com.example.battleship.Models;

public class FuncionamientoJuego {

    private JugadorPersona jugadorPersona;
    private JugadorMaquina jugadorMaquina;

    private boolean turnoJugador; // true (jugadorPersona), false (jugadorMaquina)

    private Tablero ultimoTableroJugado;

    private int barcosP = 10;
    private int barcosM = 10;
    Barco barco;

    public FuncionamientoJuego(JugadorPersona jugadorPersona, JugadorMaquina jugadorMaquina) {
        this.jugadorPersona = jugadorPersona;
        this.jugadorMaquina = jugadorMaquina;
        this.turnoJugador = true; // Siempre va a empezar jugador jugadorPersona
        //this.ultimoTableroJugado = jugadorMaquina.getTablero();
    }

    public void disparoSegunTurno(int fila, int columna) {
        boolean aciertoDisparo;

        if (turnoJugador) {
            aciertoDisparo = jugadorPersona.disparar(jugadorMaquina.getTablero(), fila, columna);
            ultimoTableroJugado = jugadorMaquina.getTablero();

            if (!aciertoDisparo) {
                cambiarTurno();
            }
        } else {
            do {
                int[] coordenadasAleatorias = jugadorMaquina.elegirCoordenadasAleatorias();
                aciertoDisparo = jugadorMaquina.disparar(jugadorPersona.getTablero(), coordenadasAleatorias[0], coordenadasAleatorias[1]);

                // Esto es lo nuevo:
                System.out.println("Jugador Máquina disparó en: (" +
                        coordenadasAleatorias[0] + ", " +
                        coordenadasAleatorias[1] + ")");

                ultimoTableroJugado = jugadorPersona.getTablero();
            } while (aciertoDisparo);
            cambiarTurno();
        }


    }

    public boolean turnoJugador() {
        return this.turnoJugador;
    }

    public void cambiarTurno() {
        turnoJugador = !turnoJugador;
    }

    public JugadorPersona getJugadorPersona() {
        return this.jugadorPersona;
    }

    public JugadorMaquina getJugadorMaquina() {
        return this.jugadorMaquina;
    }

    public boolean juegoTerminado() {
        return jugadorPersona.zeroBarcos() || jugadorMaquina.zeroBarcos();
    }

    public String ganador() {
        if (jugadorMaquina.zeroBarcos()) return jugadorPersona.getNombre();
        if (jugadorPersona.zeroBarcos()) return "Maquina";

        return null;
    }


    public Tablero getUltimoTableroJugado() {
        return this.ultimoTableroJugado;
    }

    public void disparoJugadorPersona(int fila, int columna) {
        jugadorMaquina.getTablero().recibirDisparo(fila, columna);

    }

    // Resta la cantidad de barcos iniciales (10) cuando jugadorMaquina hunde un barco
    public int cantBarcosPersona(Barco barco){
        if(barco != null && barco.barcoHundido()){
            System.out.println("⚠️ Barco hundido de persona detectado.");
            barcosP--;
            } else {
            System.out.println("🚫 NO se hundió ningún barco. TurnoJugador=" + turnoJugador + " | Barco=" + barco + " | Hundido=" + (barco != null ? barco.barcoHundido() : "null"));
        }
        return barcosP;
    }

    // Resta la cantidad de barcos iniciales (10) cuando jugadorPersona hunde un barco
    public int cantBarcosMaquina(Barco barco){
        if(barco != null && barco.barcoHundido()){
                barcosM--;
        }
        return barcosM;
    }


}
