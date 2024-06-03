module com.example.wordgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;


    opens com.example.wordgame to javafx.fxml;
    exports com.example.wordgame;
}