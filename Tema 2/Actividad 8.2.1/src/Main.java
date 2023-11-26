import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
class GestorContactosXML extends DefaultHandler {

    enum TiposTelefono {NINGUNO, CASA, TRABAJO, MOVIL};
    protected String contenido;
    protected String datosDelContacto;
    protected String telefono;
    protected TiposTelefono tipoTelefonoGuardado;
    protected boolean analizandoTelefonos;

    public void startElement(String url, String nombreLocal, String qName, Attributes attributos)
        throws SAXException {
            if (qName.equals("telefonos") ) {
                analizandoTelefonos = true;
                tipoTelefonoGuardado = TiposTelefono.NINGUNO;
                telefono = "";
            }
        }

    public void characters( char ch[], int inicio, int longitud )
        throws SAXException {
        contenido = new String( ch, inicio, longitud );
    }

    public void endElement(String uri, String nombreLocal, String qName)
        throws SAXException {
            if ( !qName.isBlank() ) {
                if ( qName.equals("nombre") )
                    datosDelContacto = contenido;
                else if ( qName.equals("apellido") )
                    datosDelContacto += " " + contenido;
                else if ( analizandoTelefonos && qName.equals("casa") && tipoTelefonoGuardado == TiposTelefono.NINGUNO ) {
                    telefono = contenido;
                    tipoTelefonoGuardado = TiposTelefono.CASA;
                }
                else if ( analizandoTelefonos && qName.equals("trabajo") && (tipoTelefonoGuardado == TiposTelefono.CASA || tipoTelefonoGuardado == TiposTelefono.NINGUNO) ) {
                    telefono = contenido;
                    tipoTelefonoGuardado = TiposTelefono.TRABAJO;
                }
                else if ( analizandoTelefonos && qName.equals("movil") ) {
                    telefono = contenido;
                    tipoTelefonoGuardado = TiposTelefono.MOVIL;
                }
                else if ( qName.equals("telefonos") ) {
                    if ( !telefono.isBlank() ) {
                        datosDelContacto += " - Teléfono: " + telefono;
                        switch (tipoTelefonoGuardado) {
                            case CASA -> datosDelContacto += " (Casa)";
                            case TRABAJO -> datosDelContacto += " (Trabajo)";
                            case MOVIL -> datosDelContacto += " (Móvil)";
                        }
                        analizandoTelefonos = false;
                    }
                    else if ( qName.equals("contacto") ) {
                        System.out.println(datosDelContacto);
                    }
                }
            }
    }
    public class Main {
        public static void main( String[] args ) {
            try {
                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                saxParser.parse("contactos.xml", new GestorContactosXML()
                );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}