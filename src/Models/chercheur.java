package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataBase.DatabaseConnector;

public class chercheur {

	private int id_chercheur ;
	private String nom ;
	private String prenom ;
	private String userName;
	private String password ;
	private String adresse;
	private String domaine;
	private String instituton;
	private Connection con;
	
	public chercheur(int id_chercheur, String nom, String prenom, String userName, String adresse,
			String domaine, String instituton) {
		super();
		this.id_chercheur = id_chercheur;
		this.nom = nom;
		this.prenom = prenom;
		this.userName = userName;

		this.adresse = adresse;
		this.domaine = domaine;
		this.instituton = instituton;
		DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();
	}
	public chercheur() {
		DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
        con = dbc.getConnection();
		//TODO Auto-generated constructor stub
	}
	public chercheur getChercheurById(int id) {
		chercheur ch = null; // Initialize ch to null outside the try block
		String query = "SELECT * FROM chercheur WHERE id_chercheur = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // Check if the ResultSet has at least one row
				ch = new chercheur(rs.getInt("id_chercheur"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("userName"), rs.getString("adresse"), rs.getString("domaine"),
						rs.getString("institution"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ch;
	}
	public int getId_chercheur() {
		return id_chercheur;
	}
	public void setId_chercheur(int id_chercheur) {
		this.id_chercheur = id_chercheur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public String getInstituton() {
		return instituton;
	}
	public void setInstituton(String instituton) {
		this.instituton = instituton;
	}

	
	
	
	
	
	
}
