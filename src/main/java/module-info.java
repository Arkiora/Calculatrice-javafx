module tp_calculatrice {
    requires javafx.controls;
    requires javafx.fxml;

    // Permet à FXMLLoader d’accéder aux classes et aux champs @FXML
    opens fr.bts.sio.tp_calculatrice to javafx.fxml;
    opens fr.bts.sio.tp_calculatrice.view to javafx.fxml;

    exports fr.bts.sio.tp_calculatrice;
}
