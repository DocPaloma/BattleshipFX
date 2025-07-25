package com.example.battleship.persistence;

import com.example.battleship.Models.FuncionamientoJuego;
import java.io.*;

/**
 * Class that is in charge of managing the save and load of the game data
 */
public class SaveManager {

    /**
     * Method in charge of saving the data
     * @param fj: game logic and game data
     * @param rutaArchivo: route where the data is stored
     */
    public static void saveGame(FuncionamientoJuego fj, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))){
            oos.writeObject(fj);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * Method that is in charge of loading the data
     * @param rutaArchivo: route where the data is store
     * @return FuncionamientoJuego which contains the game info.
     */
    public static FuncionamientoJuego loadGame( String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))){
            return (FuncionamientoJuego) ois.readObject();
        }
        catch (IOException | ClassNotFoundException ioe){
            ioe.printStackTrace();
            return null;
        }
    }
}
