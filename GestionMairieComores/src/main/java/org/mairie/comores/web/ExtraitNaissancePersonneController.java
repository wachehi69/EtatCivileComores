package org.mairie.comores.web;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.IExtraitNaissancePersonne;
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
public class ExtraitNaissancePersonneController {
	
	@Autowired
	private IExtraitNaissancePersonne extraitNaissancePersonneImpl;
	
	@Autowired
	private IEmployeMetier employeMetierImpl;

	@Autowired
	private UserMetierImpl userMetierImpl;
	
	@RequestMapping("/extraitNaissance")
	private String index(ExtraitNaissancePersonne extraitNPersonne, Model model) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
		    EmployeController.dateDujours(model);
		} catch (Exception e) {
		}
		return "extraitNaissancePersonne";
	}
	
	@GetMapping("/consulationExtraitNaissance")
	public String showForm(Model model, String motCle, ExtraitNaissancePersonne extraitNaissance, String operation, Long numExtrait,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
			
			if(operation.equals("modif")){
				ExtraitNaissancePersonne  extraitP = extraitNaissancePersonneImpl.getExtraitNaissance(numExtrait);
				model.addAttribute("extraitNaissancePersonne", extraitP);
			}
			
		} catch (Exception e) {
		  model.addAttribute("exception", e);	
		}
		
		Page<ExtraitNaissancePersonne> listExtraitPage = extraitNaissancePersonneImpl.listeParPageExtrait(motCle, page, size);
		model.addAttribute("listextraitPage", listExtraitPage.getContent());
		model.addAttribute("motCle", motCle);

		return "extraitNaissancePersonne";
	}
	
	@RequestMapping(value="/saveExtraitNaissance", method=RequestMethod.POST)
	public String enregistrer(@Valid ExtraitNaissancePersonne extraitNaissancePersonne, Errors errors, Model model){
		
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
			
		} catch (Exception e) {
		  model.addAttribute("exception", e);	
		}
		if(errors.hasErrors()){
			return "extraitNaissancePersonne";
		}
		Users user = EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
		// date de creation de l'extrait de naissance
		extraitNaissancePersonne.setDateCreation(new Date());
		// utilisateur qui a créée l'extrait de naissance
		extraitNaissancePersonne.setUser(user);
		extraitNaissancePersonneImpl.saveExtraitNaissance(extraitNaissancePersonne);
		
		return "extraitNaissancePersonne";
		
	}
	@RequestMapping("/suppressionExtrait")
	private String supprimerExtraitNaissance(Long  numExtrait, Model model, String motCle) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
		    EmployeController.dateDujours(model);
		  extraitNaissancePersonneImpl.deleteExtraitNaissance(numExtrait);
		} catch (Exception e) {
			
		}
		return "redirect:/consulationExtraitNaissance?motCle=" + motCle;
	}
	

}
