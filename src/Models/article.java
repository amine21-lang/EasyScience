package Models;

public class article {
	
	private int id_article;
	private String titre;
	private String contenue;
	private String resumer;
	
	private String motCles;

	private String categorie;
	private evaluation Evaluation;
	
    
    
	public article(int id_article, String titre, String contenue, String resumer, String motCles,
			 String categorie) {
		super();
		this.id_article = id_article;
		this.titre = titre;
		this.contenue = contenue;
		this.resumer = resumer;
		this.motCles = motCles;
		this.categorie = categorie;
		
	}


	public int getId_article() {
		return id_article;
	}


	public void setId_article(int id_article) {
		this.id_article = id_article;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getContenue() {
		return contenue;
	}


	public void setContenue(String contenue) {
		this.contenue = contenue;
	}


	public String getResumer() {
		return resumer;
	}


	public void setResumer(String resumer) {
		this.resumer = resumer;
	}


	


	public String getMotCles() {
		return motCles;
	}


	public void setMotCles(String motCles) {
		this.motCles = motCles;
	}

	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

    
    
	

}
