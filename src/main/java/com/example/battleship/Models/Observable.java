package com.example.battleship.Models;

import java.util.ArrayList;
import java.util.List;

// Esta es la clase que avisa a todos los observadores cuando ocurre un evento
public abstract class Observable {

    private List<Observer> observadores = new ArrayList<>(); // Se crea una lista porque pueden haber muchos observadores al mismo tiempo y aqui se agregan

    // Sirve para agregar un observador a la lista por ejemplo el tablero cuando recibe un disparo o cuando se cambia de turno
    public void añadirObservador(Observer observer){
        observadores.add(observer);
    }

    // Elimina los observadores de la lista cuando ya no se necesitan
    public void eliminarObservador(Observer observer){
        observadores.remove(observer);
    }

    // Este metodo es la escencia de Oberver, le dice a todos los observadores que algo esta pasando
    public void avisarObservadores(String evento, Object data){
        for(Observer observer : observadores){
            observer.actualizar(evento, data);
        }
    }


}
