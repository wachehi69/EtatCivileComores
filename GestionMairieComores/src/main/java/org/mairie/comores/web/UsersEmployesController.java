package org.mairie.comores.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.EmployeUsers;
import org.mairie.comores.entities.Roles;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsersEmployesController {

	@Autowired
	private IUserMetier userMetierImpl;
	
	private Users user = null;

	@RequestMapping("/gestionUtilisateur")
	private String index(EmployeUsers employeUsers) {
		return "employeUsers";
	}

	@RequestMapping(value = "/consulationUtilisateur", method = RequestMethod.GET)
	public String consultationUsersEmploye(String motCle, EmployeUsers employeUsers, Model model) {

		try {
			List<EmployeUsers> listUserEmpl = userMetierImpl.listeUsersEmployeParNom("%" + motCle + "%");
			model.addAttribute("listUserEmpl", listUserEmpl);
			model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "employeUsers";
	}

	@RequestMapping(value = "/creationNouveauUtilisateur", method = RequestMethod.POST)
	private String ajoutNouveauUSers(@Valid EmployeUsers employeUsers, Errors errors, Model model, String motCle) {
		model.addAttribute("motCle", motCle);
		if (errors.hasErrors()) {
			List<EmployeUsers> listUserEmpl = userMetierImpl.listeUsersEmployeParNom("%" + motCle + "%");
			model.addAttribute("listUserEmpl", listUserEmpl);
			model.addAttribute("motCle", motCle);
			return "employeUsers";
		}

		try {

			List<Employe> listUserEmpl = userMetierImpl.listeEmployeParNom("%" + employeUsers.getNomemp() + "%");
			
			boolean operationUtilisat = false;

			for (Employe employeUsers2 : listUserEmpl) {
				operationUtilisat = AjouterNouveauUtilisateur(employeUsers, operationUtilisat, employeUsers2);
			}
			if (operationUtilisat== false) throw new RuntimeException("Verifiez bien le nom,prenom et le nom du sexe de l'employe !!!!!");
			
		} catch (Exception e) {
			return "redirect:/consulationUtilisateur?motCle=" + motCle + "&error=" + e.getMessage();
		}
		return "redirect:/consulationUtilisateur?motCle=" + motCle;
	}
	 @RequestMapping(value="/suppressionUsers")
	 private String  delete(String username, String motCle){
		 
		 userMetierImpl.deleteUsers(username);
		return "redirect:/consulationUtilisateur?motCle=" + motCle;
	 }
	
	

	/**
	 * 
	 * Cette methode permet d'ajout un nouveau utilisateur
	 * @param employeUsers
	 * @param operationUtilisat
	 * @param employeUsers2
	 * @return
	 * @throws RuntimeException
	 */
	private boolean AjouterNouveauUtilisateur(EmployeUsers employeUsers, boolean operationUtilisat,
			Employe employeUsers2) throws RuntimeException {
		if (employeUsers2.getNomDuSexe().equalsIgnoreCase(employeUsers.getNomDuSexe())
				&& employeUsers2.getNomemp().equalsIgnoreCase(employeUsers.getNomemp())
				&& employeUsers2.getPrenemp().equalsIgnoreCase(employeUsers.getPrenemp())) {
			// On va chercher en base si le username n'est encours utilisation déjà
			  List<Users> listUsers = userMetierImpl.listUsers();
			  
			  for (Users users : listUsers) {
				 if(users.getEmploye().getIdempl()==employeUsers2.getIdempl()) throw new RuntimeException("utilisateur est déjà connu");
				 if(users.getUsername().equalsIgnoreCase(employeUsers.getUsername()))throw new RuntimeException("Attention le username est encours d'utilisation !!!");
			}
			  
			// creation d'un objet employe
			Employe emp = new Employe();
			emp.setIdempl(employeUsers2.getIdempl());
			emp.setNomDuSexe(employeUsers2.getNomDuSexe());
			emp.setNomemp(employeUsers2.getNomemp());
			emp.setPrenemp(employeUsers2.getPrenemp());
			// creation d'un objet role
			Set<Roles> roles = new HashSet<>();
			roles.addAll(employeUsers.getRoles());
			// création de l'utlisateur
			user = userMetierImpl.saveUsers(new Users(employeUsers.getUsername(), employeUsers.getPassword(),
					employeUsers.getActive(), roles, emp));
			operationUtilisat =true;
		}
		return operationUtilisat;
	}

}
