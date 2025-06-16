package com.example.battleship.Models;

/**
 * This class is an interface for alerts
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
public interface AlertaInterface {

    /**
     * This is the only method it handles and that classes implementing it must have
     *
     * @param tittle
     * @param header
     * @param content
     * @return true
     */
    boolean mostrarAlertaDeConfirmacion(String tittle, String header, String content);
}
