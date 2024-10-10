package apartado1.apartado1_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class RecuperarCocheAlias {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Leer y contruir un objeto catraves de un .txt
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		xstream.alias("Coche", Coche.class);
		xstream.aliasField("nombreAlias",Coche.class,"nombre");
				BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader("coche.txt"));
					String linea;
					String cadenaXMLCocheRecuperadoArchivoTexto = "";
					System.out.println("");
					while ((linea = in.readLine()) != null) {
						System.out.println(linea);
						cadenaXMLCocheRecuperadoArchivoTexto = cadenaXMLCocheRecuperadoArchivoTexto + linea;
					}
				
					Coche cocheRecuperadoArchivoTexto = (Coche) xstream.fromXML(cadenaXMLCocheRecuperadoArchivoTexto);
					System.out.println(cocheRecuperadoArchivoTexto);

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
