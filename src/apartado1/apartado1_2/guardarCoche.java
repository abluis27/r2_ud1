package apartado1.apartado1_2;

import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class guardarCoche {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coche coche1 = new Coche();
		coche1.setNombre("Mercedes");
		coche1.setModelo("c220");
		coche1.setCv(170);
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		xstream.alias("Coche", Coche.class);
		xstream.aliasField("nombreAlias",Coche.class,"nombre");
		
		String cocheEnviado = xstream.toXML(coche1);
		PrintWriter out = null;
		try {
			out = new PrintWriter("coche.txt");
			out.print(cocheEnviado);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}

		}
	}

}
