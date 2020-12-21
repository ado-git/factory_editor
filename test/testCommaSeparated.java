
import php.utiles.ArrayCodeCreator;
import comaseparatedparser.CommaSeparatedExtractor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ado
 */
public class testCommaSeparated {
    
    public static void main(String[] args)
    {
        try {
            CommaSeparatedExtractor conv = new CommaSeparatedExtractor("1 2 3 , 4 5 6 ,", new String[]{"nombre","apellido","edad"});
            
            ArrayCodeCreator c = new ArrayCodeCreator(conv.parse(), CommaSeparatedExtractor.SINGLEITEMINDICATOR);
            
            System.out.println(c.create());
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
