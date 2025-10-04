package Models;

public class publications {

	private int id_pub ;
	private String nom_fichier;
	private String id_soumission ;
	public publications(int id_pub, String nom_fichier, String id_soumission) {
		super();
		this.id_pub = id_pub;
		this.nom_fichier = nom_fichier;
		this.id_soumission = id_soumission;
	}
	public int getId_pub() {
		return id_pub;
	}
	public void setId_pub(int id_pub) {
		this.id_pub = id_pub;
	}
	public String getNom_fichier() {
		return nom_fichier;
	}
	public void setNom_fichier(String nom_fichier) {
		this.nom_fichier = nom_fichier;
	}
	public String getId_soumission() {
		return id_soumission;
	}
	public void setId_soumission(String id_soumission) {
		this.id_soumission = id_soumission;
	}
	
	
}
