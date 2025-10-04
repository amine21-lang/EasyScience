package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import Models.Articles;
import dataBase.Article;
import dataBase.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EvaluateInfosController implements Initializable {
    private int id;
    private Stage stage;
    private Articles article;
    private int id_ev;
    public int getId_ev() {
        return id_ev;
    }

    public void setId_ev(int id_ev) {
        this.id_ev = id_ev;
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }
    @FXML
    private Label descriptionId;

    @FXML
    private ImageView imagepth;

    @FXML
    private Label titleId;

    @FXML
    private Label usernameId;

    @FXML
    void evaluerClicked(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EvaluerScene.fxml"));
            Parent root = loader.load();
            EvaluerSceneController controller = loader.getController();
            controller.setStage(stage);
            Scene EvaluerScene = new Scene(root);
            stage.setScene(EvaluerScene);
            controller.setId(id);
            controller.setId_evaluateur(id_ev);
            controller.setArticle(DatabaseConnector.getArticleById(id));
            controller.lire();
            stage.setTitle("Evaluer");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void lireClicked(ActionEvent event) {

        String path = "D:\\GI\\S2\\java\\Projetcs\\EasyScience\\src\\contentFiles"; //change path

        Articles article=DatabaseConnector.getArticleById(id);
        if(article!=null)
        {
            downloadFile(path + article.getContenue());
        }
            }

              private void downloadFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("File path is invalid.");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(file.getName());
        File destFile = fileChooser.showSaveDialog(stage);

        if (destFile != null) {
            try {
                Files.copy(file.toPath(), destFile.toPath());
                System.out.println("File downloaded to: " + destFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to download the file.");
            }
        }
    }

    void lire() {
                System.out.println(id);
        if (id >= 0) {
            Articles article = DatabaseConnector.getArticleById(id);
            if (article != null) {
                System.out.println(article.toString());
                titleId.setText(article.getTitre());
                descriptionId.setText(article.getResumer());
            } else {
                System.out.println("L'article avec l'ID " + id + " n'a pas été trouvé.");
            }
        } else {
            System.out.println("Index invalide.");
        }

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArticle(Articles article) {
        this.article = article;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
    }
    

    
   

}

