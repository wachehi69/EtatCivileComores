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
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ExtraitMariagePersonne implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4390496891775175270L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numExtMariage;
	
	/*Informations D'inscription de mariage*/
	@NotEmpty(message = "jour et le mois d'inscription doivent être renseignés")
	private String dateJoursetMoisIscriptionMariage;
	@NotEmpty(message = "année de l'inscription doit être renseignée")
	private String dateAnneedeInscriptionMariage;
	@NotEmpty(message ="heure d'inscription doit être rensegnée")
	private String heureInscriptionMariage;
	private String minuteInscriptionMariage;
	@NotEmpty(message ="la commune d'inscription doit être rensegnée")
	private String communeInscriptionMariage;
	@NotEmpty(message = "le nom de l'île doit être rensegné")
	private String ileInscriptionMariage;
	@Past(message="la date doit être anterieure")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message="la date de célébration du mariage est obligatoire")
    private Date dateCelebration;
	
	
	/* Information du mari */
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caractères")
	private String nomMari;
	@NotNull
	@Size(min = 3, max = 25, message = "le prenom doit être entre 3 et 25 caractères")
	private String prenomMari;
	@NotEmpty(message = "jour et le mois de naissance doivent être renseignés")
	private String dateJoursetMoisNaissanceMari;
	@NotEmpty(message = "année de naissance doit être renseignée")
	private String dateAnneedeNaissanceMari;
	@NotEmpty(message = "la commune de naissance doit être rensegnée")
	private String communeNaissanceMari;
	@NotEmpty(message = "la profession doit être rensegnée")
	private String professionMari;
	@NotEmpty(message = "l'adresse  doit être rensegnée")
	private String adressMari;
	private String numRegistre;
	
	/* Information du marié */
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
	@NotEmpty(message="la commune de naissance doit être renseignée")
	private String communeNaissanceMarie;
	@NotEmpty(message="la profession doit être renseignée")
	private String professionMarie;
	@NotEmpty(message="l'adresse  doit être renseignée")
	private String adressMarie;
	
	/* Information du père mari */
	@NotNull
	@Size(min=3,max=25, message="le nom du père doit être entre 3 et 25 caractères")
	private String nomDuPereMari;
	@NotNull
	@Size(min=3,max=25, message="le prenom du père doit être entre 3 et 25 caractères")
	private String prenomDuPereMari;
	
	/* Information du père de mari */
	@NotEmpty(message="le nom du père doit être renseigné")
	private String nomDuPereMarie;
	@NotEmpty(message="le prenom du père doit être renseigné")
	private String prenomDuPereMarie;
	
	
	/* Information du mère du mari */
	@NotEmpty(message="le nom du mère doit être renseigné")
	private String nomDuMereMari;
	@NotEmpty(message="le prenom du mère doit être renseigné")
	private String prenomDuMereMari;
	
	/* Information du mère du marie */
	@NotEmpty(message="le nom du mère doit être renseigné")
	private String nomDuMereMarie;
	@NotEmpty(message="le prenom du mère doit être renseigné")
	private String prenomDuMereMarie;
	
	/* Information du témoin du mari */
	@NotEmpty(message="le nom doit être renseigné")
	private String nomTemoinMari;
	@NotEmpty(message="le prénom doit être renseigné")
	private String prenomTemoinMari;
	@NotEmpty(message="la profession du témoin doit être renseignée")
	private String professionTemoinMari;
	@NotEmpty(message= "l'âge doit être renseigné sur deux chiffres")
	@Size(max=2 , message= "l'âge doit être renseigné sur deux chiffres")
	private String ageTemoinMari;
	@NotEmpty(message="l'adresse doit être renseignée")
	private String adresseTemoinMari;

	/* Information du témoin de la marié */
	
	@NotEmpty(message="nom doit être renseigné")
	private String nomTemoinMarie;
	@NotEmpty(message="le prénom doit être renseigné")
	private String prenomTemoinMarie;
	@NotEmpty(message="la prefession doit être renseignée")
	private String professionTemoinMarie;
	@NotEmpty(message= "l'âge doit être renseigné sur deux chiffres")
	@Size(max=2, message= "l'âge doit être renseigné sur deux chiffres")
	private String ageTemoinMarie;
	@NotEmpty(message="l'adresse doit être renseignée")
	private String adresseTemoinMarie;
	
	/* Information admnistrateur */
	@NotEmpty(message="veuillez saisir la déclaration")
	private String declarationFaitePar;
	@NotEmpty(message="veuillez saisir la déclaration")
	private String declarationRecueParnous;
	private Date dateCreation;
	private Date dateModification;

	
	
	@ManyToOne
	@JoinColumn(name = "user_name")
	private Users user;

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



	public String getCommuneNaissanceMari() {
		return communeNaissanceMari;
	}

	public void setCommuneNaissanceMari(String communeNaissanceMari) {
		this.communeNaissanceMari = communeNaissanceMari;
	}

	public String getProfessionMari() {
		return professionMari;
	}

	public void setProfessionMari(String professionMari) {
		this.professionMari = professionMari;
	}

	public String getAdressMari() {
		return adressMari;
	}

	public void setAdressMari(String adressMari) {
		this.adressMari = adressMari;
	}

	public String getNumRegistre() {
		return numRegistre;
	}

	public void setNumRegistre(String numRegistre) {
		this.numRegistre = numRegistre;
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


	public String getCommuneNaissanceMarie() {
		return communeNaissanceMarie;
	}

	public void setCommuneNaissanceMarie(String communeNaissanceMarie) {
		this.communeNaissanceMarie = communeNaissanceMarie;
	}

	
	public String getProfessionMarie() {
		return professionMarie;
	}

	public void setProfessionMarie(String professionMarie) {
		this.professionMarie = professionMarie;
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

	public String getAgeTemoinMari() {
		return ageTemoinMari;
	}

	public void setAgeTemoinMari(String ageTemoinMari) {
		this.ageTemoinMari = ageTemoinMari;
	}

	public String getAdresseTemoinMari() {
		return adresseTemoinMari;
	}

	public void setAdresseTemoinMari(String adresseTemoinMari) {
		this.adresseTemoinMari = adresseTemoinMari;
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

	public String getAgeTemoinMarie() {
		return ageTemoinMarie;
	}

	public void setAgeTemoinMarie(String ageTemoinMarie) {
		this.ageTemoinMarie = ageTemoinMarie;
	}

	public String getAdresseTemoinMarie() {
		return adresseTemoinMarie;
	}

	public void setAdresseTemoinMarie(String adresseTemoinMarie) {
		this.adresseTemoinMarie = adresseTemoinMarie;
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

	public String getDateJoursetMoisIscriptionMariage() {
		return dateJoursetMoisIscriptionMariage;
	}

	public void setDateJoursetMoisIscriptionMariage(String dateJoursetMoisIscriptionMariage) {
		this.dateJoursetMoisIscriptionMariage = dateJoursetMoisIscriptionMariage;
	}

	public String getDateAnneedeInscriptionMariage() {
		return dateAnneedeInscriptionMariage;
	}

	public void setDateAnneedeInscriptionMariage(String dateAnneedeInscriptionMariage) {
		this.dateAnneedeInscriptionMariage = dateAnneedeInscriptionMariage;
	}

	public String getHeureInscriptionMariage() {
		return heureInscriptionMariage;
	}

	public void setHeureInscriptionMariage(String heureInscriptionMariage) {
		this.heureInscriptionMariage = heureInscriptionMariage;
	}

	public String getMinuteInscriptionMariage() {
		return minuteInscriptionMariage;
	}

	public void setMinuteInscriptionMariage(String minuteInscriptionMariage) {
		this.minuteInscriptionMariage = minuteInscriptionMariage;
	}

	public String getCommuneInscriptionMariage() {
		return communeInscriptionMariage;
	}

	public void setCommuneInscriptionMariage(String communeInscriptionMariage) {
		this.communeInscriptionMariage = communeInscriptionMariage;
	}

	public String getIleInscriptionMariage() {
		return ileInscriptionMariage;
	}

	public void setIleInscriptionMariage(String ileInscriptionMariage) {
		this.ileInscriptionMariage = ileInscriptionMariage;
	}
	

	public Date getDateCelebration() {
		return dateCelebration;
	}

	public void setDateCelebration(Date dateCelebration) {
		this.dateCelebration = dateCelebration;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
