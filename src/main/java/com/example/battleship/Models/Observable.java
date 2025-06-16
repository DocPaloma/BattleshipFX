package com.example.battleship.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class that notifies all observers when an event occurs
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta es la clase que avisa a todos los observadores cuando ocurre un evento
public abstract class Observable {

    private List<Observer> observadores = new ArrayList<>(); // Se crea una lista porque pueden haber muchos observadores al mismo tiempo y aqui se agregan

    /**
     * It is used to add an observer to the list, for example the board when it receives a shot
     *
     * @param observer
     */
    // Sirve para agregar un observador a la lista por ejemplo el tablero cuando recibe un disparo
    public void anadirObservador(Observer observer) {
        observadores.add(observer);
    }

    // Elimina los observadores de la lista cuando ya no se necesitan
    public void eliminarObservador(Observer observer) {
        observadores.remove(observer);
    }

    /**
     * This method is the essence of Observer; it tells all observers that something is happening
     *
     * @param evento
     * @param data
     */
    // Este metodo es la escencia de Observer, le dice a todos los observadores que algo esta pasando
    public void avisarObservadores(String evento, Object data){
        for(Observer observer : observadores){
            observer.actualizar(evento, data); // Actualiza todos los observadores de la lista
        }
    }


}
