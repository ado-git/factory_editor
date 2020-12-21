/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import php.TokenPair;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author ado
 */
public class AttributeManager {
    private final TokenPair pair;
    private final HashMap<String, Color> color_hash = new HashMap<>();

    public AttributeManager(TokenPair pair) throws Exception {

        color_hash.put("keyword", Color.BLUE);
        color_hash.put("opentag", Color.RED);
        color_hash.put("closetag", Color.RED);
        color_hash.put("variable", Color.DARK_GRAY);
        color_hash.put("string", Color.DARK_GRAY);
        color_hash.put("comment", Color.GRAY);
        color_hash.put("otro", Color.BLACK);
        
        if(!color_hash.containsKey(pair.getClase()))
            throw new Exception("clase de token invalido: " + pair.getClase());
        
        this.pair = pair;
    }
    
    public Color getColor()
    {
        return color_hash.get(pair.getClase());
    }
    
    public AttributeSet getAttributeSet()
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, getColor());

        aset = sc.addAttribute(aset, StyleConstants.FontSize, 14);
        
        return aset;
    }
}
