package Models;

public class revue {

	private int id_revue ;
	private String nom_revue ;
	private int id_editeur ;
	public revue(int id_revue, String nom_revue, int id_editeur) {
		super();
		this.id_revue = id_revue;
		this.nom_revue = nom_revue;
		this.id_editeur = id_editeur;
	}
	public int getId_revue() {
		return id_revue;
	}
	public void setId_revue(int id_revue) {
		this.id_revue = id_revue;
	}
	public String getNom_revue() {
		return nom_revue;
	}
	public void setNom_revue(String nom_revue) {
		this.nom_revue = nom_revue;
	}
	public int getId_editeur() {
		return id_editeur;
	}
	public void setId_editeur(int id_editeur) {
		this.id_editeur = id_editeur;
	}
	
	
	
}
