package Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Articles;
import dataBase.DatabaseConnector;

public class EditeurSceneController  {

private List<Integer> selectedEvaluatorIds = new ArrayList<>();

        private Connection con;

int id = -1;
private Stage stage;// Ajoutez une variable pour stocker la scène précédente

public void setId(int id) {
    this.id = id;
}

// Méthode pour définir la référence à Stage
public void setStage(Stage stage) {
    this.stage = stage;
}
    @FXML
    private VBox vbox;
private int idArticle=0;

    public int getIdArticle() {
    return idArticle;
}

public void setIdArticle(int idArticle) {
    this.idArticle = idArticle;
}
    ObservableList<Articles> listM;
    
    ObservableList<Articles> dataList;
    @FXML
    private TextArea ArticleSize;
    public void setArticleSize(String articleSize) {
        ArticleSize.setText(articleSize); 
    }

    @FXML
    private TableView<Articles> tableArticles;
        @FXML
    private TextField filterField;

    @FXML
    private TableColumn<Articles, Integer> colId;

    @FXML
    private TableColumn<Articles, String> colTitre;


    @FXML
    private ListView<String> listViewArticles;

    @FXML
    void ArticlesAccepted(MouseEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       
       System.out.println(colId);
       System.out.println(colTitre);

        listM = DatabaseConnector.getArticAccept();
        tableArticles.setItems(listM);
    }

    @FXML
    void ArticlesInReview(MouseEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       
       System.out.println(colId);
       System.out.println(colTitre);

        listM = DatabaseConnector.getArticleInReview();
        tableArticles.setItems(listM);
    }

    @FXML
    void ArticlesStillOnEval(MouseEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       
       System.out.println(colId);
       System.out.println(colTitre);

        listM = DatabaseConnector.getArticlesStillEvaluation();
        tableArticles.setItems(listM);
    }
   
  
    @FXML
    void ArticlesnotEval(MouseEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       
       System.out.println(colId);
       System.out.println(colTitre);

        listM = DatabaseConnector.getNonEvaluatedArticles();
        tableArticles.setItems(listM);
        
    }

    @FXML
    void ValidetBtn(ActionEvent event) {
        
        for (Integer id : selectedEvaluatorIds) {
            DatabaseConnector.chooseEvaluators(idArticle,id);
           System.out.println(id);
            System.out.println(idArticle);
        }
      
    }
    public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       
       System.out.println(colId);
       System.out.println(colTitre);

        listM = DatabaseConnector.getDataArticle();
        tableArticles.setItems(listM);
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
    

 public EditeurSceneController() {
    DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();

    }
/* 
@FXML
void getSelected(MouseEvent event) {
   int idArticle = colId.getCellData(tableArticles.getSelectionModel().getSelectedIndex());
    System.out.println(idArticle);
    System.out.println(DatabaseConnector.getDomaine(id));
    System.out.println(DatabaseConnector.getInstitution(id));
    System.out.println(DatabaseConnector.getTaille(idArticle));
    System.out.println(idArticle);
    vbox.getChildren().clear(); 
    addEvaluators(idArticle);
    
    System.out.println( DatabaseConnector.getTaille(idArticle));
    if (id <= -1) {
        return;
    }
    setArticleSize(DatabaseConnector.getTaille(idArticle)+"");
    
}
public ArrayList<Integer> addEvaluators(int idArticle) {
    vbox.getChildren().clear(); 
    String query = "SELECT c.id_chercheur, c.nom "
    + "FROM chercheur c "
    + "WHERE c.domaine = ? "
    + "AND c.institution <> ? "
    + "AND c.id_chercheur NOT IN ( "
    + "  SELECT a.Id_liste "
    + "  FROM auteurs a "
    + "  WHERE a.id_article = ? "
    + ");";
        ArrayList<Integer> ids = new ArrayList<>();
        try (PreparedStatement preparedS = con.prepareStatement(query)) {
           // preparedS.setInt(1, idCorr);
           preparedS.setString(1, DatabaseConnector.getDomaine(id));
           preparedS.setString(2, DatabaseConnector.getInstitution(id));
           preparedS.setInt(3, idArticle);


            ResultSet rs = preparedS.executeQuery();
            while (rs.next()) {
                CheckBox checkBox = new CheckBox(rs.getString("Nom"));
                    checkBox.setUserData(rs.getInt("id_chercheur")); 
                    vbox.getChildren().add(checkBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // return ids;
       return ids;
    }



private void limitCheckboxSelection(int idArticle) {
    int tailleArticle = DatabaseConnector.getTaille(idArticle);
    int maxEvaluators = tailleArticle <= 4000 ? 3 : 4;
    int selectedCount = 0;

    for (Node node : vbox.getChildren()) {
        if (node instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected()) {
                selectedCount++;
            }
            if (selectedCount > maxEvaluators) {
                checkBox.setSelected(false);
            }
        }
    }
}
*/
/* 
private void limitCheckboxSelection(int idArticle) {
    int tailleArticle = DatabaseConnector.getTaille(idArticle);
    int maxEvaluators = tailleArticle <= 4000 ? 3 : 4;
    int selectedCount = 0;
    if (vbox.getChildren() != null) {
    for (Node node : vbox.getChildren()) {
        if (node instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected()) {
                selectedCount++;
                if (!selectedEvaluatorIds.contains((Integer) checkBox.getUserData())) {
                    selectedEvaluatorIds.add((Integer) checkBox.getUserData());
                }
            } else {
                selectedEvaluatorIds.remove((Integer) checkBox.getUserData());
            }
            if (selectedCount > maxEvaluators) {
                checkBox.setSelected(false);
                selectedEvaluatorIds.remove((Integer) checkBox.getUserData());
            }
        }
    }
}
}
*/
private void limitCheckboxSelection(int idArticle) {
    int tailleArticle = DatabaseConnector.getTaille(idArticle);
    int maxEvaluators = tailleArticle <= 4000 ? 3 : 4;
    int selectedCount = 0;

    if (vbox.getChildren() != null) {
        for (Node node : vbox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    selectedCount++;
                    Integer evaluatorId = (Integer) checkBox.getUserData();
                    if (!selectedEvaluatorIds.contains(evaluatorId)) {
                        selectedEvaluatorIds.add(evaluatorId);
                    }
                } else {
                    selectedEvaluatorIds.remove((Integer) checkBox.getUserData());
                }

                if (selectedCount > maxEvaluators) {
                    checkBox.setSelected(false);
                    selectedEvaluatorIds.remove((Integer) checkBox.getUserData());
                    selectedCount--;
                }
            }
        }
    }
}

@FXML
void getSelected(MouseEvent event) {
    setIdArticle(colId.getCellData(tableArticles.getSelectionModel().getSelectedIndex()));
    System.out.println(id);
     System.out.println("id article"+ idArticle);
     System.out.println("id getDomaine"+DatabaseConnector.getDomaine(id));
     System.out.println("id getInstitution"+DatabaseConnector.getInstitution(id));
     System.out.println("id getTaille"+DatabaseConnector.getTaille(idArticle));
     System.out.println("id article"+idArticle);
     vbox.getChildren().clear(); 
     addEvaluators(idArticle);
     //alllshif();
     
     System.out.println( DatabaseConnector.getTaille(idArticle));
     if (id <= -1) {
         return;
     }
     setArticleSize(DatabaseConnector.getTaille(idArticle)+"");
     
 }
/* 
public ArrayList<Integer> addEvaluators(int idArticle) {
    
    String query = "SELECT c.id_chercheur, c.nom "
    + "FROM chercheur c "
    + "WHERE c.domaine = ? "
    + "AND c.institution <> ? "
    + "AND c.id_chercheur NOT IN ( "
    + "  SELECT a.Id_liste "
    + "  FROM auteurs a "
    + "  WHERE a.id_article = ? "
    + ");";
    ArrayList<Integer> ids = new ArrayList<>();
    try (PreparedStatement preparedS = con.prepareStatement(query)) {
        preparedS.setString(1, DatabaseConnector.getDomaine(idArticle));
        preparedS.setString(2, DatabaseConnector.getInstitution(idArticle));
        preparedS.setInt(3, idArticle);

        ResultSet rs = preparedS.executeQuery();
        while (rs.next()) {
            CheckBox checkBox = new CheckBox(rs.getString("nom"));
            checkBox.setUserData(rs.getInt("id_chercheur"));
            vbox.getChildren().add(checkBox);
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                limitCheckboxSelection(idArticle);
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ids;
}
*/


public ArrayList<Integer> addEvaluators(int idArticle) {
    String query = "SELECT c.id_chercheur, c.nom "
                 + "FROM chercheur c "
                 + "WHERE c.domaine = ? "
                 + "AND c.institution <> ? "
                 + "AND c.id_chercheur NOT IN ( "
                 + "  SELECT a.Id_liste "
                 + "  FROM auteurs a "
                 + "  WHERE a.id_article = ? "
                 + ");";

    ArrayList<Integer> ids = new ArrayList<>();

    try (PreparedStatement preparedS = con.prepareStatement(query)) {
       preparedS.setString(1, DatabaseConnector.getDomaine(id));
       
      
        preparedS.setString(2, DatabaseConnector.getInstitution(id));
       
        
       preparedS.setInt(3, idArticle);

        ResultSet rs = preparedS.executeQuery();
        while (rs.next()) {
            int idChercheur = rs.getInt("id_chercheur");
            String nom = rs.getString("nom");

            CheckBox checkBox = new CheckBox(nom);
            checkBox.setUserData(idChercheur);
            vbox.getChildren().add(checkBox);

            // Add the evaluator ID to the list
            ids.add(idChercheur);

            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                limitCheckboxSelection(idArticle);
            });
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ids;
}

public ArrayList<Integer> alllshif() {
    
    String query = "SELECT c.id_chercheur, c.nom "
    + "FROM chercheur c ";
    ArrayList<Integer> ids = new ArrayList<>();
    try (PreparedStatement preparedS = con.prepareStatement(query)) {
        

        ResultSet rs = preparedS.executeQuery();
        while (rs.next()) {
            CheckBox checkBox = new CheckBox(rs.getString("nom"));
            checkBox.setUserData(rs.getInt("id_chercheur"));
            vbox.getChildren().add(checkBox);
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                limitCheckboxSelection(idArticle);
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ids;
}





@FXML
void DownloadArticleClicked(MouseEvent event) {

}
@FXML
 
 
void searchArticle() {
    colId.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
    colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    
    dataList = DatabaseConnector.getNonEvaluatedArticles(id);
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

    
}
