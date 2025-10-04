package Models;

import java.util.*;

public class evaluation {

	private int id_evaluation ;
	private String avis ;
	
	private Date dateRevision ;
	private String remarques ;
	private static int nombreRevision=0;
	private int id_evaluateur ;
	private int idArticle;
	private int nvNotif=0;
	
	
	public evaluation(int nombreRevision, int nvNotif) {
		this.nombreRevision = nombreRevision;
		this.nvNotif = nvNotif;
	}
	public evaluation( String avis, String remarques,
			int idA, int id_evaluateur, int nv) {
		super();
		
		this.avis = avis;
		this.idArticle=idA;
		this.nvNotif=nv;
		
		this.remarques = remarques;

		this.id_evaluateur = id_evaluateur;
	}
	public int getId_evaluation() {
		return id_evaluation;
	}
	public void setId_evaluation(int id_evaluation) {
		this.id_evaluation = id_evaluation;
	}
	
	public String getAvis() {
		return avis;
	}
	public void setAvis(String avis) {
		this.avis = avis;
	}
	public void incrRevisionNumber()
	{
		this.nombreRevision++;
	}


	public Date getDateRevision() {
		return dateRevision;
	}
	public void setDateRevision(Date dateRevision) {
		this.dateRevision = dateRevision;
	}
	public String getRemarques() {
		return remarques;
	}
	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}
	public int getNombreRevision() {
		return nombreRevision;
	}
	public void setNombreRevision(int nombreRevision) {
		this.nombreRevision = nombreRevision;
	}
	public int getId_evaluateur() {
		return id_evaluateur;
	}
	public void setId_evaluateur(int id_evaluateur) {
		this.id_evaluateur = id_evaluateur;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public int getNvNotif() {
		return nvNotif;
	}
	public void setNvNotif(int nvNotif) {
		this.nvNotif = nvNotif;
	}
	









}
