package org.mairie.comores.metier;

import java.util.List;
import java.util.Optional;

import org.mairie.comores.dao.ExtraitDecesPersonneRepository;
import org.mairie.comores.entities.ExtraitDecesPersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExtraitDecesPersonneImpl implements IExtraitDecesPersonne{
	
	@Autowired
	private ExtraitDecesPersonneRepository  extraitDecesPersonneRepository;

	@Override
	public ExtraitDecesPersonne saveExtraitDeces(ExtraitDecesPersonne extraitDeces) {
		return extraitDecesPersonneRepository.save(extraitDeces);
	}

	@Override
	public ExtraitDecesPersonne getExtraitDeces(Long numExtraitDeces) {
		Optional<ExtraitDecesPersonne> extrait = extraitDecesPersonneRepository.findById(numExtraitDeces);
		if (extrait.get() == null)
			throw new RuntimeException("numero extrait invalide");
		return extrait.get();
	}

	@Override
	public void updateExtraitDeces(ExtraitDecesPersonne extraitDeces) {
		extraitDecesPersonneRepository.saveAndFlush(extraitDeces);
	}

	@Override
	public void deleteExtraitDeces(Long numExtraitDeces) {
		
		ExtraitDecesPersonne extraitDe = getExtraitDeces(numExtraitDeces);
		if (extraitDe == null)
			throw new RuntimeException("numero extrait de décès est invalide");
		extraitDecesPersonneRepository.delete(extraitDe);
		
	}  

	@Override
	public Page<ExtraitDecesPersonne> listeParPageExtraitDeces(String nom, int page, int size) {
		Page<ExtraitDecesPersonne> extraitPage = extraitDecesPersonneRepository.listeExtraitDecesParNom("%"+nom +"%", new PageRequest(page, size));
		// Pageable sortedByPriceDesc = PageRequest.of(page, size,
		// Sort.by("name").descending());
		if (extraitPage == null || extraitPage.isEmpty()) throw new RuntimeException("Nom est inexistant !!!");
		return extraitPage;
	}

	@Override
	public Page<ExtraitDecesPersonne> listeParPageExtraitParNumExtraitDeces(Long numExtraitDeces, int page, int size) {
		Page<ExtraitDecesPersonne> extraitPage = extraitDecesPersonneRepository
				.listeExtraitDecesParNumExtraitDeces( numExtraitDeces, new PageRequest(page, size));

		if (extraitPage == null || extraitPage.isEmpty())
			throw new RuntimeException("Numéro est inexistant !!!");
		return extraitPage;
	}

	@Override
	public List<ExtraitDecesPersonne> listeExtraitDeces() {
		return extraitDecesPersonneRepository.findAll();
	}

}
