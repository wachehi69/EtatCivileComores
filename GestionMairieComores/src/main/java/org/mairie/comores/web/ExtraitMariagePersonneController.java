package org.mairie.comores.web;

import org.mairie.comores.entities.ExtraitMariagePersonne;
import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.IExtraitMariagePersonne;
import org.mairie.comores.metier.UserMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		ChargerExtraitMariage(model, motCle, page, size);
		return "extraitMariagePersonne";
	}
	
	/**
	 * Methode pour charger la liste des extraits de mariages
	 * @param model
	 * @param motCle
	 * @param page
	 * @param size
	 */

	private void ChargerExtraitMariage(Model model, String motCle, int page, int size) {
		
		
		try {

			Page<ExtraitMariagePersonne> listExtraitPage = extraitMariagePersonneImpl.listeExtraitMariageParNom(motCle,
					page, size);
			model.addAttribute("listextraitPage", listExtraitPage.getContent());
			model.addAttribute("motCle", motCle);

		} catch (Exception e) {
			model.addAttribute("exception", e);

		}

		
	}


}
