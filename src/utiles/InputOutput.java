/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

/**
 *
 * @author ado
 */
public class InputOutput {
    private InputOutput(){}
    
    public static String getString(JTextComponent tComponent, String errorSourceDesc) throws Exception
    {
        if(tComponent.getText().isEmpty())
            throw new Exception(errorSourceDesc);
        
        return tComponent.getText();
    }
    public static int getInt(JTextComponent tComponent, int min, int max, String errorSourceDesc) throws Exception
    {
        if(tComponent.getText().isEmpty())
            throw new Exception(errorSourceDesc);
        
        
        int input = Integer.parseInt(tComponent.getText());
        
        if(!Validaciones.isEnRango(input, min, max))
            throw new Exception(errorSourceDesc + ": valor fuera de rango");
            
        return input;
    }
    /**
     * Devuelve un array con los elementos que fueron introducidos separados por el separador.
     * El separador por defecto la coma si se para null en regex_separator
     */
    public static String[] getSplit(JTextComponent tComponent, String regex_separator)
    {
        if(regex_separator == null)
            regex_separator = ",";
        
        if(tComponent.getText().isEmpty())
            return new String[0];
        
        return tComponent.getText().split(regex_separator);
    }
    
    public static void initCombobox(JComboBox<ComboBoxKeyValue> combo)
    {
        combo.removeAllItems();
        combo.addItem(new ComboBoxKeyValue("--seleccione--", null));
    }
    public static void addComboboxItem(JComboBox<ComboBoxKeyValue> combo, String key, int value)
    {
        combo.addItem(new ComboBoxKeyValue(key, value));
    }
    public static Integer getComboboxValue(JComboBox<ComboBoxKeyValue> combo, String errorSourceDesc) throws Exception
    {
        if(combo.getSelectedIndex() < 0)
            throw new Exception(errorSourceDesc);
        
        Integer val = ((ComboBoxKeyValue)combo.getSelectedItem()).getValue();
        if(val == null)
            throw new Exception(errorSourceDesc);
        
        return val;
    }
    
}
