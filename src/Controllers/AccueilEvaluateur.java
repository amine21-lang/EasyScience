package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Articles;
import dataBase.DatabaseConnector;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AccueilEvaluateur implements Initializable{
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TableView<Articles> tableArticles;
        @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Articles, Integer> etatN;

    @FXML
    private TableColumn<Articles, Integer> revisionNumber;

    @FXML
    private TableColumn<Articles, Integer> colId;

    @FXML
    private TableColumn<Articles, String> colTitre;



    @FXML
    private HBox cardLayout;
    ObservableList<Articles> listM;
    
    ObservableList<Articles> dataList;
    private int id_ev;
    public int getId_ev() {
        return id_ev;
    }
    

    public void setId_ev(int id_ev) {
        this.id_ev = id_ev;
    }

    int id = -1;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @FXML
    void HomeChercheurClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_chercheur.fxml"));
            Parent root = loader.load();
          
            home_evaluate_finalController controller = loader.getController();
          
            controller.setStage(stage);
            controller.setIdChercheur(getId_ev());
            controller.setUsername(DatabaseConnector.getUsername(getId_ev()));
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
            controller.setId_ev(getId_ev());
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
                    controller.setidCorr(getId_ev());
                    controller.setUsername(DatabaseConnector.getUsername(getId_ev()));
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
    
    public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        revisionNumber.setCellValueFactory(new PropertyValueFactory<>("nr"));
       etatN.setCellValueFactory(new PropertyValueFactory<>("nvn"));
      
    
       // listM = DatabaseConnector.getNonEvaluatedArticles(this.id_ev);
        listM = DatabaseConnector.getNonEvaluatedArticles();
        tableArticles.setItems(listM);
    }

    @FXML
 
 
    void searchArticle() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        revisionNumber.setCellValueFactory(new PropertyValueFactory<>("nr"));
     etatN.setCellValueFactory(new PropertyValueFactory<>("nvn"));
        
        System.out.println("ide==" + id_ev);
        dataList = DatabaseConnector.getNonEvaluatedArticles(this.id_ev);
        tableArticles.setItems(dataList);
        FilteredList<Articles> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(articles -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (articles.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                }  else
                    return false;
            });
        });
        SortedList<Articles> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableArticles.comparatorProperty());
        tableArticles.setItems(sortedData);
    }

@FXML
void getSelected(MouseEvent event) {
    id = colId.getCellData(tableArticles.getSelectionModel().getSelectedIndex());
    System.out.println(id);
    if (id <= -1) {
        return;
    }
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EvaluateInfos.fxml"));
        Parent root = loader.load();
        EvaluateInfosController controller = loader.getController();
        controller.setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        //controller.setId(colId.getCellData(tableArticles.getSelectionModel().getSelectedIndex()));
        controller.setId_ev(id_ev);

      
        Scene EvaluateInfos = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(EvaluateInfos);
        controller.setId(id);
        controller.setArticle(DatabaseConnector.getArticleById(id));
        controller.lire();
        Articles article = DatabaseConnector.getArticleById(id);
        stage.setTitle("Evaluate Infos");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       updateTable();
    }

}
