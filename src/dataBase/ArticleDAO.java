package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    // Méthode pour récupérer la liste des articles depuis la base de données
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        Connection conn = DatabaseConnector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Requête SQL pour sélectionner tous les articles avec leur état depuis la table "soumission"
            String query = "SELECT id_article, titre, etat FROM soumission JOIN article ON soumission.id_article = article.id_article";
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Parcours des résultats et ajout des articles à la liste
            while (resultSet.next()) {
                String id = resultSet.getString("id_article");
                String titre = resultSet.getString("titre");
                String etat = resultSet.getString("etat");
                articles.add(new Article(id, titre, etat));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des articles : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return articles;
    }
}
