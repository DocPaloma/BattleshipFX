package com.example.battleship.Models;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    public static final int tamano = 10; // El tablero sera de 10x10

    private Barco[][] celdas; // matriz donde iran objetos de tipo Barco
    private List<Barco> barcosColocados; // Para saber que barcos estan en juego o puestos en tablero

    // El constructor prepara el tablero
    public Tablero() {
        celdas = new Barco[tamano][tamano]; // matriz vacia de 10x10
        barcosColocados = new ArrayList<>(); // Lista vacia donde se pondran los barcos
    }

    // Este metodo intenta colocar un barco en la posicion que se le dio con una orientacion
    public boolean colocarBarco(Barco barco, int fila, int columna, boolean vertical){
        int tamanoBarco = barco.getTamano(); // tomamos el tamaño del barco y lo guardamos


        // Verificar limites del tablero y choques de barcos
        for (int i = 0; i < tamanoBarco; i++) { // Recorre el tamaño de celdas que ocupa el barco
            // Se da una ubicacion en donde estara el barco, segun su orientacion y tamaño se extiende hacia abajo o a la derecha
            int f = fila + (vertical ? i : 0); // Si es horizontal fila se mantiene fija y la columna avanza a la derecha
            int c = columna + (vertical ? 0 : i); // Si no es horizontal columna se mantiene fija y fila avanza hacia abajo

            if (f < 0 || f >= tamano || c < 0 || c >= tamano){
                return false; // El barco esta fuera del tablero
            }
            if (celdas[f][c] != null){
                return false; // Ya un barco esta ocupando este espacio
            }
        }


        // Esta parte coloca el barco despues de validar que si se puede}
        for  (int i = 0; i < tamanoBarco; i++) {
            int f = fila + (vertical ? i : 0);
            int c = columna + (vertical ? 0 : i);
            celdas[f][c] = barco; // Añade el barco y ocupa esa ubicacion en la matriz
        }

        barco.setPosicion(fila, columna); // Solo guarda el punto de inicio para poder luego calcular sus otras celdas segun tamaño y orientacion
        barco.setVerical(vertical); // Para modificar la orientacion del barco segun el parametro pasado
        barcosColocados.add(barco); // Se añade el barco a la lista

        return true;
    }


    // Para obtener la lista de barcos colocados en el tablero
    public List<Barco> getBarcosColocados() {
        return barcosColocados;
    }


    public boolean recibirDisparo(int fila, int columna){
        if(celdas[fila][columna] != null) { // Verifica si esa ubicacion esta ocupada
            Barco barco = celdas[fila][columna]; // Accede a la matriz, la ubicacion en donde se disparo
            barco.recibirImpacto(); // el daño del barco aumenta 1 debido a que se le disparo
            if (barco.estaHundido()) {
                barcosColocados.remove(barco);
            }
            return true; // acerto en el disparo
        } else {
            return false; // Fallo en el disparo
        }
    }



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



    // Metodo para copiar la estrategia que jugadorPersona creo en la primer pantalla
    public boolean hayBarcoEn(int fila, int columna) {
        return celdas[fila][columna] != null; // Ajusta según tu lógica
    }

}
