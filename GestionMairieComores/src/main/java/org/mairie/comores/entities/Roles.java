package org.mairie.comores.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Roles implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="role", nullable=false, length=20)
	private String role;
	
	public Roles() {
		super();
	}

	public Roles(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
   @Override
public String toString() {
	return role;
}
}
