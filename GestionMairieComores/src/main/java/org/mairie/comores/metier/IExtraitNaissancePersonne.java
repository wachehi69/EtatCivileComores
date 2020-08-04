package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.springframework.data.domain.Page;

public interface IExtraitNaissancePersonne {
	
	public ExtraitNaissancePersonne  saveExtraitNaissance(ExtraitNaissancePersonne extrait) ;
	public ExtraitNaissancePersonne getExtraitNaissance(Long numExtrait);
	public void updateExtraitNaissance(ExtraitNaissancePersonne extrait);
	public void deleteExtraitNaissance(Long numExtrait);
	public Page<ExtraitNaissancePersonne> listeParPageExtrait(String nom, int page, int size);
	public Page<ExtraitNaissancePersonne> listeParPageExtraitParNumExtrait(Long numExtrait, int page, int size);
	public List<ExtraitNaissancePersonne> listeExtraitNaissance(); 
	
	
	

}
