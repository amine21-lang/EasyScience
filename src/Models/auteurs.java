package Models;

public class auteurs {

	private int id_liste;
	private String nom_auteur;
	private String prenom_auteur;
	private String adresse_auteur ;
	private int institution_auteur ;
	private int id_corr ;
	
	public auteurs(int id_liste, String nom_auteur, String prenom_auteur, String adresse_auteur, int institution_auteur,
			int id_corr) {
		super();
		this.id_liste = id_liste;
		this.nom_auteur = nom_auteur;
		this.prenom_auteur = prenom_auteur;
		this.adresse_auteur = adresse_auteur;
		this.institution_auteur = institution_auteur;
		this.id_corr = id_corr;
	}
	public int getId_liste() {
		return id_liste;
	}
	public void setId_liste(int id_liste) {
		this.id_liste = id_liste;
	}
	public String getNom_auteur() {
		return nom_auteur;
	}
	public void setNom_auteur(String nom_auteur) {
		this.nom_auteur = nom_auteur;
	}
	public String getPrenom_auteur() {
		return prenom_auteur;
	}
	public void setPrenom_auteur(String prenom_auteur) {
		this.prenom_auteur = prenom_auteur;
	}
	public String getAdresse_auteur() {
		return adresse_auteur;
	}
	public void setAdresse_auteur(String adresse_auteur) {
		this.adresse_auteur = adresse_auteur;
	}
	public int getInstitution_auteur() {
		return institution_auteur;
	}
	public void setInstitution_auteur(int institution_auteur) {
		this.institution_auteur = institution_auteur;
	}
	public int getId_corr() {
		return id_corr;
	}
	public void setId_corr(int id_corr) {
		this.id_corr = id_corr;
	}
	
	
	
}
