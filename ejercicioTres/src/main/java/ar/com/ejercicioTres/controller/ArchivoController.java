package ar.com.ejercicioTres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.ejercicioTres.service.IComvivaFileService;

@Controller
@RequestMapping(value = "/archivo")
@CrossOrigin(origins = "*")
@Validated
public class ArchivoController {
	
	@Autowired
	@Qualifier("comvivaFileService")
	private IComvivaFileService comvivaFileService;
	
	@PostMapping(value = "/cargar")
	public String cargaRegistro(@RequestParam("archivoCargado") MultipartFile arch, RedirectAttributes ra) throws Exception {
		try {
			if(comvivaFileService.esTextoPlano(arch.getContentType())) {
				comvivaFileService.subirArchivo(arch.getInputStream(), arch.getOriginalFilename());
				ra.addFlashAttribute("msj", "Archivo subido correctamente!");
			} else {
				ra.addFlashAttribute("msj", "El archivo debe ser texto plano (.txt) ");
			}
			return "redirect:/home";
		} catch(Exception e) {
			ra.addFlashAttribute("msj", "Error al subir el archivo");
			return "redirect:/home";
		}
	}
	
	@GetMapping(value = "/registros")
	public String verRegistros(Model model) throws Exception {
		model.addAttribute("archivos", comvivaFileService.consultaRegistros());
		return "archivosCargados";
	}	

}
