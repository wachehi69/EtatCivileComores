package org.mairie.comores.metier;

import java.util.List;
import java.util.Optional;

import org.mairie.comores.dao.PersonneRepository;
import org.mairie.comores.entities.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonneMetierImpl implements IPersonneMetier{
	@Autowired
	private PersonneRepository personneRepository;

	@Override
	public Personne getPersonne(Long id) {
		
	Optional<Personne> pers = personneRepository.findById(id);	
		return pers.get();
	}

	@Override
	public List<Personne> listePersonne() {
		return personneRepository.findAll();
	}

	@Override
	public Personne savePersonne(Personne personne) {
		return personneRepository.save(personne);
	}

	
}
