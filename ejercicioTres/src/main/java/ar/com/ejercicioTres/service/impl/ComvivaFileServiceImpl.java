package ar.com.ejercicioTres.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.ejercicioTres.domain.ComvivaFile;
import ar.com.ejercicioTres.repository.JpaComvivaFile;
import ar.com.ejercicioTres.service.IComvivaFileService;

@Service("comvivaFileService")
public class ComvivaFileServiceImpl implements IComvivaFileService {

	@Autowired
	@Qualifier("jpaComvivaFile")
	private JpaComvivaFile jpaComvivaFile;
	
	private static final String CONTENT_TYPE_TEXTO_PLANO = "text/plain";
	
	@Override
	public String subirArchivo(InputStream archivo, String nombre) {
		String textoArchivo = leerArchivo(archivo);
		if(textoArchivo != null) { 
			Double suma = buscarValoresNumericosYSumarlos(textoArchivo);
			if(suma == null) {
				return "El texto no contiene valores númericos";
			}
			insertarDato(new ComvivaFile(nombre, suma));
			return "Archivo cargado correctamente!";
		} else {
			return "Hubo un error al intentar leer el archivo";
		}
	}
	

	
	private Double buscarValoresNumericosYSumarlos(String textoArchivo) {
		List<Double> numeros = new ArrayList<>();
		Matcher valoresEncontrados = Pattern.compile("([0-9]\\d*)(\\.\\d+)?").matcher(textoArchivo);
		while (valoresEncontrados.find()) {
			numeros.add(Double.valueOf(valoresEncontrados.group()));
		} 
		if(!numeros.isEmpty()) {
        	return mostrarResultadoYObtenerSuma(numeros);
		} else {
			System.out.println("No hay valores númericos en el texto");
		}
		return null;
	}
	
	private Double mostrarResultadoYObtenerSuma(List<Double> numeros) {
		Double suma = new Double(0);
		StringBuilder textoFinal = new StringBuilder();
	    DecimalFormat df = new DecimalFormat();
		for(int i = 0 ; i < numeros.size() ; i++) {
			textoFinal.append(df.format(numeros.get(i)));
			suma += numeros.get(i);
			if(i != numeros.size() - 1) {
				textoFinal.append(" + ");
			} else {
				textoFinal.append(" = ");
				textoFinal.append(df.format(suma));
			}
		}
		System.out.println(textoFinal.toString());
		return suma;
	}
	
	private void insertarDato(ComvivaFile archivo) {
		jpaComvivaFile.saveAndFlush(archivo);
	}
	
	private String leerArchivo(InputStream archivo) {
        StringBuilder texto = new StringBuilder();
    	try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(archivo))){
	        String linea = null;
	        while((linea = bufferedReader.readLine()) != null) {
	        	texto.append(linea);
	        }
	        return texto.toString();
		} catch (IOException ioe) {
			System.out.println("No se pudo leer el archivo cargado");
		}
		return null;
	}
	
	public boolean esTextoPlano(String contentType) {
		return contentType != null 
			? contentType.equalsIgnoreCase(CONTENT_TYPE_TEXTO_PLANO)
			: false;
	}

	@Override
	public List<ComvivaFile> consultaRegistros() {
		return jpaComvivaFile.findAll();
	}

}
