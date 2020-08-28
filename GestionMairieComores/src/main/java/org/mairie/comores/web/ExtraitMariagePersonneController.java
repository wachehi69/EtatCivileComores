package org.mairie.comores.web;

import java.util.Date;

import javax.validation.Valid;

import org.mairie.comores.entities.ExtraitMariagePersonne;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.IExtraitMariagePersonne;
import org.mairie.comores.metier.UserMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExtraitMariagePersonneController {
	
	
	@Autowired
	private IExtraitMariagePersonne  extraitMariagePersonneImpl;
	
	@Autowired
	private UserMetierImpl  userMetierImpl;
	
	@Autowired
	private IEmployeMetier employeMetierImpl;
	
	
	
	@RequestMapping("/extraitMariage")
	private String index(ExtraitMariagePersonne extraitMPersonne, Model model) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
		} catch (Exception e) {
		}
		return "extraitMariagePersonne";
	}
	
	@GetMapping("/consulationExtraitMariage")
	public String showForm(Model model, String motCle, ExtraitMariagePersonne extraitMariage, 
			String operation, Long numExtMariage,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
			
			if (operation.equals("modif")) {
				ExtraitMariagePersonne extraitP = extraitMariagePersonneImpl.getExtraitMariage(numExtMariage);
				model.addAttribute("extraitMariagePersonne", extraitP);
				model.addAttribute("operation", operation);
				model.addAttribute("etat", operation);
			}

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		ChargerExtraitMariage(model, numExtMariage, motCle, page, size);
		return "extraitMariagePersonne";
	}
	
	@RequestMapping(value = "/saveExtraitMariage", method = RequestMethod.POST)
	public String enregistrer(@Valid ExtraitMariagePersonne extraitMariagePersonne, Errors errors, Model model,
			String operation, String motCle, String action, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
	
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		if (null != action && action.equalsIgnoreCase("annuler")) {
			return "redirect:/consulationExtraitMariage?motCle=" + motCle + "&page=" + page;
		}
		if (errors.hasErrors()) {
			Page<ExtraitMariagePersonne> listExtraitPage = extraitMariagePersonneImpl.listeExtraitMariageParNom(motCle, page, size);
			model.addAttribute("listextraitPage", listExtraitPage.getContent());
			model.addAttribute("motCle", motCle);
			model.addAttribute("operation", "modif");
			model.addAttribute("etat", "modif");
			return "extraitMariagePersonne";
		}
		//ici on va récuperer l'utilisateur qui est connecté
		Users user = EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
		if (operation.equals("modif")) {
			UpdateExtraitMariage(extraitMariagePersonne, user);
		} else {
		
		// date de creation de l'extrait de naissance
		      extraitMariagePersonne.setDateCreation(new Date());
		//date de modification de l'extrait de naissance      
		      extraitMariagePersonne.setDateModification(new Date());
		// utilisateur qui a créée l'extrait de naissance
			  extraitMariagePersonne.setUser(user);
			  extraitMariagePersonneImpl.saveExtraitMariage(extraitMariagePersonne);
		}		
				
		return "redirect:/consulationExtraitMariage?motCle=" + motCle + "&page=" + page;
	}
	
	@RequestMapping("/suppressionExtraitMariage")
	private String supprimerExtraitMariage(Long numExtMariage, Model model, String motCle, int page) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
			extraitMariagePersonneImpl.deleteExtraitMariage(numExtMariage);
					} catch (Exception e) {

		}
		return "redirect:/consulationExtraitMariage?motCle=" + motCle + "&page=" + page;
	}

	
	
	/**
	 * Cette methode permet de mettre à jour un extrait de mariage
	 * @param extraitMariagePersonne
	 * @param user
	 */
	
	private void UpdateExtraitMariage(@Valid ExtraitMariagePersonne extraitMariagePersonne, Users user) {
		
		
		ExtraitMariagePersonne extrMP = extraitMariagePersonneImpl.getExtraitMariage(extraitMariagePersonne.getNumExtMariage());
		// Récuperation de la date de creation
		extraitMariagePersonne.setDateCreation(extrMP.getDateCreation());
		extraitMariagePersonne.setDateModification(new Date());
		extraitMariagePersonne.setUser(user);
		//mise à jour
		extraitMariagePersonneImpl.updateExtraitMariage(extraitMariagePersonne);
		
	}

	/**
	 * Methode pour charger la liste des extraits de mariages
	 * @param model
	 * @param motCle
	 * @param page
	 * @param size
	 */

	private void ChargerExtraitMariage(Model model, Long numExtMariage, String motCle, int page, int size) {
		
		try {
			Page<ExtraitMariagePersonne> listExtraitPage = extraitMariagePersonneImpl.listeExtraitMariageParNom(motCle,
					page, size);
			model.addAttribute("listextraitPage", listExtraitPage.getContent());
			int[] pages = new int[listExtraitPage.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("page", page);
			model.addAttribute("numExtMariage ", numExtMariage);

		} catch (Exception e) {
			model.addAttribute("exception", e);

		}

		
	}
	

}
