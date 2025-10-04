import java.io.IOException; 
import javafx.fxml.FXML; 
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage; 
public class Brows_scene_lire_Controller { 
    private Stage stage; 
     @FXML
    private HBox brows;
public Stage getStage() { 
    return stage; } 
public void setStage(Stage stage) { 
        this.stage = stage; 
    } 
@FXML private Label ArticleContenue; 
@FXML private Label ArticleTitle; 
@FXML private Button BackButton; 
@FXML public void setArticleData(String title ,String contenue ){ 
    this.ArticleContenue.setText(contenue); 
this.ArticleTitle.setText(title); 
} 


/*
 * @FXML
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
 */
@FXML
void back_to_articles(MouseEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


/* 
@FXML private void click_back(MouseEvent event) { 
    try { FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml")); 
Parent root = loader.load(); MainController controller = loader.getController(); 
Stage stage = (Stage) BackButton.getScene().getWindow(); stage.getScene().setRoot(root);
 } catch (IOException e) { e.printStackTrace(); } 
} 
*/


/* @FXML private void click_back(MouseEvent event) { 
    Stage stage = (Stage) BackButton.getScene().getWindow(); stage.close(); }*/ 
}