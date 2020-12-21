/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser.analizer.estructuras;

import comaseparatedparser.analizer.TokenClass;
import tokenizer.Token;

/**
 *
 * @author ado
 */
public class Quoted extends ItemBase{

    public Quoted(Token<TokenClass> t) {
        super(t);
    }
    
    @Override
    public String getText() {
        StringBuilder b = new StringBuilder(token.getText());
        
        b.deleteCharAt(b.length() - 1);
        b.deleteCharAt(0);
        
        return replaceEscapes(b.toString());
    }
    private String replaceEscapes(String input)
    {
        input =  input.replaceAll("\\\\\\\"", "\"");
        
        return input;
    }
}
