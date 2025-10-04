package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;



import Models.Articles;
import Models.evaluation;
import dataBase.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EvaluerSceneController {
    
    private Articles article;
    private int id;
    private evaluation ev;
    private int id_evaluateur;
    private Connection con;
     LocalDateTime current = LocalDateTime.now();
     Timestamp currentDateTime = Timestamp.valueOf(current);
    

 public int getId_evaluateur() {
        return id_evaluateur;
    }
    

    public EvaluerSceneController() {
        DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
		con = dbc.getConnection();

}


    public void setId_evaluateur(int id_evaluateur) {
        this.id_evaluateur = id_evaluateur;
    }

@FXML
    private Label TitleID;

    @FXML
    private TextField remarqueID;

    @FXML
    private Label resumeID;
      @FXML
    private RadioButton acceptedBtn;

    @FXML
    private ToggleGroup decision;

    @FXML
    private RadioButton majeurREVBtn;

    @FXML
    private RadioButton mineurRevbtn;

    @FXML
    private RadioButton refuBtn;

     private Stage stage;
    public void setStage(Stage stage) {
        this.stage=stage;
    }

   @FXML
void btnEvaluerClicked(ActionEvent event) throws IOException {
    String decisionChoisie = getSelectedDecision();
    String remarque = remarqueID.getText();
    this.ev = new evaluation(decisionChoisie, remarque, id, id_evaluateur,0);
    System.out.println("Décision: " + ev.getAvis());
    System.out.println("Remarque: " + remarque);
    System.out.println("id article: " + id);
    System.out.println("évaluateur: " + id_evaluateur);

    if (remarque != null && !remarque.trim().isEmpty() && decisionChoisie != null) {

        String query = "UPDATE EVALUATION SET avis = ?, remarques = ?, nombreRevision = ?, dateRevision = ?, nvNotif = ? WHERE id_evaluateur = ? AND idArticle = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ev.incrRevisionNumber();

    System.out.println("incr " +ev.getNombreRevision());
            ps.setString(1, ev.getAvis());
            ps.setString(2, ev.getRemarques());
           
            ps.setInt(3, ev.getNombreRevision());
            ps.setTimestamp(4,currentDateTime);
            ps.setInt(5, 0);
            ps.setInt(6, id_evaluateur);
            ps.setInt(7, id);

            int rows = ps.executeUpdate();
            if(rows>0)
            System.out.println("evaluation updated");
            System.out.println(ev.getAvis());

            if (rows > 0) {
                System.out.println("Evaluation added successfully");
            } else {
                System.out.println("Evaluation not added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AccueilEvaluateur.fxml"));
                Parent root = loader.load();

         AccueilEvaluateur controller = loader.getController();
              
                controller.setStage(stage);
                Scene AccueilScene = new Scene(root);
                stage.setScene(AccueilScene);
                
                controller.setId_ev(this.id_evaluateur);
                controller.updateTable();
                controller.searchArticle();
            
                
                stage.setTitle("Accueil");
                stage.show();
    } else {
        System.out.println("Remarque or decision is null/empty");
    }
}

    void lire() {
                System.out.println(id);
        if (id >= 0) {
            Articles article = DatabaseConnector.getArticleById(id);
            if (article != null) {
                System.out.println(article.toString());
                TitleID.setText(article.getTitre());
                resumeID.setText(article.getresumer());
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
    private String getSelectedDecision() {
        RadioButton selectedRadioButton = (RadioButton) decision.getSelectedToggle();
        return selectedRadioButton == null ? "Aucune sélection" : selectedRadioButton.getText();
    }

}
