package ar.com.ejercicioTres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ejercicioTres.domain.ComvivaFile;
import ar.com.ejercicioTres.service.IComvivaFileService;

@RestController
@RequestMapping(value = "/consulta")
@CrossOrigin(origins = "*")
@Validated
public class RestApiController {
	
	@Autowired
	@Qualifier("comvivaFileService")
	private IComvivaFileService comvivaFileService;
	
	@GetMapping(value = "/registros", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ComvivaFile> consultaRegistro() throws Exception {
		return comvivaFileService.consultaRegistros();
	}	
}
