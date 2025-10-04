package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.Articles;
import dataBase.DatabaseConnector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditeurDecisionSceneController implements Initializable{
    private Stage stage;// Ajoutez une variable pour stocker la scène précédente

    // Méthode pour définir la référence à Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }
   

   Articles article=new Articles(0, null);
    @FXML
    private RadioButton acceptedBtn;

    @FXML
    private TableColumn<Articles, Integer> colId;

    @FXML
    private TableColumn<Articles, String> colTitre;

    @FXML
    private ToggleGroup decisin;

    @FXML
    private RadioButton majeurREVBtn;

    @FXML
    private RadioButton mineurRevbtn;

    @FXML
    private RadioButton refuBtn;

    @FXML
    private TextField remarqueID;

    @FXML
    private TableView<Articles> tableArticles;
    private int id=-1;
    
    public void setId(int id) {
        this.id = id;
    }
private int idArticle;


    public int getIdArticle() {
    return idArticle;
}

public void setIdArticle(int idArticle) {
    this.idArticle = idArticle;
}


    ObservableList<Articles> listM;
    @FXML
    void ValidateBtnClicked(ActionEvent event) {
        String decisionChoisie = getSelectedDecision();
        String decision = remarqueID.getText();
        String etat;
        System.out.println(decisionChoisie);
        System.out.println(decision);
        Connection conn = DatabaseConnector.getConnection();
        if (decisionChoisie.equals("Accept")) {
            etat = "Accept";
        } else if (decisionChoisie.equals("Major revision")) {
            etat = "En Attente";
        } else if (decisionChoisie.equals("Minor revision")) {
            etat = "En cours";
        } else {
            etat = "";
        }
    
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO revue (decision, etat, id_article, id_editeur  ) VALUES(?,?,?,?) ");
            ps.setString(1, decisionChoisie);
            ps.setString(2, etat);
            ps.setInt(3, idArticle);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditeurScene.fxml"));
            Parent root = loader.load();
            EditeurSceneController controller = loader.getController();
            controller.setStage(stage);
            Scene EditeurScene = new Scene(root);
            stage.setScene(EditeurScene);
            stage.setTitle("home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    private String getSelectedDecision() {
        RadioButton selectedRadioButton = (RadioButton) decisin.getSelectedToggle();
        return selectedRadioButton == null ? "Aucune sélection" : selectedRadioButton.getText();
    }

    @FXML
    void getSelected(MouseEvent event) {
        idArticle = colId.getCellData(tableArticles.getSelectionModel().getSelectedIndex());
        System.out.println(idArticle);
        article= DatabaseConnector.getRemarquesById(idArticle);
        System.out.println(article.getRemarque());
        remarqueID.setText("Remarque : "+ article.getRemarque() + " avis : "+ article.getAvis());

         System.out.println(article.getRemarque());
        if (id <= -1) {
            return;
        }
        
    }
 public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        

        listM = DatabaseConnector.getArticlesStillEvaluation();
        tableArticles.setItems(listM);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
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
      

}
