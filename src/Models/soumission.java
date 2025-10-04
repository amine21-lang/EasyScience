package Models;

import java.util.*;

public class soumission {

	private int id_soumission;
	private Date date_soumission;
	private String decision_editeur;
	private int id_editeur;
	private int id_corr;
	private int id_article;
	private int etat ;
	
	public soumission(int id_soumission, Date date_soumission, String decision_editeur, int id_editeur, int id_corr,
			int id_article, int etat) {
		super();
		this.id_soumission = id_soumission;
		this.date_soumission = date_soumission;
		this.decision_editeur = decision_editeur;
		this.id_editeur = id_editeur;
		this.id_corr = id_corr;
		this.id_article = id_article;
		this.etat = etat;
	}

	public int getId_soumission() {
		return id_soumission;
	}

	public void setId_soumission(int id_soumission) {
		this.id_soumission = id_soumission;
	}

	public Date getDate_soumission() {
		return date_soumission;
	}

	public void setDate_soumission(Date date_soumission) {
		this.date_soumission = date_soumission;
	}

	public String getDecision_editeur() {
		return decision_editeur;
	}

	public void setDecision_editeur(String decision_editeur) {
		this.decision_editeur = decision_editeur;
	}

	public int getId_editeur() {
		return id_editeur;
	}

	public void setId_editeur(int id_editeur) {
		this.id_editeur = id_editeur;
	}

	public int getId_corr() {
		return id_corr;
	}

	public void setId_corr(int id_corr) {
		this.id_corr = id_corr;
	}

	public int getId_article() {
		return id_article;
	}

	public void setId_article(int id_article) {
		this.id_article = id_article;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	
}
