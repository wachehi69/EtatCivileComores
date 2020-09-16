package org.mairie.comores.web;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.mairie.comores.entities.ExtraitMariagePersonne;
import org.mairie.comores.metier.IExtraitMariagePersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PDFExtraitMariageExport {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private IExtraitMariagePersonne   extraitMariagePersonne;

	private static final String DIRECTORY = "C:/RepertoirExtraitMariage";

	@RequestMapping("/PDFExportExtraitMariage")
	public ResponseEntity<InputStreamResource> downloadFile1(Long numExtMariage, Model model) {

		try {

			ExtraitMariagePersonne extrait = extraitMariagePersonne.getExtraitMariage(numExtMariage);

			String fileName = "Ext_Mariage_" + extrait.getNomMari() + extrait.getPrenomMari() + "_" + extrait.getNumExtMariage() + ".pdf";
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
