package com.example.battleship.Controllers;

import com.example.battleship.Models.*;
import com.example.battleship.Views.JuegoBatallaNavalView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
import java.util.List;

/**
 * This class keeps the PantallaJugadorView interface updated according to the interaction of jugadorPersona
 * when creating their strategy and when pressing the button to start the game
 *
 * @author vaneg
 * @author Alejandro Medina
 * @version 1.0
 */
// Esta clase mantiene actualizada la interface PantallaJugadorView segun la intaraccion de jugadorPersona
// cuando crea su estrategia y cuando presiona el boton para iniciar el juego
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
    private BarcoVisual barcoVisual;
    private ImageView barcoSeleccionadoImageView; // para modificar visualmente la imagen del barco que se puso, ponerla trasparente
    private StackPane barcoVisualSeleccionado;
    private String rutaImagenseleccionada;

    private StackPane[][] celdas = new StackPane[10][10]; // matriz 10x10 de StackPane


    /**
     * This method is the first one that runs when this screen starts. It sets a background image in the
     * main container, creates the first instance of jugadorPersona, creates the visual board, also enables
     * the keyboard to interact with the board, and loads the ship images
     *
     * @throws IOException
     */
    // Este metodo es el primero que se ejecuta cuando incia esta pantalla, coloca una imagen de fondo en el
    // contenedor principal, crea la primera instancia de jugadorPersona, crea el tablero visual, ademas activa
    // el teclado para que interactue con el tablero y carga las imagenes de los barcos
    public void initialize() throws IOException {
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        hBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");

        jugadorPersona = new JugadorPersona("Jugador");

        crearTableroVisual();
        gridPaneTableroJugador.setOnKeyPressed(this::teclado); // Permite que el tablero escuche las teclas que el usuario presiona
        gridPaneTableroJugador.setFocusTraversable(true); // Permite que el GridPane tenga el foco o sea donde se va a realizar una accion (Permite saber quien esta escuchando al teclado)
        cargarImagenesBarco();
    }


    /**
     * This method creates the 10x10 board and in each position creates a StackPane and adds it to the GridPane.
     * It saves the position that the user selects to place the ship and adds it
     */
    // Este metodo crea el tablero 10x10 y en cada posicion crea un StackPane y las añade al gridPane,
    // guarda la posicion que el usuairio escoge para colocar el barco y lo añade
    private void crearTableroVisual(){
        // Aqui se crea el tablero visual
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                StackPane celda = new StackPane(); // Las celdas se crean StackPane porque permite mucha flexibilidad visual para poner imagenes encima sin borrar la anteriror y que sea facil
                celda.setPrefSize(45, 45);

                // Guarda la posicion de la celda
                final int filaSeleccionada = fila;
                final int columnaSeleccionada = columna;

                // Para que el usuario haga clic en las celdas
                celda.setOnMouseClicked(e -> {
                    if(barcoSeleccionado != null){
                        // Se coloca el barco en el tablero con la posicion y direccion
                        boolean colocarBarco = jugadorPersona.getTablero().colocarBarco(barcoSeleccionado, filaSeleccionada, columnaSeleccionada, barcoSeleccionado.esVerical());

                        // Si el barco se coloco lo que hace es añadirlo visualmente
                        if(colocarBarco){
                            barcoSeleccionado.setPosicionInicio(filaSeleccionada, columnaSeleccionada);
                            barcoSeleccionado.setFigura(barcoVisual);

                            agregarBarcoComoFigura2D(filaSeleccionada, columnaSeleccionada); // Lo crea como figura 2D apartir de una imagen
                            jugadorPersona.getTablero().imprimirTablero();
                            barcoSeleccionado = null; // Borra el barco anteriormente seleccionado para poder seleccionar otro y evitar seguir usando ese
                        }
                    }
                });
                gridPaneTableroJugador.add(celda, columna, fila); // Añade cada celda al GridPane
                celdas[fila][columna] = celda; // Se guarda la celda en una matriz para poder acceder a ella despues de una manera mas facil
            }
        }
    }

    /**
     * This method loads the ship images and sends them to the method crearImagenesBarcos to add
     * them to the FlowPane
     */
    // Este metodo carga las imagenes de los barcos y las pasa al metodo crearImagenesBarcos para que las
    // añada al FlowPane
    /**
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
     **/

    private void cargarImagenesBarco(){
        crearFiguraBarcoEnFlowPane("fragata");
        crearFiguraBarcoEnFlowPane("fragata");
        crearFiguraBarcoEnFlowPane("fragata");
        crearFiguraBarcoEnFlowPane("fragata");

        crearFiguraBarcoEnFlowPane("destructor");
        crearFiguraBarcoEnFlowPane("destructor");
        crearFiguraBarcoEnFlowPane("destructor");

        crearFiguraBarcoEnFlowPane("submarino");
        crearFiguraBarcoEnFlowPane("submarino");

        crearFiguraBarcoEnFlowPane("portaaviones");
    }


    /**
     * his method visually creates a ship image based on the given path, adds it to a FlowPane, and assigns
     * events so the user can select it and add it to the board
     *
     * @param ruta
     * @param nombre
     * @param tamano
     */
    // Este metodo crea visualmente una imagen de barco segun la ruta que se le pase, la agrega a un FlowPane y
    // le asigna eventos para que el usuario pueda seleccionarla y añadirla al tablero
    /**
    private void crearImagenesBarcos(String ruta, String nombre, int tamano) {
        Image image = new Image(getClass().getResource(ruta).toExternalForm()); // Carga la imagen con el parametro ruta que le pasan
        ImageView imagenBarco = new ImageView(image); // Carga la imagen y la guarda en un ImageView

        // Crea el tamaño de la imagen
        imagenBarco.setFitHeight(tamano * 45); // Representa el alto
        imagenBarco.setFitWidth(45);

        // Evento al hacer clic sobre las imagenes, cada que se crea una imagen se crea con este evento
        imagenBarco.setOnMouseClicked(e -> {
            rutaImagenseleccionada = ruta; // Guarda la ruta de la imagen que se selecciono para usarla en el momento de crear las figuras 2D
            barcoSeleccionado = BarcoFactory.crearBarco(nombre, rutaImagenseleccionada); // Cuando se hace clic sobre una imagen se crea un objeto barco desde la fabrica
            System.out.println("Ruta de imagen seleccionada: " + rutaImagenseleccionada);

            // Aqui le damos la ruta de la imagen al barco
            barcoSeleccionado.setRutaImagen(rutaImagenseleccionada);

            gridPaneTableroJugador.requestFocus(); // Se llama a requestFocus (activar foco del teclado en el tablero) para que en el gridPane se detecte cuando se presiona la tecla R y se debe tener un barco seleccionado previamente
            imagenBarco.setOpacity(0.0); // Se oculta la imagen que fue seleccionada
        });

        // agrega la imagen al contenedor
        flowPaneContenedorBarcos.getChildren().add(imagenBarco); // Se añade la imagen del barco en el FlowPane para que se pueda ver visualmente en el contenedor
    }**/

    // Este metodo reemplaza crearImagenesBarcos
    private void crearFiguraBarcoEnFlowPane(String tipoBarco){
        BarcoVisual figura = BarcoVisualFactory.crearBarco(tipoBarco);
        Group barcoGroup = new Group();
        barcoGroup.getChildren().addAll(figura.crearFiguras());

        barcoGroup.setOnMouseClicked(e -> {
            barcoSeleccionado = BarcoFactory.crearBarco(tipoBarco, null);
            barcoVisual =  BarcoVisualFactory.crearBarco(tipoBarco);
            barcoSeleccionado.setFigura(barcoVisual); // Asocia la figura al barco
            gridPaneTableroJugador.requestFocus(); // Para escuchar cuando se presione la R
            barcoGroup.setOpacity(0);
        });
        flowPaneContenedorBarcos.getChildren().add(barcoGroup);
    }


    /**
     * This method adds the selected image from the FlowPane to the board, but transforms it into a 2D figure
     * using a rectangle. It places it at the selected coordinates and stretches it based on the size of the ship
     *
     * @param fila
     * @param columna
     */
    // Este metodo agrega la imagen seleccionada en el FlowPane al tablero pero la transforma a figura 2D
    // mediante rectangle, la posiciona en las coordenadas seleccionadas y la extiende segun el tamaño del barco
    private void agregarBarcoComoFigura2D(int fila, int columna) {
        int tamano = barcoSeleccionado.getTamano();
        boolean vertical = barcoSeleccionado.esVerical();

        List<Node> figuras = barcoVisual.crearFiguras();

        // Agrupamos todas las figuras para que se puedan mover juntas
        Group barcoGroup = new Group();
        barcoGroup.getChildren().addAll(figuras);

        // Ajustar la posición visual del grupo para que parezca anclado a la celda seleccionada
        if (vertical) {
            barcoGroup.setTranslateY(45 * (tamano - 1) / 2.0);
        } else {
            barcoGroup.setRotate(90); // Rotar para que sea horizontal
            barcoGroup.setTranslateX(45 * (tamano - 1) / 2.0);
            //barcoGroup.setTranslateX(70);
        }

        StackPane celdaInicial = celdas[fila][columna];
        celdaInicial.getChildren().add(barcoGroup);
    }


    /**
     * This method detects when the letter R is pressed on the keyboard. Its function is to change the
     * ship's direction to add it horizontally
     *
     * @param event
     */
    // Este metodo detecta cuando se presiona la letra R del teclado, su funcion es cambiar la orientacion del
    // barco para añadirlo horizontal
    private void teclado(KeyEvent event) {
        if (event.getCode() == KeyCode.R && barcoSeleccionado != null) {
            barcoSeleccionado.setVerical(!barcoSeleccionado.esVerical()); // Cambia el valor el booleano
        }
    }


    /**
     * This method is a button event. When the button is clicked, it shows an alert asking the user if they
     * finished creating their strategy and want to start the game. If the user accepts, it creates the
     * jugadorMaquina and makes it create its strategy randomly. Then, it loads the game screen and sends the players
     * and game logic to the controller
     *
     * @param event
     * @throws IOException
     */
    // Este metodo es un evento de un boton, el cual cuando se presiona lanza una alerta para preguntarle al usuario
    // si termino de crear su estrategia y si desea iniciar el juego, si acepta crea el jugadorMaquina y hace que
    // cree su estrategia de manera aleatoria, carga la pantalla del juego, pasa los jugadores y la logica del juego
    // al controlador
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
            juegoBatallaNavalView.getController().setJugadorMaquina(jugadorMaquina);

            juegoBatallaNavalView.getController().setFuncionamientoJuego(new FuncionamientoJuego(jugadorPersona, jugadorMaquina));
            juegoBatallaNavalView.getController().alIniciarJuego();

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
