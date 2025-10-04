package Controllers;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


import dataBase.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class addSubmissionController implements Initializable  {

    private Connection con;
    private Stage stage;
    private File contentFileInfo;
    
    private int idCorr;
    @FXML
    private TextField category;
    private ArrayList<Integer> authorsSelected;
     @FXML
    private TextField content;
    @FXML
    private Button sendButton;

    @FXML
    private TextField keywords;
    @FXML
    private TextField title;
    
    @FXML
    private TextField length;

    @FXML
    private Button contentFile;
 
      @FXML
    private TextArea resumer;


    @FXML
    private VBox vbox;

    public addSubmissionController() {
        DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     
     
    }
    public void setidCorr(int id)
    {
        this.idCorr=id;

    
    }

    public int getIdCorr() {
        return idCorr;
    }
    public ArrayList<Integer> addAuthors() {
        String query = "SELECT * FROM chercheur WHERE userrole = 'C' AND id_chercheur != ?";
        ArrayList<Integer> ids = new ArrayList<>();
        try (PreparedStatement preparedS = con.prepareStatement(query)) {
            preparedS.setInt(1, idCorr);
            System.out.println("idc"+idCorr);
            ResultSet rs = preparedS.executeQuery();
            while (rs.next()) {
                CheckBox checkBox = new CheckBox(rs.getString("userName"));
                checkBox.setUserData(rs.getInt("id_chercheur")); // Store the ID in the userData property
                vbox.getChildren().add(checkBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }
    private ArrayList<Integer> getCheckedAuthors() {
        ArrayList<Integer> checkedAuthors = new ArrayList<>();
        for (var node : vbox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected() && checkBox.getUserData() != null) { // Check if userData is not null
                    checkedAuthors.add((Integer) checkBox.getUserData());
                }
            }
        }
        return checkedAuthors;
    }
    @FXML
    void uploadContentFile(ActionEvent event) {
        contentFileInfo = chooseFile("D:\\GI\\S2\\java\\Projetcs\\EasyScience\\src\\contentFiles");
        if (contentFileInfo != null) {
            System.out.println("Content file selected: " + contentFileInfo.getAbsolutePath());
            if (copyFileToDirectory(contentFileInfo, new File("D:\\GI\\S2\\java\\Projetcs\\EasyScience\\src\\contentFiles"))) {
                this.length.setText(countWordsInPDF(contentFileInfo.getAbsolutePath()) + "");
             
            }
        } else {
            System.out.println("Content file has not been uploaded");
           
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
    private boolean copyFileToDirectory(File selectedFile, File targetDirectory) {
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs(); // Create the directory if it doesn't exist
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

    // Method to check if a file has been uploaded
    public boolean isContentFileUploaded() {
        return contentFile != null;
    }

void homeSubClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/home_Soumission.fxml"));
            Parent root = loader.load();
        
            manageSubmission controller = loader.getController();
        
            controller.setStage(stage);
            Scene home_Soumission = new Scene(root);
            stage.setScene(home_Soumission);
          controller.setidCorr(idCorr);
           System.out.println(DatabaseConnector.getUsername(idCorr));
           
            stage.setTitle("home_Soumission");
            stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load EditeurScene.fxml");
        }

    }


    @FXML
    void sendInformations(ActionEvent event) {
        this.authorsSelected = getCheckedAuthors();
        if (isContentFileUploaded()) {
            int listAuthorId = 0;
            int articleId = 0;
            String queryAddArticle = "INSERT INTO ARTICLE (titre, contenue, resumer, motCles, categorie, taille) VALUES (?, ?, ?, ?, ?,?)";
            String querySetSubmission = "INSERT INTO SOUMISSION (id_corr, id_article) VALUES (?, ?)";
            String queryInsertAuthor = "INSERT INTO AUTEURS(id_auteur, id_article) VALUES (?, ?)";
    
            try (PreparedStatement preparedS = con.prepareStatement(queryAddArticle, Statement.RETURN_GENERATED_KEYS)) {
                preparedS.setString(1, this.title.getText());
                preparedS.setString(2, contentFileInfo.getName());
                preparedS.setString(3, this.resumer.getText());
                preparedS.setString(4, this.keywords.getText());
                preparedS.setString(5, this.category.getText());
                preparedS.setInt(6, Integer.valueOf(this.length.getText()));
    
                int rows = preparedS.executeUpdate();
                if (rows > 0) {
                    System.out.println("Article added successfully");
    
                    try (ResultSet generatedKeys = preparedS.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            articleId = generatedKeys.getInt(1);
                            System.out.println("Created article ID: " + articleId);
    
                            // Insert authors
                            for (Integer authorId : authorsSelected) {
                                try (PreparedStatement ps = con.prepareStatement(queryInsertAuthor)) {
                                    ps.setInt(1, authorId);
                                    ps.setInt(2, articleId);
                                    int authorRows = ps.executeUpdate();
                                    if (authorRows > 0) {
                                        System.out.println("Author " + authorId + " associated with article " + articleId);
                                    } else {
                                        System.out.println("Failed to associate author " + authorId);
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
    
                            // Insert submission
                            try (PreparedStatement ps = con.prepareStatement(querySetSubmission)) {
                                ps.setInt(1, idCorr);
                                ps.setInt(2, articleId);
                                int submissionRows = ps.executeUpdate();
                                if (submissionRows > 0) {

                                    System.out.println("Submission added successfully");

                                    homeSubClicked(event);



                                } else {
                                    System.out.println("Failed to add submission for article " + articleId);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("No generated keys found for the article.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Article has not been added");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please upload both content and summary files.");
        }
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
    @FXML
    void EvaluateArticle(MouseEvent event) {

    }
    @FXML
    void ManageSubmission(MouseEvent event) {

    }

    @FXML
    void yourArticleClicked(MouseEvent event) {

    }
}