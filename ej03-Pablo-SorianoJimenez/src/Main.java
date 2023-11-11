import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File xmlFile = new File("videojuegos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos <item>
            NodeList itemList = doc.getElementsByTagName("item");

            // Iterar a través de los nodos <item> y mostrar los datos de los registros activos
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String estado = itemElement.getElementsByTagName("Estado").item(0).getTextContent();
                    if ("Activa".equals(estado)) {
                        String titulo = itemElement.getElementsByTagName("Título").item(0).getTextContent();
                        String semilla = itemElement.getElementsByTagName("Semilla").item(0).getTextContent();
                        String palabrasClave = itemElement.getElementsByTagName("Palabras_clave").item(0).getTextContent();
                        System.out.println("Título: " + titulo);
                        System.out.println("Semilla: " + semilla);
                        System.out.println("Palabras Clave: " + palabrasClave);
                        System.out.println("---------------------------");
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}