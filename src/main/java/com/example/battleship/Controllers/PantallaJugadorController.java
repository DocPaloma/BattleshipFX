package com.example.battleship.Controllers;

import com.example.battleship.Models.Barco;
import com.example.battleship.Models.JugadorPersona;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PantallaJugadorController {

    @FXML
    private FlowPane flowPaneContenedorBarcos;

    @FXML
    private GridPane gridPaneTableroJugador;

    @FXML
    private Label labelNombreJugador;

    private JugadorPersona jugadorPersona;
    private Barco barcoSeleccionado;
    private ImageView barcoSeleccionadoImageView; // para modificar visualmente la imagen del barco que se puso, ponerla trasparente
    private StackPane barcoVisualSeleccionado;
    private String rutaImagenseleccionada;





    public void initialize() throws IOException {
        jugadorPersona = new JugadorPersona("Jugador");

        crearTableroVisual();
        gridPaneTableroJugador.setOnKeyPressed(this::teclado);
        gridPaneTableroJugador.setFocusTraversable(true);
        cargarImagenesBarco();
    }



    private void crearTableroVisual(){
        // Aqui se crea el tablero visual
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                StackPane celda = new StackPane(); // Las celdas se crean StackPane porque permite mucha flexibilidad visual para poner imagenes encima sin borrar la anteriror y que sea facil
                celda.setPrefSize(45, 45);
                //celda.setStyle("-fx-background-color: lightblue;");

                // Guarda la posicion de la celda
                final int filaSeleccionada = fila;
                final int columnaSeleccionada = columna;



                celda.setOnMouseClicked(e -> {
                    if(barcoSeleccionado != null){
                        boolean colocarBarco = jugadorPersona.getTablero().colocarBarco(barcoSeleccionado, filaSeleccionada, columnaSeleccionada, barcoSeleccionado.esVerical());

                        if(colocarBarco){
                            agregarBarcoComoFigura2D(filaSeleccionada, columnaSeleccionada);
                            jugadorPersona.getTablero().imprimirTablero();
                            barcoSeleccionado = null;

                        }
                    }
                });
                gridPaneTableroJugador.add(celda, columna, fila);
            }
        }

    }

    private void cargarImagenesBarco(){
        // Cargar las 4 imagenes de las fragatas
        crearImagenesBarcos("/com/example/battleship/Images/fragata1.png", "fragata1", 1);
        crearImagenesBarcos("/com/example/battleship/Images/fragata2.png", "fragata2", 1);
        crearImagenesBarcos("/com/example/battleship/Images/fragata3.png", "fragata3", 1);
        crearImagenesBarcos("/com/example/battleship/Images/fragata4.png", "fragata4", 1);

        // Cargar las 3 imagenes de los destructores
        crearImagenesBarcos("/com/example/battleship/Images/desctructor1.png", "destructor1", 2);
        crearImagenesBarcos("/com/example/battleship/Images/destructor2.png", "destructor2", 2);
        crearImagenesBarcos("/com/example/battleship/Images/destructor3.png", "destructor3", 2);

        // Cargar las 2 imagenes de los submarinos
        crearImagenesBarcos("/com/example/battleship/Images/submarino1.png", "submarino1", 3);
        crearImagenesBarcos("/com/example/battleship/Images/submarino2.png", "submarino2", 3);

        // Cargar la imagen del portaAvion
        crearImagenesBarcos("/com/example/battleship/Images/portaAviones.png", "portaAviones", 4);
    }

    private void crearImagenesBarcos(String ruta, String nombre, int tamano) {
        InputStream imageStream = getClass().getResourceAsStream(ruta);
        ImageView imagenBarco = new ImageView(new Image(imageStream)); // Carga la imagen y la guarda en un ImageView

        // Crea el tamaño de la imagen
        imagenBarco.setFitHeight(tamano * 45); // Representa el alto
        imagenBarco.setFitWidth(45);

        // Evento al hacer clic sobre las imagenes, cada que se crea una imagen se crea con este evento
        imagenBarco.setOnMouseClicked(e -> {
            barcoSeleccionado = new Barco(nombre, tamano); // El barco seleccionado se le crea un objeto Barco con su nombre y tamaño
            barcoSeleccionado.setVerical(true); // Por defecto el barco se pone vertical a menos que se presione la letra R
            rutaImagenseleccionada = ruta; // Guarda la ruta de la imagen que se selecciono para usarla en el momento de crear las figuras 2D
            System.out.println("Ruta de imagen seleccionada: " + rutaImagenseleccionada);
            gridPaneTableroJugador.requestFocus(); // Se llama a requestFocus para que en el gridPane se detecte cuando se presiona la tecla R
        });

        // agrega la imagen al contenedor
        flowPaneContenedorBarcos.getChildren().add(imagenBarco);
    }

    private void agregarBarcoComoFigura2D(int fila, int columna) {
        int tamano = barcoSeleccionado.getTamano();
        boolean vertical = barcoSeleccionado.esVerical();
        int celda = 45;

        ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(rutaImagenseleccionada)));

        for (int i = 0; i < tamano; i++) {
            int f = vertical ? fila + i : fila;
            int c = vertical ? columna : columna + i;

            for (Node node : gridPaneTableroJugador.getChildren()) {
                Integer nodeRow = GridPane.getRowIndex(node);
                Integer nodeCol = GridPane.getColumnIndex(node);
                if (nodeRow == null) nodeRow = 0;
                if (nodeCol == null) nodeCol = 0;

                if (nodeRow == f && nodeCol == c) {
                    Rectangle rect = new Rectangle(celda, celda);
                    rect.setFill(patron);
                    rect.setStroke(Color.BLACK);

                    StackPane celdaPane = (StackPane) node;
                    celdaPane.getChildren().add(rect);
                    break;
                }
            }
        }
    }

    private void teclado(KeyEvent event) {
        if (event.getCode() == KeyCode.R && barcoSeleccionado != null) {
            barcoSeleccionado.setVerical(!barcoSeleccionado.esVerical());
        }
    }



























    /**

    // Cuando el usuario hace clic sobre una imagen de un barco crea un objeto tipo Barco para empezar a usarlo en la logica
    private void asignarEventoBarco(ImageView imagenBarcoSeleccionado, String nombreBarco, int tamanoBarco) {
        imagenBarcoSeleccionado.setOnMouseClicked(e -> {
           barcoSeleccionado = new Barco(nombreBarco, tamanoBarco); // Lo crea
           barcoSeleccionadoImageView = imagenBarcoSeleccionado; // Lo guarda para modificar su visualidad despues de usado
           barcoSeleccionado.setVerical(true); // Por defecto el barco se pone horizontal
           gridPaneTableroJugador.requestFocus(); // Esto habilita que detecte si se toca la letra R para el cambio de orientacion
        });
    }

    // Detecta si un jugador presiona la tecla R mientras tiene un barco seleccionado para cambiar la orientacion de barco
    private void teclado(KeyEvent event) {
        if(event.getCode() == KeyCode.R && barcoSeleccionado != null){
            boolean nuevaOrientacion = !barcoSeleccionado.esVerical();
            barcoSeleccionado.setVerical(nuevaOrientacion);
        }
    }

     **/




    /**
    private void agregarBarcoAlTablero(ImageView imageViewOriginal, Barco barco, int fila, int columna){

        int tamanoBarco = barco.getTamano();
        boolean vertical = barco.esVerical();

        // Modificacion de la dimension visual del barco segun la orientacion
        double ancho = vertical ? 45 : tamanoBarco * 45;
        double alto = vertical ? tamanoBarco * 45 : 45;


        ImageView copiaBarco = new ImageView(imageViewOriginal.getImage());
        // Asignacion de la dimension de los barcos
        copiaBarco.setFitWidth(ancho);
        copiaBarco.setFitHeight(alto);
        copiaBarco.setRotate(vertical ? 0 : 90);

        // Buscar la celda inicial
        for(Node node : gridPaneTableroJugador.getChildren()){
            Integer nodeFila = GridPane.getRowIndex(node);
            Integer nodeColumna = GridPane.getColumnIndex(node);



            // Por seguridad en caso de valores nulos (esquinas sin coordenadas explícitas)
            if (nodeFila == null) nodeFila = 0;
            if (nodeColumna == null) nodeColumna = 0;

            if (nodeFila == fila && nodeColumna == columna) {
                StackPane celda = (StackPane) node;
                celda.getChildren().add(copiaBarco); // Agregar imagen a esa celda
                break;
            }


        }
    }
     **/



    /**
     * This method receives a player of type JugadorPersona and stores it in the jugador atribute
     * @param jugador
     */
    public void setJugador(JugadorPersona jugador) {
        this.jugadorPersona = jugador;
    }

    /**
     * This method displays the name of JugadorPersona in label
     */
    public void mostrarNombreJugador(){
        labelNombreJugador.setText(jugadorPersona.getNombre());
    }
}
