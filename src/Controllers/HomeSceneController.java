package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeSceneController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void btn_AuthentificateClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/LoginScene.fxml"));
            Parent root = loader.load();
            loginSceneController controller = loader.getController();
            controller.setStage(stage);
            Scene loginScene = new Scene(root);
            stage.setScene(loginScene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void btn_GuestClicked(ActionEvent event) {
        // Logique pour le bouton "Continue As Guest"
    }
}
