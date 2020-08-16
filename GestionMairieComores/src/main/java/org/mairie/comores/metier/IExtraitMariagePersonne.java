package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.entities.ExtraitMariagePersonne;

import org.springframework.data.domain.Page;

public interface IExtraitMariagePersonne {
	
	public ExtraitMariagePersonne  saveExtraitMariage(ExtraitMariagePersonne extraitMariage) ;
	public ExtraitMariagePersonne getExtraitMariage(Long numExtMariage);
	public void updateExtraitMariage(ExtraitMariagePersonne extraitMariage);
	public void deleteExtraitMariage(Long numExtMariage);
	public Page<ExtraitMariagePersonne> listeExtraitMariageParNom(String nom, int page, int size);
	public Page<ExtraitMariagePersonne> listeExtraitMariageParNumExtrait(Long numExtrait, int page, int size);
	public List<ExtraitMariagePersonne> listeExtraitMariage(); 
	
	

}
