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
public class ExtraitMariagePersonne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4390496891775175270L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long   numExtMariage;
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caractères")
	private String nomMari;
	@NotNull
	@Size(min=3,max=25, message="le prenom doit être entre 3 et 25 caractères")
	private String prenomMari;
	@NotEmpty(message="la date du jour et le mois de naissance doivent être renseignés")
	private String dateJoursetMoisNaissanceMari;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissanceMari;
	@NotEmpty(message="heure de naissance doit être rensegnée")
	private String heureNaissanceMari;
	private String minuteNaissanceMari;
	@NotEmpty(message="la commune de naissance doit être rensegnée")
	private String communeNaissanceMari;
	@NotEmpty(message="île de naissance doit être rensegnée")
	private String ileDeNaissanceMari;
	@NotEmpty(message="la profession doit être rensegnée")
	private String professionMAri;
	@NotEmpty(message="l'adresse  doit être rensegnée")
	private String adressMari;
	
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caractères")
	private String nomMarie;
	@NotNull
	@Size(min=3,max=25, message="le prenom doit être entre 3 et 25 caractères")
	private String prenomMarie;
	@NotEmpty(message="la date du jour et le mois de naissance doivent être renseignés")
	private String dateJoursetMoisNaissanceMarie;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissanceMarie;
	@NotEmpty(message="heure de naissance doit être rensegnée")
	private String heureNaissanceMarie;
	private String minuteNaissanceMarie;
	@NotEmpty(message="la commune de naissance doit être rensegnée")
	private String communeNaissanceMarie;
	@NotEmpty(message="île de naissance doit être rensegnée")
	private String ileDeNaissanceMarie;
	@NotEmpty(message="la profession doit être rensegnée")
	private String professionMArie;
	@NotEmpty(message="l'adresse  doit être rensegnée")
	private String adressMarie;
	
	@NotNull
	@Size(min=3,max=25, message="le nom du père doit être entre 3 et 25 caractères")
	private String nomDuPereMari;
	@NotNull
	@Size(min=3,max=25, message="le prenom du père doit être entre 3 et 25 caractères")
	private String prenomDuPereMari;
	private String dateJoursetMoisNaissancePereMari;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissancePereMari;
	private String heureNaissancePereMari;
	private String minuteNaissancePereMari;
	@NotEmpty(message="la commune de naissance du père doit être rensegnée")
	private String communeNaissancePereMari;
	@NotEmpty(message="la profession du père de naissance doit être rensegnée")
	private String professionPereMari;
	@NotEmpty(message="le nom du mère doit être rensegné")
	
	
	@NotNull
	@Size(min=3,max=25, message="le nom du père doit être entre 3 et 25 caractères")
	private String nomDuPereMarie;
	@NotNull
	@Size(min=3,max=25, message="le prenom du père doit être entre 3 et 25 caractères")
	private String prenomDuPereMarie;
	
	
	private String dateJoursetMoisNaissancePereMarie;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissancePereMarie;
	private String heureNaissancePereMarie;
	private String minuteNaissancePereMarie;
	@NotEmpty(message="la commune de naissance du père doit être rensegnée")
	private String communeNaissancePereMarie;
	@NotEmpty(message="la profession du père de naissance doit être rensegnée")
	private String professionPereMarie;
	@NotEmpty(message="le nom du mère doit être rensegné")
	
	private String nomDuMereMari;
	@NotEmpty(message="le prenom du mère doit être rensegné")
	private String prenomDuMereMari;
	private String dateJoursetMoisNaissanceMereMari;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissanceMereMari;
	private String heureNaissanceMereMari;
	private String minuteNaissanceMereMari;
	private String communeNaissanceMereMari;
	@NotEmpty(message="la prefession du mère doit être rensegnée")
	private String professionMereMari;

	
	private String nomDuMereMarie;
	@NotEmpty(message="le prenom du mère doit être rensegné")
	private String prenomDuMereMarie;
	private String dateJoursetMoisNaissanceMereMarie;
	@NotEmpty(message="année de naissance doit être renseignée")
	private String dateAnneedeNaissanceMereMarie;
	private String heureNaissanceMereMarie;
	private String minuteNaissanceMereMarie;
	private String communeNaissanceMereMarie;
	@NotEmpty(message="la prefession du mère doit être rensegnée")
	private String professionMereMarie;
	
	@NotEmpty(message="nom doit être rensegné")
	private String nomTemoinMari;
	@NotEmpty(message="le prénom doit être rensegné")
	private String prenomTemoinMari;
	@NotEmpty(message="la prefession doit être rensegnée")
	private String professionTemoinMari;
	@NotEmpty(message="l'âge doit être rensegné")
	private Long ageTemoinMari;
	@NotEmpty(message="l'adresse doit être renseignée")
	private String adresseTemoinMari;

	
	
	@NotEmpty(message="nom doit être rensegné")
	private String nomTemoinMarie;
	@NotEmpty(message="le prénom doit être rensegné")
	private String prenomTemoinMarie;
	@NotEmpty(message="la prefession doit être rensegnée")
	private String professionTemoinMarie;
	@NotEmpty(message="l'âge doit être rensegné")
	private Long ageTemoinMarie;
	
	@NotEmpty(message="l'adresse doit être renseignée")
	private String adresseTemoinMarie;


	
	private String declarationFaitePar;
	private String declarationRecueParnous;
	private String numRegistre;
	private Date dateCreation;
	private Date dateModification;
	@ManyToOne
	@JoinColumn(name="user_name")
	private Users  user;
	
	
	
	
	public ExtraitMariagePersonne() {
		super();
	}
	
	
	public Long getNumExtMariage() {
		return numExtMariage;
	}
	public void setNumExtMariage(Long numExtMariage) {
		this.numExtMariage = numExtMariage;
	}
	public String getNomMari() {
		return nomMari;
	}
	public void setNomMari(String nomMari) {
		this.nomMari = nomMari;
	}
	public String getPrenomMari() {
		return prenomMari;
	}
	public void setPrenomMari(String prenomMari) {
		this.prenomMari = prenomMari;
	}
	public String getDateJoursetMoisNaissanceMari() {
		return dateJoursetMoisNaissanceMari;
	}
	public void setDateJoursetMoisNaissanceMari(String dateJoursetMoisNaissanceMari) {
		this.dateJoursetMoisNaissanceMari = dateJoursetMoisNaissanceMari;
	}
	public String getDateAnneedeNaissanceMari() {
		return dateAnneedeNaissanceMari;
	}
	public void setDateAnneedeNaissanceMari(String dateAnneedeNaissanceMari) {
		this.dateAnneedeNaissanceMari = dateAnneedeNaissanceMari;
	}
	public String getHeureNaissanceMari() {
		return heureNaissanceMari;
	}
	public void setHeureNaissanceMari(String heureNaissanceMari) {
		this.heureNaissanceMari = heureNaissanceMari;
	}
	public String getMinuteNaissanceMari() {
		return minuteNaissanceMari;
	}
	public void setMinuteNaissanceMari(String minuteNaissanceMari) {
		this.minuteNaissanceMari = minuteNaissanceMari;
	}
	public String getCommuneNaissanceMari() {
		return communeNaissanceMari;
	}
	public void setCommuneNaissanceMari(String communeNaissanceMari) {
		this.communeNaissanceMari = communeNaissanceMari;
	}
	public String getIleDeNaissanceMari() {
		return ileDeNaissanceMari;
	}
	public void setIleDeNaissanceMari(String ileDeNaissanceMari) {
		this.ileDeNaissanceMari = ileDeNaissanceMari;
	}
	public String getProfessionMAri() {
		return professionMAri;
	}
	public void setProfessionMAri(String professionMAri) {
		this.professionMAri = professionMAri;
	}
	public String getAdressMari() {
		return adressMari;
	}
	public void setAdressMari(String adressMari) {
		this.adressMari = adressMari;
	}
	public String getNomMarie() {
		return nomMarie;
	}
	public void setNomMarie(String nomMarie) {
		this.nomMarie = nomMarie;
	}
	public String getPrenomMarie() {
		return prenomMarie;
	}
	public void setPrenomMarie(String prenomMarie) {
		this.prenomMarie = prenomMarie;
	}
	public String getDateJoursetMoisNaissanceMarie() {
		return dateJoursetMoisNaissanceMarie;
	}
	public void setDateJoursetMoisNaissanceMarie(String dateJoursetMoisNaissanceMarie) {
		this.dateJoursetMoisNaissanceMarie = dateJoursetMoisNaissanceMarie;
	}
	public String getDateAnneedeNaissanceMarie() {
		return dateAnneedeNaissanceMarie;
	}
	public void setDateAnneedeNaissanceMarie(String dateAnneedeNaissanceMarie) {
		this.dateAnneedeNaissanceMarie = dateAnneedeNaissanceMarie;
	}
	public String getHeureNaissanceMarie() {
		return heureNaissanceMarie;
	}
	public void setHeureNaissanceMarie(String heureNaissanceMarie) {
		this.heureNaissanceMarie = heureNaissanceMarie;
	}
	public String getMinuteNaissanceMarie() {
		return minuteNaissanceMarie;
	}
	public void setMinuteNaissanceMarie(String minuteNaissanceMarie) {
		this.minuteNaissanceMarie = minuteNaissanceMarie;
	}
	public String getCommuneNaissanceMarie() {
		return communeNaissanceMarie;
	}
	public void setCommuneNaissanceMarie(String communeNaissanceMarie) {
		this.communeNaissanceMarie = communeNaissanceMarie;
	}
	public String getIleDeNaissanceMarie() {
		return ileDeNaissanceMarie;
	}
	public void setIleDeNaissanceMarie(String ileDeNaissanceMarie) {
		this.ileDeNaissanceMarie = ileDeNaissanceMarie;
	}
	public String getProfessionMArie() {
		return professionMArie;
	}
	public void setProfessionMArie(String professionMArie) {
		this.professionMArie = professionMArie;
	}
	public String getAdressMarie() {
		return adressMarie;
	}
	public void setAdressMarie(String adressMarie) {
		this.adressMarie = adressMarie;
	}
	public String getNomDuPereMari() {
		return nomDuPereMari;
	}
	public void setNomDuPereMari(String nomDuPereMari) {
		this.nomDuPereMari = nomDuPereMari;
	}
	public String getPrenomDuPereMari() {
		return prenomDuPereMari;
	}
	public void setPrenomDuPereMari(String prenomDuPereMari) {
		this.prenomDuPereMari = prenomDuPereMari;
	}
	public String getDateJoursetMoisNaissancePereMari() {
		return dateJoursetMoisNaissancePereMari;
	}
	public void setDateJoursetMoisNaissancePereMari(String dateJoursetMoisNaissancePereMari) {
		this.dateJoursetMoisNaissancePereMari = dateJoursetMoisNaissancePereMari;
	}
	public String getDateAnneedeNaissancePereMari() {
		return dateAnneedeNaissancePereMari;
	}
	public void setDateAnneedeNaissancePereMari(String dateAnneedeNaissancePereMari) {
		this.dateAnneedeNaissancePereMari = dateAnneedeNaissancePereMari;
	}
	public String getHeureNaissancePereMari() {
		return heureNaissancePereMari;
	}
	public void setHeureNaissancePereMari(String heureNaissancePereMari) {
		this.heureNaissancePereMari = heureNaissancePereMari;
	}
	public String getMinuteNaissancePereMari() {
		return minuteNaissancePereMari;
	}
	public void setMinuteNaissancePereMari(String minuteNaissancePereMari) {
		this.minuteNaissancePereMari = minuteNaissancePereMari;
	}
	public String getCommuneNaissancePereMari() {
		return communeNaissancePereMari;
	}
	public void setCommuneNaissancePereMari(String communeNaissancePereMari) {
		this.communeNaissancePereMari = communeNaissancePereMari;
	}
	public String getProfessionPereMari() {
		return professionPereMari;
	}
	public void setProfessionPereMari(String professionPereMari) {
		this.professionPereMari = professionPereMari;
	}
	public String getNomDuPereMarie() {
		return nomDuPereMarie;
	}
	public void setNomDuPereMarie(String nomDuPereMarie) {
		this.nomDuPereMarie = nomDuPereMarie;
	}
	public String getPrenomDuPereMarie() {
		return prenomDuPereMarie;
	}
	public void setPrenomDuPereMarie(String prenomDuPereMarie) {
		this.prenomDuPereMarie = prenomDuPereMarie;
	}
	public String getDateJoursetMoisNaissancePereMarie() {
		return dateJoursetMoisNaissancePereMarie;
	}
	public void setDateJoursetMoisNaissancePereMarie(String dateJoursetMoisNaissancePereMarie) {
		this.dateJoursetMoisNaissancePereMarie = dateJoursetMoisNaissancePereMarie;
	}
	public String getDateAnneedeNaissancePereMarie() {
		return dateAnneedeNaissancePereMarie;
	}
	public void setDateAnneedeNaissancePereMarie(String dateAnneedeNaissancePereMarie) {
		this.dateAnneedeNaissancePereMarie = dateAnneedeNaissancePereMarie;
	}
	public String getHeureNaissancePereMarie() {
		return heureNaissancePereMarie;
	}
	public void setHeureNaissancePereMarie(String heureNaissancePereMarie) {
		this.heureNaissancePereMarie = heureNaissancePereMarie;
	}
	public String getMinuteNaissancePereMarie() {
		return minuteNaissancePereMarie;
	}
	public void setMinuteNaissancePereMarie(String minuteNaissancePereMarie) {
		this.minuteNaissancePereMarie = minuteNaissancePereMarie;
	}
	public String getCommuneNaissancePereMarie() {
		return communeNaissancePereMarie;
	}
	public void setCommuneNaissancePereMarie(String communeNaissancePereMarie) {
		this.communeNaissancePereMarie = communeNaissancePereMarie;
	}
	public String getProfessionPereMarie() {
		return professionPereMarie;
	}
	public void setProfessionPereMarie(String professionPereMarie) {
		this.professionPereMarie = professionPereMarie;
	}
	public String getNomDuMereMari() {
		return nomDuMereMari;
	}
	public void setNomDuMereMari(String nomDuMereMari) {
		this.nomDuMereMari = nomDuMereMari;
	}
	public String getPrenomDuMereMari() {
		return prenomDuMereMari;
	}
	public void setPrenomDuMereMari(String prenomDuMereMari) {
		this.prenomDuMereMari = prenomDuMereMari;
	}
	public String getDateJoursetMoisNaissanceMereMari() {
		return dateJoursetMoisNaissanceMereMari;
	}
	public void setDateJoursetMoisNaissanceMereMari(String dateJoursetMoisNaissanceMereMari) {
		this.dateJoursetMoisNaissanceMereMari = dateJoursetMoisNaissanceMereMari;
	}
	public String getDateAnneedeNaissanceMereMari() {
		return dateAnneedeNaissanceMereMari;
	}
	public void setDateAnneedeNaissanceMereMari(String dateAnneedeNaissanceMereMari) {
		this.dateAnneedeNaissanceMereMari = dateAnneedeNaissanceMereMari;
	}
	public String getHeureNaissanceMereMari() {
		return heureNaissanceMereMari;
	}
	public void setHeureNaissanceMereMari(String heureNaissanceMereMari) {
		this.heureNaissanceMereMari = heureNaissanceMereMari;
	}
	public String getMinuteNaissanceMereMari() {
		return minuteNaissanceMereMari;
	}
	public void setMinuteNaissanceMereMari(String minuteNaissanceMereMari) {
		this.minuteNaissanceMereMari = minuteNaissanceMereMari;
	}
	public String getCommuneNaissanceMereMari() {
		return communeNaissanceMereMari;
	}
	public void setCommuneNaissanceMereMari(String communeNaissanceMereMari) {
		this.communeNaissanceMereMari = communeNaissanceMereMari;
	}
	public String getProfessionMereMari() {
		return professionMereMari;
	}
	public void setProfessionMereMari(String professionMereMari) {
		this.professionMereMari = professionMereMari;
	}
	public String getNomDuMereMarie() {
		return nomDuMereMarie;
	}
	public void setNomDuMereMarie(String nomDuMereMarie) {
		this.nomDuMereMarie = nomDuMereMarie;
	}
	public String getPrenomDuMereMarie() {
		return prenomDuMereMarie;
	}
	public void setPrenomDuMereMarie(String prenomDuMereMarie) {
		this.prenomDuMereMarie = prenomDuMereMarie;
	}
	public String getDateJoursetMoisNaissanceMereMarie() {
		return dateJoursetMoisNaissanceMereMarie;
	}
	public void setDateJoursetMoisNaissanceMereMarie(String dateJoursetMoisNaissanceMereMarie) {
		this.dateJoursetMoisNaissanceMereMarie = dateJoursetMoisNaissanceMereMarie;
	}
	public String getDateAnneedeNaissanceMereMarie() {
		return dateAnneedeNaissanceMereMarie;
	}
	public void setDateAnneedeNaissanceMereMarie(String dateAnneedeNaissanceMereMarie) {
		this.dateAnneedeNaissanceMereMarie = dateAnneedeNaissanceMereMarie;
	}
	public String getHeureNaissanceMereMarie() {
		return heureNaissanceMereMarie;
	}
	public void setHeureNaissanceMereMarie(String heureNaissanceMereMarie) {
		this.heureNaissanceMereMarie = heureNaissanceMereMarie;
	}
	public String getMinuteNaissanceMereMarie() {
		return minuteNaissanceMereMarie;
	}
	public void setMinuteNaissanceMereMarie(String minuteNaissanceMereMarie) {
		this.minuteNaissanceMereMarie = minuteNaissanceMereMarie;
	}
	public String getCommuneNaissanceMereMarie() {
		return communeNaissanceMereMarie;
	}
	public void setCommuneNaissanceMereMarie(String communeNaissanceMereMarie) {
		this.communeNaissanceMereMarie = communeNaissanceMereMarie;
	}
	public String getProfessionMereMarie() {
		return professionMereMarie;
	}
	public void setProfessionMereMarie(String professionMereMarie) {
		this.professionMereMarie = professionMereMarie;
	}
	public String getNomTemoinMari() {
		return nomTemoinMari;
	}
	public void setNomTemoinMari(String nomTemoinMari) {
		this.nomTemoinMari = nomTemoinMari;
	}
	public String getPrenomTemoinMari() {
		return prenomTemoinMari;
	}
	public void setPrenomTemoinMari(String prenomTemoinMari) {
		this.prenomTemoinMari = prenomTemoinMari;
	}
	public String getProfessionTemoinMari() {
		return professionTemoinMari;
	}
	public void setProfessionTemoinMari(String professionTemoinMari) {
		this.professionTemoinMari = professionTemoinMari;
	}
	public Long getAgeTemoinMari() {
		return ageTemoinMari;
	}
	public void setAgeTemoinMari(Long ageTemoinMari) {
		this.ageTemoinMari = ageTemoinMari;
	}
	public String getNomTemoinMarie() {
		return nomTemoinMarie;
	}
	public void setNomTemoinMarie(String nomTemoinMarie) {
		this.nomTemoinMarie = nomTemoinMarie;
	}
	public String getPrenomTemoinMarie() {
		return prenomTemoinMarie;
	}
	public void setPrenomTemoinMarie(String prenomTemoinMarie) {
		this.prenomTemoinMarie = prenomTemoinMarie;
	}
	public String getProfessionTemoinMarie() {
		return professionTemoinMarie;
	}
	public void setProfessionTemoinMarie(String professionTemoinMarie) {
		this.professionTemoinMarie = professionTemoinMarie;
	}
	public Long getAgeTemoinMarie() {
		return ageTemoinMarie;
	}
	public void setAgeTemoinMarie(Long ageTemoinMarie) {
		this.ageTemoinMarie = ageTemoinMarie;
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


	public String getAdresseTemoinMari() {
		return adresseTemoinMari;
	}


	public void setAdresseTemoinMari(String adresseTemoinMari) {
		this.adresseTemoinMari = adresseTemoinMari;
	}


	public String getAdresseTemoinMarie() {
		return adresseTemoinMarie;
	}


	public void setAdresseTemoinMarie(String adresseTemoinMarie) {
		this.adresseTemoinMarie = adresseTemoinMarie;
	}

}
