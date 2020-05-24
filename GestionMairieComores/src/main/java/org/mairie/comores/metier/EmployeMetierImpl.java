package org.mairie.comores.metier;

import java.util.List;
import java.util.Optional;

import org.mairie.comores.dao.EmployeRepository;
import org.mairie.comores.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeMetierImpl implements IEmployeMetier{
	
	@Autowired
	private EmployeRepository employeRepository;

	@Override
	public Employe saveEmploye(Employe employe) {
		
		List<Employe> employes = listEmploye();
		 for (Employe emp : employes) {
			 if(emp.getMail().equalsIgnoreCase(employe.getMail())) throw new RuntimeException("Email en cour d'utilisation !!!");
		}
		return employeRepository.save(employe);
	}

	@Override
	public Employe chargerEmploye(Long idempl) {
		
	Optional<Employe> emp = employeRepository.findById(idempl);
	 if(emp.get() == null)  throw new RuntimeException("cet employé est inexistant");
		return emp.get();
	}

	@Override
	public List<Employe> listEmploye() {
		return employeRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Employe> listeEmployeParNom(String nomemp, int page, int size) {
		Page<Employe> pageEmp = employeRepository.listeEmployeParNom("%"+nomemp +"%", new PageRequest(page, size) );
		if(pageEmp == null || pageEmp.isEmpty()) throw new RuntimeException("Nom introuvable");
		return pageEmp;
	}

	@Override
	public void supprimerEmployer(Long idempl) {
		employeRepository.deleteById(idempl);
	}

	@Override
	public Employe modifierEmploye(Employe employe) {
		return employeRepository.saveAndFlush(employe);
	}

	@Override
	public Employe chercherEmployeParNomEtPrenom(String nom, String prenom) {
		return employeRepository.chercherEmployeParNomEtPrenom(nom, prenom);
	}

}
