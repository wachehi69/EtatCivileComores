package org.mairie.comores.web;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.mairie.comores.metier.IExtraitNaissancePersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PDFEAttestationParentaleExport {

	@Autowired
	private ServletContext servletContext;


	private static final String DIRECTORY1 = "C:/RepertoirAttParentalTerritoire";
	private static final String DIRECTORY2 = "C:/RepertoirAttParentale";
	private static final String fileName1= "Autorisation_parentale_de_sortie.pdf";
	private static final String fileName2= "Attestation_autorisation_parentale.pdf";

	@RequestMapping("/PDFExportAttestationParental")
	public ResponseEntity<InputStreamResource> downloadFile1(Model model) {

		try {

			MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName1);

			File file = new File(DIRECTORY1 + "/" + fileName1);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) 
					.body(resource);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return null;
	}
	
	@RequestMapping("/PDFExportAutorisationParental")
	public ResponseEntity<InputStreamResource> downloadFile2(Model model) {

		try {

			MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName2);

			File file = new File(DIRECTORY2 + "/" + fileName2);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) 
					.body(resource);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return null;
	}

}
