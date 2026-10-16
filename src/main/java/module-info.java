module com.nicolas.gestionproductos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.nicolas.gestionproductos to javafx.fxml;
    opens com.nicolas.gestionproductos.controller to javafx.fxml;
    opens com.nicolas.gestionproductos.model to javafx.fxml;
    opens com.nicolas.gestionproductos.dto to javafx.fxml;

    exports com.nicolas.gestionproductos;
    exports com.nicolas.gestionproductos.dto;
}