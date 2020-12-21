/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ado
 */
public class VariableDomParser {
    private final Element variable;

    public VariableDomParser(Element variable) {
        this.variable = variable;
    }

    
    private String getAttribute(String attrib_name, boolean is_mandatory) throws Exception
    {
        if(variable.getAttributes().getNamedItem(attrib_name) == null)
        {
            if(is_mandatory)
                throw new Exception("variable sin " + attrib_name);
            else
                return null;
        }
        else
        {
            String value = variable.getAttributes().getNamedItem(attrib_name).getNodeValue();

            if(is_mandatory && (value == null || value.isEmpty()))
                throw new Exception("atributo sin valor: " + attrib_name);
            
            return value;
        }

    }
    /**
     * Retorna un arreglo con los fields de la variable. Si no hay fields, el arreglo se devuelve vacio, nunca es null.
     */
    private String[] getFields() throws Exception
    {
        NodeList l = variable.getElementsByTagName("field");
        int len = l.getLength();
        if( len == 0)
            return new String[]{};
        
        String[] ar = new String[len];

        for (int i = 0; i < ar.length; i++) {
            Node field_name = l.item(i).getAttributes().getNamedItem("field_name");
            if(field_name == null)
                throw new Exception("falta el field_name");
            
            String value = field_name.getNodeValue();
            if(value == null || value.isEmpty())
                throw new Exception("falta el valor del field_name");
            
            ar[i] = StringEscapeUtils.unescapeHtml4(value);
        }
        
        return ar;
    }
    /**
     * Devuelve un objeto Variable correspondiente al Nodo variable del dom con que se inicializo la clase.
     */
    public Variable parse() throws Exception
    {
        //no repetir nombres de atributos de nodos para evitar complicaciones.
        
        String var_name = StringEscapeUtils.unescapeHtml4(getAttribute("var_name", true));
        String descripcion = StringEscapeUtils.unescapeHtml4(getAttribute("comment", false));
        String tipo = StringEscapeUtils.unescapeHtml4(getAttribute("type", true));
        String d_value = StringEscapeUtils.unescapeHtml4(getAttribute("d_value", false));
        
        if(tipo.compareTo(EnumBasico.ARRAY.toString()) == 0)
        {
            return new Variable(var_name, descripcion, tipo, d_value, getFields());
        }
        else
            return new Variable(var_name, descripcion, tipo, d_value, null);
    }
}
