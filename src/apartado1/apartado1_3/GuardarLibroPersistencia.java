package apartado1.apartado1_3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class GuardarLibroPersistencia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Libro libro = new Libro();
		libro.setTitulo("Cien años de soledad");
		libro.setAutor("Gabriel García Márquez");
		libro.setAnioPublicacion(1967);
		String ruta="/home/alumno/Documentos/libro.txt";
		File archivo = new File(ruta);
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		String libroEnviado = xstream.toXML(libro);
		System.out.println(libroEnviado);
		PrintWriter out = null;
		try {
			out = new PrintWriter(archivo);
			out.print(libroEnviado);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}

		}
	}

}
