module com.example.battleship {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;
    requires java.logging;

    opens com.example.battleship to javafx.fxml;
    opens com.example.battleship.Controllers to javafx.fxml;
    exports com.example.battleship;


    // Agrega esto:
    exports com.example.battleship.Models;
    //exports com.example.battleship.Figuras;

}