package org.mairie.comores.entities;

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
public class Personne {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPers;
	@NotNull
	@Size(min=3,max=25, message="le nom doit avoir une longueur de 3 à 25 caracteres")
	private String name;
	@NotEmpty(message="l'email ne doit être vide")
	@Email(regexp="^(.+)@(.+)$", message="email invalide")
	private String email;
	 @Past(message="la date de naissance doit être anterieure")
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date datenaissance;
	 
	
	
	public Personne() {
		super();
	}

	public Personne(String name, String email, Date datenaissance) {
		super();
		this.name = name;
		this.email = email;
		this.datenaissance = datenaissance;
	}

	public Long getIdPers() {
		return idPers;
	}

	public void setIdPers(Long idPers) {
		this.idPers = idPers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	

}
