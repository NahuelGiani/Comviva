package ar.com.ejercicioTres.service;

import java.io.InputStream;
import java.util.List;

import ar.com.ejercicioTres.domain.ComvivaFile;

public interface IComvivaFileService {
	
	public String subirArchivo(InputStream archivo, String nombre);
	public List<ComvivaFile> consultaRegistros();
	public boolean esTextoPlano(String contentType);

}
