package com.example.battleship.Controllers;

import com.example.battleship.Models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

/**
 * This class controls and visually updates the JuegoBatallaNavalView interface based
 * on what happens in the game
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
public class JuegoBatallaNavalController implements Observer {

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

    private AlertaInterface alerta = new AlertaIniciarJuego();
    private FuncionamientoJuego funcionamientoJuego;

    private StackPane[][] celdasJugadorPersona = new StackPane[10][10];
    private StackPane[][] celdasJugadorMaquina = new StackPane[10][10];

    @FXML
    private Label labelBarcosM;

    @FXML
    private Label labelBarcosP;

    /**
     * Saves and updates the JugadorPersona instance and keeps the board current
     *
     * @param jugador representa al jugadorPersona
     */
    public void setJugador(JugadorPersona jugador) {
        this.jugador = jugador;
        this.jugador.getTablero().añadirObservador(this);
        mostrarTableroJugador();
    }

    // Método para mostrar el tablero de la máquina
    public void setJugadorMaquina(JugadorMaquina jugadorMaquina) {
        this.jugadorMaquina = jugadorMaquina;
        this.jugadorMaquina.getTablero().añadirObservador(this);
        tableroJugadorMaquinaDisparos();
        eventosDisparosJugadores();
    }

    @FXML
    public void initialize() {
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        hBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");


        gridPaneTableroJugador2.setVisible(true);
        //tableroJugadorMaquinaDisparos();
    }



    private void mostrarTableroJugador(){
        // Se limpia el tablero para que ingrese
        gridPaneTableroJugador2.getChildren().clear();

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true);
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
                celdasJugadorPersona[fila][columna] = celda;
            }
        }
    }


    // Metodo para mostrar los barcos del jugadorMaquina
    private void mostrarTableroMaquina(){
        gridPaneTableroMaquina.getChildren().clear(); // Limpia el tablero y coloca los barcos que estan para mantenerlo actualizado

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true);
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
                celdasJugadorMaquina[fila][columna] = celda;
            }
        }
    }

    private void tableroJugadorMaquinaDisparos() {
        gridPaneTableroMaquina.getChildren().clear(); // Limpia el tablero para mantener actualizado

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true);
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");


                if(jugadorMaquina.getTablero().huboDisparoAqui(fila, columna)){
                    if(jugadorMaquina.getTablero().getBarco(fila, columna)!=null){

                       Image bomba = new Image(getClass().getResource("/com/example/battleship/Images/bomba.png").toExternalForm());
                       ImageView image = new ImageView(bomba);
                       image.setFitHeight(40);
                       image.setFitWidth(40);
                       celda.getChildren().add(image);


                        // Esto es lo nuevo: añadir fueguito
                        Image fuego = new Image(getClass().getResource("/com/example/battleship/Images/fuego.png").toExternalForm()); // asegúrate que sea .gif o .png
                        ImageView imageFuego = new ImageView(fuego);
                        imageFuego.setFitHeight(35);
                        imageFuego.setFitWidth(35);
                        celda.getChildren().add(imageFuego); // Lo añade encima de la bomba
                    } else{

                        Image X = new Image(getClass().getResource("/com/example/battleship/Images/X.png").toExternalForm());
                        ImageView image = new ImageView(X);
                        image.setFitHeight(40);
                        image.setFitWidth(40);
                        celda.getChildren().add(image);
                    }
                }


                gridPaneTableroMaquina.add(celda, columna, fila);
                celdasJugadorMaquina[fila][columna] = celda;
            }
        }

        eventosDisparosJugadores();
    }


    private void eventosDisparosJugadores(){
        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                final int filaFinal = fila;
                final int columnaFinal = columna;

                StackPane celda = celdasJugadorMaquina[fila][columna];
                celda.setPickOnBounds(true);
                celda.setOnMouseClicked(event -> {
                    if(funcionamientoJuego.turnoJugador() && !funcionamientoJuego.juegoTerminado()){
                        funcionamientoJuego.disparoSegunTurno(filaFinal, columnaFinal);
                        mostrarBarcosJugador(filaFinal, columnaFinal);

                        if(!funcionamientoJuego.turnoJugador() && !funcionamientoJuego.juegoTerminado()){
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
                                int[] coordenadas = jugadorMaquina.elegirCoordenadasAleatorias();
                                funcionamientoJuego.disparoSegunTurno(coordenadas[0], coordenadas[1]);
                                Timeline delayLabelUpdate = new Timeline(new KeyFrame(Duration.millis(200), ev -> {
                                    mostrarBarcosQueLeQuedanJP(coordenadas[0], coordenadas[1]);
                                }));
                                delayLabelUpdate.setCycleCount(1);
                                delayLabelUpdate.play();
                            }));
                            timeline.setCycleCount(1);
                            timeline.play();
                        }
                    }
                });
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
            tableroJugadorMaquinaDisparos();
            //tableroMaquinaVisible = false;
        } else {
            mostrarTableroMaquina(); // Muestra los barcos
            //tableroMaquinaVisible = true;
        }

        tableroMaquinaVisible = !tableroMaquinaVisible;
    }


    public void mostrarNombreJugador2(){
        labelNombre2.setText(jugador.getNombre());
    }

    private void mostrarBomba(int fila, int columna, boolean esMaquina){
        Image bomba = new Image(getClass().getResource("/com/example/battleship/Images/bomba.png").toExternalForm());
        ImageView view = new ImageView(bomba);
        view.setFitHeight(40);
        view.setFitWidth(40);
        if(esMaquina){
            celdasJugadorMaquina[fila][columna].getChildren().add(view);
        } else{
            celdasJugadorPersona[fila][columna].getChildren().add(view);
        }
    }

    private void mostrarX(int fila, int columna,  boolean esMaquina){
        Image x = new Image(getClass().getResource("/com/example/battleship/Images/X.png").toExternalForm());
        ImageView view = new ImageView(x);
        view.setFitHeight(40);
        view.setFitWidth(40);
        if(esMaquina){
            celdasJugadorMaquina[fila][columna].getChildren().add(view);
        } else{
            celdasJugadorPersona[fila][columna].getChildren().add(view);
        }
    }

    private void mostrarFuego(Barco barco, Tablero tablero){
        Image fuego  = new Image(getClass().getResource("/com/example/battleship/Images/fuego.png").toExternalForm());

        int filaInicio = barco.getFila();
        int columnaInicio = barco.getColumna();
        boolean vertical = barco.esVerical();
        int tamano = barco.getTamano();
        //ImageView view = new ImageView(fuego);
        //view.setFitHeight(40);
        //view.setFitWidth(40);

        for(int i = 0; i < tamano; i++){
            int fila = filaInicio + (vertical ? i : 0);
            int columna = columnaInicio + (vertical ? 0 : i);

            StackPane celda;
            if(tablero == jugadorMaquina.getTablero()){
                celda = celdasJugadorMaquina[fila][columna]; // Barco de la maquina hundido
                //celdasJugadorMaquina[fila][columna].getChildren().add(new ImageView(fuego));
            } else{
                celda = celdasJugadorPersona[fila][columna]; // Barco del jugador hundido
                //celdasJugadorPersona[fila][columna].getChildren().add(new ImageView(fuego));
            }

            celda.getChildren().clear();
            ImageView  view = new ImageView(fuego);
            view.setFitHeight(40);
            view.setFitWidth(40);
            celda.getChildren().add(view);
        }
    }

    @Override
    public void actualizar(String evento, Object data) {
        switch (evento){
            case "impacto":
                int[] coordenadas1 = (int[]) data;

                // Aqui se verifica a que tablero se impacta
                boolean disparoDeJugador = funcionamientoJuego.turnoJugador();

                mostrarBomba(coordenadas1[0], coordenadas1[1], disparoDeJugador);
                break;
            case "agua":
                int[] coords2 = (int[]) data;

                boolean disparoDeJugadorAgua = funcionamientoJuego.turnoJugador();

                mostrarX(coords2[0], coords2[1], disparoDeJugadorAgua);
                break;
            case "barco_hundido":
                PosicionBarco info = (PosicionBarco) data;
                // Permite saber a quien le pertenece el barco hundido
                boolean barcoMaquina = info.getTablero() == jugadorMaquina.getTablero();
                mostrarFuego(info.getBarco(), info.getTablero());
                break;
        }


        // En esta parte se verifica si el juego finalizo
        if(funcionamientoJuego != null && funcionamientoJuego.juegoTerminado()){
            String ganador = funcionamientoJuego.ganador();
            mostrarAlertaFinDelJuego(ganador);
        }
    }

    private void mostrarAlertaFinDelJuego(String ganador){
        javafx.application.Platform.runLater(() -> {
           boolean continuar = alerta.mostrarAlertaDeConfirmacion("Fin del juego", "Juego terminado!!!", "El ganador es: " + ganador);

           if(continuar){
               System.out.println("El ganador es: " + ganador);
           }
        });
    }

    public void setFuncionamientoJuego(FuncionamientoJuego funcionamientoJuego) {
        this.funcionamientoJuego = funcionamientoJuego;
    }


    // este metodo decidi crearlo porque antes lo tenia en initialize y al abrir esta pantalla me salia que el tablero
    // de la maquina era vacio (un error), entonces lo cree para asegurarme de que los datos del tablero ya habian sido creados correctamente
    public void alIniciarJuego(){
        tableroJugadorMaquinaDisparos();
        mostrarNombreJugador2();
    }


    // Actualiza los barcos de jugadorMaquina cuando la persona los hunde
    public void mostrarBarcosJugador(int fila, int columna){
        Barco barcoDisparado = jugadorMaquina.getTablero().getBarco(fila, columna);
        int barcosRestantes = funcionamientoJuego.cantBarcosMaquina(barcoDisparado);
        labelBarcosM.setText("maquina: " + barcosRestantes);
    }

    // Actualiza la cantidad de barcos de jugadorPersona cuando la maquina los hunde
    public void mostrarBarcosQueLeQuedanJP(int fila, int columna){
        Barco barcoDisparado = jugador.getTablero().getBarco(fila, columna);
        int barcosRestantes = funcionamientoJuego.cantBarcosPersona(barcoDisparado);
        labelBarcosP.setText("persona: " + barcosRestantes);
    }

}
