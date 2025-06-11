package com.example.battleship.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JugadorMaquina extends Jugador {

    private Random random = new Random();

    private List<String> coordenadasEscogidas = new ArrayList<>();

    public JugadorMaquina(String nombre) {
        super("Maquina");
    }


    public void colocarBarcosAleatoriamente() {
        List<Barco> barcosDisponibles = crearListaBarcosConImagenes();

        // Mientras aún haya barcos por colocar
        while (!barcosDisponibles.isEmpty()) {
            // Seleccionar un barco aleatorio de los disponibles
            int indiceBarco = random.nextInt(barcosDisponibles.size());
            Barco barco = barcosDisponibles.get(indiceBarco);

            boolean colocado = false;
            int intentos = 0;
            final int MAX_INTENTOS = 100; // Para evitar bucles infinitos

            while (!colocado && intentos < MAX_INTENTOS) {
                boolean vertical = random.nextBoolean();
                int fila = random.nextInt(10);
                int columna = random.nextInt(10);

                barco.setVerical(vertical);

                if (getTablero().colocarBarco(barco, fila, columna, vertical)) {
                    barco.setPosicionInicio(fila, columna);
                    colocado = true;
                    // Eliminar el barco de la lista una vez colocado
                    barcosDisponibles.remove(indiceBarco);

                    System.out.println("Barco colocado: " + barco.getNombre() +
                            " (" + barco.getTamano() + " casillas) en " +
                            "[" + fila + "," + columna + "] " +
                            (vertical ? "VERTICAL" : "HORIZONTAL"));
                    imprimirTableroBinario();
                }
                intentos++;
            }

            if (intentos >= MAX_INTENTOS) {
                // Si no se pudo colocar después de muchos intentos, quitar el barco de la lista
                barcosDisponibles.remove(indiceBarco);
            }
        }
    }


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


    private List<Barco> crearListaBarcosConImagenes(){
        List<Barco> barcosDisponibles = new ArrayList<>();

        barcosDisponibles.add(new Barco("fragata1", 1, "/com/example/battleship/Images/fragata1.png"));
        barcosDisponibles.add(new Barco("fragata2", 1, "/com/example/battleship/Images/fragata2.png"));
        barcosDisponibles.add(new Barco("fragata3", 1, "/com/example/battleship/Images/fragata3.png"));
        barcosDisponibles.add(new Barco("fragata4", 1, "/com/example/battleship/Images/fragata4.png"));

        barcosDisponibles.add(new Barco("destructor1", 2, "/com/example/battleship/Images/destructor1.png"));
        barcosDisponibles.add(new Barco("destructor2", 2, "/com/example/battleship/Images/destructor2.png"));
        barcosDisponibles.add(new Barco("destructor3", 2, "/com/example/battleship/Images/destructor3.png"));

        barcosDisponibles.add(new Barco("submarino1", 3, "/com/example/battleship/Images/submarino1.png"));
        barcosDisponibles.add(new Barco("submarino2", 3, "/com/example/battleship/Images/submarino2.png"));

        barcosDisponibles.add(new Barco("portaAviones", 4, "/com/example/battleship/Images/portaAviones.png"));
        return barcosDisponibles;
    }

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
        //while(getTablero().huboDisparoAqui(fila, columna));
        coordenadasEscogidas.add(coordenadaElegida);
        return new int[]{fila, columna};
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
