package apartado3;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Coche coche1 = recuperarCocheDeXML(new File("coche.txt"));
            System.out.println(coche1);
            File ficheroDestinoCoche = new File("cocheDOM.txt");
            ficheroDestinoCoche.createNewFile(); // Por si acaso no existe
            guardarCocheAXML(ficheroDestinoCoche, coche1);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static Coche recuperarCocheDeXML(File ficheroCocheTxt)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = docBuildFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(ficheroCocheTxt);
        return reconstruirCoche(doc.getElementsByTagName("Coche"));
    }

    private static Coche reconstruirCoche(NodeList cocheXML) {
        Element elementoCoche = (Element) cocheXML.item(0);
        String nombre = elementoCoche.getElementsByTagName("nombreAlias").item(0).getTextContent();
        String modelo = elementoCoche.getElementsByTagName("modelo").item(0).getTextContent();
        int cv = Integer.parseInt(elementoCoche.getElementsByTagName("cv").item(0).getTextContent());
        return new Coche(nombre, modelo, cv);
    }

    private static void guardarCocheAXML(File ficheroCocheTxt, Coche coche)
            throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = docBuildFactory.newDocumentBuilder();
        Document docCoche = dBuilder.newDocument();
        insertarCocheEnDoc(docCoche, coche);
        generarXML(docCoche, ficheroCocheTxt);
    }

    private static void insertarCocheEnDoc(Document docCoche, Coche coche) {
        Element elementoCoche = docCoche.createElement("Coche");
        docCoche.appendChild(elementoCoche);

        Element elementoNombre = docCoche.createElement("nombre");
        Element elementoModelo = docCoche.createElement("modelo");
        Element elementoCv = docCoche.createElement("cv");

        elementoNombre.appendChild(docCoche.createTextNode(coche.getNombre()));
        elementoModelo.appendChild(docCoche.createTextNode(coche.getModelo()));
        elementoCv.appendChild(docCoche.createTextNode(Integer.toString(coche.getCv())));

        elementoCoche.appendChild(elementoNombre);
        elementoCoche.appendChild(elementoModelo);
        elementoCoche.appendChild(elementoCv);
    }

    private static void generarXML(Document docCoche, File ficheroDestino) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(docCoche);
        StreamResult resultado = new StreamResult(ficheroDestino);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Para insertar indentacion
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Tamaño de la indentacion
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // Para omitir la declaracion del XML
        transformer.transform(source, resultado);
    }


}
