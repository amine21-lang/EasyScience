public class ArticleData {
    String titre;
    String resumer;
    String datePublication;

    public ArticleData(String titre, String resumer, String datePublication) {
        this.titre = titre;
        this.resumer = resumer;
        this.datePublication = datePublication;
    }
    
    // Getters for the properties
    public String getTitre() {
        return titre;
    }

    public String getResumer() {
        return resumer;
    }

    public String getDatePublication() {
        return datePublication;
    }
}
