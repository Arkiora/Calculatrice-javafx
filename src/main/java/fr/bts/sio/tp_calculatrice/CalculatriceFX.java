package fr.bts.sio.tp_calculatrice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatriceFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Charge le FXML depuis view
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fr/bts/sio/tp_calculatrice/view/calculatrice.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        // Applique la feuille de style
        scene.getStylesheets().add(
                getClass().getResource("/fr/bts/sio/tp_calculatrice/view/myCSS.css").toExternalForm());

        stage.setTitle("Calculatrice JavaFX – BTS SIO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
