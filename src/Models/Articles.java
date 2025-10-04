package Models;
public class Articles {
    private int idArticle;
    private String  titre;
    private String  resumer;
    private String  motCles;
    private String  auteurs;
    private String  categorie;
    private String  Imagepth;
    private String remarque;
    private String etat;
    private String contenue;
    private String avis;
    private int nr=0;
    private String nvn=" ";
    



    
  
    
    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getNvn() {
        return nvn;
    }

    public void setNvn(String nvn) {
        this.nvn = nvn;
    }

    public Articles() {
    }
    

    public Articles(int idArticle, String remarque, String avis, int u, int o) {
        this.idArticle = idArticle;
        this.remarque = remarque;
        this.avis=avis;
    }

    public Articles(int idArticle, String titre, String resumer) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.resumer = resumer;
    }
    

    public Articles(int idArticle,String remarque) {
        this.idArticle = idArticle;
        this.remarque = remarque;
    }

    public String getResumer() {
        return resumer;
    }

    public void setResumer(String resumer) {
        this.resumer = resumer;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getresumer() {
        return resumer;
    }
    public void setresumer(String resumer) {
        this.resumer = resumer;
    }
    public String getMotCles() {
        return motCles;
    }
    public void setMotCles(String motCles) {
        this.motCles = motCles;
    }
    public String getAuteurs() {
        return auteurs;
    }
    public void setAuteurs(String auteurs) {
        this.auteurs = auteurs;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getImagepth() {
        return Imagepth;
    }
    public void setImagepth(String imagepth) {
        Imagepth = imagepth;
    }

    @Override
    public String toString() {
        return "Articles [idArticle=" + idArticle + ", titre=" + titre + ", resumer=" + resumer + "]";
    }

    public Articles(int idArticle, String titre, String resumer, String auteurs) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.resumer = resumer;
        this.auteurs = auteurs;
    }

    public Articles(int idArticle, String titre, String etat,int i) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
    


    
   
}