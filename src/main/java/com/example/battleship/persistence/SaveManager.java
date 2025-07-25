package com.example.battleship.persistence;

import com.example.battleship.Models.FuncionamientoJuego;
import java.io.*;

public class SaveManager {

    public static void saveGame(FuncionamientoJuego fj, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))){
            oos.writeObject(fj);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

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
