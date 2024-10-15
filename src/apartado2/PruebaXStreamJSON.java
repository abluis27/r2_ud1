package apartado2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class PruebaXStreamJSON {
    public static void main(String[] args) {
        Coche coche = new Coche();
        coche.setMarca("Honda");
        coche.setModelo("Civid type r");
        coche.setAño(2022);
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("coche", Coche.class);
        // Dar permisos
        xstream.addPermission(AnyTypePermission.ANY);
        // Convertir objeto a JSON
        String jsonCoche = xstream.toXML(coche);
        System.out.println("JSON generado:");
        System.out.println(jsonCoche);
        // Guardar el JSON
        try (FileWriter fileWriter = new FileWriter("coche.json")) {
            fileWriter.write(jsonCoche);
            System.out.println("JSON guardado en coche.json");
        } catch (IOException e) {
            e.getMessage();
        }
        // Leer el JSON del archivo y regenerar el objeto
        try (BufferedReader reader = new BufferedReader(new FileReader("coche.json"))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String jsonFromFile = jsonBuilder.toString();
            Coche cocheRegenerado = (Coche) xstream.fromXML(jsonFromFile);
            System.out.println("Coche regenerado:");
            System.out.println(cocheRegenerado);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
