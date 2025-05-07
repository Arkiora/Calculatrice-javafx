package fr.bts.sio.tp_calculatrice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    @FXML private TableView<Utilisateur> tableView;
    @FXML private TableColumn<Utilisateur, Integer> colId;
    @FXML private TableColumn<Utilisateur, String>  colNom;
    @FXML private TableColumn<Utilisateur, String>  colPrenom;
    @FXML private TableColumn<Utilisateur, String>  colEmail;
    @FXML private TableColumn<Utilisateur, String> colDepartement;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colNom.setCellValueFactory(data -> data.getValue().nameProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colDepartement.setCellValueFactory(data -> data.getValue().departementProperty());
        loadData();
    }


    private void loadData() {
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        String sql = "SELECT ID, name, email, departement FROM user_info";

        try (Connection conn = DbConnection.getInstance();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Utilisateur(
                        rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("departement")
                ));
            }
            tableView.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                    "Erreur lors du chargement des utilisateurs :\n" + e.getMessage())
                    .showAndWait();
        }
    }

    @FXML
    private void onDetailClicked() {
        Utilisateur selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DetailsUtilisateur.show(selected);
        } else {
            new Alert(Alert.AlertType.WARNING, "Aucun utilisateur sélectionné.")
                    .showAndWait();
        }
    }
}
