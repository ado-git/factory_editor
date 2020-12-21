/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser;

import comaseparatedparser.analizer.Analizer;
import comaseparatedparser.analizer.AnalizerException;
import comaseparatedparser.analizer.MyListener;

/**
 *
 * @author ado
 */
public class TestAnalizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MyListener listener = new MyListener();
        
        String input = "1 3 \" 5f \" ,2,3, 4 ,\"5\" \"\"";
        
        System.out.println("Input: " + input + "\n");
        
        try {
            Analizer a = new Analizer(input, listener);
            //Analizer a = new Analizer("\"x=\\\"a\\\";y=_function('azul');\", 1");
            
            a.parse();
            
            listener.getStart().getItemlist().forEach((t) -> {
                System.out.println("itemlist");
                t.getItems().forEach((itembase) -> {
                    System.out.println(itembase.getText());
                });
            });
            
        }catch (AnalizerException e) {
            System.out.println("Error del analizer: " + e.getMessage());
        } catch(Exception e){
            System.out.println("Error del lexer: " + e.getMessage());
        }
    }
}
