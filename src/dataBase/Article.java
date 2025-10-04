package dataBase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
    private final StringProperty id;
    private final StringProperty titre;
    private final StringProperty etat;

    public Article(String id, String titre, String etat) {
        this.id = new SimpleStringProperty(id);
        this.titre = new SimpleStringProperty(titre);
        this.etat = new SimpleStringProperty(etat);
    }
   

    public String getId() {
        return id.get();
    }

    public StringProperty getIdProperty() {
        return id;
    }

    public String getTitre() {
        return titre.get();
    }

    public StringProperty getTitreProperty() {
        return titre;
    }

    public String getEtat() {
        return etat.get();
    }

    public StringProperty getEtatProperty() {
        return etat;
    }
}
