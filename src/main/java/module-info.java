module com.example.testui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.testui to javafx.fxml;
    exports com.example.testui;
}