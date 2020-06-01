package org.mairie.comores.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "public")
public class Users implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username",nullable=false, length = 25)
	private String username; 
	
	@Column(name = "password", nullable=false, length = 250)
	private String password;
	
	private Boolean active;
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name="username"),  
		inverseJoinColumns = @JoinColumn(name = "role"))
	   private Set<Roles> roles;
	
	@OneToOne  
    @JoinColumn( name="emp_id", nullable=false)
	 private Employe employe;


	public Users() {
		super();
	}

	public Users(String username, String password, Boolean active, Set<Roles> roles, Employe employe) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.employe = employe;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

}
