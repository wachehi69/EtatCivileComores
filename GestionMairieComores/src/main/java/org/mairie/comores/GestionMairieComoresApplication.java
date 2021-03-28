package org.mairie.comores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.mairie.comores.dao.EmployeRepository;
import org.mairie.comores.dao.ExtraitDecesPersonneRepository;
import org.mairie.comores.dao.ExtraitNaissancePersonneRepository;
import org.mairie.comores.dao.UserRepository;
import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.EmployeUsers;
import org.mairie.comores.entities.ExtraitDecesPersonne;
import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.mairie.comores.entities.Roles;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.UserMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class GestionMairieComoresApplication implements CommandLineRunner {
	@Autowired
	private EmployeRepository employeRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExtraitNaissancePersonneRepository extraitNaissanceRepository;
	
	@Autowired
	private ExtraitDecesPersonneRepository  extraitDecesPersonneRepository;
	
	

	List<EmployeUsers> listEmplUsers = new ArrayList<EmployeUsers>();

	public static void main(String[] args) {
		SpringApplication.run(GestionMairieComoresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
          
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	/*	try {
			// Ajouter un employé en base
			AjoutEmploye(dateFormat);
			// affichage des employés
			getAffichageEmploye();

		} catch (Exception e) {
			System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
			System.err.println(e.getMessage());
		}

		// creation des users
		ajoutUsers();
		//initialiser le premier extrait de naissance
		initialisationExtraitNaissance();
		// afficher tous employes qui sont utilisateurs  
		//initialisation de l'extrait de decès
		initialisationExtraitDeces();*/
		
		try {
			affichageUtilisateur();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Methode qui permet d'afficher tous les employés qui sont utilisateurs
	 */
	public void affichageUtilisateur() {
		List<Employe> listEmpl = userRepository.listeUsersEmployeParNom("%" + "abdoul " + "%");
		List<Users> listUsers = userRepository.listeEmployeUsesParNom("%" + "abdoul" + "%");

		List<EmployeUsers> listEmpUsers = getAffichageEmployesUsers(listEmpl, listUsers);
		listEmpUsers.forEach(System.out::println);
		
		System.out.println("***************************");
		
		for (EmployeUsers employeUsers : listEmpUsers) {
			
			System.out.print(employeUsers.getNomDuSexe() + " ");
			System.out.print(employeUsers.getNomemp() + " ");
			System.out.print(employeUsers.getPrenemp() + " ");
			System.out.print(employeUsers.getUsername() + " ");
			System.out.print(employeUsers.getPassword()+ " ");
			
			for (Roles role :employeUsers.getRoles() ) {
				System.out.print(role.getRole() + " ");
			}
			System.out.println();
			
		}
	}

	/**
	 * Cette methode permet d'initialiser un extrait de naissance
	 */
	public void initialisationExtraitNaissance() {
		// Creation d'un extrait de naissance
		   ExtraitNaissancePersonne extrait = new ExtraitNaissancePersonne();
		//Creation de l'utilisateur   
		   Users user = new Users();
		   user.setUsername("wachehi");
		   user.setPassword("9703d4cc78b774db9346c3d3231af9ac");
		   
		// creation d'un objet role
			Roles role1 = new Roles("USER");
			Roles role2 = new Roles("ADMIN");
			Set<Roles> roles = new HashSet<>();
			roles.add(role1);
			roles.add(role2);
		    user.setRoles(roles);
		 // creation d'un objet employe 
			Optional<Employe> emp = employeRepository.findById(1L);
		    user.setEmploye(emp.get());
		    user.setActive(true);
		    
		   // appel de la methode Ajout extrait de naissance  
			AjoutExtraitNaissance(extrait,user);
	}
	
	
	/**
	 * Cette methode permet d'initialiser un extrait de naissance
	 */
	public void initialisationExtraitDeces() {
		// Creation d'un extrait de naissance
		   ExtraitDecesPersonne extraitDeces = new ExtraitDecesPersonne();
		//Creation de l'utilisateur   
		   Users user = new Users();
		   user.setUsername("wachehi");
		   user.setPassword("9703d4cc78b774db9346c3d3231af9ac");
		   
		// creation d'un objet role
			Roles role1 = new Roles("USER");
			Roles role2 = new Roles("ADMIN");
			Set<Roles> roles = new HashSet<>();
			roles.add(role1);
			roles.add(role2);
		    user.setRoles(roles);
		 // creation d'un objet employe 
			Optional<Employe> emp = employeRepository.findById(1L);
		    user.setEmploye(emp.get());
		    user.setActive(true);
		    
		   // appel de la methode Ajout extrait de deces 
			AjoutExtraitDeces(extraitDeces,user);
	}
	
	 public void AjoutExtraitDeces(ExtraitDecesPersonne extraitDeces, Users user) {
		
		   extraitDeces.setNomDuSexe("Monsieur");
		   extraitDeces.setNom("Bacar");
		   extraitDeces.setPrenom("wali");
		   extraitDeces.setDateJoursetMoisDeces("le trois avril");
		   extraitDeces.setDateAnneedeDeces("mil neuf quatre vingt un");
		   extraitDeces.setLieuDeDeces("Mutsamudu");
		   extraitDeces.setCommuneDuDeces("Musamudu");
		   
		   extraitDeces.setDateJoursetMoisNaissance("premier mai");
		   extraitDeces.setDateAnneedeNaissance("mil neut cent soixante un");
		   extraitDeces.setHeureNaissance("vingt une");
		   extraitDeces.setMinuteNaissance("trente");
		   extraitDeces.setCommuneNaissance("Mutsamudu");
		   extraitDeces.setNomDuPere("Marouani");
		   extraitDeces.setPrenomDuPere("wali");
		   extraitDeces.setNomDuMere("zaitoune");
		   extraitDeces.setPrenomDuMere("Salim");
		   
		   extraitDeces.setDeclarationFaitePar("Declaration fait par Michelle boudra ");
		   extraitDeces.setDeclarationRecueParnous("Declaration reçu par nous");
		   extraitDeces.setNumRegistre("1");
		   extraitDeces.setDateCreation(new Date());
		   extraitDeces.setUser(user);
		   extraitDecesPersonneRepository.save(extraitDeces);	
		
	}

	/**
	  * Methode qui permet de d'initialiser un extrait de naissance
	  * @param extrait
	  * @param user
	  */
	public void AjoutExtraitNaissance(ExtraitNaissancePersonne extrait, Users user) {
		   extrait.setNomDuSexe("Monsieur");
		   extrait.setNom("Abdoul Madjid");
		   extrait.setPrenom("Ali");
		   extrait.setDateJoursetMoisNaissance("premier mai");
		   extrait.setDateAnneedeNaissance("mil neut cent soixante neuf");
		   extrait.setHeureNaissance("vingt une");
		   extrait.setMinuteNaissance("trente");
		   extrait.setCommuneNaissance("Mutsamudu");
		   extrait.setNomDuPere("Haladi");
		   extrait.setPrenomDuPere("Abdoul Madjid");
		   extrait.setDateJoursetMoisNaissancePere("trente juin");
		   extrait.setDateAnneedeNaissancePere("mil neuf cent quarante huite");
		   extrait.setHeureNaissancePere("vingt");
		   extrait.setMinuteNaissancePere("quarate");
		   extrait.setCommuneNaissancePere("Mutsamudu");
		   extrait.setProfessionPere("Commerçant");
		   extrait.setNomDuMere("Fatima");
		   extrait.setPrenomDuMere("Houmadi");
		   extrait.setDateJoursetMoisNaissanceMere("premier octobre");
		   extrait.setDateAnneedeNaissanceMere("mil neuf cent cinquante un");
		   extrait.setHeureNaissanceMere("onze");
		   extrait.setMinuteNaissanceMere("trente");
		   extrait.setCommuneNaissanceMere("Mutsamudu");
		   extrait.setProfessionMere("Mère au foyer");
		   extrait.setDeclarationFaitePar("Declaration fait par Michelle boudra ");
		   extrait.setDeclarationRecueParnous("Declaration reçu par nous");
		   extrait.setNumRegistre("1");
		   extrait.setDateCreation(new Date());
		   extrait.setUser(user);
		   extraitNaissanceRepository.save(extrait);	
		
	}
	
	/**
	 * 
	 */
	private void getAffichageEmploye() {
		// supprimer un employé
		// employeRepository.deleteById(3L);
		// afficher la liste des employés commençant par m
		Page<Employe> listeEmployeM = employeRepository.listeEmployeParNom("M" + "%", new PageRequest(0, 5));
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
		Employe empnp = employeRepository.chercherEmployeParNomEtPrenom("bacar", "lili");
		System.out.println("employe par nom et prenom :" + empnp);
		// modifier un nom de l'employé qui existe déjà
		Optional<Employe> empj = employeRepository.findById(3L);
		Employe empp = empj.get();
		empp.setNomemp("Said");
		employeRepository.saveAndFlush(empp);
	}

	private List<EmployeUsers> getAffichageEmployesUsers(List<Employe> listEmpl, List<Users> listUsers) {

		for (Employe employe : listEmpl) {
			EmployeUsers employeUsers = new EmployeUsers();
			for (Users users : listUsers) {

				if (employe.getIdempl() == users.getEmploye().getIdempl()) {

					employeUsers.setIdempl(employe.getIdempl());
					employeUsers.setNomDuSexe(employe.getNomDuSexe());
					employeUsers.setNomemp(employe.getNomemp());
					employeUsers.setPrenemp(employe.getPrenemp());
					employeUsers.setUsername(users.getUsername());
					employeUsers.setPassword(users.getPassword());
					employeUsers.setRoles(users.getRoles());
					// ajout des utilisateurs
					listEmplUsers.add(employeUsers);
				}
			}
		}

		return listEmplUsers;
	}

	/**
	 * @param dateFormat
	 * @throws ParseException
	 */
	private void AjoutEmploye(SimpleDateFormat dateFormat) throws ParseException {
		Date date1 = null;
		Date date2 = null;
		Date date3 = null;
		date1 = dateFormat.parse("15/04/1981");
		date2 = dateFormat.parse("20/04/1978");
		date3 = dateFormat.parse("01/05/1987");
		Employe emp1 = employeRepository.save(new Employe("Monsieur", "Abdoul Madjid", "Ali", "wachehi@gmail.com",
				"Secretaire", date1, new Date(), 12000));
		Employe emp2 = employeRepository.save(new Employe("Madame", "Said Omar Djamalati", "Zahiya", "zahiya@gmail.com",
				"Comptable", date2, new Date(), 15000));
		Employe emp3 = employeRepository.save(new Employe("Madame", "Mohamed farouk", "yaya", "farouk@gmail.com",
				"Comptable", date3, new Date(), 15000));
		Employe emp4 = employeRepository.save(new Employe("Monsieur", "Abdoul Madjid", "Seimour", "seimour@gmail.com",
			"Informaticien", date3, new Date(), 18000));
		Employe emp5 = employeRepository.save(new Employe("Madame", "Mohamed bacar", "lili", "lili@gmail.com",
			"intandant", date2, new Date(), 15000));
		Employe emp6 = employeRepository.save(
				new Employe("Monsieur", "bacar", "lili", "lili@gmail.com", "responsable", date2, new Date(), 15000));
		
		Employe emp7 = employeRepository.save(
				new Employe("Monsieur", "bacar", "lola", "lola@gmail.com", "responsable", date2, new Date(), 15000));
		
		Employe emp8 = employeRepository.save(
				new Employe("Monsieur", "Mohamed", "soso", "soso@gmail.com", "responsable", date2, new Date(), 15000));
		
		Employe emp9 = employeRepository.save(
				new Employe("Madame", "Abdoul Madjid", "nafissa", "nafissa@gmail.com", "responsable", date2, new Date(), 15000));

	}

	/**
	 * cette methode permet creer un nouveau users
	 */
	private void ajoutUsers() {
		// creation d'un objet employe 1
		Employe emp1 = new Employe();
		emp1.setIdempl(1L);
		// creation d'un objet role
		Roles role1 = new Roles("USER");
		Roles role2 = new Roles("ADMIN");
		Set<Roles> roles1 = new HashSet<>();
		roles1.add(role1);
		roles1.add(role2);

		// creation d'un objet employe 2
		Employe emp2 = new Employe();
		emp2.setIdempl(4L);
		// creation d'un objet role
		Roles role3 = new Roles("USER");
		Roles role4 = new Roles("ADMIN");
		Set<Roles> roles2 = new HashSet<>();
		roles2.add(role3);
		 roles2.add(role4);

		// creation d'un objet employe 2
		Employe emp3 = new Employe();
		emp3.setIdempl(2L);
		// creation d'un objet role
		Roles role5 = new Roles("USER");
		Roles role6 = new Roles("ADMIN");
		Set<Roles> roles4 = new HashSet<>();
		roles4.add(role5);
		roles4.add(role6);

		Users user1 = userRepository.save(new Users("wachehi", "9703d4cc78b774db9346c3d3231af9ac", true, roles1, emp1));
		Users user2 = userRepository.save(new Users("seimour", "25d55ad283aa400af464c76d713c07ad", true, roles2, emp2));
		Users user3 = userRepository.save(new Users("zahiya", "25d55ad283aa400af464c76d713c07ad", true, roles4, emp3));

	}
	

}
