package apartado1.apartado1_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class RecuperarXMLPersistencia {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Leer y contruir un objeto atraves de un .txt
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		String ruta="/home/alumno/Documentos/libro.txt";
		File archivo = new File(ruta);
				BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader(archivo));
					String linea;
					String cadenaXMLLibroRecuperadoArchivoTextoPersistencia = "";
					System.out.println("");
					while ((linea = in.readLine()) != null) {
						System.out.println(linea);
						cadenaXMLLibroRecuperadoArchivoTextoPersistencia = cadenaXMLLibroRecuperadoArchivoTextoPersistencia + linea;
					}
					
					Libro libroRecuperadoArchivoTextopersistencia = (Libro) xstream.fromXML(cadenaXMLLibroRecuperadoArchivoTextoPersistencia);
					System.out.println(libroRecuperadoArchivoTextopersistencia);

				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException ex) {
							System.out.println(ex.getMessage());
						}
					}
				}
	}

}
