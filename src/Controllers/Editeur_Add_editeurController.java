package Controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dataBase.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Editeur_Add_editeurController {
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
    private TextField domaineId;
    @FXML
    private TextField institutionId;
    private int id;
    private String username;
           public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    void btnCreateClicked(MouseEvent event) {
        System.out.println("hhhhhh");

        String nom = tf_fistName.getText();
        String prenom = tf_lastName.getText();
        String adresse = tf_email.getText();
        String password = tf_password.getText();
        String userName = tf_userName.getText();
        String domaine=institutionId.getText();
        String institution=domaineId.getText();
        System.out.println("hhhhhh");
        
             
              try {
                  
                  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyscience", "root", "");
        
                  
                 String query = "INSERT INTO chercheur (nom, prenom, userName, password, adresse, domaine, institution, userRole) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                  PreparedStatement preparedStatement = connection.prepareStatement(query);
                  
                  preparedStatement.setString(1, nom);
              preparedStatement.setString(2, prenom);
              preparedStatement.setString(3, userName);
              
              preparedStatement.setString(4, password);
              preparedStatement.setString(5, adresse);
              preparedStatement.setString(6, domaine);
              preparedStatement.setString(7, institution);
              preparedStatement.setString(8, "E");

        
        
        preparedStatement.executeUpdate();
        
        connection.close();
        homeClicked( event);
                  
              } catch (SQLException e) {
                  e.printStackTrace();
                  
              }

          
          }
        @FXML
    void chooseEvaClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditeurScene.fxml"));
            Parent root = loader.load();
        
            EditeurSceneController controller = loader.getController();
        
            controller.setStage(stage);
            Scene EditeurScene = new Scene(root);
            stage.setScene(EditeurScene);
          controller.setId(id);
           System.out.println(DatabaseConnector.getUsername(id));
           
            stage.setTitle("EditeurScene");
            stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load EditeurScene.fxml");
        }

    }
    @FXML
    void EvaluationProessClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditeursEvaluationProcess.fxml"));
            Parent root = loader.load();
        
            EditeursEvaluationProcessController controller = loader.getController();
        
            controller.setStage(stage);
            Scene EditeurScene = new Scene(root);
            stage.setScene(EditeurScene);
          controller.setId(id);
           System.out.println(DatabaseConnector.getUsername(id));
           
            stage.setTitle("Accueil");
            stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load AccueilScene.fxml");
        }
    }

    @FXML
    void MakeDecClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditeurDecisionScene.fxml"));
            Parent root = loader.load();
        
            EditeurDecisionSceneController controller = loader.getController();
        
            controller.setStage(stage);
            Scene EditeurDecisionScene = new Scene(root);
            stage.setScene(EditeurDecisionScene);
          controller.setId(id);
           System.out.println(DatabaseConnector.getUsername(id));
           
            stage.setTitle("EditeurDecisionScene");
            stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load EditeurDecisionScene.fxml");
        }
    }
    @FXML
    void homeClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditeurHomeScene.fxml"));
            Parent root = loader.load();
        
            EditeurHomeSceneController controller = loader.getController();
        
            controller.setStage(stage);
            Scene EditeurHomeScene = new Scene(root);
            stage.setScene(EditeurHomeScene);
          controller.setId(id);
          controller.setIdusername(DatabaseConnector.getUsername(id));
           System.out.println(DatabaseConnector.getUsername(id));
          
            
            stage.setTitle("EditeurHomeScene");
            stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load EditeurHomeScene.fxml");
        }
    }
    @FXML
    void AddEditeurClicked(MouseEvent event){
       try {
          
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Editeur_Add_editeur.fxml"));
           Parent root = loader.load();
       
           Editeur_Add_editeurController controller = loader.getController();
       
           controller.setStage(stage);
           Scene Editeur_Add_editeur = new Scene(root);
           stage.setScene(Editeur_Add_editeur);
           controller.setId(id);
           controller.setUsername(DatabaseConnector.getUsername(id));
          System.out.println(DatabaseConnector.getUsername(id));
         
           
           stage.setTitle("Editeur_Add_editeur");
           stage.show();
       
       } catch (IOException e) {
           e.printStackTrace();
           System.err.println("Failed to load EditeurHomeScene.fxml");
       }  
    }
      
    @FXML
    void DownloadArticleClicked(MouseEvent event) {
System.out.println("by ismail");
    }
}
