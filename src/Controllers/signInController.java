package Controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signInController {
    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_userName;

    private Stage stage;// Ajoutez une variable pour stocker la scène précédente

    // Méthode pour définir la référence à Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    

    @FXML
    void btnLoginClicked(ActionEvent event) {
     
    }

       @FXML
    void btnCreateClicked(ActionEvent event) {
 // Récupérer les valeurs des champs texte
        String userName = tf_userName.getText();
        String password = tf_password.getText();

        // Insérer les données dans la base de données
        try {
            // Connexion à la base de données
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyscience", "root", "");

            // Requête d'insertion
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            // Exécuter la requête
            preparedStatement.executeUpdate();

            // Fermer la connexion
            connection.close();

            // Afficher un message de succès ou effectuer d'autres actions nécessaires
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion ou d'insertion dans la base de données
        }
    
    }

    // Ajoutez une méthode pour revenir à la scène précédente
    @FXML
    void btnBackClicked(ActionEvent event) {
                try {
            // Charger le fichier FXML de la scène de création de compte
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));

            // Créer une nouvelle scène avec la racine chargée à partir du fichier FXML
            Scene signinScene = new Scene(root);

            // Définir la nouvelle scène sur la scène actuelle
            stage.setScene(signinScene);

            // Montrer la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
