/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import javax.swing.JComboBox;

/**
 *
 * @author ado
 */
public class ComboBoxBasicManager {
    private final JComboBox<String> combo;
    private final String seleccione_text = "--seleccione--";

    public ComboBoxBasicManager(JComboBox<String> combo) {
        this.combo = combo;
    }
    
    public void init()
    {
        combo.removeAllItems();
        combo.addItem(seleccione_text);
    }
    public void addItem(String value)
    {
        combo.addItem(value);
    }
    public String getValue(String errorSourceDesc) throws Exception
    {
        if(combo.getSelectedIndex() < 0)
            throw new UserFriendlyException(errorSourceDesc);
        
        String val = combo.getSelectedItem().toString();
        if(val == null  || val.isEmpty() || val.compareTo(seleccione_text) == 0)
            throw new UserFriendlyException(errorSourceDesc);
        
        return val;
    }    
}
