package Controllers;

import Models.Articles;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CardController {
  
    @FXML
    private ImageView idImage;

    @FXML
    private HBox boxHello;

    @FXML
    private Label idAuteur;

    @FXML
    private Label idNomArticle;

    private String[] colors= {"B9E5FF", "BOB2FE", "FB9AAG", "FF5856"};
    public void setData(Articles article){
        Image image= new Image(getClass().getResourceAsStream(article.getImagepth()));
        idImage.setImage(image);
        idNomArticle.setText(article.getTitre());
        idAuteur.setText(article.getAuteurs());
        boxHello.setStyle("-fx-background-color: "+Color.web(colors[(int) (Math.random()*colors.length)]));
    }
}
