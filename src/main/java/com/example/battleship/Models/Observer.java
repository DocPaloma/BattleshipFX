package com.example.battleship.Models;

/**
 * The Observer pattern is used to update the visual board depending on where the shot landed
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Se esta usando el patron Observer para la actualizacion del tablero visual segun el disparo donde cayo
// Esta es la interfaz que escucha los eventos
public interface Observer {

    /**
     * The only method keeps the observers updated and lets them react depending on the case
     *
     * @param evento
     * @param data
     */
    // El unico metodo permite mantener actualizado a los observadores y que reaccionen segun el caso
    void actualizar(String evento, Object data);
}
