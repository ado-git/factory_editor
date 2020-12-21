/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.text.StringEscapeUtils;
import org.xml.sax.SAXException;

/**
 *
 * @author ado
 */
public class VariableIO {
    private final String file_path;
    private final Document dom;

    public VariableIO(String file_path) throws ParserConfigurationException , SAXException , IOException {
        this.file_path = file_path;
        
        if(new File(file_path).exists())
            dom = loadDom();
        else
        {
            dom = createDom();
        }
    }
    
    private Document createDom() throws ParserConfigurationException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        Document dom = db.newDocument();

        dom.appendChild(dom.createElement("body"));
        
        return dom;
    }
    
    private Document loadDom() throws ParserConfigurationException , SAXException , IOException  {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
//        dbf.setValidating(true);
//        dbf.setIgnoringElementContentWhitespace(true);
        
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        return db.parse(file_path);
    }

    private Node getVariableNode(String var_name)
    {
        NodeList l = getVariablesNodeList();
        
        int len = l.getLength();
        
        for (int i = 0; i < len; i++)
        {
            Node n = l.item(i).getAttributes().getNamedItem("var_name");
            
            if(n!= null && n.getNodeValue() != null && n.getNodeValue().compareTo(var_name) == 0)
            {
                return l.item(i);
            }
        }
        
        return null;
    }
    
    public boolean existsVariable(String var_name)
    {
        return (getVariableNode(var_name) != null);
    }
    
    /**
     * Añade atributos y fields al Element "variable".
     */
    private void setVariableElement(Variable var, Element var_elem)
    {
        var_elem.setAttribute("var_name", StringEscapeUtils.escapeHtml4(var.getName()));
        var_elem.setAttribute("comment", StringEscapeUtils.escapeHtml4(var.getDescripcion()));
        var_elem.setAttribute("type", StringEscapeUtils.escapeHtml4(var.getTipo().toString()));
        var_elem.setAttribute("d_value", StringEscapeUtils.escapeHtml4(var.getD_value()));
        
        if(var.getTipo().getTipoBasico() == EnumBasico.ARRAY)
        {
            for (String field_name : var.getTipo().getFields()){
                Element field = dom.createElement("field");
                field.setAttribute("field_name", StringEscapeUtils.escapeHtml4(field_name));
                
                var_elem.appendChild(field);
            }
        }
    }

    /**
     * Elimina las lineas en blanco dentro de un nodo
     */
    private void trimWhitespace(Node node)
    {
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if(child.getNodeType() == Node.TEXT_NODE) {
                child.setTextContent(child.getTextContent().trim());
            }
        }
    }
    
    /**
     * Añade una variable, si ya existe con este nombre, lanza una excepcion.
     */
    public void addVariable(Variable var) throws Exception
    {
        _saveVariable(var, false);
    }

    /**
     * Modifica o añade una variable.
     */
    public void saveVariable(Variable var) throws Exception
    {
        _saveVariable(var, true);
    }
    
    private void _saveVariable(Variable var, boolean may_override) throws Exception
    {
        Element var_elem;
        
        if(existsVariable(var.getName()))
        {
            if(!may_override)
                throw new Exception("ya existe la variable: " + var.getName());
            
            var_elem = (Element)getVariableNode(var.getName());
        
        
            NodeList list =  var_elem.getElementsByTagName("*");
            int len = list.getLength();

            //se borran los elementos hijos.
            ArrayList<Node> a = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                a.add(list.item(i));
            }

            a.forEach((t) -> {
                var_elem.removeChild(t);
            });

            trimWhitespace(var_elem);
        }
        else
        {
            Element e = dom.getDocumentElement();

            var_elem = dom.createElement("variable");

            e.appendChild(var_elem);
        }
        
        //actualiza valores
        setVariableElement(var, var_elem);
    }
    /**
     * Guarda el dom en el file xml
     */
    public void saveFile() throws TransformerException, IOException, ParserConfigurationException {

        Transformer tr = TransformerFactory.newInstance().newTransformer();

        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.setOutputProperty(OutputKeys.METHOD, "xml");
        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(file_path)));
    }
    
    private NodeList getVariablesNodeList()
    {   
        return dom.getDocumentElement().getElementsByTagName("variable");
    }
    
    public ArrayList<Variable> getVariables() throws Exception
    {
        ArrayList<Variable> a_list = new ArrayList<>();
        
        NodeList l = getVariablesNodeList();
        
        int len = l.getLength();
        
        for (int i = 0; i < len; i++)
        {
            VariableDomParser p = new VariableDomParser((Element)l.item(i));
            a_list.add(p.parse());
        }
        
        return a_list;
    }
    public Variable getVariable(String var_name) throws Exception
    {   
        return new VariableDomParser((Element)getVariableNode(var_name)).parse();
    }
}
