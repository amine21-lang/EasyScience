import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class CardController_final {
    @FXML
    private Label ArticletTitle;
    
    @FXML
    private Label mot_resumer;
    @FXML
    private Label Article_Date;
    private Stage stage;
    

    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setTitle(String title) {
        ArticletTitle.setText(title);
    }
    public void setResumer(String resumer) {
        mot_resumer.setText(resumer);
    }
    public void setDate(String Date) {
        Article_Date.setText(Date);
    }
  
    @FXML
    private void click_to_read(MouseEvent event) {
        if (articleData != null && mainController != null) {
            mainController.loadBrowsSceneForArticle(articleData);
        }
    }
    private MainController mainController;

public void setMainController(MainController mainController) {
    this.mainController = mainController;
}
private ArticleData articleData;

public void setArticleData(ArticleData articleData) {
    this.articleData = articleData;
}

}