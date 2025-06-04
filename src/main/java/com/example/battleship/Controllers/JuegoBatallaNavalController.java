package com.example.battleship.Controllers;

import com.example.battleship.Models.Barco;
import com.example.battleship.Models.JugadorMaquina;
import com.example.battleship.Models.JugadorPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class JuegoBatallaNavalController {

    @FXML
    private GridPane gridPaneTableroJugador2;

    @FXML
    private GridPane gridPaneTableroMaquina;

    @FXML
    private Label labelNombre2;

    @FXML
    private HBox hBoxPrincipal;

    private boolean tableroMaquinaVisible = false;

    private JugadorPersona jugador;
    private JugadorMaquina jugadorMaquina;

    public void setJugador(JugadorPersona jugador) {
        this.jugador = jugador;
        mostrarTableroJugador();
    }

    // Método para mostrar el tablero de la máquina
    public void setJugadorMaquina(JugadorMaquina jugadorMaquina) {
        this.jugadorMaquina = jugadorMaquina;
        inicializarTableroMaquinaVacio();
    }

    @FXML
    public void initialize() {
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        hBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");


        gridPaneTableroJugador2.setVisible(true);
        inicializarTableroMaquinaVacio();
    }



    private void mostrarTableroJugador(){
        // Se limpia el tablero para que ingrese
        gridPaneTableroJugador2.getChildren().clear();

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                StackPane celda = new StackPane();
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");

                // Verifica si hay un barco en la celda en la que se encuentra
                if(jugador.getTablero().hayBarco(fila, columna)){
                    String ruta = jugador.getTablero().getRutaImagen(fila, columna);

                    ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(ruta)));
                    Rectangle barcoRectangle = new Rectangle(40, 40);
                    barcoRectangle.setFill(patron);
                    celda.getChildren().add(barcoRectangle);
                }

                gridPaneTableroJugador2.add(celda, columna, fila);
            }
        }
    }


    // Metodo para mostrar los barcos del jugadorMaquina
    private void mostrarTableroMaquina(){
        gridPaneTableroMaquina.getChildren().clear(); // Limpia el tablero y coloca los barcos que estan para mantenerlo actualizado

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                StackPane celda = new StackPane();
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");

                if(jugadorMaquina.getTablero().hayBarco(fila, columna)){
                    String ruta = jugadorMaquina.getTablero().getRutaImagen(fila, columna);

                    ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(ruta)));
                    Rectangle barcoRectangle = new Rectangle(40, 40);
                    barcoRectangle.setFill(patron);
                    celda.getChildren().add(barcoRectangle);
                }

                gridPaneTableroMaquina.add(celda, columna, fila);
            }
        }
    }

    private void inicializarTableroMaquinaVacio() {
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                StackPane celda = new StackPane();
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");
                gridPaneTableroMaquina.add(celda, columna, fila);
            }
        }
    }


    @FXML
    void onActionBotonMostrarTableroMaquina(ActionEvent event) {
        if (jugadorMaquina == null) {
            System.err.println("JugadorMaquina aún no ha sido inicializado.");
            return;
        }

        // Limpiar completamente antes de agregar nuevas celdas
        gridPaneTableroMaquina.getChildren().clear();

        if (tableroMaquinaVisible) {
            inicializarTableroMaquinaVacio(); // Oculta los barcos
            //tableroMaquinaVisible = false;
        } else {
            mostrarTableroMaquina(); // Muestra los barcos
            //tableroMaquinaVisible = true;
        }

        tableroMaquinaVisible = !tableroMaquinaVisible;
    }


    private boolean esInicioDeBarco(int fila, int columna, Barco barco){
        if (barco == null) return false;

        if(barco.esVerical()){
            return fila == 0 || jugador.getTablero().getBarco(fila - 1, columna) != barco;
        } else{
            return columna == 0 || jugador.getTablero().getBarco(fila, columna - 1) != barco;
        }
    }

    public void mostrarNombreJugador2(){
        labelNombre2.setText(jugador.getNombre());
    }



    /**
    private void mostrarTableroJugador(){
        // Se limpia el tablero para que ingrese
        gridPaneTableroJugador2.getChildren().clear();

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                StackPane celda = new StackPane();
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black; -fx-background-color: lightblue;");

                Barco barcoEnCelda = jugador.getTablero().getBarco(fila, columna);
                if(barcoEnCelda != null && barcoEnCelda.esInicio(fila, columna)) {
                    int tamano = barcoEnCelda.getTamano();
                    boolean vertical = barcoEnCelda.esVerical();

                    int ancho = vertical ? 45 : 45 * tamano;
                    int alto = vertical ? 45 * tamano : 45;

                    String ruta = jugador.getTablero().getRutaImagen(fila, columna);
                    ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(ruta)));

                    Rectangle barcoRect = new Rectangle(ancho, alto);
                    barcoRect.setFill(patron);
                    barcoRect.setStroke(Color.BLACK);

                    if (vertical) {
                        barcoRect.setTranslateY((alto - 45) / 2.0); // Esto ya está bien para vertical
                    } else {
                        barcoRect.setTranslateX(0); // Esta línea es la que ajusta horizontalmente
                    }

                    celda.getChildren().add(barcoRect);

                }
                gridPaneTableroJugador2.add(celda, columna, fila);
            }
        }
    }
     **/


    /**
    private void mostrarTableroJugador() {
        gridPaneTableroJugador2.getChildren().clear();

        // Primero: Dibujar todas las celdas vacías
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                StackPane celda = new StackPane();
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black; -fx-background-color: lightblue;");
                gridPaneTableroJugador2.add(celda, columna, fila);
            }
        }

        // Segundo: Dibujar los barcos solo en sus celdas de inicio
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                Barco barcoEnCelda = jugador.getTablero().getBarco(fila, columna);
                if (barcoEnCelda != null && barcoEnCelda.esInicio(fila, columna)) {
                    int tamano = barcoEnCelda.getTamano();
                    boolean vertical = barcoEnCelda.esVerical();

                    int ancho = vertical ? 45 : 45 * tamano;
                    int alto = vertical ? 45 * tamano : 45;

                    String ruta = barcoEnCelda.getRutaImagen();
                    ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(ruta)));

                    Rectangle barcoRect = new Rectangle(ancho, alto);
                    barcoRect.setFill(patron);
                    barcoRect.setStroke(Color.BLACK);

                    if (vertical) {
                        barcoRect.setTranslateY((alto - 45) / 2.0); // Centrado vertical
                    } else {
                        barcoRect.setTranslateX(0); // Alineado a la izquierda
                    }

                    // Obtener la celda de inicio y añadir el rectángulo
                    for (Node node : gridPaneTableroJugador2.getChildren()) {
                        Integer nodeRow = GridPane.getRowIndex(node);
                        Integer nodeCol = GridPane.getColumnIndex(node);
                        if (nodeRow == null) nodeRow = 0;
                        if (nodeCol == null) nodeCol = 0;

                        if (nodeRow == fila && nodeCol == columna) {
                            StackPane celdaInicial = (StackPane) node;
                            celdaInicial.getChildren().add(barcoRect);
                            break;
                        }
                    }
                }
            }
        }
    }
     **/

}
