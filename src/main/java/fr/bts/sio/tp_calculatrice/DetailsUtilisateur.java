package fr.bts.sio.tp_calculatrice;

import javafx.scene.control.Alert;

public class DetailsUtilisateur {
    public static void show(Utilisateur u) {
        Alert dlg = new Alert(Alert.AlertType.INFORMATION);
        // Affiche le nom (name) et l'email, plus le département
        dlg.setTitle("Détails de l'utilisateur");
        dlg.setHeaderText(u.nameProperty().get());  // nom de l'utilisateur
        dlg.setContentText(
                "ID          : " + u.idProperty().get() + "\n" +
                        "Email       : " + u.emailProperty().get() + "\n" +
                        "Département : " + u.departementProperty().get()
        );
        dlg.showAndWait();
    }
}
