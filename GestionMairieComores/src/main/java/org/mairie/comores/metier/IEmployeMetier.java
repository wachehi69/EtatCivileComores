package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.entities.Employe;
import org.springframework.data.domain.Page;

public interface IEmployeMetier {
	
	public Employe saveEmploye(Employe employe);
	public Employe chargerEmploye(Long idempl);
	public List<Employe> listEmploye();
	public Employe chercherEmployeParNomEtPrenom(String nom, String prenom);
	public Page<Employe>listeEmployeParNom(String nom, int page, int size);
	public void supprimerEmployer(Long idempl);
	public Employe modifierEmploye(Employe employe);

}
