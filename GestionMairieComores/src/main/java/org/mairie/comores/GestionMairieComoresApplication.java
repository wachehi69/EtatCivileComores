package org.mairie.comores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mairie.comores.dao.EmployeRepository;
import org.mairie.comores.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class GestionMairieComoresApplication implements CommandLineRunner {
	@Autowired
	private EmployeRepository  employeRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionMairieComoresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	/* SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		Date date2 = null;
		Date date3 = null;
        
		try {
		    date1 = dateFormat.parse("15/04/1981");
		    date2 = dateFormat.parse("18/03/88");
		    date3 = dateFormat.parse("01/05/1987");
		    Employe emp1 =employeRepository.save(new Employe("Monsieur", "Abdoul Madjid", "Ali", "wachehi@gmail.com", "Secretaire", date1, new Date(), 12000));
		    Employe emp2 = employeRepository.save(new Employe("Madame", "Said Omar Djamalati", "Zahiya", "zahiya@gmail.com", "Comptable", date2, new Date(), 15000));
		    Employe emp3 = employeRepository.save(new Employe("Madame", "Mohamed farouk", "yaya", "farouk@gmail.com", "Comptable", date3, new Date(), 15000));
		    Employe emp4 = employeRepository.save(new Employe("Monsieur", "Abdoul Madjid", "Seimour", "seimour@gmail.com", "Informaticien", date3, new Date(), 18000));
		    Employe emp5 = employeRepository.save(new Employe("Madame", "Mohamed bacar", "lili", "lili@gmail.com", "intandant", date2, new Date(), 15000));
		    Employe emp6 = employeRepository.save(new Employe("Monsieur", "bacar", "lili", "lili@gmail.com", "responsable", date2, new Date(), 15000));
		 
		     // supprimer un employé
		    //employeRepository.deleteById(3L);
		    // afficher la liste des employés commençant par m
		     Page<Employe> listeEmployeM = employeRepository.listeEmployeParNom("M"+"%", new PageRequest(0, 5) );
		     System.out.print("======voila la liste de tous les employes qui commence par m =======");
		     listeEmployeM.forEach(System.out::println);
		     // afficher un employé particulier
		      Optional<Employe> emp = employeRepository.findById(5L);
		      System.out.println("l'employe numero 5 est :" + emp.get());
		     // afficher tous les employés
		      System.out.print("======voila la liste de tous les employes =======");
		      List<Employe> listeEmploye = employeRepository.findAll();
		       listeEmploye.stream().forEach(System.out::println);
		     // afficher un employé par nom et prenom
	         Employe empnp = employeRepository.chercherEmployeParNomEtPrenom("bacar","lili");
		       System.out.println("employe par nom et prenom :" + empnp);	 
		     // modifier un nom de l'employé qui existe déjà
		       Optional<Employe> empj = employeRepository.findById(3L);
		       Employe empp = empj.get();
		       empp.setNomemp("said"); 
		       employeRepository.saveAndFlush(empp);
		       
		} catch (Exception e) {
		    System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
		    System.err.println(e.getMessage());
		}*/
		
	}

}
