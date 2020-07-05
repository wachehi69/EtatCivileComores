package org.mairie.comores.entities;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idempl;
	@NotEmpty(message="choisir le nom du sexe")
	private String nomDuSexe;
	@NotNull
	@Size(min=3,max=25, message="le nom doit être entre 3 et 25 caracteres")
	private String nomemp;
	@NotNull
	@Size(min=3,max=25, message="le prenom doit être entre 3 et 25 caracteres")
	private String prenemp;
	@NotNull
	@Size(min=3,max=25, message="le username doit être entre 3 et 25 caracteres")
	private String username;
	@NotNull
	@Size(min=8, message="le mot de passe doit avoir un minimun 8 caracteres")
	private String password;
	@NotNull
	private boolean active;
	@NotEmpty(message="choisir au moins un rôle")
	private Set<Roles> roles;

	public EmployeUsers() {
		super();
	}
	
	

	public EmployeUsers(Long idempl, String nomDuSexe, String nomemp, String prenemp, String username, String password,
			boolean active, Set<Roles> roles) {
		super();
		this.idempl = idempl;
		this.nomDuSexe = nomDuSexe;
		this.nomemp = nomemp;
		this.prenemp = prenemp;
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return nomDuSexe + " " + nomemp + " " + prenemp + " " + username + " " + password +
				"" + roles; 
	}

}
