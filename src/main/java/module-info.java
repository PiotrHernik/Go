module com.example.go {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.go to javafx.fxml;
    exports com.example.go;
}