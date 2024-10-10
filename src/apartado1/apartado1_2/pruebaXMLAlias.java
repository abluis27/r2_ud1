package apartado1.apartado1_2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class pruebaXMLAlias {
	public static void main(String[] args) {
		Coche coche1 = new Coche();
		coche1.setNombre("Mercedes");
		coche1.setModelo("c220");
		coche1.setCv(170);
		// Apartado 1.1
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		// Enviar un coche a XMl utilizando toXML
		xstream.alias("coche", Coche.class);
		xstream.aliasField("nombreAcfdgfdlias",Coche.class,"nombre");
		

		String cocheEnviado = xstream.toXML(coche1);
		// Estructura del XML:
		System.out.println(cocheEnviado);
		// Recuperar un coche con XML utilizando fromXML
		Coche cocheRecuperado = (Coche) xstream.fromXML(cocheEnviado);
		System.out.println(cocheRecuperado);
	}

}
