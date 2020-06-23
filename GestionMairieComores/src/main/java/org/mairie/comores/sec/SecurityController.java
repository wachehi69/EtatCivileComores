package org.mairie.comores.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// on va creer ici un deuxieme controleur pour gerer uniquement l'identification
@Controller
public class SecurityController {

	// c'est l'action d'identification de l'application
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/* Action par defaut */
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/employes";
	}

	@RequestMapping(value = "/403")
	public String accessDenied() {
		return "403";
	}

}
