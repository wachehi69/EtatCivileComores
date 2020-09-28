package org.mairie.comores.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class ExtraitMariagePersonneController {
	
	
	@Autowired
	private IExtraitMariagePersonne  extraitMariagePersonneImpl;
	
	@Autowired
	private UserMetierImpl  userMetierImpl;
	
	@Autowired
	private IEmployeMetier employeMetierImpl;
	
	String nomFichierSource = "c:\\extraitMariage\\extraitMariage.pdf";
	Paragraph paragraph;
	Phrase phrase;
	Path source;
	Path destination;
	String NomExtrait = "EXTRAIT D'ACTE DE MARIAGE";

	
	
	
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
		ChargerExtraitMariage(model, motCle, page, size);
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
	
	
	@RequestMapping("/generationExtraitMariage")
	private String creationExtraitMariage(Long numExtMariage, Model model, String motCle, int page) {

		Document document = new Document(PageSize.A4);

		ExtraitMariagePersonne extraitMPersonne = extraitMariagePersonneImpl.getExtraitMariage(numExtMariage);
		/* Creation du repertoir extrait de mariage */
		Path repertoir = Paths.get("c:\\extraitMariage\\");
		try {
			Files.createDirectories(repertoir);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		File fichier = new File(nomFichierSource);

		try (FileOutputStream fileoutp = new FileOutputStream(fichier)) {

			/* creation du fichier extrait de mariage */
			if (!fichier.exists())
				fichier.createNewFile(); // creation du fichier s'il n'existe pas

			// Création d'une instance de fichier PDF
			PdfWriter.getInstance(document, new FileOutputStream(fichier));
			System.out.print("Le fichier est créee : " + fichier.getAbsolutePath());
			/* ouverture du fichier en ecriture */
			document.open();
			com.lowagie.text.Font fonte = FontFactory.getFont("timesroman", BaseFont.TIMES_ROMAN, 8);
			com.lowagie.text.Font fonte1 = FontFactory.getFont("timesroman", BaseFont.TIMES_ROMAN, 6);
			com.lowagie.text.Font fonte2 = FontFactory.getFont("timesroman", BaseFont.TIMES_ROMAN, 12);
			// com.lowagie.text.Font fonte3 =
			// FontFactory.getFont("timesroman",BaseFont.TIMES_ROMAN, 14);
			// com.lowagie.text.Font fonte4 =
			// FontFactory.getFont("timesroman",BaseFont.TIMES_ROMAN, 16);

			/* l'entete de l'extrait de mariage */
			document.add(new Paragraph("UNION DES COMORES", fonte));
			document.add(new Paragraph("Unité- Solidarité-Développement", fonte));
			document.add(new Paragraph("MINISTERE DE L'INTERIEUR", fonte));

			fonte2.setStyle(Font.BOLD); // mettre le style en gras
			paragraph = new Paragraph(NomExtrait, fonte2);
			paragraph.setAlignment(Element.ALIGN_RIGHT); // alignement à droite
			paragraph.setLeading(5f); // espacement entre deux lignes
			document.add(paragraph);

			/* inserer l'image */
			Image image = Image.getInstance("mairie.png");
			// image.setAbsolutePosition(220f, 550f);
			document.add(image);

			document.add(new Paragraph("PREFECTURE DE MUTSAMUDU", fonte));
			document.add(new Paragraph("         service état civil", fonte1));

			/* Affichage de la date systeme */

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			DateFormat formater = new SimpleDateFormat("EEEE d MMM yyyy");

			
			
			String dat = dateFormat.format(extraitMPersonne.getDateCreation());
			Font fonteDatesys = fonte2;
			fonteDatesys.setStyle(Font.BOLD);
			paragraph = new Paragraph("Acte N°: " + extraitMPersonne.getNumExtMariage() + " Du " + dat, fonteDatesys);
			paragraph.setAlignment(Element.ALIGN_CENTER);

			document.add(paragraph);

			paragraph = new Paragraph("Registre N°: " + extraitMPersonne.getNumRegistre());
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(fonte2);
			document.add(paragraph);
			
			phrase = new Phrase("Le  : ");
			phrase.add(new Chunk("                " + extraitMPersonne.getDateJoursetMoisIscriptionMariage()));
			document.add(phrase);

			document.add(new Paragraph(""));
			
			phrase = new Phrase("de l'an :   ");
			phrase.add(new Chunk("         " + extraitMPersonne.getDateAnneedeInscriptionMariage()));
			document.add(phrase);

			document.add(new Paragraph(""));
			
			phrase = new Phrase("à :   ");
			phrase.add(new Chunk("                 " + extraitMPersonne.getCommuneInscriptionMariage() + '-' + extraitMPersonne.getIleInscriptionMariage()));
			document.add(phrase);

			document.add(new Paragraph(""));
			
			if(null != extraitMPersonne.getMinuteInscriptionMariage()){
			phrase = new Phrase("à :  ");
			phrase.add(new Chunk("                  " + extraitMPersonne.getHeureInscriptionMariage() + "  heures " + extraitMPersonne.getMinuteInscriptionMariage() + "  minutes"));
			document.add(phrase);
			}else{
				
			phrase = new Phrase("  ");
			phrase.add(new Chunk("                     " + extraitMPersonne.getHeureInscriptionMariage() + "heures" ,fonte2));
			document.add(phrase);	
				
			}
			paragraph = new Paragraph("a été inscrit le mariage de  : ".concat(extraitMPersonne.getNomMari().toUpperCase()) + "  " + extraitMPersonne.getPrenomMari());
			paragraph.setFont(fonte2);
			document.add(paragraph);

			phrase = new Phrase("né le :  ");
			phrase.add(new Chunk("            " + extraitMPersonne.getDateJoursetMoisNaissanceMari() + " " +  extraitMPersonne.getDateAnneedeNaissanceMari()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("à :   ");
			phrase.add(new Chunk("                  " + extraitMPersonne.getCommuneNaissanceMari() + "-" + extraitMPersonne.getIleInscriptionMariage()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("profession :");
			phrase.add(new Chunk("      " + extraitMPersonne.getProfessionMari()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("demeurant à  :");
			phrase.add(new Chunk(" " + extraitMPersonne.getAdressMari()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("Fils de : ");
			phrase.add(new Chunk("           " + extraitMPersonne.getPrenomDuPereMari().toUpperCase() + " " + extraitMPersonne.getNomDuPereMari() + " et de "
					+ extraitMPersonne.getPrenomDuMereMari() + " " +  extraitMPersonne.getNomDuMereMari()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("avec : ");
			phrase.add(
					new Chunk("              " + extraitMPersonne.getPrenomMarie().toUpperCase() + " " +  extraitMPersonne.getNomMarie()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("née le :");
			phrase.add(new Chunk("             " + extraitMPersonne.getDateJoursetMoisNaissanceMarie() + " " + extraitMPersonne.getDateAnneedeNaissanceMarie()));
			document.add(phrase);

			phrase = new Phrase("  à  ");
			phrase.add(new Chunk(" " + extraitMPersonne.getCommuneNaissanceMarie() ));
			document.add(phrase);

			document.add(new Paragraph(""));


			phrase = new Phrase("Profession  :");
			phrase.add(new Chunk("     " + extraitMPersonne.getProfessionMarie()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("demeurant à :");
			phrase.add(new Chunk("   " + extraitMPersonne.getCommuneNaissanceMarie() + " - "
					+ extraitMPersonne.getIleInscriptionMariage()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("fille de :");
			phrase.add(new Chunk("            " + extraitMPersonne.getNomDuPereMarie() + " " + extraitMPersonne.getPrenomDuPereMarie()));
			document.add(phrase);

			phrase = new Phrase("  et de ");
			phrase.add(new Chunk(" " + extraitMPersonne.getNomDuMereMarie() + " " + extraitMPersonne.getPrenomDuMereMarie()));
			document.add(phrase);

			document.add(new Paragraph(""));
			

			phrase = new Phrase("Mariage célébré le : ");
			phrase.add(new Chunk("   " + formater.format(extraitMPersonne.getDateCelebration()) + "  à  " 
			 + extraitMPersonne.getCommuneInscriptionMariage(), fonte2));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("En presence de : ");
			phrase.add(new Chunk("        " + extraitMPersonne.getNomTemoinMari() + "  " + extraitMPersonne.getPrenomTemoinMari()));
			document.add(phrase);
			
			document.add(new Paragraph(""));

			phrase = new Phrase("âgée de : ");
			phrase.add(new Chunk("                    " + extraitMPersonne.getAgeTemoinMari() + " ans, profession " + extraitMPersonne.getProfessionTemoinMari()));
			document.add(phrase);
			
			document.add(new Paragraph(""));

			phrase = new Phrase("demeurant à  :");
			phrase.add(new Chunk("             " + extraitMPersonne.getAdresseTemoinMari() + " ans, profession " + extraitMPersonne.getProfessionTemoinMari()));
			document.add(phrase);
			
			document.add(new Paragraph(""));
			
			phrase = new Phrase("et de :");
			phrase.add(new Chunk("                         " + extraitMPersonne.getNomTemoinMarie() + "  " + extraitMPersonne.getPrenomTemoinMarie()));
			document.add(phrase);
			
			document.add(new Paragraph(""));

			phrase = new Phrase("âgée de : ");
			phrase.add(new Chunk("                   " + extraitMPersonne.getAgeTemoinMarie() + " ans, profession de " + extraitMPersonne.getProfessionTemoinMarie()));
			document.add(phrase);
			
			document.add(new Paragraph(""));

			phrase = new Phrase("demeurant à  :");
			phrase.add(new Chunk("             " + extraitMPersonne.getAdresseTemoinMarie() + ", profession de " + extraitMPersonne.getProfessionTemoinMari()));
			document.add(phrase);

			Paragraph para = new Paragraph("Déclaration faite par: ", fonte2);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(new Paragraph(para));
			
			phrase = new Phrase(" ");
			phrase.add(new Chunk(" " + extraitMPersonne.getDeclarationFaitePar()));
			document.add(phrase);
			
			Paragraph para1 = new Paragraph("Déclaration reçue par nous: ", fonte2);
			para1.setAlignment(Element.ALIGN_CENTER);
			document.add(new Paragraph(para1));
			
			phrase = new Phrase(" ");
			phrase.add(new Chunk(" " + extraitMPersonne.getDeclarationRecueParnous()));
			document.add(phrase);

			document.add(new Paragraph("MENTIONS MARGINALES", fonte2));

			/* pied de page */
			paragraph = new Paragraph("Pour copie d'acte certifiée conforme");
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			paragraph.setFont(fonte);
			document.add(paragraph);
			
			document.add(new Paragraph("                                                                      Délivrée à             "
					+ " " + extraitMPersonne.getCommuneInscriptionMariage() + ", le :  " + dateFormat.format(new Date())));

			paragraph = new Paragraph(
					"                                                                                                             Le Maire",
					fonte2);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			fonteDatesys = fonte2;
			fonteDatesys.setStyle(Font.BOLD);
			paragraph.setFont(fonteDatesys);
			document.add(paragraph);

			paragraph = new Paragraph(
					"                                                                                                            Officier de l’Etat Civil");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(fonteDatesys);
			document.add(paragraph);
			/*
			 * Image image1 = Image.getInstance("/images/cachetcomores.png");
			 * document.add(image1);
			 */

			document.close(); // fermeture avant de copier le fichier

			/*
			 * creation du repertoire de stockage des extraits de naissances et
			 * instanciation de fichier
			 */
			Path dossierRepertoir = Paths.get("c:\\RepertoirExtraitMariage\\");
			// pour creer un repertoire s'il existe pas
			if (!Files.exists(dossierRepertoir)) {
				Files.createDirectory(dossierRepertoir);
			} else {
				System.out.println("Le dossier RepertoirExtrait existe");
			}

			File source = new File(nomFichierSource);
			// le nom de fichier extrait de mariage fini aura le nom suivant
			String nomFichierDest = "Ext_Mariage_" + extraitMPersonne.getNomMari() + extraitMPersonne.getPrenomMari() + "_"
					+ extraitMPersonne.getNumExtMariage() + ".pdf";

			nomFichierDest = nomFichierDest.replaceAll(" ", ""); 

			// Le repertoire de stockage des extraits de mariages et nom final de l'extrait
			File destina = new File("c:\\RepertoirExtraitMariage\\" + nomFichierDest);
			// copie le fichier source créée vers le repertoire RepertoirExtrait
			copyFile(source, destina);

		} catch (Exception e) {
			e.printStackTrace();
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

	private void ChargerExtraitMariage(Model model, String motCle, int page, int size) {
		
		try {
			Page<ExtraitMariagePersonne> listExtraitPage = extraitMariagePersonneImpl.listeExtraitMariageParNom(motCle,
					page, size);
			model.addAttribute("listextraitPage", listExtraitPage.getContent());
			int[] pages = new int[listExtraitPage.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("page", page);
			//model.addAttribute("numExtMariage ", numExtMariage);
            model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			model.addAttribute("exception", e);

		}
	}
	
		/**
		 * Cette methode permet de copier un fichier d'un repertoir à un autre
		 * 
		 * @param source
		 * @param destina
		 * @return
		 */

		public static boolean copyFile(File source, File destina) {

			FileOutputStream destinationFile = null;
			FileInputStream sourceFile;

			try {
				// Declaration et ouverture des flux
				sourceFile = new FileInputStream(source);

				try {
					destinationFile = new FileOutputStream(destina);

					// Lecture par segment de 0.5Mo
					byte buffer[] = new byte[512 * 1024];
					int nbLecture;

					while ((nbLecture = sourceFile.read(buffer)) != -1) {
						destinationFile.write(buffer, 0, nbLecture);
					}

				} finally {
					sourceFile.close();
					destinationFile.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
				return false; // Erreur
			}

			return true; // Résultat OK
		}
		
		/*public static boolean isValid(String text) {
		    if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
		        return false;
		    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		    df.setLenient(false);
		    try {
		        df.parse(text);
		        return true;
		    } catch (Exception ex) {
		        return false;
		    }
		}*/

	}
	
