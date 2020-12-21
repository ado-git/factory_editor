/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php.utiles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ado
 */
public class ArrayCodeCreator {
    private final ArrayList<HashMap<String, String>> alist;
    private final String singleItemIndicator;

    public ArrayCodeCreator(ArrayList<HashMap<String, String>> alist, String singleItemIndicator) {
        this.alist = alist;
        this.singleItemIndicator = singleItemIndicator;
    }

    /**
     * Determina si se trata de un hash que contiene un solo elemento sin nombre.
     */
    private boolean isSingleEntry(HashMap<String, String> hash)
    {
        return (hash.containsKey(singleItemIndicator) && hash.size() == 1);
    }
    private StringBuilder innerArray(StringBuilder b, HashMap<String, String> hash)
    {
        b.append("array(\n");

        for(String key : hash.keySet())
        {
            b.append("'").append(key).append("' => ").append(hash.get(key)).append(",\n");
        }

        b.deleteCharAt(b.length() - 2);//quita la ultima coma

        return b.append(")");
    }
    
    public String create()
    {
        StringBuilder b = new StringBuilder();
        
        b.append("array(");
        if(alist.size() > 0)
        {
            for(HashMap<String, String> hash : alist)
            {
                if(hash.isEmpty())
                    continue;
                
                if(isSingleEntry(hash))
                {
                    b.append(hash.get(singleItemIndicator));
                }
                else
                {
                    b = innerArray(b, hash);
                }

                b.append(",");
                
            }
            b.deleteCharAt(b.length() - 1);//quita la ultima coma
            
        }
        b.append(")");
        
        return b.toString();
    }
}
