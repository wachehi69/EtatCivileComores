package org.mairie.comores.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.EmployeUsers;
import org.mairie.comores.entities.Roles;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.EmployeMetierImpl;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersEmployesController {

	@Autowired
	private IUserMetier userMetierImpl;
	@Autowired
	private IEmployeMetier employeMetierImpl;

	@RequestMapping("/gestionUtilisateur")
	private String index(EmployeUsers employeUsers) {
		return "employeUsers";
	}

	@RequestMapping(value = "/consulationUtilisateur")
	public String consultationUsersEmploye(String motCle,EmployeUsers employeUsers, Model model) {

		try {
			List<EmployeUsers> listUserEmpl = userMetierImpl.listeUsersEmployeParNom("%" + motCle + "%");
			model.addAttribute("listUserEmpl", listUserEmpl);
			model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "employeUsers";
	}

	@RequestMapping(value = "/creationNouveauUtilisateur")
	private String ajoutNouveauUSers(@Valid EmployeUsers employeUsers,	Errors errors, Model model, String motCle) {
		
		if (errors.hasErrors()) {
			List<EmployeUsers> listUserEmpl = userMetierImpl.listeUsersEmployeParNom("%" + motCle + "%");
			model.addAttribute("listUserEmpl", listUserEmpl);
			model.addAttribute("motCle", motCle);
			return "employeUsers";
		}
		
		List<Employe> listUserEmpl = userMetierImpl.listeEmployeParNom("%" + employeUsers.getNomemp() + "%");
		                                                 
		for (Employe employeUsers2 : listUserEmpl) {
			
			if(employeUsers2.getNomDuSexe().equalsIgnoreCase(employeUsers.getNomDuSexe())
					&& employeUsers2.getNomemp().equalsIgnoreCase(employeUsers.getNomemp())
					&& employeUsers2.getPrenemp().equalsIgnoreCase(employeUsers.getPrenemp())){
				
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
				
				userMetierImpl.saveUsers(new Users(employeUsers.getUsername(),
						employeUsers.getPassword(), employeUsers.getActive(), roles, emp));
			}
			
		}

		return "redirect:/consulationUtilisateur?motCle="+ motCle;
	}

}
