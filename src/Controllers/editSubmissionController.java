package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import Models.article;
import dataBase.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class editSubmissionController implements Initializable {

    private Connection con;
    private Stage stage;
    
    private int idCorr;
  
      private ArrayList<Integer> idArticles;
    private  ObservableList<article> articles;
    private File contentFileInfo;
    private int currentIdArticle;
    LocalDateTime current = LocalDateTime.now();
     Timestamp currentDateTime = Timestamp.valueOf(current);

 
    public int getIdCorr() {
        return idCorr;
    }
    @FXML 
    private TableColumn<article, Integer> idArticle;
    @FXML
    private Pane lengthArticlePane;
    
    @FXML
    private Pane contenuePane;
    @FXML
    private TextField editorDecision;
    
    @FXML
    private TableView<article> articleTableView;

    @FXML
    private Button newContentFile;
    @FXML
    private Button sendButton;
    @FXML
    private TextField length;

    @FXML
    private TextField summaryContent;

    @FXML
    private TableColumn<article, String> titleArticle;

     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idArticle.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        titleArticle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        articleTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            checkHandling();
        });

        
    }
    public void checkHandling()
    {
        String query="SELECT decision FROM REVUE WHERE id_article = ?";
        String decision="";
        String query2="SELECT resumer FROM article WHERE id_article = ? ";

        int ida=getidsEditedArticles();
        System.out.println("the id of the article " + ida);

        try(PreparedStatement ps=con.prepareStatement(query)) {

            ps.setInt(1, ida);
            ResultSet rs=ps.executeQuery();
            this.editorDecision.clear();
            while(rs.next())
            {
                decision=rs.getString("decision");
              
                this.editorDecision.setText(decision);
                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        try(PreparedStatement ps=con.prepareStatement(query2)) {
            ps.setInt(1, ida);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                this.summaryContent.setText(rs.getString("resumer"));
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(checkEtat(ida))
        {
            this.summaryContent.setEditable(true);
            this.contenuePane.setVisible(true);
            this.lengthArticlePane.setVisible(true);

        }
        else 
{
    this.summaryContent.setEditable(false);
    this.contenuePane.setVisible(false);
    this.lengthArticlePane.setVisible(false);

}

    }
     
    public void setidCorr(int id) {
        this.idCorr = id;
    }
    private int getidsEditedArticles() {
        int idA=0;
       
        ObservableList<article> selectedArticles = articleTableView.getSelectionModel().getSelectedItems();
        
       idA=selectedArticles.get(0).getId_article();
       this.currentIdArticle=idA;
       
       System.out.println(idA);

        return idA;
    }

        public editSubmissionController() {
            DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();
        idArticles = new ArrayList<>(); 
   
    }
      public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void seIds()
    {
        // Load data
        this.articles = getArticles();

        articleTableView.setItems(articles);
    }
      private ObservableList<article> getArticles() {
        ObservableList<article> articles = FXCollections.observableArrayList();
        String query1 = "SELECT id_article FROM SOUMISSION WHERE id_corr = ?";
        String query2 = "SELECT * FROM ARTICLE WHERE id_article = ?";

        try (PreparedStatement ps = con.prepareStatement(query1)) {
            System.out.println(idCorr);
            ps.setInt(1, idCorr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idArticles.add(rs.getInt(1));
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(query2)) {
            for (Integer id : idArticles) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String title = resultSet.getString("titre");
                    String contenue = resultSet.getString("contenue");
                    String resumer = resultSet.getString("resumer");
                    String motCles = resultSet.getString("motCles");
                    String categorie = resultSet.getString("Categorie");
                    articles.add(new article(id, title, contenue, resumer, motCles, categorie));
                    System.out.println(title + contenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @FXML
    void uploadNewContentFile(ActionEvent event) {
        article selectedArticle = articleTableView.getSelectionModel().getSelectedItem();
        if (selectedArticle != null) {
            contentFileInfo = chooseFile("D:\\GI\\S2\\java\\Projetcs\\EasyScience\\src\\contentFiles\\");
            if (contentFileInfo != null) {
                System.out.println("Content file selected: " + contentFileInfo.getAbsolutePath());
                if (copyFileToDirectory(contentFileInfo, new File("D:\\GI\\S2\\java\\Projetcs\\EasyScience\\src\\contentFiles\\"), selectedArticle)) {
                    this.length.setText(countWordsInPDF(contentFileInfo.getAbsolutePath()) + "");
                }
            } else {
                System.out.println("Content file has not been uploaded");
            }
        } else {
            System.out.println("No article selected");
        }
    }

    @FXML
    void EvaluateArticle(MouseEvent event) {

    }
    @FXML
    void ManageSubmission(MouseEvent event) {

    }

    @FXML
    void yourArticleClicked(MouseEvent event) {

    }
    @FXML
    void HomeChercheurClicked(MouseEvent event) {
        try {
            System.out.println("Authentication successful.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_chercheur.fxml"));
            Parent root = loader.load();
          
            home_evaluate_finalController controller = loader.getController();
          
            controller.setStage(stage);
            controller.setIdChercheur(getIdCorr());
            controller.setUsername(DatabaseConnector.getUsername(getIdCorr()));
            Scene home_chercheur = new Scene(root);
            stage.setScene(home_chercheur);
            
            
            stage.setTitle("Accueil");
            stage.show();
           
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load manageSubmission.fxml");
            }
    }
   

    // Method to handle file selection
    private File chooseFile(String initialDirPath) {
        FileChooser fileChooser = new FileChooser();

        File initialDir = new File(initialDirPath);
        if (initialDir.exists()) {
            fileChooser.setInitialDirectory(initialDir);
        }

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser.showOpenDialog(stage);
    }

    // Method to copy file to a directory with a random suffix
    private boolean copyFileToDirectory(File selectedFile, File targetDirectory, article selectedArticle) {
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs(); // Create the directory if it doesn't exist
        }
    
        // Check if a file with the same name as the contenue field exists in the target directory
        File oldFile = new File(targetDirectory, selectedArticle.getContenue());
        if (oldFile.exists()) {
            // Delete the old file
            boolean deleted = oldFile.delete();
            if (!deleted) {
                System.out.println("Failed to delete the old file: " + oldFile.getAbsolutePath());
            }
        }
    
        int min = 1000;
        int max = 9999;
        int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
        String randomSuffix = String.valueOf(randomNumber);
        String fileName = selectedFile.getName();
        String fileExtension = ".pdf";
        String fileNameWithRandom = fileName.substring(0, fileName.lastIndexOf('.')) + randomSuffix + fileExtension;
        File targetFile = new File(targetDirectory, fileNameWithRandom);
    
        try {
            Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied to: " + targetFile.getAbsolutePath());
    
            // Update the contenue field of the selected article with the new file name
            selectedArticle.setContenue(fileNameWithRandom);
    
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to copy the file.");
            return false;
        }
    }
    // Method to count words in a PDF file
    private int countWordsInPDF(String filePath) {
        int wordCount = 0;
        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            String[] words = text.split("\\s+");
            wordCount = words.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }
    @FXML
    void sendNewInformations(ActionEvent event) {
        System.out.println("send");
        if (isContentFileUploaded()) {
            System.out.println("dkhl");
            String query = "UPDATE article SET contenue = ?, resumer = ?, taille = ? WHERE id_article = ?";
            String query2="UPDATE EVALUATION  SET avis = ?, remarques = ?, nvNotif = ? WHERE idArticle = ?";
            String query3 = "UPDATE REVUE SET etat = ?, decision = ? WHERE id_article = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, this.contentFileInfo.getName());
                ps.setString(2, this.summaryContent.getText());
                ps.setInt(3, Integer.valueOf(this.length.getText()));
                ps.setInt(4, currentIdArticle);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Article modified successfully");
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try(PreparedStatement ps=con.prepareStatement(query2)) {
               
                ps.setString(1, " ");
                ps.setString(2," ");
                ps.setInt(3, 1);
                ps.setInt(4, currentIdArticle);
                int rows=ps.executeUpdate();
                if(rows>0)
                System.out.println("Evaluation updated");

            } catch (Exception e) {
                // TODO: handle exception
            }
            try(PreparedStatement ps=con.prepareStatement(query3)) {
                ps.setString(1,"ENCOURS");
                ps.setString(2, " ");
                ps.setInt(3, currentIdArticle);
                int rows =ps.executeUpdate();
                if(rows>0)
                System.out.println("revue executed");
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            System.out.println("Content file not uploaded");
        }
    }
    
 

  
     private boolean checkEtat(int idA)
     {
       
        String decision ="";
        String query ="SELECT decision FROM REVUE WHERE id_article = ? ";
        try(PreparedStatement ps= con.prepareStatement(query)) {
            ps.setInt(1,idA);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                decision = rs.getString("decision");

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Decision is "+decision);

        if(decision.equals("Major revision") || decision.equals("Minor revision") )
        {
            System.out.println("allowed to modify");
            return true;
        }

        if(decision=="")
            System.out.println("decision not geted");
        if(decision.equals("Accepte") || decision.equals("Refuse"))
            System.out.println("refused or accepted");
            
        return false;

     }
    // Method to check if a file has been uploaded
    public boolean isContentFileUploaded() {
        return  contentFileInfo != null;
    }

    
}
