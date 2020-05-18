package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.entities.Personne;

public interface IPersonneMetier {
	
	public Personne getPersonne(Long id);
	public List<Personne> listePersonne();
	public Personne savePersonne(Personne personne);

}
