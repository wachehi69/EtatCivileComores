package org.mairie.comores.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idempl;
	@NotEmpty(message="le nom du sexe doit être rensegné")
	private String nomDuSexe;
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caracteres")
	private String nomemp;
	@NotNull
	@Size(min=3,max=25, message="le prenom doit être entre 3 et 25 caracteres")
	private String prenemp;
	@NotEmpty(message="l'email ne doit être vide")
	@Email(regexp="^(.+)@(.+)$", message="email invalide")
	private String mail;
	@NotEmpty(message="la fonction ne doit pas être vide")
	private String fonction;
	@Past(message="la date de naissance doit être anterieure")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="la date de naissance doit être renseignée")
	private Date dateNaissance;
	//@Past(message="la date de recrutement doit être anterieure")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="la date de recrutement doit être renseignée")
	private Date dateRecrutement;
	@NotNull(message="le salaire ne doit pas être vide")
	private double salaire;
	

	public Employe() {
		super();

	}

	public Employe(String nomDuSexe, String nomemp, String prenemp, String mail, String fonction, Date dateNaissance,
			Date dateRecrutement, double salaire) {
		super();
		this.nomDuSexe = nomDuSexe;
		this.nomemp = nomemp;
		this.prenemp = prenemp;
		this.mail = mail;
		this.fonction = fonction;
		this.dateNaissance = dateNaissance;
		this.dateRecrutement = dateRecrutement;
		this.salaire = salaire;
	}

	public Long getIdempl() {
		return idempl;
	}

	public void setIdempl(Long idempl) {
		this.idempl = idempl;
	}

	public String getNomDuSexe() {
		return nomDuSexe;
	}

	public void setNomDuSexe(String nomDuSexe) {
		this.nomDuSexe = nomDuSexe;
	}

	public String getNomemp() {
		return nomemp;
	}

	public void setNomemp(String nomemp) {
		this.nomemp = nomemp;
	}

	public String getPrenemp() {
		return prenemp;
	}

	public void setPrenemp(String prenemp) {
		this.prenemp = prenemp;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateRecrutement() {
		return dateRecrutement;
	}

	public void setDateRecrutement(Date dateRecrutement) {
		this.dateRecrutement = dateRecrutement;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		return nomemp;
	}

}
