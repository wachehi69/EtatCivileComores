package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.entities.ExtraitDecesPersonne;
import org.springframework.data.domain.Page;

public interface IExtraitDecesPersonne {
	
	public ExtraitDecesPersonne  saveExtraitDeces(ExtraitDecesPersonne extraitDeces) ;
	public ExtraitDecesPersonne getExtraitDeces(Long numExtraitDeces);
	public void updateExtraitDeces(ExtraitDecesPersonne extraitDeces);
	public void deleteExtraitDeces(Long numExtraitDeces);
	public Page<ExtraitDecesPersonne> listeParPageExtraitDeces(String nom, int page, int size);
	public Page<ExtraitDecesPersonne> listeParPageExtraitParNumExtraitDeces(Long numExtraitDeces, int page, int size);
	public List<ExtraitDecesPersonne> listeExtraitDeces(); 
	
	
	

}
