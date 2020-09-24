package org.mairie.comores.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ExtraitDecesPersonne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long   numExtraitDeces;
	@NotEmpty(message="le nom du sexe doit être rensegné")
	private String nomDuSexe;
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caractères")
	private String nom;
	@NotNull
	@Size(min=3,max=25, message="le prenom doit être entre 3 et 25 caractères")
	private String prenom;
	@NotEmpty(message="la date du jour et le mois de décès doivent être renseignés")
	private String dateJoursetMoisDeces;
	@NotEmpty(message="année de décès doit être renseignée")
	private String dateAnneedeDeces;
	@NotEmpty(message="le lieu de décès doit être rensegné")
	private String lieuDeDeces;
	@NotEmpty(message="la commune du décès doit être rensegné")
	private String communeDuDeces;
	@NotEmpty(message="la date du jour et le mois de naissance doivent être renseignés")
	private String dateJoursetMoisNaissance;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissance;
	@NotEmpty(message="heure de naissance doit être rensegnée")
	private String heureNaissance;
	private String minuteNaissance;
	@NotEmpty(message="la commune de naissance doit être rensegnée")
	private String communeNaissance;
	@NotNull
	@Size(min=3,max=25, message="le nom du père doit être entre 3 et 25 caractères")
	private String nomDuPere;
	@NotNull
	@Size(min=3,max=25, message="le prenom du père doit être entre 3 et 25 caractères")
	private String prenomDuPere;
	@NotEmpty(message="le nom du mère doit être rensegné")
	private String nomDuMere;
	@NotEmpty(message="le prenom du mère doit être rensegné")
	private String prenomDuMere;
	
	private String declarationFaitePar;
	private String declarationRecueParnous;
	private String numRegistre;
	private Date dateCreation;
	private Date dateModification;
	@ManyToOne
	@JoinColumn(name="user_name")
	private Users  user;
	
	
	

	public ExtraitDecesPersonne() {
		super();
	}


	public Long getNumExtraitDeces() {
		return numExtraitDeces;
	}


	public void setNumExtraitDeces(Long numExtraitDeces) {
		this.numExtraitDeces = numExtraitDeces;
	}


	public String getNomDuSexe() {
		return nomDuSexe;
	}


	public void setNomDuSexe(String nomDuSexe) {
		this.nomDuSexe = nomDuSexe;
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


	public String getDateJoursetMoisDeces() {
		return dateJoursetMoisDeces;
	}


	public void setDateJoursetMoisDeces(String dateJoursetMoisDeces) {
		this.dateJoursetMoisDeces = dateJoursetMoisDeces;
	}


	public String getDateAnneedeDeces() {
		return dateAnneedeDeces;
	}


	public void setDateAnneedeDeces(String dateAnneedeDeces) {
		this.dateAnneedeDeces = dateAnneedeDeces;
	}


	public String getLieuDeDeces() {
		return lieuDeDeces;
	}


	public void setLieuDeDeces(String lieuDeDeces) {
		this.lieuDeDeces = lieuDeDeces;
	}


	public String getCommuneDuDeces() {
		return communeDuDeces;
	}


	public void setCommuneDuDeces(String communeDuDeces) {
		this.communeDuDeces = communeDuDeces;
	}


	public String getDateJoursetMoisNaissance() {
		return dateJoursetMoisNaissance;
	}


	public void setDateJoursetMoisNaissance(String dateJoursetMoisNaissance) {
		this.dateJoursetMoisNaissance = dateJoursetMoisNaissance;
	}


	public String getDateAnneedeNaissance() {
		return dateAnneedeNaissance;
	}


	public void setDateAnneedeNaissance(String dateAnneedeNaissance) {
		this.dateAnneedeNaissance = dateAnneedeNaissance;
	}


	public String getHeureNaissance() {
		return heureNaissance;
	}


	public void setHeureNaissance(String heureNaissance) {
		this.heureNaissance = heureNaissance;
	}


	public String getMinuteNaissance() {
		return minuteNaissance;
	}


	public void setMinuteNaissance(String minuteNaissance) {
		this.minuteNaissance = minuteNaissance;
	}


	public String getCommuneNaissance() {
		return communeNaissance;
	}


	public void setCommuneNaissance(String communeNaissance) {
		this.communeNaissance = communeNaissance;
	}


	public String getNomDuPere() {
		return nomDuPere;
	}


	public void setNomDuPere(String nomDuPere) {
		this.nomDuPere = nomDuPere;
	}


	public String getPrenomDuPere() {
		return prenomDuPere;
	}


	public void setPrenomDuPere(String prenomDuPere) {
		this.prenomDuPere = prenomDuPere;
	}


	public String getNomDuMere() {
		return nomDuMere;
	}


	public void setNomDuMere(String nomDuMere) {
		this.nomDuMere = nomDuMere;
	}


	public String getPrenomDuMere() {
		return prenomDuMere;
	}


	public void setPrenomDuMere(String prenomDuMere) {
		this.prenomDuMere = prenomDuMere;
	}


	public String getDeclarationFaitePar() {
		return declarationFaitePar;
	}


	public void setDeclarationFaitePar(String declarationFaitePar) {
		this.declarationFaitePar = declarationFaitePar;
	}


	public String getDeclarationRecueParnous() {
		return declarationRecueParnous;
	}


	public void setDeclarationRecueParnous(String declarationRecueParnous) {
		this.declarationRecueParnous = declarationRecueParnous;
	}


	public String getNumRegistre() {
		return numRegistre;
	}


	public void setNumRegistre(String numRegistre) {
		this.numRegistre = numRegistre;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public Date getDateModification() {
		return dateModification;
	}


	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	
}
