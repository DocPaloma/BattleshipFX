package com.example.battleship.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the board of both players, a 10x10 grid where ships can be placed and
 * shots are received. It extends Observable so the board can notify when a cell is interacted with,
 * to show an image
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase representa el Tablero de ambos jugadores, cuadricula de 10x10 en donde se puede crear la estrategia
// de barcos y se reciben disparos. Se extiende de Observable para que el tablero avise cuando interactuan con
// una celda para que se muestre una imagen
public class Tablero extends Observable{

    public static final int tamano = 10; // El tablero sera de 10x10

    private Barco[][] celdas; // matriz donde iran objetos de tipo Barco
    private List<Barco> barcosColocados; // Para saber que barcos estan en juego o puestos en tablero

    private boolean[][] disparoRealizado = new boolean[tamano][tamano]; // matriz booleana donde se guardan los lugares donde se disparo


    /**
     * The constructor sets up the board by creating a 10x10 matrix that stores Barco objects,
     * and creates and initializes an empty list
     */
    // El constructor prepara el tablero en donde se crea una matriz 10x10 que alamcena Barco, crea e inicializa
    // una lista vacia
    public Tablero() {
        celdas = new Barco[tamano][tamano]; // matriz vacia de 10x10
        barcosColocados = new ArrayList<>(); // Lista vacia donde se pondran los barcos
    }


    /**
     * This method places a ship in the given position with an orientation, checks that it doesn't
     * go out of the board and that it doesn't overlap another ship
     *
     * @param barco
     * @param fila
     * @param columna
     * @param vertical
     * @return true or false
     */
    // Este metodo coloca un barco en la posicion que se le dio con una orientacion, valida sino se sale del
    // tablero y que no se ponga encima de otro
    public boolean colocarBarco(Barco barco, int fila, int columna, boolean vertical){
        int tamanoBarco = barco.getTamano(); // tomamos el tamaño del barco y lo guardamos


        // Verificar limites del tablero y choques de barcos
        for (int i = 0; i < tamanoBarco; i++) { // Recorre el tamaño de celdas que ocupa el barco
            // Se da una ubicacion en donde estara el barco, segun su orientacion y tamaño se extiende hacia abajo o a la derecha
            int f = fila + (vertical ? i : 0); // Si es horizontal fila se mantiene fija y la columna avanza a la derecha
            int c = columna + (vertical ? 0 : i); // Si no es horizontal columna se mantiene fija y fila avanza hacia abajo

            // Esta validacion es para que el barco no se salga del tablero
            if (f < 0 || f >= tamano || c < 0 || c >= tamano){
                return false; // El barco esta fuera del tablero
            }
            if (celdas[f][c] != null){
                return false; // Ya un barco esta ocupando este espacio
            }
        }


        // Esta parte coloca el barco despues de validar que si se puede}
        for (int i = 0; i < tamanoBarco; i++) {
            int f = fila + (vertical ? i : 0);
            int c = columna + (vertical ? 0 : i);
            celdas[f][c] = barco; // Añade el barco y ocupa esa ubicacion en la matriz
        }

        barco.setPosicion(fila, columna);
        barco.setVerical(vertical); // Para modificar la orientacion del barco segun el parametro pasado
        barcosColocados.add(barco); // Se añade el barco a la lista

        return true; // Retorna true si se logro colocar
    }


    /**
    // Para obtener la lista de barcos colocados en el tablero
    public List<Barco> getBarcosColocados() {
        return barcosColocados;
    }
     **/


    /**
     * This method manages what happens when the player shoots at a cell. It marks the shot based
     * on what happened. If it hits a ship, it updates its state
     *
     * @param fila
     * @param columna
     * @return barco or null
     */
    // Este metodo gestiona lo que ocurre cuando el jugador dispara en una celda, se marca el disparo segun
    // lo que ocurrio, si impacto a un barco actualiza su estado
    public Barco recibirDisparo(int fila, int columna){
        disparoRealizado[fila][columna] = true; // Aqui se guarda el disparo que se hizo

        // Se verifica si en esa posicion hay un barco
        if(celdas[fila][columna] != null) {
            Barco barco = celdas[fila][columna]; // Accede a la matriz, la ubicacion en donde se disparo
            barco.recibirImpacto(); // el daño del barco aumenta 1 debido a que se le disparo
            if (barco.barcoHundido()) {
                // Si el barco ya esta hundido avisa al observador para que coloque los fuegos
                avisarObservadores("barco_hundido", new PosicionBarco(barco, this)); // this porque es en el que se esta en ese momento
            } else{
                // De lo contrario solo pone un impacto, una imagen de bomba
                avisarObservadores("impacto", new int[]{fila, columna});
            }
            return barco; // acerto en el disparo
        } else {
            // Si donde disparo no habia un barco entonces coloca una imagen de X
            avisarObservadores("agua", new int[]{fila, columna});
            return null; // Retorna null porque no le dio a un barco
        }
    }


    /**
     * Prints the board in the console with 0 (empty) and 1 (ship)
     */
    // Imprime el tablero en consola con 0 (vacío) y 1 (barco)
    public void imprimirTablero() {
        System.out.println("Estado del tablero:");
        for (int fila = 0; fila < tamano; fila++) {
            for (int columna = 0; columna < tamano; columna++) {
                if (celdas[fila][columna] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println(); // Nueva línea por fila
        }
        System.out.println(); // Línea extra para separar
    }


    /**
     * Method to copy the strategy that the player created to know if there is a ship at certain coordinates
     *
     * @param fila
     * @param columna
     * @return true or false
     */
    // Metodo para copiar la estrategia que el jugador creo para saber si hay un barco en ciertas coordenadas
    public boolean hayBarco(int fila, int columna) {
        return celdas[fila][columna] != null;
    }

    /**
     * Here we capture the image path according to the row and column where it is located
     *
     * @param fila
     * @param columna
     * @return
     */
    // Aqui capturamos la ruta de la imagen segun la fila y columna en la que se encuentre
    public String getRutaImagen(int fila, int columna) {
        return celdas[fila][columna].getRutaImagen();
    }


    /**
     * This method reads and returns the cells where a ship is located
     *
     * @param fila
     * @param columna
     * @return celdas[fila][columna]
     */
    // Este metodo lee y devuelve la celdas en las que esta ubicado un barco
    public Barco getBarco(int fila, int columna) {
        return celdas[fila][columna];
    }


    /**
     * This method checks if all the ships in the list are already sunk
     *
     * @return true or false
     */
    // Este metodo verifica si todos los barcos de la lista ya se encuentran hundidos
    public boolean zeroBarcos(){
        for(Barco barco :  barcosColocados){ // Recorre uno por uno los barcos de la lista
            if(!barco.barcoHundido()){ // Si hay barcos que aun no estan hundidos retorna false
                return false;
            }
        }
        return true; // De lo contrario si encuentra que todos los barcos estan hundidos retorna true
    }


    /**
     * This method checks if there was a shot at certain coordinates
     *
     * @param fila
     * @param columna
     * @return true or false
     */
    // Este metodo verifica si hubo un disparo en unas coordenadas determinadas
    public boolean huboDisparoAqui(int fila, int columna){
        return disparoRealizado[fila][columna];
    }


}
