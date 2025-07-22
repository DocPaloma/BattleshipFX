package com.example.battleship.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents the computer player. It extends from Player, has a name, a board, places ships
 * randomly, chooses coordinates randomly, shoots, and checks if it has no ships left
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase representa al jugadorMaquina, se extiende de Jugador, cuenta con un nombre, un tablero, coloca
// barcos aleatoriamente, elige coordenadas aleatoriamnete, dispara y determina si se queda sin barcos
public class JugadorMaquina extends Jugador {

    private Random random = new Random();
    private List<String> coordenadasEscogidas = new ArrayList<>();

    /**
     * Constructor that calls the parent class constructor using super
     *
     * @param nombre
     */
    // Constructor el cual llama al constructor de la clase padre mediante el super
    public JugadorMaquina(String nombre) {
        super("Maquina");
    }


    /**
     * This method places the ships randomly, stores all the ships in a list, selects one at random,
     * has 100 attempts to place it, and then removes it whether it was placed or not
     */
    // Este metodo permite colocar los barcos de manera aleatoria, guarda todos los barcos en una lista,
    // selecciona uno al azar, tiene 100 intentos para colocarlo y despues lo elimina si lo logro o no
    public void colocarBarcosAleatoriamente() {
        List<Barco> barcosDisponibles = crearListaBarcosConImagenes(); // Lista de barcos que estan disponibles

        // Hasta que no queden barcos en la lista, por eso se usa el while
        while (!barcosDisponibles.isEmpty()) {
            // Seleccionar un barco aleatorio de los disponibles
            int indiceBarco = random.nextInt(barcosDisponibles.size()); // Escoge un indice de esa lista (un barco)
            Barco barco = barcosDisponibles.get(indiceBarco); // El barco escogido lo guarda en variable tipo Barco

            boolean colocado = false; // Inicia en false porque aun no lo ha colocado en el tablero
            int intentos = 0;
            final int maxIntentos = 100; // Para evitar bucles infinitos

            // Hasta que colocado sea true y no exceda el maximo de intentos
            while (!colocado && intentos < maxIntentos) {
                boolean vertical = random.nextBoolean(); // Escoge aleatoriamente la orientacion
                int fila = random.nextInt(10); // Escoge aleatoriamente la fila
                int columna = random.nextInt(10); // Escoge aleatoriamente la columna

                barco.setVerical(vertical); // Modifica la orientacion del barco

                // Aqui es donde empieza a colocar el barco
                if (getTablero().colocarBarco(barco, fila, columna, vertical)) {
                    barco.setPosicionInicio(fila, columna); // Modifica la posicion inicial del barco
                    colocado = true;
                    // Eliminar el barco de la lista una vez colocado
                    barcosDisponibles.remove(indiceBarco);

                    System.out.println("Barco colocado: " + barco.getNombre() +
                            " (" + barco.getTamano() + " casillas) en " +
                            "[" + fila + "," + columna + "] " +
                            (vertical ? "VERTICAL" : "HORIZONTAL"));
                    imprimirTableroBinario(); // Imprimi los espacios que ocupo el barco, 1 ocupado, 0 desocupado
                }
                intentos++;
            }

            // Si se superan o igualan el numero maximo de intentos se elimina el barco de la lista
            if (intentos >= maxIntentos) {
                // Si no se pudo colocar después de muchos intentos, quitar el barco de la lista
                barcosDisponibles.remove(indiceBarco);
            }
        }
    }


    /**
     * This method prints the board as ships are added to see the occupied spots
     */
    // Este metodo imprimi el tablero a medida que se van añadiendo los barcos para ver los lugares ocupados
    private void imprimirTableroBinario() {
        System.out.println("   0 1 2 3 4 5 6 7 8 9"); // Encabezado columnas

        for (int fila = 0; fila < 10; fila++) {
            System.out.print(fila + " "); // Encabezado filas
            for (int columna = 0; columna < 10; columna++) {
                Barco barco = getTablero().getBarco(fila, columna);
                System.out.print(barco != null ? "1 " : "0 ");
            }
            System.out.println();
        }
    }


    /**
     * This method adds the ships to a list with their name, size, and path
     *
     * @return List barcosDisponibles
     */
    // Este metodo permite añadir los barcos a una lista con su nombre, tamaño y ruta
    private List<Barco> crearListaBarcosConImagenes(){
        List<Barco> barcosDisponibles = new ArrayList<>();

        Barco barco1 = new Barco("fragata", 1);
        barco1.setFigura(new FiguraFragata()); // Asignar figura
        barcosDisponibles.add(barco1);

        Barco barco2 = new Barco("fragata", 1);
        barco2.setFigura(new FiguraFragata());
        barcosDisponibles.add(barco2);

        Barco barco3 = new Barco("fragata", 1);
        barco3.setFigura(new FiguraFragata());
        barcosDisponibles.add(barco3);

        Barco barco4 = new Barco("fragata", 1);
        barco4.setFigura(new FiguraFragata());
        barcosDisponibles.add(barco4);

        Barco destructor1 = new Barco("destructor", 2);
        destructor1.setFigura(new FiguraDesctructor());
        barcosDisponibles.add(destructor1);

        Barco destructor2 = new Barco("destructor", 2);
        destructor2.setFigura(new FiguraDesctructor());
        barcosDisponibles.add(destructor2);

        Barco destructor3 = new Barco("destructor", 2);
        destructor3.setFigura(new FiguraDesctructor());
        barcosDisponibles.add(destructor3);

        Barco submarino1 = new Barco("submarino", 3);
        submarino1.setFigura(new FiguraSubmarino());
        barcosDisponibles.add(submarino1);

        Barco submarino2 = new Barco("submarino", 3);
        submarino2.setFigura(new FiguraSubmarino());
        barcosDisponibles.add(submarino2);

        Barco portaAviones = new Barco("portaAviones", 4);
        portaAviones.setFigura(new FiguraPortaAviones());
        barcosDisponibles.add(portaAviones);
        return barcosDisponibles;
    }

    /**
     * This method selects random coordinates that do not repeat
     *
     * @return int[fila][columna]
     */
    // Este metodo permite escoger coordenadas al azar y que no se repitan
    public int[] elegirCoordenadasAleatorias(){
        Random random = new Random();
        int fila;
        int columna;
        String coordenadaElegida;

        do{
            fila = random.nextInt(10);
            columna = random.nextInt(10);
            coordenadaElegida = fila + "," + columna;
        } while(coordenadasEscogidas.contains(coordenadaElegida));
        coordenadasEscogidas.add(coordenadaElegida);
        return new int[]{fila, columna};
    }

    /**
     * his method allows the jugadorMaquina to shoot at the enemy board (jugadorPersona)
     *
     * @param enemigo
     * @param fila
     * @param columna
     * @return true or false
     */
    // Este metodo le permite al jugadorMaquina que le dispare al tablero enemigo (jugadorPersona)
    @Override
    public boolean disparar(Tablero enemigo, int fila, int columna) {
        Barco resultado = enemigo.recibirDisparo(fila, columna);
        return resultado != null;
    }

    /**
     * This method checks if the jugadorMaquina has no ships left
     *
     * @return true or false
     */
    // Este metodo permite saber si jugadorMaquina se quedo sin barcos
    public boolean zeroBarcos(){
        return getTablero().zeroBarcos();
    }
}
