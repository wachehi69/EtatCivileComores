package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.dao.ExtraitMariagePersonneRepository;
import org.mairie.comores.entities.ExtraitMariagePersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ExtraitMariagePersonneImpl implements IExtraitMariagePersonne{
	
	@Autowired
	private ExtraitMariagePersonneRepository  mariagePersonneRepository;

	@Override
	public ExtraitMariagePersonne saveExtraitMariage(ExtraitMariagePersonne extraitMariage) {
		if(null == extraitMariage ) throw new RuntimeException("Données incorretes");
		return mariagePersonneRepository.save(extraitMariage);
	}

	@Override
	public ExtraitMariagePersonne getExtraitMariage(Long numExtMariage) {
		
		
		if(null == numExtMariage ) throw new RuntimeException("numéro extrait incorrecte");
		return mariagePersonneRepository.findById(numExtMariage).get();
	}

	@Override
	public void updateExtraitMariage(ExtraitMariagePersonne extraitMariage) {
		
		if(null == extraitMariage ) throw new RuntimeException("Données incorretes");
		mariagePersonneRepository.saveAndFlush(extraitMariage);
	
		
	}

	@Override
	public void deleteExtraitMariage(Long numExtMariage) {
		
		if(null== numExtMariage) throw new RuntimeException("numero incorrecte");
		ExtraitMariagePersonne extraitM = getExtraitMariage(numExtMariage);
		mariagePersonneRepository.delete(extraitM);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<ExtraitMariagePersonne> listeExtraitMariageParNom(String nomMari, int page, int size) {
		Page<ExtraitMariagePersonne> listeExtraitMariage = mariagePersonneRepository.listeExtraitMariageParNom("%"+nomMari+"%", new PageRequest(page, size));
		   if(null==listeExtraitMariage || listeExtraitMariage.isEmpty()) throw new RuntimeException("Aucun extrait existe sur ce nom") ;
		
		return listeExtraitMariage ;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<ExtraitMariagePersonne> listeExtraitMariageParNumExtrait(Long numExtrait, int page, int size) {
		
		 
		Page<ExtraitMariagePersonne> listeExtraitMariage = mariagePersonneRepository.listeExtraitMariageParNumExtrait(numExtrait, new PageRequest(page, size));
		   if(null==listeExtraitMariage || listeExtraitMariage.isEmpty()) throw new RuntimeException("Aucun extrait existe sur ce numéro") ;
		
		return listeExtraitMariage;
	}

	@Override
	public List<ExtraitMariagePersonne> listeExtraitMariage() {
		return mariagePersonneRepository.findAll();
	}
	
	

}
