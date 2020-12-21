/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.IOException;
import php.PHPTokenParser;

/**
 *
 * @author ado
 */
public class Operations {
    
    public static String insertVariable(String varname)
    {
        StringBuilder b = new StringBuilder();
        
        b.append("<?php echo $");
        b.append(varname);
        b.append(";?>");
        
        return b.toString();
    }
    
    private static String makeFieldsSugestions(String[] fields)
    {
        StringBuilder b = new StringBuilder();
        
        for(String s: fields)
        {
            b.append("<?php //echo $var['").append(s).append("'];?>");
        }
        
        return b.toString();
    }
    public static String insertForeach(String varname, String[] fields, String text)
    {
        if(text == null)
            text = "";
        
        StringBuilder b = new StringBuilder();
        
        b.append("<?php foreach($").append(varname).append(" as $var): ?>");
        
        if(fields != null && fields.length != 0)
            b.append(makeFieldsSugestions(fields));
        else
            b.append("<?php //echo $var;?>");
        
        b.append("\n");
        b.append(text);
        b.append("<?php endforeach; ?>");

        return b.toString();
    }    
    
    public static String insertIF(String varname, String text)
    {
        if(text == null)
            text = "";
        
        StringBuilder b = new StringBuilder();
        
        b.append("<?php if($").append(varname).append("): ?>\n");
        b.append(text);
        b.append("<?php endif; ?>");

        return b.toString();
    }

    public static String insertFor_CommaSeparated(String varname)
    {
        StringBuilder b = new StringBuilder();
        
        b.append("<?php if(isset($");
        b.append(varname);
        b.append("[0])){echo $");
        b.append(varname);
        b.append("[0];} for($i = 1; $i < count($");
        b.append(varname);
        b.append("); $i++){echo \",\".$");
        b.append(varname);
        b.append("[$i];} ?>");
        
        return b.toString();
    }
    
    public static String replacePHPtags(PHPTokenParser runner, String text) throws IOException, Exception
    {
        StringBuilder b = new StringBuilder();
        
        runner.writeDataFile(text);
        
        runner.parse((t) -> {

            try {

                if(t.getClase().compareTo("opentag") == 0)
                {
                    b.append("<?php openPHPtag();?>");
                }
                else if(t.getClase().compareTo("closetag") == 0)
                {
                    b.append("<?php closePHPtag();?>");
                }
                else
                {
                    b.append(t.getToken());
                }
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        return b.toString();
    }
}
