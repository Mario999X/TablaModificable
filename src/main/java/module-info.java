module com.example.tablamodificable {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tablamodificable to javafx.fxml;
    exports com.example.tablamodificable;
}