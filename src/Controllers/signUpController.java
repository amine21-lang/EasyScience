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

public class signUpController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
     @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_fistName;

    @FXML
    private TextField tf_lastName;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_userName;
    
           @FXML
    void btnCreateClicked(ActionEvent event) {
 

 String nom = tf_fistName.getText();
 String prenom = tf_lastName.getText();
 String adresse = tf_email.getText();
 String password = tf_password.getText();
 String userName = tf_userName.getText();
 String domaine="";
 String institution="";

 
       
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyscience", "root", "");

            
            String query = "INSERT INTO chercheur (nom,prenom,userName,password,adresse,domaine,institution ) VALUES (?, ?,?, ?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);
        preparedStatement.setString(3, userName);
        
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, adresse);
        preparedStatement.setString(6, domaine);
        preparedStatement.setString(7, institution);

 // Exécuter la requête
 preparedStatement.executeUpdate();

 // Fermer la connexion
 connection.close();
 navigateToLoginScene();

            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    
    }
  
    @FXML
    void btnLogInClicked(ActionEvent event) {
        navigateToLoginScene();
    }
    private void navigateToLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/loginScene.fxml"));
            Parent root = loader.load();
            loginSceneController controller = loader.getController();
            controller.setStage(stage);
            Scene loginScene = new Scene(root);
            stage.setScene(loginScene);
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
