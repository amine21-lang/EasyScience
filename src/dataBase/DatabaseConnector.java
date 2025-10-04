package dataBase;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Articles;
import Models.evaluation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/easyscience";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private Connection con;
    
    private static DatabaseConnector dbc;

    // Méthode pour établir une connexion à la base de données
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
    public static DatabaseConnector getDatabaseConnection() {
        if (dbc == null) {
            dbc = new DatabaseConnector();
        }
        return dbc;
    }


    private DatabaseConnector() {
        con=getConnection();
    }







        public static ObservableList<Articles> getDataArticle(){
        Connection conn = getConnection();
        ObservableList<Articles> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from article");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                Articles article = new Articles(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getString("resumer")
                );
                list.add(article);               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ObservableList<Articles> getNonEvaluatedArticles(){
        Connection conn = getConnection();
        ObservableList<Articles> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ARTICLE.id_article, ARTICLE.titre, ARTICLE.resumer " +
            "FROM ARTICLE " +
            "LEFT JOIN EVALUATION ON ARTICLE.id_article = EVALUATION.idArticle " +
            "WHERE EVALUATION.idArticle IS NULL");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                Articles article = new Articles(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getString("resumer")
                );
                list.add(article);               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
 /*   
    public static ObservableList<Articles> getArticlesStillEvaluation(){
        Connection conn = getConnection();
        ObservableList<Articles> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ARTICLE.id_article, ARTICLE.titre, ARTICLE.resumerFROM ARTICLE INNER JOIN EVALUATION ON ARTICLE.id_article = EVALUATION.idArticle LIMIT 1");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                Articles article = new Articles(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getString("resumer")
                );
                list.add(article);               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    */
    public static ObservableList<Articles> getArticlesStillEvaluation() {
        Connection conn = getConnection();
        ObservableList<Articles> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ARTICLE.id_article, ARTICLE.titre, ARTICLE.resumer FROM ARTICLE INNER JOIN EVALUATION ON ARTICLE.id_article = EVALUATION.idArticle GROUP BY ARTICLE.id_article");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Articles article = new Articles(
                        rs.getInt("id_article"),
                        rs.getString("titre"),
                        rs.getString("resumer")
                );
                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static String gettitreByID(int id){
        Connection conn = getConnection();
        String titre = null;
       
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT titre FROM article WHERE id_article = ?");
            
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                titre= rs.getString("titre");
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return titre;
    }


    public static ObservableList<Articles> getArticlesEtatArticles(){
        Connection conn = getConnection();
        ObservableList<Articles> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id_article, etat " +
            "FROM revue ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int idArticle = rs.getInt("id_article");
                String etat = rs.getString("etat");
                String titre = gettitreByID(idArticle);
                Articles article = new Articles(idArticle, titre, etat,0);
                list.add(article);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public static ObservableList<Articles> getNonEvaluatedArticles(int ide){
        Connection conn = getConnection();
        ObservableList<Articles> list = FXCollections.observableArrayList();
      
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ARTICLE.id_article, ARTICLE.titre, ARTICLE.resumer, EVALUATION.nvNotif, EVALUATION.nombreRevision " +
            "FROM ARTICLE " +
            "LEFT JOIN EVALUATION ON ARTICLE.id_article = EVALUATION.idArticle " +
            "WHERE EVALUATION.id_evaluateur = ? AND (EVALUATION.nombreRevision = ? OR EVALUATION.nvNotif = ?)");
            ps.setInt(1, ide);
            ps.setInt(2, 0);
            ps.setInt(3, 1);
            System.out.println("ide = "+ide);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                evaluation evv=new evaluation(Integer.valueOf(rs.getString("nombreRevision")),rs.getInt("nvNotif"));
               System.out.println("nomre revision "+ evv.getNvNotif());
                Articles article = new Articles(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getString("resumer"));
                
                list.add(article);               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


public static ObservableList<Articles> getArticleInReview() {
    Connection conn = getConnection();
    ObservableList<Articles> list = FXCollections.observableArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = conn.prepareStatement(
                "SELECT ARTICLE.id_article, ARTICLE.titre, ARTICLE.resumer " +
                "FROM ARTICLE " +
                "INNER JOIN evaluation ON ARTICLE.id_article = evaluation.idArticle " +
                "WHERE evaluation.avis IN (?, ?)"
        );
        ps.setString(1, "Major revision");
        ps.setString(2, "Minor revision");
        rs = ps.executeQuery();
        while (rs.next()) {
            Articles article = new Articles(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getString("resumer")
            );
            list.add(article);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return list;
}
public static ObservableList<Articles> getArticAccept() {
    Connection conn = getConnection();
    ObservableList<Articles> list = FXCollections.observableArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = conn.prepareStatement(
                "SELECT ARTICLE.id_article, ARTICLE.titre, ARTICLE.resumer " +
                "FROM ARTICLE " +
                "INNER JOIN revue ON ARTICLE.id_article = revue.id_article " +
                "WHERE revue.etat = ?"
        );
        ps.setString(1, "Accept");
        rs = ps.executeQuery();
        while (rs.next()) {
            Articles article = new Articles(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getString("resumer")
            );
            list.add(article);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return list;
}



    public static Articles getRemarquesById(int id) {
        Connection conn = getConnection();
        Articles article = new Articles(id, null);
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM evaluation WHERE idArticle = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {   
                article = new Articles(
                        rs.getInt("idArticle"),
                        rs.getString("remarques"),
                        rs.getString("avis"),0,0
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public static String getUsername(int id){
        Connection conn = getConnection();
        String username = null;
       
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT userName FROM chercheur WHERE id_chercheur = ?");
            
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                username= rs.getString("userName");
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public static int getTaille(int id){
        Connection conn = getConnection();
        int taille=0;
       
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT taille FROM article WHERE id_article = ?");
            
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                taille= rs.getInt("taille");
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return taille;
    }
    public static Articles getArticleById(int id) {
        Connection conn = getConnection();
        Articles article = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM article WHERE id_article = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {   
                article = new Articles(
                        rs.getInt("id_article"),
                        rs.getString("titre"),
                        rs.getString("resumer"),
                        rs.getString("contenue")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }
    public static String getDomaine(int id){
        Connection conn = getConnection();
        String domaine="";
       
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT domaine FROM chercheur WHERE id_chercheur  = ?");
            
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                domaine= rs.getString("domaine");
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return domaine;
    }
    public static void chooseEvaluators(int idArticle, int idEvaluateur) {
        Connection conn = getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO evaluation (avis, dateRevision, remarques, idArticle, id_evaluateur) VALUES ('', CURRENT_TIMESTAMP(), '', ?, ?)");
            ps.setInt(1, idArticle);
            System.out.println(idArticle);
            ps.setInt(2, idEvaluateur);
            System.out.println(idEvaluateur);
            System.out.println("iuezfzecneiuvnusero"); 
            int r= ps.executeUpdate();
            System.out.println("awili "+r);
            ps.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getInstitution(int id){
        Connection conn = getConnection();
        String institution="";
       
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT institution FROM chercheur WHERE id_chercheur  = ?");
            
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                institution= rs.getString("institution");
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return institution;
    }


    }



