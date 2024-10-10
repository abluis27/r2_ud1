package apartado1.apartado1_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class pruebaXML {
	public static void main(String[] args) {
		Coche coche1 = new Coche();
		coche1.setNombre("Mercedes");
		coche1.setModelo("c220");
		coche1.setCv(170);
		// Apartado 1.1
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		// Enviar un coche a XMl utilizando toXML
		String cocheEnviado = xstream.toXML(coche1);
		// Estructura del XML:
		System.out.println(cocheEnviado);
		// Recuperar un coche con XML utilizando fromXML
		Coche cocheRecuperado = (Coche) xstream.fromXML(cocheEnviado);
		System.out.println(cocheRecuperado);
	}

}
