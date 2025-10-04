package Controllers;

import dataBase.Article;
import dataBase.ArticleDAO;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ArticlesController {
    // Méthode pour définir la référence à Stage
    public void setStage(Stage stage) {
    }
    @FXML
    private TableView<Article> tableView;

    @FXML
    private TableColumn<Article, String> idColumn;

    @FXML
    private TableColumn<Article, String> titreColumn;

    private ObservableList<Article> articles = FXCollections.observableArrayList();

    // Méthode d'initialisation appelée automatiquement lors du chargement du fichier FXML
    @FXML
    public void initialize() {
        // Configurez les colonnes pour afficher les données d'article
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().getTitreProperty());

        // Chargez les articles depuis la base de données
        loadArticles();
    }

    // Méthode pour charger les articles depuis la base de données
    private void loadArticles() {
        ArticleDAO articleDAO = new ArticleDAO();
        articles.addAll(articleDAO.getAllArticles());
        tableView.setItems(articles);
    }

    // Méthode appelée lors du clic sur le bouton "Accepted"
    @FXML
    void btnAcceptedClicked(ActionEvent event) {
        ObservableList<Article> acceptedArticles = FXCollections.observableArrayList();
        for (Article article : articles) {
            if (article.getEtat().equals("accepte")) {
                acceptedArticles.add(article);
            }
        }
        tableView.setItems(acceptedArticles);
    }

    // Méthode appelée lors du clic sur le bouton "Avalue"
    @FXML
    void btnAvalueClicked(ActionEvent event) {
        // Implémentez le code pour filtrer les articles avec l'état "Avalue"
    }

    // Méthode appelée lors du clic sur le bouton "Encours"
    @FXML
    void btnEncoursClicked(ActionEvent event) {
        // Implémentez le code pour filtrer les articles avec l'état "Encours"
    }

    // Méthode appelée lors du clic sur le bouton "Tous"
    @FXML
    void btnTousClicked(ActionEvent event) {
        // Réinitialisez la liste des articles pour afficher tous les articles
        tableView.setItems(articles);
    }
}
