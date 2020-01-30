

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio {

	public static void main(String[] args) {
		System.out.println("Por favor, ingrese la ruta del archivo a leer: ");
		String rutaArchivo = leerInput();
		File archivo = new File(rutaArchivo); //"/opt/hola.txt"
		String textoArchivo = leerArchivo(archivo);
		if(textoArchivo != null) {
			List<Double> numeros = new ArrayList<>();
			Matcher valoresEncontrados = Pattern.compile("([0-9]\\d*)(\\.\\d+)?").matcher(textoArchivo);
			while (valoresEncontrados.find()) {
				numeros.add(Double.valueOf(valoresEncontrados.group()));
			} 
			if(!numeros.isEmpty()) {
				System.out.println(mostrarResultado(numeros));
			} else {
				System.out.println("No hay valores númericos en el texto");  
			}
		}
	}
	
	private static String mostrarResultado(List<Double> numeros) {
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
		return textoFinal.toString();
	}

	private static String leerInput() {
		String valorCargado = null;
		try (BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in))){
			valorCargado = reader.readLine();
		} catch (IOException e) {
			System.out.println("Hubo un error al intentar leer el valor ingresado");
			System.exit(0);
		}
		return valorCargado;
	}
	
	private static String leerArchivo(File archivo) {
        if(archivo.exists()) {
            StringBuilder texto = new StringBuilder();
        	try (BufferedReader bufferedReader = new BufferedReader(new FileReader (archivo))){
    	        String linea = null;
    	        while((linea = bufferedReader.readLine()) != null) {
    	        	texto.append(linea);
    	        }
    	        return texto.toString();
    		} catch (IOException ioe) {
    			System.out.println("No se pudo leer el archivo " + archivo.getName());
    		}
        } else {
        	System.out.println("El archivo " + archivo.getName() + " no existe");
        }
		return null;
	}
}
