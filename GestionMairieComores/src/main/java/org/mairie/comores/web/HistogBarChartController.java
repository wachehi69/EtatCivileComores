package org.mairie.comores.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.IExtraitNaissancePersonne;
import org.mairie.comores.metier.UserMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 



@Controller
public class HistogBarChartController {
	
	
	@Autowired
	private IExtraitNaissancePersonne  extraitNaissancePersonneImpl;
	
	@Autowired
	private IEmployeMetier employeMetierImpl;

	@Autowired
	private UserMetierImpl userMetierImpl;
	
	
	@RequestMapping("/histogrammeBarreParMois")
	private void barHistogrammeParMois(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);

		} catch (Exception e) {
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		
		// Récuperer a liste des extrait de naissance
		   List<ExtraitNaissancePersonne> listeExtaitN = extraitNaissancePersonneImpl.listeExtraitNaissance();
		  
		   String [] mois = {"Janvier", "Fevrier", "Mars" , "Avril", "Mai", "juin", "Juillet" , "Août",
				   "Septembre", "Octobre", "Novembre", "Decembre"};
		   int [] nombreNaissanceGarcon = new int[12];  // Contient le nbre de naissances de garçon par mois sur les douze mois
		   int [] nombreNaissanceFille = new int[12];   // Contient le nbre de naissances de fille par mois sur les douze mois
		   String[] genreGarcon = new String[12];       // contient  la chaine garçon
		   String[] genreFille = new String[12];        // contien la chaine fille 
		   String[] month = new String[12];
		
		   Calendar calendar = new GregorianCalendar();
			   
			  int nbNaissanceGarcon = 0; // nbre de naissance
			  int nbNaissanceFille = 0;
			  for(int i=0; i<mois.length ; i++){
				  nbNaissanceGarcon=0;
				  nbNaissanceFille =0;
			  collecteDonneeStatiqueParMois(listeExtaitN, mois, nombreNaissanceGarcon, nombreNaissanceFille, genreGarcon,
					genreFille, month, calendar, nbNaissanceGarcon, nbNaissanceFille, i);
			  
				 
			   dataset.addValue(nombreNaissanceGarcon[i], genreGarcon[i], month[i]);
			   dataset.addValue(nombreNaissanceFille[i],   genreFille[i], month[i]);
			  }

	    JFreeChart barChart = ChartFactory.createBarChart("Representation graphique des naissances", "", 
	      "Nombre de naissances/Mois", dataset, PlotOrientation.VERTICAL, true, true, false); 
	     OutputStream out = response.getOutputStream(); 
	     response.setContentType("image/png"); 
	     ChartUtilities.writeChartAsPNG(out, barChart, 2000, 1000); 
	    
	    
	}
	
	@RequestMapping("/histogrammeBarreParAn")
	private void barHistogrammeParAn(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);

		} catch (Exception e) {
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		
		// Récuperer a liste des extrait de naissance
		   List<ExtraitNaissancePersonne> listeExtaitN = extraitNaissancePersonneImpl.listeExtraitNaissance();
		  
		   String [] annee = {"2018","2019", "2020", "2021", "2022", "2023", "2024" , "2025",
				   "2026", "2027", "2028", "2029","2030"};
		   int [] nombreNaissanceGarcon = new int[14];  // Contient le nbre de naissances de garçon par an 
		   int [] nombreNaissanceFille = new int[14];   // Contient le nbre de naissances de fille par an
		   String[] genreGarcon = new String[14];       // contient la chaine garçon
		   String[] genreFille = new String[14];        // contient la chaine fille 
		   String[] tabannee = new String[14];          // contient les années
		
		   Calendar calendar = new GregorianCalendar();
			   
			  int nbNaissanceGarcon = 0; // compteur pour le nbre de naissances garçon
			  int nbNaissanceFille = 0;  // compteur pour le nbre de naissances fille
			  for(int i=0; i<annee.length ; i++){
				  nbNaissanceGarcon=0;
				  nbNaissanceFille =0;
			  collecteDonneeStatiqueParAn(listeExtaitN, annee, nombreNaissanceGarcon, nombreNaissanceFille, genreGarcon,
					genreFille, tabannee, calendar, nbNaissanceGarcon, nbNaissanceFille, i);
			  
			   dataset.addValue(nombreNaissanceGarcon[i], genreGarcon[i], tabannee[i]);
			   dataset.addValue(nombreNaissanceFille[i],  genreFille[i],  tabannee[i]);
			  }

	    JFreeChart barChart = ChartFactory.createBarChart("Representation graphique des naissances", "", 
	      "Nombre de naissances/An", dataset, PlotOrientation.VERTICAL, true, true, false); 
	     OutputStream out = response.getOutputStream(); 
	     response.setContentType("image/png"); 
	     ChartUtilities.writeChartAsPNG(out, barChart, 2000, 1000); 
	    
	    
	}
      
	private void collecteDonneeStatiqueParAn(List<ExtraitNaissancePersonne> listeExtaitN, String[] annee,
			int[] nombreNaissanceGarcon, int[] nombreNaissanceFille, String[] genreGarcon, String[] genreFille,
			String[] tabannee, Calendar calendar, int nbNaissanceGarcon, int nbNaissanceFille, int i) {
		
		for(ExtraitNaissancePersonne extraiN : listeExtaitN){
			  
		    // Récuperation des naissances pour chaque mois*/
		   // on recupère la date de creation de chaque extrait de naissance			  
			  Date dteMois =  extraiN.getDateCreation();
			   calendar.setTime(dteMois);
			 // on va extraire la date et le mois 
			   int  dteyear  = calendar.get(Calendar.YEAR);
			   //int dtemonth = calendar.get((Calendar.MONTH)) + 1;
			   String civilite = extraiN.getNomDuSexe();
			      
			      //On va convertir un int en string
			      String dteyearStr = String.valueOf(dteyear);
			      
			      if(civilite.equalsIgnoreCase("feminin") ) {
			    	  if(annee[i].equals(dteyearStr)){
				    	  ++ nbNaissanceFille;
				      } 
			      }else  {
			    	  if(annee[i].equals(dteyearStr) ){ 
			    		  ++ nbNaissanceGarcon;
			    	  }
			      }
			    }
		    	// on affecte la somme des naissance fille
		  		nombreNaissanceFille[i] = nbNaissanceFille;
		  		// on affecte la somme des naissance garçon
		  		nombreNaissanceGarcon[i] = nbNaissanceGarcon;
		  		// affectation du concerné
		  		tabannee[i]= annee[i];
		  		//la civilié pour les garçons
		  		genreGarcon[i] ="garcon";
		  	    //la civilié pour les filles
		  		genreFille[i] ="fille";
	}

		
	

	/**
	 * Cette methode permet de collecter de données statiques 
	 * pour les naissances/ mois
	 * pour afficher des histogrammes 
	 * @param listeExtaitN
	 * @param mois
	 * @param nombreNaissanceGarcon
	 * @param nombreNaissanceFille
	 * @param genreGarcon
	 * @param genreFille
	 * @param month
	 * @param calendar
	 * @param nbNaissanceGarcon
	 * @param nbNaissanceFille
	 * @param i
	 */
	private void collecteDonneeStatiqueParMois(List<ExtraitNaissancePersonne> listeExtaitN, String[] mois,
			int[] nombreNaissanceGarcon, int[] nombreNaissanceFille, String[] genreGarcon, String[] genreFille,
			String[] month, Calendar calendar, int nbNaissanceGarcon, int nbNaissanceFille, int i) {
		for(ExtraitNaissancePersonne extraiN : listeExtaitN){
			  
		    // Récuperation des naissances pour chaque mois*/
		   // on recupère la date de creation de chaque extrait de naissance			  
			  Date dteMois =  extraiN.getDateCreation();
			   calendar.setTime(dteMois);
			   int  dteyear  = calendar.get(Calendar.YEAR);
			   int dtemonth = calendar.get((Calendar.MONTH)) + 1;
			   String civilite = extraiN.getNomDuSexe();
			    // on teste si le mois de l'extrait de naissance qui a été crée 
			    // correspond bien à celui du tableau du mois
			   
			    // Récuperons la date du jours pour extraire l'année encore
			      Date datejours =  new Date();
			      calendar.setTime(datejours);
			      // Récupérons l'année encore
			      int anneeEncour = calendar.get(Calendar.YEAR);
			      
			      if(civilite.equalsIgnoreCase("feminin") && anneeEncour== dteyear) {
			    	  if(mois[i].equals(mois[dtemonth-1])){
				    	  ++ nbNaissanceFille;
				      } 
			      }else  {
			    	  if(mois[i].equals(mois[dtemonth-1]) && anneeEncour== dteyear){ 
			    		  ++ nbNaissanceGarcon;
			    	  }
			      }
			    }
		    	// on affecte la somme des naissance fille
		  		nombreNaissanceFille[i] = nbNaissanceFille;
		  		// on affecte la somme des naissance garçon
		  		nombreNaissanceGarcon[i] = nbNaissanceGarcon;
		  		// affectation du concerné
		  		month[i]= mois[i];
		  		//la civilié pour les garçons
		  		genreGarcon[i] ="garcon";
		  	    //la civilié pour les filles
		  		genreFille[i] ="fille";
	}

}
