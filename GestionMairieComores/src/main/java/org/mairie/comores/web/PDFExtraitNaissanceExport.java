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
public class PDFExtraitNaissanceExport {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private IExtraitNaissancePersonne extraitNaissancePersonneImpl;

	private static final String DIRECTORY = "C:/RepertoirExtrait";

	@RequestMapping("/PDFExportExtraitNaissance")
	public ResponseEntity<InputStreamResource> downloadFile1(Long numExtrait, Model model) {

		try {

			ExtraitNaissancePersonne extrait = extraitNaissancePersonneImpl.getExtraitNaissance(numExtrait);

			String fileName = "Ext_" + extrait.getNom() + extrait.getPrenom() + "_" + extrait.getNumExtrait() + ".pdf";
			fileName = fileName.replace(" ", "");
			MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
			System.out.println("fileName: " + fileName);
			System.out.println("mediaType: " + mediaType);

			File file = new File(DIRECTORY + "/" + fileName);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return null;
	}
}
