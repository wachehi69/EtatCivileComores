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
public class ExtraitNaissancePersonne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long   numExtrait;
	@NotEmpty(message="le nom du sexe doit être rensegné")
	private String nomDuSexe;
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caractères")
	private String nom;
	@NotNull
	@Size(min=3,max=25, message="le prenom doit être entre 3 et 25 caractères")
	private String prenom;
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
	private String dateJoursetMoisNaissancePere;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissancePere;
	private String heureNaissancePere;
	private String minuteNaissancePere;
	@NotEmpty(message="la commune de naissance du père doit être rensegnée")
	private String communeNaissancePere;
	@NotEmpty(message="la profession du père de naissance doit être rensegnée")
	private String professionPere;
	@NotEmpty(message="le nom du mère doit être rensegné")
	private String nomDuMere;
	@NotEmpty(message="le prenom du mère doit être rensegné")
	private String prenomDuMere;
	private String dateJoursetMoisNaissanceMere;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissanceMere;
	private String heureNaissanceMere;
	private String minuteNaissanceMere;
	@NotEmpty(message="la commune de naissance du mère doit être rensegnée")
	private String communeNaissanceMere;
	@NotEmpty(message="la profession du mère doit être rensegnée")
	private String professionMere;
	private String declarationFaitePar;
	private String declarationRecueParnous;
	private String numRegistre;
	private Date dateCreation;
	private Date dateModification;
	@ManyToOne
	@JoinColumn(name="user_name")
	private Users  user;
	
	

	public ExtraitNaissancePersonne() {
		super();
	}


	public Long getNumExtrait() {
		return numExtrait;
	}

	public void setNumExtrait(Long numExtrait) {
		this.numExtrait = numExtrait;
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
	public String getHeureNaissance() {
		return heureNaissance;
	}
	public void setHeureNaissance(String heureNaissance) {
		this.heureNaissance = heureNaissance;
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

	public String getDateJoursetMoisNaissancePere() {
		return dateJoursetMoisNaissancePere;
	}


	public void setDateJoursetMoisNaissancePere(String dateJoursetMoisNaissancePere) {
		this.dateJoursetMoisNaissancePere = dateJoursetMoisNaissancePere;
	}


	public String getCommuneNaissancePere() {
		return communeNaissancePere;
	}

	public void setCommuneNaissancePere(String communeNaissancePere) {
		this.communeNaissancePere = communeNaissancePere;
	}
	public String getProfessionPere() {
		return professionPere;
	}
	public void setProfessionPere(String professionPere) {
		this.professionPere = professionPere;
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
	
	public String getCommuneNaissanceMere() {
		return communeNaissanceMere;
	}
	public void setCommuneNaissanceMere(String communeNaissanceMere) {
		this.communeNaissanceMere = communeNaissanceMere;
	}
	public String getProfessionMere() {
		return professionMere;
	}
	public void setProfessionMere(String professionMere) {
		this.professionMere = professionMere;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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


	public String getMinuteNaissance() {
		return minuteNaissance;
	}


	public void setMinuteNaissance(String minuteNaissance) {
		this.minuteNaissance = minuteNaissance;
	}


	public String getDateAnneedeNaissancePere() {
		return dateAnneedeNaissancePere;
	}


	public void setDateAnneedeNaissancePere(String dateAnneedeNaissancePere) {
		this.dateAnneedeNaissancePere = dateAnneedeNaissancePere;
	}


	public String getHeureNaissancePere() {
		return heureNaissancePere;
	}


	public void setHeureNaissancePere(String heureNaissancePere) {
		this.heureNaissancePere = heureNaissancePere;
	}


	public String getMinuteNaissancePere() {
		return minuteNaissancePere;
	}


	public void setMinuteNaissancePere(String minuteNaissancePere) {
		this.minuteNaissancePere = minuteNaissancePere;
	}


	public String getDateJoursetMoisNaissanceMere() {
		return dateJoursetMoisNaissanceMere;
	}


	public void setDateJoursetMoisNaissanceMere(String dateJoursetMoisNaissanceMere) {
		this.dateJoursetMoisNaissanceMere = dateJoursetMoisNaissanceMere;
	}


	public String getDateAnneedeNaissanceMere() {
		return dateAnneedeNaissanceMere;
	}


	public void setDateAnneedeNaissanceMere(String dateAnneedeNaissanceMere) {
		this.dateAnneedeNaissanceMere = dateAnneedeNaissanceMere;
	}


	public String getHeureNaissanceMere() {
		return heureNaissanceMere;
	}


	public void setHeureNaissanceMere(String heureNaissanceMere) {
		this.heureNaissanceMere = heureNaissanceMere;
	}


	public String getMinuteNaissanceMere() {
		return minuteNaissanceMere;
	}


	public void setMinuteNaissanceMere(String minuteNaissanceMere) {
		this.minuteNaissanceMere = minuteNaissanceMere;
	}


	public Date getDateModification() {
		return dateModification;
	}


	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	
	
}
