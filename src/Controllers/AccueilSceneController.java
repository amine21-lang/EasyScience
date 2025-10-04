package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilSceneController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void btnadmincliched(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/editeurButtonsScene.fxml"));
            Parent root = loader.load();
            ArticlesController controller = loader.getController();
            controller.setStage(stage);
            Scene EditeurScene = new Scene(root);
            stage.setScene(EditeurScene);
            stage.setTitle("EditeurScene");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
