package com.example.battleship.Controllers;

import com.example.battleship.Models.*;
import com.example.battleship.Views.InicioJuegoView;
import com.example.battleship.persistence.SaveManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.example.battleship.Controllers.InicioJuegoController;

import java.io.IOException;
import java.util.List;

import static com.example.battleship.persistence.SaveManager.saveGame;

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

    private String fileRoute = "saves/savegame.dat";

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

    @FXML
    private Button leaveGameButton;


    /**
     * Saves and updates the JugadorPersona instance and keeps the board current
     *
     * @param jugador
     */
    public void setJugador(JugadorPersona jugador) {
        this.jugador = jugador;
        this.jugador.getTablero().anadirObservador(this); // Añade el observador de tablero de jugadorPersona a la lista
        mostrarTableroJugador();
        //eventosDisparosJugadores();
    }

    /**
     * This method saves and updates the jugadorMaquina object, assigns the observer to its board,
     * prepares the board, and sets up shot events
     *
     * @param jugadorMaquina
     */
    // Este metodo guarda y actualiza el objetod de jugadorMaquina, le asigna el observador a su tablero,
    // prepara el tablero y asigna eventos de disparos
    public void setJugadorMaquina(JugadorMaquina jugadorMaquina) {
        this.jugadorMaquina = jugadorMaquina;
        this.jugadorMaquina.getTablero().anadirObservador(this); // Añade el observador del tablero del jugadroMaquina a la lista
        tableroJugadorMaquinaDisparos();
        eventosDisparosJugadores();
    }


    /**
     * This method runs the first things that should be shown when this screen starts.
     * It sets a background image in the main container and makes both players' boards visible
     */
    // Este metodo ejecuta lo primero que deberia de mostrar cuando arranque esta pantalla, coloca una imagen
    // de fondo en el contenedor principal y hace visible el tablero de ambos jugadores
    @FXML
    public void initialize() {
        String imagenFondo = getClass().getResource("/com/example/battleship/Images/imagenFondo2.png").toExternalForm();
        hBoxPrincipal.setStyle("-fx-background-image: url('" + imagenFondo + "'); -fx-background-size: cover;");


        gridPaneTableroMaquina.setVisible(true);
        gridPaneTableroJugador2.setVisible(true);
    }


    /**
     * This method creates and shows the board of the jugadorPersona created before. It checks where
     * there are ships and adds them as Rectangles, sets an image on them, adds them to the cell,
     * and then saves them on the board (GridPane)
     */
    // Este metodo crea y muestra el tablero del jugadorPersona creado anteriormente, verifica donde hay
    // barcos y los agrega como Rectangle, les asigna una imagen, lo añade a la celda y luego lo guarda en
    // el tablero (GridPane)
    private void mostrarTableroJugador(){
        // Se limpia el tablero para que ingrese
        gridPaneTableroJugador2.getChildren().clear();

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                // Aqui se crea una celda visual por cada posicion del taablero
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true); // Permite que se puede capturar eventos en la celda
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");

                // Verifica si hay un barco en la celda en la que se encuentra
                if(jugador.getTablero().hayBarco(fila, columna)){
                    Barco barco = jugador.getTablero().getBarco(fila, columna);
                    int tamano = barco.getTamano();

                    if(barco.esInicio(fila,columna)){
                        BarcoVisual figura = barco.getFigura();
                        figura.setVertical(barco.esVerical());

                        List<Node> figuras = figura.crearFiguras();
                        Group group = new Group(figuras);

                        if(barco.esVerical()){
                            group.setTranslateY(45 * (tamano - 1) / 2.0);
                        }else {
                            group.setRotate(90); // Rotar para que sea horizontal
                            group.setTranslateX(45 * (tamano - 1) / 2.0);
                            //barcoGroup.setTranslateX(70);
                        }

                        celda.getChildren().add(group);
                    }
                }

                gridPaneTableroJugador2.add(celda, columna, fila); // Se añade la celda creada al gridPane con su posicion
                celdasJugadorPersona[fila][columna] = celda; // Aqui se guarda una referencia para poder manipular esas celdas de manera mas sencilla despues
            }
        }
    }


    /**
     * Method to show the jugadorMaquina ships on the board. It creates a cell in each position,
     * checks where there are ships and adds them as Rectangles, adds an image, stores it in the cell,
     * and then adds it to the board (GridPane).
     */
    // Metodo para mostrar los barcos del jugadorMaquina en el tablero, crea una celda en cada posicion,
    // verifica donde hay barcos y los agrega como rectangle, le agrega una imagen, lo guarda en la celda y
    // luego lo agrega en el tablero (GridPane)
    private void mostrarTableroMaquina(){
        System.out.println("Mostrando tablero de la máquina...");
        gridPaneTableroMaquina.getChildren().clear(); // Limpia el tablero y coloca los barcos que estan para mantenerlo actualizado

        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true);
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");

                if(jugadorMaquina.getTablero().hayBarco(fila, columna)){
                    /**
                    String ruta = jugadorMaquina.getTablero().getRutaImagen(fila, columna);
                    ImagePattern patron = new ImagePattern(new Image(getClass().getResourceAsStream(ruta)));
                    Rectangle barcoRectangle = new Rectangle(40, 40);
                    barcoRectangle.setFill(patron);
                    celda.getChildren().add(barcoRectangle);
                     **/
                    Barco barco = jugadorMaquina.getTablero().getBarco(fila, columna);
                    int tamano = barco.getTamano();

                    // Solo muestra la figura una vez por barco y es en la inicial
                    if(barco.esInicio(fila,columna)){
                        System.out.println("Mostrando barco en [" + fila + "," + columna + "]");
                        BarcoVisual figura = barco.getFigura();
                        figura.setVertical(barco.esVerical());

                        if (barco != null && barco.esInicio(fila, columna)) {
                            System.out.println("✔ " + barco.getNombre() + " tiene figura: " + (barco.getFigura() != null));
                        }

                        List<Node> figuras = figura.crearFiguras();
                        Group group = new Group(figuras);


                        if(barco.esVerical()){
                            group.setTranslateY(45 * (tamano - 1) / 2.0);
                        }else {
                            group.setRotate(90); // Rotar para que sea horizontal
                            group.setTranslateX(45 * (tamano - 1) / 2.0);
                            //barcoGroup.setTranslateX(70);
                        }

                        celda.getChildren().add(group);
                    }
                }

                gridPaneTableroMaquina.add(celda, columna, fila);
                celdasJugadorMaquina[fila][columna] = celda;
            }
        }
    }


    /**
     * This method creates another visual board for jugadorMaquina, but where the shots made by
     * jugadorPersona are shown depending on the case. It adds the images to the cell,
     * and the cell is added to the gridPane with its position and a reference is saved
     */
    // Este metodo crea otro tablero visual de jugadorMaquina pero en donde se muestran los disparos
    // que le hizo jugadorPersona segun el caso, agrega las imagenes a la celda y la celda se agrega al
    // gridPane con su posicion y se guarda una referencia
    private void tableroJugadorMaquinaDisparos() {
        gridPaneTableroMaquina.getChildren().clear(); // Limpia el tablero para mantener actualizado

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                // Aqui estaria creando una celda visual por cada posicion
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true);
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");


                // Si hubo un disparo en la celda y hay un barco en ese posicion
                if(jugadorMaquina.getTablero().huboDisparoAqui(fila, columna)){
                    if(jugadorMaquina.getTablero().getBarco(fila, columna)!=null){

                        // En esta parte se crea la imagen de una bomba que seria un impacto al barco
                       FiguraBomba bomba = new FiguraBomba();
                       celda.getChildren().addAll(bomba.crearObjeto());


                        // Aqui se crea la imagen de fuego si el barco fue hundido, lo pone encima de la bomba
                        FiguraFuego fuego = new FiguraFuego();
                        celda.getChildren().addAll(fuego.crearObjeto());
                    } else{

                        // De lo contrario, sino hay un barco en esa posicion lo que pondria como imagen de disparo seria una X
                        FiguraX X =  new FiguraX();
                        celda.getChildren().addAll(X.crearObjeto());
                    }
                }

                gridPaneTableroMaquina.add(celda, columna, fila); // La celda creada con las imagenes la añada al tablero con la posicion
                celdasJugadorMaquina[fila][columna] = celda; // Guarda una referencia
            }
        }
        eventosDisparosJugadores(); // Se coloca para agregarle los eventos a cada celda
    }


    /**
     * This method adds events to all the cells of the machine player so the human player can shoot.
     * It captures the selected cells and makes the shot
     */
    // Este metodo le añade eventos a todas las celdas del jugadorMaquina para que jugadorPersona pueda
    // disparar, captura las celdas que selecciono y realiza el disparo
    private void eventosDisparosJugadores(){

        // Permite que recorra todas las celdas del gridPane para añadirle los eventos
        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                final int filaFinal = fila; // Permite capturar la fila en la que se hizo clic
                final int columnaFinal = columna; // Permite capturar la columna en la que se hizo clic

                StackPane celda = celdasJugadorMaquina[fila][columna]; // Se obtiene la referencia de las celdas de la maquina que es donde jugadorPersona interactua
                celda.setPickOnBounds(true);

                // Aqui se crea el evento de clic de mouse
                celda.setOnMouseClicked(event -> {
                    if(funcionamientoJuego.getTurnoJugador() && !funcionamientoJuego.juegoTerminado()){
                        funcionamientoJuego.disparoSegunTurno(filaFinal, columnaFinal); // jugadorPersona dispara en las coordenadas seleccionadas

                        // Para saber si es turno de la maquina
                        if(!funcionamientoJuego.getTurnoJugador() && !funcionamientoJuego.juegoTerminado()){
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e ->{ // Espera 2 segundos para disparar
                                int[] coordenadasUsadas = funcionamientoJuego.disparoSegunTurno(-1, -1);
                                int fila2 = coordenadasUsadas[0];
                                System.out.println("Coor fila " + fila2);
                                int columna2 = coordenadasUsadas[1];
                                System.out.println("Coor columna " + columna2);
                            }));
                            timeline.setCycleCount(1);
                            timeline.play(); // Inicia la ejecucion
                        }
                        saveGame(funcionamientoJuego, fileRoute);
                    }
                });
            }
        }
    }


    /**
     * Button event that, when pressed, shows the board created by the machine player with the ships,
     * and when pressed again, returns to the default shots board
     *
     * @param event
     */
    // Evento de boton al presionarlo muestra en tablero creado por el jugadorMaquina con los barcos y
    // cuando se vuelve a presionar vuelve al tablero por defecto de disparos
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
        } else {
            mostrarTableroMaquina(); // Muestra el tablero con los barcos
        }

        tableroMaquinaVisible = !tableroMaquinaVisible; // Cambia el valor booleano
    }


    /**
     * Method to show the jugadorPersona name in a label
     */
    public void mostrarNombreJugador2(){
        labelNombre2.setText(jugador.getNombre());
    }

    /**
     * This method shows a bomb image in a specific cell of the board, depending on who the
     * board belongs to
     *
     * @param fila
     * @param columna
     * @param esMaquina
     */
    // Este metodo muestra una imagen de una bomba en una celda especifica del tabllero dependiendo de a
    // quien le pertenezca el tablero
    private void mostrarBomba(int fila, int columna, boolean esMaquina){
        double cuadricula = 45;

        FiguraBomba bomba = new FiguraBomba();
        List<Node> formaBomba = bomba.crearObjeto();

        Group groupBomba = new Group(formaBomba);
        groupBomba.setTranslateX(columna * cuadricula);
        groupBomba.setTranslateY(fila * cuadricula);
        // Si disparo jugadorPersona es en el tablero de jugadorMaquina, sino seria en el tablero de jugadorPersona
        if(esMaquina){
            gridPaneTableroMaquina.getChildren().add(groupBomba);
        } else{
            gridPaneTableroJugador2.getChildren().add(groupBomba);
        }
    }


    /**
     * This method shows an X image in a specific cell of the board, depending on who the
     * board belongs to
     *
     * @param fila
     * @param columna
     * @param esMaquina
     */
    // Este metodo muestra una imagen de una X en una celda especifica del tablero dependiendo de a
    // quien pertenezca el tablero
    private void mostrarX(int fila, int columna,  boolean esMaquina){
        double cuadricula = 45;

        FiguraX X = new FiguraX();
        List<Node> formaX = X.crearObjeto();

        Group groupX = new Group(formaX);
        groupX.setTranslateX(columna * cuadricula);
        groupX.setTranslateY(fila * cuadricula);
        if(esMaquina){
            gridPaneTableroMaquina.getChildren().add(groupX);
        } else{
            gridPaneTableroJugador2.getChildren().add(groupX);
        }
    }


    /**
     * This method shows a fire image and puts it in each cell of the size of the ship on the board
     * of the player it belongs to
     *
     * @param barco
     * @param tablero
     */
    // Este metodo muestra una imagen de fuego y las pone en cada celda del tamaño del barco en el tablero
    // del jugador a quien le pertenezca
    private void mostrarFuego(Barco barco, Tablero tablero){

        int filaInicio = barco.getFila();
        int columnaInicio = barco.getColumna();
        boolean vertical = barco.esVerical();
        int tamano = barco.getTamano();

        double cuadricula = 45; // Tamaño de celda

        for(int i = 0; i < tamano; i++){
            int fila = filaInicio + (vertical ? i : 0);
            int columna = columnaInicio + (vertical ? 0 : i);

            FiguraFuego fuego = new FiguraFuego();
            List<Node> formaFuego = fuego.crearObjeto();

            Group fuegoGroup = new Group(formaFuego);
            fuegoGroup.setTranslateX(columna * cuadricula);
            fuegoGroup.setTranslateY(fila * cuadricula);


            if(tablero == jugadorMaquina.getTablero()){
                gridPaneTableroMaquina.getChildren().add(fuegoGroup); // Barco de la maquina hundido
            } else{
                gridPaneTableroJugador2.getChildren().add(fuegoGroup); // Barco del jugador hundido
            }
        }
    }


    /**
     * This method runs automatically when something happens on the players' boards. It
     * updates the board visually depending on the event. The event is a String that says what happened,
     * and the object can be an array of integers or a PosicionBarco
     *
     * @param evento
     * @param objeto
     */
    // Este metodo se ejecuta automaticamente cuando ocurre algo en los tableros de los jugadores, se
    // encarga de actualizar visualmente el tablero segun el evento ocurrido, el evento es un String
    // que describe lo que sucedio para saber que se hace y el objeto puede ser un arreglo de enteros o de posicionBarco
    @Override
    public void actualizar(String evento, Object objeto) { // Hay parametro Object para poder almacenar cualquier tipo objeto (Es la super clase de todas las clases)
        switch (evento){
            case "impacto":
                // En impacto se espera que objeto sea un arreglo de 2 enteros que representan las coordenadas
                int[] coordenadas1 = (int[]) objeto; // Aqui se hace cast en donde pasa del Object objeto a un arreglo de enteros

                // Aqui se verifica a que tablero se impacta segun el turno
                boolean disparoDeJugador = funcionamientoJuego.getTurnoJugador();

                mostrarBomba(coordenadas1[0], coordenadas1[1], disparoDeJugador); // Se muestra la bomba segun donde se impacto
                break;
            case "agua":
                int[] coords2 = (int[]) objeto; // Sucede lo mismo con impacto

                boolean disparoDeJugadorAgua = funcionamientoJuego.getTurnoJugador();

                mostrarX(coords2[0], coords2[1], disparoDeJugadorAgua);
                break;
            case "barco_hundido":
                PosicionBarco info = (PosicionBarco) objeto; // Se hace un cast convierte el Object en una instancia de tipo PosicionBarco
                // Permite saber a quien le pertenece el barco hundido
                boolean barcoMaquina = info.getTablero() == jugadorMaquina.getTablero(); // Compara si el tablero donde se hundio el barco es el mismo que el de la maquina
                mostrarFuego(info.getBarco(), info.getTablero());
                Barco barcoHundido = info.getBarco();


                // Aqui se actualizan los labels de la cantidad de barcos que le queda a cada jugador
                    if (barcoMaquina) {
                        // Aqui se muestran los barcos de la maquina (disparos de la persona)
                        labelBarcosM.setText("maquina: " + funcionamientoJuego.cantBarcosMaquina(info.getBarco()));
                    } else {
                        // Aqui la cantidad de barcos de la maquina ()
                        labelBarcosP.setText("persona: " + funcionamientoJuego.cantBarcosPersona(info.getBarco()));
                    }

                    /**
                    // toLowerCase ignora mayusculas y contains verifica que tenga fragata y olvida el numero
                    if(barcoHundido.getNombre().toLowerCase().contains("fragata")){
                        boolean esDeMaquina = info.getTablero() == jugadorMaquina.getTablero();

                    }
                     **/
                break;
        }


        // En esta parte se verifica si el juego finalizo
        if(funcionamientoJuego != null && funcionamientoJuego.juegoTerminado()){
            String ganador = funcionamientoJuego.ganador();
            mostrarAlertaFinDelJuego(ganador);
        }
    }


    /**
     * This method shows an alert window saying the game is over and shows the winner
     *
     * @param ganador
     */
    // Este metodo muestra una ventana de alerta en donde menciona que el juego termino y muestra el ganador
    private void mostrarAlertaFinDelJuego(String ganador){
        javafx.application.Platform.runLater(() -> { // Permite que corra en el hilo principal
           boolean continuar = alerta.mostrarAlertaDeConfirmacion("Fin del juego", "Juego terminado!!!", "El ganador es: " + ganador);

           if(continuar){
               System.out.println("El ganador es: " + ganador);
           }
        });
    }

    /**
     * Este metodo guarda una referencia logica de la clase
     *
     * @param funcionamientoJuego
     */
    // Este metodo guarda una referencia logica de la clase
    public void setFuncionamientoJuego(FuncionamientoJuego funcionamientoJuego) {
        this.funcionamientoJuego = funcionamientoJuego;

    }


    /**
     * This method creates the shooting board of jugadorMaquina and shows the name of jugadorPersona
     */
    // este metodo decidi crearlo porque antes lo tenia en initialize y al abrir esta pantalla me salia que el tablero
    // de la maquina era vacio (un error), entonces lo cree para asegurarme de que los datos del tablero ya habian sido creados correctamente
    public void alIniciarJuego(){
        tableroJugadorMaquinaDisparos();
        mostrarNombreJugador2();

        // Inicializa los labels con los valores iniciales (10 barcos cada uno)
        labelBarcosM.setText("maquina: 10");
        labelBarcosP.setText("persona: 10");
    }

    /**
     * This method makes that the player can return to the main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionLeaveButton(ActionEvent event) throws IOException {
        LeaveGameAlert alert = new  LeaveGameAlert();

        boolean confirmation = alert.mostrarAlertaDeConfirmacion(
                "Alerta de abandonar juego",
                "This is an alert window",
                "Quieres regresar a la pantalla de titulo?");
        if (confirmation){
            hideGameView();
            returnMenu();
        }
        else {

        }

    }

    /**
     * Method that returns and load the main menu window
     * @throws IOException
     */
    private void returnMenu() throws IOException {
        InicioJuegoView mainMenu = InicioJuegoView.getInstance();
        mainMenu.show();
    }

    /**
     * Hides the game window
     */
    public void hideGameView() {
        Stage gameStage = (Stage) labelBarcosM.getScene().getWindow();
        gameStage.hide();
    }


    /**
     * loads the game data on the viewable part
     * @param loadedGame: game from the load file
     */
    public void restoreGameState(FuncionamientoJuego loadedGame) {
        setFuncionamientoJuego(loadedGame);
        updatePlayers(loadedGame.getPersonaPlayer(),loadedGame.getCPUPlayer());
        tableroJugadorDisparos();
        tableroJugadorMaquinaDisparos();
        if (!funcionamientoJuego.juegoTerminado() && !funcionamientoJuego.juegoTerminado()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                int[] coords = funcionamientoJuego.disparoSegunTurno(-1, -1);
                tableroJugadorMaquinaDisparos();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        System.out.println("¿Turno jugador cargado?:" + funcionamientoJuego.getTurnoJugador());
    }


    /**
     * updates the information from the save file.
     * @param player: human player
     * @param cpu: machine player
     */
    private void updatePlayers(JugadorPersona player, JugadorMaquina cpu) {
        setJugadorMaquina(cpu);
        setJugador(player);
    }


    private void tableroJugadorDisparos() {
        gridPaneTableroJugador2.getChildren().clear();

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                StackPane celda = new StackPane();
                celda.setPickOnBounds(true);
                celda.setPrefSize(45, 45);
                celda.setStyle("-fx-border-color: black;");

                // Mostrar barcos del jugador
                if (jugador.getTablero().hayBarco(fila, columna)) {
                    Barco barco = jugador.getTablero().getBarco(fila, columna);
                    if (barco.esInicio(fila, columna)) {
                        BarcoVisual figura = barco.getFigura();
                        figura.setVertical(barco.esVerical());
                        Group group = new Group(figura.crearFiguras());

                        if(barco.esVerical()) {
                            group.setTranslateY(45 * (barco.getTamano() - 1) / 2.0);
                        } else {
                            group.setRotate(90);
                            group.setTranslateX(45 * (barco.getTamano() - 1) / 2.0);
                        }

                        celda.getChildren().add(group);
                    }
                }

                // Mostrar disparos recibidos
                if(jugador.getTablero().huboDisparoAqui(fila, columna)) {
                    if(jugador.getTablero().getBarco(fila, columna) != null) {
                        FiguraBomba bomba = new FiguraBomba();
                        celda.getChildren().addAll(bomba.crearObjeto());

                        FiguraFuego fuego = new FiguraFuego();
                        celda.getChildren().addAll(fuego.crearObjeto());
                    } else {
                        FiguraX x = new FiguraX();
                        celda.getChildren().addAll(x.crearObjeto());
                    }
                }

                gridPaneTableroJugador2.add(celda, columna, fila);
                celdasJugadorPersona[fila][columna] = celda;
            }
        }
    }




}
