package Controllers;

import java.io.IOException;

import dataBase.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class home_evaluate_finalController {
        private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Label username=null;

    private int IdChercheur=0;
    

    public Label getUsername() {
        return username;
    }
    

    public void setUsername(String username) {
        this.username.setText(username);
    }
    @FXML
    void HomeChercheurClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_chercheur.fxml"));
            Parent root = loader.load();
          
            home_evaluate_finalController controller = loader.getController();
          
            controller.setStage(stage);
            controller.setIdChercheur(getIdChercheur());
            controller.setUsername(DatabaseConnector.getUsername(getIdChercheur()));
            Scene home_chercheur = new Scene(root);
            stage.setScene(home_chercheur);
            
            
            stage.setTitle("Accueil");
            stage.show();
           
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load manageSubmission.fxml");
            }
    }
    @FXML
    void EvaluateArticle(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AccueilEvaluateur.fxml"));
            Parent root = loader.load();
          
            AccueilEvaluateur controller = loader.getController();
          
            controller.setStage(stage);
            controller.setId_ev(getIdChercheur());
            //controller.setUsername(DatabaseConnector.getUsername(getIdChercheur()));
            Scene AccueilEvaluateur = new Scene(root);
            stage.setScene(AccueilEvaluateur);
            
            
            stage.setTitle("AccueilEvaluateur");
            stage.show();
           
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load manageSubmission.fxml");
            }
    }

    @FXML
    void ManageSubmission(MouseEvent event) {
         try {
                    System.out.println("Authentication successful.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_Soumission.fxml"));
                    Parent root = loader.load();
                  
                    manageSubmission controller = loader.getController();
                  
                    controller.setStage(stage);
                    controller.setidCorr(getIdChercheur());
                    controller.setUsername(DatabaseConnector.getUsername(getIdChercheur()));
                    Scene home_chercheur = new Scene(root);
                    stage.setScene(home_chercheur);
                    
                    
                    stage.setTitle("manageSubmission");
                    stage.show();
                   
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Failed to load manageSubmission.fxml");
                    }

    }

    @FXML
    void yourArticleClicked(MouseEvent event) {

    }

    public int getIdChercheur() {
        return IdChercheur;
    }

    public void setIdChercheur(int idChercheur) {
        IdChercheur = idChercheur;
    }


    public Stage getStage() {
        return stage;
    }
    
}
