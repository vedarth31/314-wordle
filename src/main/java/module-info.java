module com.example.finalprojectwordle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.finalprojectwordle to javafx.fxml;
    exports com.example.finalprojectwordle;
}