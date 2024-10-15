package apartado1.apartado1_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Ejercicio2 {

	public static void main(String[] args) {
		System.out.println("*****************************Escritura**************************************");
		// TODO Auto-generated method stub
		Cafe cafe1 = new Cafe("nespreso",2,45);
		Cafe cafe2 = new Cafe("lidl",22,7);
		Proveedor proveedor = new Proveedor();
		// Apartado 1.1
		proveedor.setNombreProveedor("Proveedor");
		proveedor.setCalle("Calle");
		proveedor.setCiudad("madrid");
		proveedor.setPais("España");
		proveedor.setEsNacional("Importacion");
		proveedor.setCp(28530);
		proveedor.setEmpresa("Empresa");
		proveedor.setCif("CIFFF");
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		
		String ruta="/home/alumno/Documentos/ejercicio1.xml";
		File archivo = new File(ruta);
		
		proveedor.addCafe(cafe1);
		proveedor.addCafe(cafe2);
		xstream.alias("Proveedor", Proveedor.class);
		xstream.alias("Cafe", Cafe.class);
		xstream.aliasAttribute(Proveedor.class,"cif","cif");
		xstream.aliasAttribute(Proveedor.class,"empresa","empresa");
	
		String proveedorEnviado= xstream.toXML(proveedor);
		System.out.println(proveedorEnviado);
		
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(archivo);
			out.print(proveedorEnviado);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}

		}
		recuperar();
	}
	public static void recuperar() {
		System.out.println("*****************************Recuperar**************************************");
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		String ruta="/home/alumno/Documentos/ejercicio1.xml";
		File archivo = new File(ruta);
				BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader(archivo));
					String linea;
					String cadenaXMLProveedorRecuperadoArchivoXMLPersistencia = "";
					System.out.println("");
					xstream.alias("Proveedor", Proveedor.class);
					xstream.alias("Cafe", Cafe.class);
					while ((linea = in.readLine()) != null) {
						System.out.println(linea);
						cadenaXMLProveedorRecuperadoArchivoXMLPersistencia = cadenaXMLProveedorRecuperadoArchivoXMLPersistencia + linea;
					}

					Proveedor proveedorRecuperadoArchivoXMLpersistencia = (Proveedor) xstream.fromXML(cadenaXMLProveedorRecuperadoArchivoXMLPersistencia);
					System.out.println(proveedorRecuperadoArchivoXMLpersistencia);

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

