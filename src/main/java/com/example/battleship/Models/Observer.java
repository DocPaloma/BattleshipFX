package com.example.battleship.Models;

// Se esta usando el patron Observer para la actualizacion del tablero visual segun el disparo donde cayo
// Esta es la interfaz que escucha los eventos
public interface Observer {
    void actualizar(String evento, Object data);
}
