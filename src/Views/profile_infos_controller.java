import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class profile_infos_controller {

    @FXML
    private TextField IdDomaine;
    @FXML
    private TextField IdFisrtName;
    @FXML
    private TextField IdInsitution;
    @FXML
    private TextField IdLastName;
    @FXML
    private TextField IdUserName;
    @FXML
    private Button modify_infos;

    private boolean isEditable = false;

    // Connexion à la base de données
    String url = "jdbc:mysql://localhost:3306/miniprojetjava_final";
    String user = "root";
    String password = "";
    //private id_chercheur=;

    @FXML
    public void initialize() {
        // Désactiver la possibilité de modifier les champs de texte IdFisrtName, IdLastName et IdUserName
        IdFisrtName.setEditable(false);
        IdLastName.setEditable(false);
        IdUserName.setEditable(false);

        // Récupérer les données de l'utilisateur depuis la base de données
        fetchUserData();
    }

    @FXML
    void click_to_modify_infos(MouseEvent event) {
        if (!isEditable) {
            // Activer la possibilité de modifier les champs de texte IdDomaine et IdInsitution
            IdDomaine.setEditable(true);
            IdInsitution.setEditable(true);
            isEditable = true;
            modify_infos.setText("Enregistrer");
        } else {
            // Enregistrer les modifications dans la base de données
            saveModifications();
            isEditable = false;
            modify_infos.setText("Modifier");
        }
    }

    private void fetchUserData() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String query = "SELECT nom, prenom, userName, domaine, institution FROM chercheur WHERE id_chercheur = 2"; // Remplacez 1 par l'ID de l'utilisateur connecté
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                IdFisrtName.setText(resultSet.getString("nom"));
                IdLastName.setText(resultSet.getString("prenom"));
                IdUserName.setText(resultSet.getString("userName"));
                IdDomaine.setText(resultSet.getString("domaine"));
                IdInsitution.setText(resultSet.getString("institution"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveModifications() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String query = "UPDATE chercheur SET domaine = '" + IdDomaine.getText() + "', institution = '" + IdInsitution.getText() + "' WHERE id_chercheur = 2"; // Remplacez 1 par l'ID de l'utilisateur connecté
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}