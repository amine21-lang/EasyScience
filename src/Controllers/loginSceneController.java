package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.pdfbox.Loader;

import dataBase.DatabaseConnector;

public class loginSceneController {
    private Connection con;

    public loginSceneController() {
        DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
       this.con = dbc.getConnection();
    
    }

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void btnOnClicked(ActionEvent event) {
        String userName = tf_username.getText();
        String password = tf_password.getText();

        System.out.println("Attempting login with username: " + userName + gettheId());

        if (authenticateUser(userName, password)) {
             switch (gettheUserRole()) {
                case "E":
                System.out.println(gettheUserRole());
                try {
                    System.out.println("Authentication successful.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditeurHomeScene.fxml"));
                    Parent root = loader.load();
                  
                    EditeurHomeSceneController controller = loader.getController();
                  
                    controller.setStage(stage);
                    Scene EditeurHomeScene = new Scene(root);
                    stage.setScene(EditeurHomeScene);
                    controller.setId(gettheId());
                   System.out.println(DatabaseConnector.getUsername(gettheId()));
                    controller.setIdusername( DatabaseConnector.getUsername(gettheId()));
                    
                    stage.setTitle("Accueil");
                    stage.show();
                   
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Failed to load AccueilScene.fxml");
                }
                    break;
                case "C":
                System.out.println(gettheUserRole());
                try {
                    System.out.println("Authentication successful.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_chercheur.fxml"));
                    Parent root = loader.load();
                  
                    home_evaluate_finalController controller = loader.getController();
                  
                    controller.setStage(stage);
                    controller.setIdChercheur(gettheId());
                    controller.setUsername(DatabaseConnector.getUsername(gettheId()));
                    Scene home_chercheur = new Scene(root);
                    stage.setScene(home_chercheur);
                    
                    
                    stage.setTitle("Accueil");
                    stage.show();
                   
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Failed to load AccueilScene.fxml");
                }
                    break;
              
               
               
                    default:
                    break;
            }
           
      
        } else {
            System.out.println("Authentication failed.");
            showAlert("Échec de la connexion", "Erreur", "Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    private boolean authenticateUser(String userName, String password) {
        try {
            String query = "SELECT * FROM chercheur WHERE username = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private int gettheId() {
        String query = "SELECT id_chercheur FROM chercheur WHERE username = ?";
        int id = 0;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, this.tf_username.getText());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    private String gettheUserRole() {
        String query = "SELECT userRole FROM chercheur WHERE id_chercheur = ?";
       String userRole = null;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, gettheId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                userRole= rs.getString("userRole");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRole;
    }



    @FXML
    void btnCreateClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/signUpScene.fxml"));
            Parent root = loader.load();
           
            Scene signinScene = new Scene(root);
            signUpController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(signinScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load signUpScenetest.fxml");
        }
    }
}