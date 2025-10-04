package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import dataBase.DatabaseConnector;

public class manageSubmission {

    private Stage stage;
    private String userName;
    private int idCorr;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setUsername(String username)
    {
        this.userName=username;

    }
    public void setidCorr(int id)
    {
        this.idCorr=id;

    }

    @FXML
    void addArticle(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/addSubmission.fxml"));
            Parent root = loader.load();
          

            addSubmissionController controller = loader.getController();
            controller.setidCorr(idCorr);
          
            if (controller == null) {
                System.out.println("Controller is null!");
            } else {
                System.out.println("Controller loaded successfully.");
            }
         
            controller.setStage(stage);
            controller.setidCorr(idCorr);
            controller.addAuthors();
         

            Scene addSubmissionScene = new Scene(root);
            stage.setScene(addSubmissionScene);
            stage.setTitle("ADD a submission");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
            controller.setId_ev(getIdCorr());
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
                    controller.setidCorr(getIdCorr());
                    controller.setUsername(DatabaseConnector.getUsername(getIdCorr()));
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
    public int getIdCorr() {
        return idCorr;
    }
    @FXML
    void deleteArticle(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/deleteSubmission.fxml"));
            Parent root = loader.load();
          
            deleteSubmissionController controller = loader.getController();
            controller.setidCorr(idCorr);
          
            if (controller == null) {
                System.out.println("Controller is null!");
            } else {
                System.out.println("Controller loaded successfully.");
            }
         
            controller.setStage(stage);
            controller.setidCorr(idCorr);
            controller.seIds();
         
         

            Scene deleteSubmissionScene = new Scene(root);
            stage.setScene(deleteSubmissionScene);
            stage.setTitle("ADD a submission");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    @FXML
    void pursueArticle(ActionEvent event) {

        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/pursueSubmission.fxml"));
            Parent root = loader.load();
          

            pursueSubmissionController controller = loader.getController();
            controller.setidCorr(idCorr);
          
            if (controller == null) {
                System.out.println("Controller is null!");
            } else {
                System.out.println("Controller loaded successfully.");
            }
         
            controller.setStage(stage);
            controller.setidCorr(idCorr);
            controller.seIds();
    
            Scene pursueSubmission = new Scene(root);
            stage.setScene(pursueSubmission);
            stage.setTitle("Pursue a submission");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void updateArticle(ActionEvent event) {
          
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/editSubmission.fxml"));
            Parent root = loader.load();
          

            editSubmissionController controller = loader.getController();
            controller.setidCorr(idCorr);
          
            if (controller == null) {
                System.out.println("Controller is null!");
            } else {
                System.out.println("Controller loaded successfully.");
            }
         
            controller.setStage(stage);
            System.out.println("kadoz "+idCorr);
            controller.setidCorr(idCorr);
            controller.seIds();
    
            Scene editSubmission = new Scene(root);
            stage.setScene(editSubmission);
            stage.setTitle("Edit a submission");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void HomeChercheurClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_chercheur.fxml"));
            Parent root = loader.load();
          
            home_evaluate_finalController controller = loader.getController();
          
            controller.setStage(stage);
            controller.setIdChercheur(getIdCorr());
            controller.setUsername(DatabaseConnector.getUsername(getIdCorr()));
            Scene home_chercheur = new Scene(root);
            stage.setScene(home_chercheur);
            
            
            stage.setTitle("Accueil");
            stage.show();
           
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load manageSubmission.fxml");
            }
    }

   



}
 





