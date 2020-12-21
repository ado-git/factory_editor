/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser;

import comaseparatedparser.analizer.Analizer;
import comaseparatedparser.analizer.AnalizerException;
import comaseparatedparser.analizer.MyListener;
import comaseparatedparser.analizer.estructuras.Itemlist;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *Clase para extraer un arreglo de arreglos a partir de listas separadas por comas, con un arreglo de fields como referencia.
 * 
 * @author ado
 */
public class CommaSeparatedExtractor {
    private final String input;
    private final String[] fields;
    public static final String SINGLEITEMINDICATOR = "";

    public CommaSeparatedExtractor(String input, String[] fields) {
        this.input = input;
        this.fields = fields;
    }

    private String cleanFieldName(String field)
    {
        if(field == null)
            throw new RuntimeException("field nulo");
        
        field = field.trim();
        
        if(field.isEmpty())
            throw new RuntimeException("field vacio");
        
        return field;
    }
    /**
     * Este utiliza los fields como keys.
     * Elimina cualquier whitespace limitrofe de los fields.
     */
    private HashMap<String, String> createHash(String[] input)
    {
        HashMap<String, String> hash = new HashMap<>();
        
        for (int i = 0; i < input.length; i++) {
            String key = cleanFieldName(fields[i]);
            
            if(hash.containsKey(key))
                throw new RuntimeException("field repetido: "+ key);
            
            hash.put(key, input[i]);
        }
        
        return hash;
    }
    
    /**
     * Este usa un "" como key
     */
    private HashMap<String, String> createHashSimple(String[] input)
    {
        HashMap<String, String> hash = new HashMap<>();
        
        for (int i = 0; i < input.length; i++) {
            hash.put(SINGLEITEMINDICATOR, input[i]);
        }
        
        return hash;
    }
    
    private String[] toStringArray(Itemlist i_list)
    {
        String[] a = new String[i_list.getItems().size()];
        
        for (int i = 0; i < a.length; i++) {
            a[i] = i_list.getItems().get(i).getText();
            
        }
        
        return a;
    }
    
    /**
     * Extrae maps de field a elemento 
     * Cada map tiene que tener tamaño igual a la cantidad de fields
     * @return 
     * @throws comaseparatedparser.analizer.AnalizerException 
     * @throws java.lang.Exception 
     */
    public ArrayList<HashMap<String, String>> parse() throws AnalizerException, Exception
    {
        ArrayList<HashMap<String, String>> alist = new ArrayList<>();
        
        int field_count;
        
        if(fields == null)
            field_count = 0;//se admitira solo un elemento por objeto
        else
            field_count = fields.length;
        
        MyListener listener = new MyListener();
        
        Analizer a = new Analizer(input, listener);
        
        a.parse();
        
        listener.getStart().getItemlist().forEach((t) -> {
            if(t.getItems().isEmpty())
                return;//ver si esto no funciona mal.

            String[] parsed_fields = toStringArray(t);

            if(field_count == 0 && parsed_fields.length == 1)
            {
                alist.add(createHashSimple(parsed_fields));
            }
            else if(field_count == parsed_fields.length)
            {
                alist.add(createHash(parsed_fields));
            }
            else
                throw new RuntimeException("cantidad de elementos incorrecta: " + "objecto actual: ... field_count: "+field_count + "parsed_fields.length: " + parsed_fields.length);
            });
        
        return alist;
    }
}
