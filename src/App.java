import java.io.IOException;

import Controllers.MainSceneController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    public void setStage(Stage stage) {
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainScene.fxml"));
        Parent root = loader.load();
        MainSceneController controller = loader.getController();
        controller.setStage(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
