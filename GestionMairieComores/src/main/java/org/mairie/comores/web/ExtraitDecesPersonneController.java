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

import org.mairie.comores.entities.ExtraitDecesPersonne;
import org.mairie.comores.entities.Users;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.IExtraitDecesPersonne;
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
public class ExtraitDecesPersonneController {

	@Autowired
	private IExtraitDecesPersonne extraitDecesPersonneImpl;

	@Autowired
	private IEmployeMetier employeMetierImpl;

	@Autowired
	private UserMetierImpl userMetierImpl;

	String nomFichierSource = "c:\\extraitDeces\\extraitDeces.pdf";
	Paragraph paragraph;
	Phrase phrase;
	Path source;
	Path destination;
	String NomExtrait = "EXTRAIT D'ACTE DE DECES";

	@RequestMapping("/extraitDeces")
	private String index(ExtraitDecesPersonne extraitDPersonne, Model model) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
		} catch (Exception e) {
		}
		return "extraitDecesPersonne";
	}

	@GetMapping("/consulationExtraitDeces")
	public String showForm(Model model, String motCle, ExtraitDecesPersonne extraitDecesPersonne, String operation,
			Long numExtraitDeces, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);

			if (operation.equals("modif")) {
				ExtraitDecesPersonne extraitP = extraitDecesPersonneImpl.getExtraitDeces(numExtraitDeces);
				model.addAttribute("extraitDecesPersonne", extraitP);
				model.addAttribute("operation", operation);
				model.addAttribute("etat", operation);
			}

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		ChargerExtraitDeces(model, motCle, page, size);
		return "extraitDecesPersonne";
	}


	@RequestMapping(value = "/saveExtraitDeces", method = RequestMethod.POST)
	public String enregistrer(@Valid ExtraitDecesPersonne extraitDecesPersonne, Errors errors, Model model,
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
			return "redirect:/consulationExtraitDeces?motCle=" + motCle + "&page=" + page;
		}

		if (errors.hasErrors()) {
			Page<ExtraitDecesPersonne> listExtraitPage = extraitDecesPersonneImpl.listeParPageExtraitDeces(motCle, page, size);
			model.addAttribute("listextraitPage", listExtraitPage.getContent());
			model.addAttribute("motCle", motCle);
			model.addAttribute("operation", "modif");
			model.addAttribute("etat", "modif");

			return "extraitDecesPersonne";
		}
		Users user = EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
		if (operation.equals("modif")) {
			UpdateExtraitDeces(extraitDecesPersonne, user);
		} else {
			// date de creation de l'extrait de naissance
			extraitDecesPersonne.setDateCreation(new Date());
			// utilisateur qui a créée l'extrait de décès
			extraitDecesPersonne.setUser(user);
			extraitDecesPersonneImpl.saveExtraitDeces(extraitDecesPersonne);
	}

		return "redirect:/consulationExtraitDeces?motCle=" + motCle + "&page=" + page;

	}
	
	/**
	 * 
	 * @param numExtraitDeces
	 * @param model
	 * @param motCle
	 * @param page
	 * @return
	 */

	@RequestMapping("/suppressionExtraitDeces")
	private String supprimerExtraitDeces(Long numExtraitDeces, Model model, String motCle, int page) {
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);
			extraitDecesPersonneImpl.deleteExtraitDeces(numExtraitDeces);
			} catch (Exception e) {

		}
		return "redirect:/consulationExtraitDeces?motCle=" + motCle + "&page=" + page;
	}

	

	@RequestMapping("/generationExtraitDeces")
	private String creationExtraitNaissance(Long numExtraitDeces, Model model, String motCle, int page) {

		Document document = new Document(PageSize.A4);

		ExtraitDecesPersonne extraitDPersonne = extraitDecesPersonneImpl.getExtraitDeces(numExtraitDeces);
		// Creation du repertoir extrait de décès
		Path repertoir = Paths.get("c:\\extraitDeces\\");
		try {
			Files.createDirectories(repertoir);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		File fichier = new File(nomFichierSource);

		try (FileOutputStream fileoutp = new FileOutputStream(fichier)) {

			 //creation du fichier extrait de naissance 
			if (!fichier.exists())
				fichier.createNewFile(); // creation du fichier s'il n'existe
											// pas

			// Création d'une instance de fichier PDF
			PdfWriter.getInstance(document, new FileOutputStream(fichier));
			System.out.print("Le fichier est créee : " + fichier.getAbsolutePath());
			 //ouverture du fichier en ecriture 
			document.open();
			com.lowagie.text.Font fonte = FontFactory.getFont("timesroman", BaseFont.TIMES_ROMAN, 8);
			com.lowagie.text.Font fonte1 = FontFactory.getFont("timesroman", BaseFont.TIMES_ROMAN, 6);
			com.lowagie.text.Font fonte2 = FontFactory.getFont("timesroman", BaseFont.TIMES_ROMAN, 12);
			// com.lowagie.text.Font fonte3 =
			// FontFactory.getFont("timesroman",BaseFont.TIMES_ROMAN, 14);
			// com.lowagie.text.Font fonte4 =
			// FontFactory.getFont("timesroman",BaseFont.TIMES_ROMAN, 16);

			 //l'entete de l'extrait de naissance 
			document.add(new Paragraph("UNION DES COMORES", fonte));
			document.add(new Paragraph("Unité- Solidarité-Développement", fonte));
			document.add(new Paragraph("MINISTERE DE L'INTERIEUR", fonte));

			fonte2.setStyle(Font.BOLD); // mettre le style en gras
			paragraph = new Paragraph(NomExtrait, fonte2);
			paragraph.setAlignment(Element.ALIGN_RIGHT); // alignement à droite
			paragraph.setLeading(5f); // espacement entre deux lignes
			document.add(paragraph);

			 //inserer l'image 
			Image image = Image.getInstance("mairie.png");

			// image.setAbsolutePosition(220f, 550f);
			document.add(image);

			document.add(new Paragraph("VILLE DE MUTSAMUDU", fonte));
			document.add(new Paragraph("         service état civil", fonte1));

			 //Affichage de la date systeme 
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dat = dateFormat.format(extraitDPersonne.getDateCreation());
			Font fonteDatesys = fonte2;
			fonteDatesys.setStyle(Font.BOLD);
			paragraph = new Paragraph("Acte N°: " + extraitDPersonne.getNumExtraitDeces() + " Du " + dat, fonteDatesys);
			// paragraph = new Paragraph( dat, fonteDatesys);
			paragraph.setAlignment(Element.ALIGN_CENTER);

			document.add(paragraph);

			paragraph = new Paragraph("Registre N°: " + extraitDPersonne.getNumRegistre());
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(fonte2);
			document.add(paragraph);

			//contenu de l'extrait de naissance 
			paragraph = new Paragraph(
					"Décès de  : ".concat(extraitDPersonne.getNom()) + "  " + extraitDPersonne.getPrenom());
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(fonte2);
			document.add(paragraph);

			phrase = new Phrase("Le   ");
			phrase.add(new Chunk("                                   " + extraitDPersonne.getDateJoursetMoisDeces()
					));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("et l'an :   ");
			phrase.add(new Chunk("                            " + extraitDPersonne.getDateAnneedeDeces()));
			document.add(phrase);

			document.add(new Paragraph(""));

			
			document.add(new Paragraph(""));

			if (extraitDPersonne.getNomDuSexe().equalsIgnoreCase("monsieur")) {
				phrase = new Phrase("Est décédé à: ");
				phrase.add(new Chunk("                   " + extraitDPersonne.getLieuDeDeces()));
				document.add(phrase);
			} else {
				phrase = new Phrase("Est décédée à: ");
				phrase.add(new Chunk("                 " + extraitDPersonne.getLieuDeDeces()));
				document.add(phrase);
			}

			document.add(new Paragraph(""));

			phrase = new Phrase("Dans la commune de : ");
			phrase.add(new Chunk("     " + extraitDPersonne.getCommuneDuDeces()));
			document.add(phrase);

			document.add(new Paragraph(""));
			
			phrase = new Phrase("Nom et prenom:          ");
			phrase.add(new Chunk("      ".concat(extraitDPersonne.getNom()) + "  " + extraitDPersonne.getPrenom()));
			document.add(phrase);
			
			document.add(new Paragraph(""));

			phrase = new Phrase("De sexe : ");
			phrase.add(new Chunk("                          " + extraitDPersonne.getNomDuSexe()));
			document.add(phrase);

			document.add(new Paragraph(""));

			phrase = new Phrase("Fils de : ");
			phrase.add(new Chunk("                            " + extraitDPersonne.getNomDuPere().toUpperCase() + " "
					+ extraitDPersonne.getPrenomDuPere()));
			document.add(phrase);


			document.add(new Paragraph(""));

			phrase = new Phrase("Et de :");
			phrase.add(new Chunk("                               " + extraitDPersonne.getNomDuMere().toUpperCase() + " "
					+ extraitDPersonne.getPrenomDuMere()));
			document.add(phrase);


			document.add(new Paragraph(""));


			document.add(new Paragraph(""));
			phrase = new Phrase("Dressé le : ");
			phrase.add(new Chunk("                       " + dat));
			document.add(phrase);

			document.add(new Paragraph(" "));
			phrase = new Phrase("Déclaration faite par: ");
			phrase.add(new Chunk(" " + extraitDPersonne.getDeclarationFaitePar()));
			document.add(phrase);
			document.add(new Paragraph(" "));
			phrase = new Phrase("Déclaration reçue par nous: ");
			phrase.add(new Chunk(" " + extraitDPersonne.getDeclarationRecueParnous()));
			document.add(phrase);

			document.add(new Paragraph(" "));

			document.add(new Paragraph("MENTIONS MARGINALES", fonte2));

			// pied de page 
			document.add(new Paragraph("       "));
			document.add(new Paragraph("       "));
			paragraph = new Paragraph("Pour extrait  certifié conforme aux registres");
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			paragraph.setFont(fonte);
			document.add(paragraph);

			document.add(new Paragraph("       "));
			paragraph = new Paragraph(
					extraitDPersonne.getCommuneDuDeces() + ", le :  " + dateFormat.format(new Date()));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			paragraph.setFont(fonte);
			document.add(paragraph);

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
			
			 //* Image image1 = Image.getInstance("/images/cachetcomores.png");
			 //* document.add(image1);
			 

			document.close(); // fermeture avant de copier le fichier

			
			 // creation du repertoire de stockage des extraits de naissances et
			 // instanciation de fichier
			 
			Path dossierRepertoir = Paths.get("c:\\RepertoirExtraitDeces");
			// pour creer un repertoire s'il existe pas
			if (!Files.exists(dossierRepertoir)) {
				Files.createDirectory(dossierRepertoir);
			} else {
				System.out.println("Le dossier RepertoirExtraitDeces existe");
			}

			File source = new File(nomFichierSource);
			// le nom de fichier extrait de naissance fini aura le nom suivant
			String nomFichierDest = "Ext_Dec_" + extraitDPersonne.getNom() + extraitDPersonne.getPrenom() + "_"
					+ extraitDPersonne.getNumExtraitDeces() + ".pdf";

			nomFichierDest = nomFichierDest.replaceAll(" ", ""); // renvoie une
																	// copie du
																	// String

			// Le repertoire de stockage des extraits de décès et nom final
			// de l'extrait
			File destina = new File("c:\\RepertoirExtraitDeces\\" + nomFichierDest);
			// copie le fichier source créée vers le repertoire RepertoirExtraitDeces
			copyFile(source, destina);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/consulationExtraitDeces?motCle=" + motCle + "&page=" + page;
	}

	
	
	

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
	
	
   /**
    *  Cette methode permet de charger les extraits de décès
    * @param model
    * @param motCle
    * @param page
    * @param size
    */
	private void ChargerExtraitDeces(Model model, String motCle, int page, int size) {
		try {

			Page<ExtraitDecesPersonne> listExtraitPage = extraitDecesPersonneImpl.listeParPageExtraitDeces(motCle,
					page, size);
			model.addAttribute("listextraitPage", listExtraitPage.getContent());
			int[] pages = new int[listExtraitPage.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("page", page);
			model.addAttribute("motCle", motCle);

		} catch (Exception e) {
			model.addAttribute("exception", e);

		}
	}
	
	/**
	 * Cette methode permet de mettre à jour les extraits de décès
	 * 
	 * @param extraitDecesPersonne
	 * @param user
	 */
	private void UpdateExtraitDeces(ExtraitDecesPersonne extraitDecesPersonne, Users user) {
		ExtraitDecesPersonne extrNP = extraitDecesPersonneImpl
				.getExtraitDeces(extraitDecesPersonne.getNumExtraitDeces());
		// Récuperation de la date de creation
		extraitDecesPersonne.setDateCreation(extrNP.getDateCreation());
		extraitDecesPersonne.setDateModification(new Date());
		extraitDecesPersonne.setUser(user);
		extraitDecesPersonneImpl.updateExtraitDeces(extraitDecesPersonne);
	}
	
}
