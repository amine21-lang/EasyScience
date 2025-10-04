package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Models.article;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class deleteSubmissionController implements Initializable {
    private Connection con;
    private Stage stage;
    private int idCorr;
    private ArrayList<Integer> idArticles;
    private ObservableList<article> articles;

    @FXML
    private TableView<article> articleTableView;

    @FXML
    private TableColumn<article, Integer> idArticle;

    @FXML
    private TableColumn<article, String> titleArticle;

    @FXML
    private Button deleteButton;

    public int getIdCorr() {
        return idCorr;
    }

    public deleteSubmissionController() {
        DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();
        idArticles = new ArrayList<>();
        articles = FXCollections.observableArrayList(); // Initialize the ObservableList
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idArticle.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        titleArticle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        articleTableView.setItems(articles); // Set the items for the TableView
    }

    public void seIds() {
        // Clear the articleTableView
        articleTableView.getItems().clear();
        // Load data
        articles = getArticles();
        articleTableView.setItems(articles); // Set the new items for the TableView
    }

    public void setidCorr(int id) {
        this.idCorr = id;
    }

    private ObservableList<article> getArticles() {
        ObservableList<article> articlesList = FXCollections.observableArrayList();
        idArticles.clear(); // Clear the idArticles list to avoid duplication

        String query1 = "SELECT id_article FROM SOUMISSION WHERE id_corr = ?";
        String query2 = "SELECT * FROM ARTICLE WHERE id_article = ?";

        try (PreparedStatement ps = con.prepareStatement(query1)) {
            System.out.println("idCorr: " + idCorr);
            ps.setInt(1, idCorr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idArticle = rs.getInt(1);
                idArticles.add(idArticle);
                System.out.println("Found id_article: " + idArticle);
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
                    articlesList.add(new article(id, title, contenue, resumer, motCles, categorie));
                    System.out.println("Loaded article: " + title);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articlesList;
    }

    private int getidsDeletedArticles() {
        int idA = 0;
        ObservableList<article> selectedArticles = articleTableView.getSelectionModel().getSelectedItems();

        if (!selectedArticles.isEmpty()) {
            idA = selectedArticles.get(0).getId_article();
            System.out.println("Selected article ID: " + idA);
        }

        return idA;
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        String query = "DELETE FROM ARTICLE WHERE id_article = ?";
        String query2 ="DELETE FROM SOUMISSION WHERE id_article = ?";
        int selectedArticleId = getidsDeletedArticles();

        if (selectedArticleId != 0) {
            try (PreparedStatement ps = con.prepareStatement(query2)) {
                ps.setInt(1, selectedArticleId);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("soumission with IDArticle " + selectedArticleId + " deleted");
                    // Refresh the TableView
                    seIds();
                } else {
                    System.out.println("soummison with IDArticle " + selectedArticleId + " found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try(PreparedStatement ps=con.prepareStatement(query)) {
                ps.setInt(1, selectedArticleId);
                int rows=ps.executeUpdate();
                if(rows>0)
                {
                    System.out.println("ARTICLE DELETED");
                }
                else 
                {
                    System.out.println("NOT DELETED");
                }
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            System.out.println("No article selected");
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