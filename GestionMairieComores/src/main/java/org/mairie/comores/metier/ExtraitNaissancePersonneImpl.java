package org.mairie.comores.metier;

import java.util.List;
import java.util.Optional;

import org.mairie.comores.dao.ExtraitNaissancePersonneRepository;
import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExtraitNaissancePersonneImpl implements IExtraitNaissancePersonne {

	@Autowired
	private ExtraitNaissancePersonneRepository extraitNaissancePersonneRepository;

	@Override
	public ExtraitNaissancePersonne saveExtraitNaissance(ExtraitNaissancePersonne extrait) {
		return extraitNaissancePersonneRepository.save(extrait);
	}

	@Override
	public void updateExtraitNaissance(ExtraitNaissancePersonne extrait) {
		extraitNaissancePersonneRepository.saveAndFlush(extrait);
	}

	@Override
	public ExtraitNaissancePersonne getExtraitNaissance(Long numExtrait) {
		Optional<ExtraitNaissancePersonne> extrait = extraitNaissancePersonneRepository.findById(numExtrait);
		if (extrait.get() == null)
			throw new RuntimeException("numero extrait invalide");
		return extrait.get();
	}

	@Override
	public void deleteExtraitNaissance(Long numExtrait) {
		if(numExtrait==null) throw new RuntimeException("numero extrait innexistant");
		ExtraitNaissancePersonne extrait = getExtraitNaissance(numExtrait);
		extraitNaissancePersonneRepository.delete(extrait);
	}

	@Override
	public Page<ExtraitNaissancePersonne> listeParPageExtrait(String nom, int page, int size) {
		Page<ExtraitNaissancePersonne> extraitPage = extraitNaissancePersonneRepository
				.listeExtraitNaissanceParNom("%"+nom +"%", new PageRequest(page, size));
		// Pageable sortedByPriceDesc = PageRequest.of(page, size,
		// Sort.by("name").descending());
		if (extraitPage == null || extraitPage.isEmpty()) throw new RuntimeException("Nom est inexistant !!!");
		return extraitPage;
	}

	@Override
	public List<ExtraitNaissancePersonne> listeExtraitNaissance() {
		return extraitNaissancePersonneRepository.findAll();
	}

	@Override
	public Page<ExtraitNaissancePersonne> listeParPageExtraitParNumExtrait(Long numExtrait, int page, int size) {
		
		Page<ExtraitNaissancePersonne> extraitPage = extraitNaissancePersonneRepository
				.listeExtraitNaissanceParNumExtrait( numExtrait, new PageRequest(page, size));

		if (extraitPage == null || extraitPage.isEmpty())
			throw new RuntimeException("Numéro est inexistant !!!");
		return extraitPage;
	}

}
