/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser.analizer;

import tokenizer.Token;

/**
 *
 * @author ado
 */
public interface ParserListener {
    public void item(Token<TokenClass> t);
    public void quoted(Token<TokenClass> t);
    public void space_itemlist(Token<TokenClass> t);
    public void coma(Token<TokenClass> t);
    public void space_postcoma(Token<TokenClass> t);
}
