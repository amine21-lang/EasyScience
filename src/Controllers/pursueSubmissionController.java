package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Resultset;

import Models.*;
import dataBase.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class pursueSubmissionController implements Initializable {
   private Connection con;
   private Stage stage;
   private chercheur chercheur;
  
    public int getIdCorr() {
    return idCorr;
}
    private int idCorr;
       private ArrayList<Integer> idArticles;
    private  ObservableList<article> articles;
     @FXML
    private TableView<article> articleTableView;

    @FXML
    private TableColumn<article, Integer> idArticle;

    @FXML
    private TextArea evaluatorsComments;
    @FXML
    private TextField editorDecision;

    @FXML
    private TableColumn<article, String> titleArticle;
     


    public pursueSubmissionController() {
        DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();
        idArticles = new ArrayList<>(); 
        chercheur =new chercheur();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      idArticle.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        titleArticle.setCellValueFactory(new PropertyValueFactory<>("titre"));
          // Add event handler for selection changes
    articleTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        handleTableSelection(newValue);
    });
     
    }
    public void setidCorr(int id)
    {
        this.idCorr=id;

    }
    public void seIds()
    {
        // Load data
        this.articles = getArticles();

        articleTableView.setItems(articles);
    }
     private ObservableList<article> getArticles() {
        ObservableList<article> articles = FXCollections.observableArrayList();
        String query1 = "SELECT id_article FROM SOUMISSION WHERE id_corr = ?";
        String query2 = "SELECT * FROM ARTICLE WHERE id_article = ?";

        try (PreparedStatement ps = con.prepareStatement(query1)) {
            System.out.println("id Corresp " + idCorr);
            ps.setInt(1, idCorr);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                idArticles.add(rs.getInt(1));
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(query2)) {
            for (Integer id : idArticles) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String title = resultSet.getString("titre");
                    String contenue = resultSet.getString("contenue");
                    String resumer = resultSet.getString("resumer");
                    String motCles = resultSet.getString("motCles");
                    String categorie = resultSet.getString("Categorie");
                    articles.add(new article(id, title, contenue, resumer, motCles, categorie));
                    System.out.println(title + contenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
    private void handleTableSelection(article selectedArticle) {
        if (selectedArticle != null) {
            // Set the evaluatorsComments and editorDecision fields based on the selected article
            String evaluatorsCommentsText = "SELECT c.nom, c.prenom, e.avis " +
                                            "FROM EVALUATION e " +
                                            "JOIN chercheur c ON e.id_evaluateur = c.id_chercheur " +
                                            "WHERE e.idArticle = ?";
            String editorDecisionText = "SELECT decision FROM REVUE WHERE id_article = ?";

    
            try (PreparedStatement ps = con.prepareStatement(evaluatorsCommentsText)) {
                ps.setInt(1, selectedArticle.getId_article());
                System.out.println(selectedArticle.getId_article());
                ResultSet rs = ps.executeQuery();
                StringBuilder evaluatorsCommentBuilder = new StringBuilder();
                while (rs.next()) {
                    evaluatorsCommentBuilder
                                            .append(rs.getString("nom"))
                                            .append(" ")
                                            .append(rs.getString("prenom"))
                                            .append( ": ")
                                            .append(rs.getString("avis"))
                                            .append("\n");
                }
                evaluatorsComments.setText(evaluatorsCommentBuilder.toString());


            } catch (Exception e) {
                e.printStackTrace();
            }
            try (PreparedStatement ps = con.prepareStatement(editorDecisionText)) {
                ps.setInt(1, selectedArticle.getId_article());
                System.out.println(selectedArticle.getId_article());
                ResultSet rs = ps.executeQuery();
                
                editorDecision.clear();
                while (rs.next()) {
                    String tmp=rs.getString("decision");
                    editorDecision.setText(tmp);
                  
                }
              


            } catch (Exception e) {
                e.printStackTrace();
            }
    
    
          
        } else {
            evaluatorsComments.setText("");
            editorDecision.setText("");
        }
    }
    @FXML
    void EvaluateArticle(MouseEvent event) {

    }
    @FXML
    void ManageSubmission(MouseEvent event) {

    }

    @FXML
    void yourArticleClicked(MouseEvent event) {

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
