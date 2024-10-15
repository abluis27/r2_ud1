package apartado1.apartado1_5;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class Ejercicio1 {

	public static void main(String[] args) {
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
		proveedor.addCafe(cafe1);
		proveedor.addCafe(cafe2);
		xstream.alias("Proveedor", Proveedor.class);
		xstream.alias("Cafe", Cafe.class);
		xstream.aliasAttribute(Proveedor.class,"cif","cif");
		xstream.aliasAttribute(Proveedor.class,"empresa","empresa");

		
	
		String proveedorEnviado= xstream.toXML(proveedor);
		// Estructura del XML:
		System.out.println(proveedorEnviado);
		// Recuperar un coche con XML utilizando fromXML
		Proveedor proveedorRecuperado = (Proveedor) xstream.fromXML(proveedorEnviado);
		System.out.println(proveedorRecuperado);
	}

	

}

class Cafe {
	private String marca;
	private float precio;
	private int ventas;

	public Cafe(String marca, float precio, int ventas) {
		this.marca = marca;
		this.precio = precio;
		this.ventas=ventas;

	}
	public Cafe() {
		
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}
}
class Proveedor {
    @Override
	public String toString() {
		return "Proveedor [nombreProveedor=" + nombreProveedor + ", calle=" + calle + ", ciudad=" + ciudad + ", pais="
				+ pais + ", esNacional=" + esNacional + ", cp=" + cp + ", cafes=" + cafes + "]";
	}


	private String nombreProveedor;
    private String calle;
    private String ciudad;
    private String pais;
    public void setCif(String cif) {
		this.cif = cif;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	private String esNacional;
    private int cp;
    private String cif;
    private String empresa;
    private List<Cafe> cafes = new ArrayList<>();
    /*public Proveedor(String nombreProveedor, String calle, String ciudad, String pais,String esNacional, int cp) {
		this.nombreProveedor = nombreProveedor;
		this.calle = calle;
		this.ciudad = ciudad;
		this.pais = pais;
		this.cp = cp;
		this.esNacional=esNacional;
		this.cafes = cafes;
	}*/
    
    public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setEsNacional(String esNacional) {
		this.esNacional = esNacional;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public List<Cafe> getCafes() {
		return cafes;
	}

	public void setCafes(List<Cafe> cafes) {
		this.cafes = cafes;
	}

	
	public void addCafe(Cafe cafe) {
        cafes.add(cafe);
    }

}
