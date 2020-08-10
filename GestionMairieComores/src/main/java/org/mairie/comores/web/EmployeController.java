package org.mairie.comores.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.UserMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeController {
	@Autowired
	private IEmployeMetier employeMetierImpl;

	@Autowired
	private UserMetierImpl userMetierImpl;

	@RequestMapping("/employes")
	private String index(Employe employe, Model model) {
		try {
			// Recuperer l'utilisateur connecté
			ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			 dateDujours(model);
		} catch (Exception e) {
		}

		return "employes";
	}


	/**
	 * Cette fonction permet d'afficher les employes page par page
	 * 
	 * @param model
	 * @param motCle
	 * @param employe
	 * @param operation
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/consultationEmployes")
	public String showForm(Model model, String motCle, Employe employe, String operation, String exceptionMail,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		chargerListeEmploye(model, motCle, employe, operation, page, size);
		try {
			// Recuperer l'utilisateur connecté
			ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			dateDujours(model);
		} catch (Exception e) {
		}

		model.addAttribute("exceptionMail", exceptionMail);
		return "employes";
	}

	/**
	 * Cette fonction permet d'enregistrer un employe en base de données
	 * 
	 * @param employe
	 * @param errors
	 * @param model
	 * @param motCle
	 * @return
	 */

	@PostMapping("/saveEmployes")
	public String register(@Valid Employe employe, Errors errors, Model model, String motCle, String operation,
			String action, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		try {
			// Recuperer l'utilisateur connecté
			ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
		} catch (Exception e) {
		}

		if (action != null && action.equals("annuler")) {
			return "redirect:/consultationEmployes?motCle=" + motCle + "&page=" + page;
		}
		if (errors.hasErrors()) {
			if (operation.equals("modif")) {
				model.addAttribute("etat", operation);
			}
			chargerListeEmploye(model, motCle, employe, operation, page, size);
			return "employes";
		} else {

			if (employe.getIdempl() != null) {
				employeMetierImpl.modifierEmploye(employe);
				return "redirect:/consultationEmployes?motCle=" + motCle + "&page=" + page;
			}
			// avant la validation cherchons si le mail de l'employé n'est
			// pas encours d'utilisation
			try {
				employeMetierImpl.saveEmploye(employe);

			} catch (Exception e) {
				return "redirect:/consultationEmployes?motCle=" + motCle + "&exceptionMail=" + e.getMessage();
			}
		}
		return "redirect:/consultationEmployes?motCle=" + motCle;
	}

	/**
	 * Cette methode permet de supprimer un employé
	 * 
	 * @param idempl
	 * @param nomemp
	 * @return
	 */

	@GetMapping(value = "/suppressionEmploye")
	private String suppressionEmploye(Long idempl, String motCle, int page) {

		employeMetierImpl.supprimerEmployer(idempl);
		return "redirect:/consultationEmployes?motCle=" + motCle + "&page=" + page;

	}

	/**
	 * @param model
	 * @param motCle
	 * @param employe
	 * @param operation
	 * @param page
	 * @param size
	 */
	private void chargerListeEmploye(Model model, String motCle, Employe employe, String operation, int page,
			int size) {
		model.addAttribute("motCle", motCle);
		try {
			Page<Employe> pageEmployes = employeMetierImpl.listeEmployeParNom(motCle, page, size);
			model.addAttribute("listeEmployes", pageEmployes.getContent());
			int[] pages = new int[pageEmployes.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("page", page);
			if (operation.equalsIgnoreCase("modif")) {
				Employe employe1 = employeMetierImpl.chargerEmploye(employe.getIdempl());
				model.addAttribute("employe", employe1);
				model.addAttribute("etat", operation);
				model.addAttribute("operation", operation);
			}

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
	}

	/**
	 * Cette methode permet de charger l'utilisateur connecté
	 * 
	 * @param model
	 * @param username
	 */
	public static Users ChargerUserConnection(Model model, UserMetierImpl userMetierImpl,
			IEmployeMetier employeMetierImpl) {
		Users user =null;
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (null != username) {
			user = userMetierImpl.getUsers(username);
			Employe emp = employeMetierImpl.chargerEmploye(user.getEmploye().getIdempl());
			model.addAttribute("empl", emp);
		}
		return user;
	}
	
	

	/**
	 * Cette methode permet d'afficher la date du jour
	 * @param model
	 */
	public static void dateDujours(Model model) {
		
		Date date = new Date( );

		  // définir un format personnalisé(France
		  DateFormat format_fr =
		          DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
		  // Appliquer le format à la date
		  System.out.println(format_fr.format(date));
		  
		  model.addAttribute("datejours", format_fr.format(date));
	}

}
