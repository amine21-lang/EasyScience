package Controllers;

import java.io.IOException;
import java.sql.Connection;


import dataBase.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditeurHomeSceneController {
 private Stage stage;
  private Connection con;
     
    public void setStage(Stage stage) {
        this.stage=stage;
    }
    @FXML
    private Label Idusername;
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
      
    
   

    public void setIdusername(String idusername) {
        Idusername.setText(idusername);
    }
    private int id;

    public void setId(int id) {
        this.id = id;
    }
   
    public void updateUserName(int id){
        Idusername.setText(DatabaseConnector.getUsername(id));
    }
  

    

}
