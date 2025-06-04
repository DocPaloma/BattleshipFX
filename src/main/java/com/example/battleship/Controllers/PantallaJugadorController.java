package com.example.battleship.Controllers;

import com.example.battleship.Models.AlertaIniciarJuego;
import com.example.battleship.Models.Barco;
import com.example.battleship.Models.JugadorMaquina;
import com.example.battleship.Models.JugadorPersona;
import com.example.battleship.Views.JuegoBatallaNavalView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.InputStream;

public class PantallaJugadorController {

    @FXML
    private FlowPane flowPaneContenedorBarcos;

    @FXML
    private GridPane gridPaneTableroJugador;

    @FXML
    private Label labelNombreJugador;

    @FXML
    private HBox hBoxPrincipal;

    private JugadorPersona jugadorPersona;
    private JugadorMaquina jugadorMaquina;
    private Barco barcoSeleccionado;
    private ImageView barcoSeleccionadoImageView; // para modificar visualmente la imagen del barco que se puso, ponerla trasparente
    private StackPane barcoVisualSeleccionado;
    private String rutaImagenseleccionada;





    public void initialize() throws IOException {
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        hBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");

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
        crearImagenesBarcos("/com/example/battleship/Images/destructor1.png", "destructor1", 2);
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

            // Aqui le damos la ruta de la imagen al barco
            barcoSeleccionado.setRutaImagen(rutaImagenseleccionada);

            gridPaneTableroJugador.requestFocus(); // Se llama a requestFocus para que en el gridPane se detecte cuando se presiona la tecla R
            imagenBarco.setOpacity(0.0);
        });

        // agrega la imagen al contenedor
        flowPaneContenedorBarcos.getChildren().add(imagenBarco);
    }

    private void agregarBarcoComoFigura2D(int fila, int columna) {
        int tamano = barcoSeleccionado.getTamano();
        boolean vertical = barcoSeleccionado.esVerical();

        int ancho = vertical ? 45 : 45 * tamano;
        int alto = vertical ? 45 * tamano : 45;

        ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(rutaImagenseleccionada)));

        Rectangle barcoRect = new Rectangle(ancho, alto);
        barcoRect.setFill(patron);
        barcoRect.setStroke(Color.BLACK);

        if (vertical) {
            barcoRect.setTranslateY((alto - 45) / 2.0); // Esto ya está bien para vertical
        } else {
            barcoRect.setTranslateX(0); // Esta línea es la que ajusta horizontalmente
        }

        for (Node node : gridPaneTableroJugador.getChildren()) {
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

    private void teclado(KeyEvent event) {
        if (event.getCode() == KeyCode.R && barcoSeleccionado != null) {
            barcoSeleccionado.setVerical(!barcoSeleccionado.esVerical());
        }
    }

    @FXML
    void onActionBotonEstrategiaLista(ActionEvent event) throws IOException {
        AlertaIniciarJuego alert =  new AlertaIniciarJuego();

        boolean confirmacion = alert.mostrarAlertaDeConfirmacion
                ("Alerta de iniciar juego", "Esta es una ventana de alerta", "Deseas iniciar la partida???");
        if (confirmacion) {
            jugadorMaquina = new JugadorMaquina("Jugador Maquina");
            jugadorMaquina.colocarBarcosAleatoriamente();

            JuegoBatallaNavalView juegoBatallaNavalView = JuegoBatallaNavalView.getInstance();
            juegoBatallaNavalView.getController().setJugador(jugadorPersona);
            juegoBatallaNavalView.getController().mostrarNombreJugador2();
            juegoBatallaNavalView.getController().setJugadorMaquina(jugadorMaquina);

            juegoBatallaNavalView.show();
        }
    }

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
