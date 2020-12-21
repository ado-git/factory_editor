/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.util.HashSet;

/**
 *
 * @author ado
 */
public class Validaciones {
    
    private Validaciones(){}
    /**
     * valida si un numero n esta en el rango min y max
     * @param n numero a validar
     * @param min minimo
     * @param max maximo
     * @return true si esta en el rango, falso si no
     */
    
    public static boolean isEnRango(double n, double min, double max)
    {
        return n >= min && n <= max;
    }
    public static boolean isInSet(Character c ,HashSet<Character> a, boolean convertLowerCase)
    {
        if(convertLowerCase)
            return a.contains(("" + c).toLowerCase().charAt(0));
        
        return a.contains(c);
    }   
}
