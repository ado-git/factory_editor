/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser.analizer;

import comaseparatedparser.analizer.estructuras.Item;
import comaseparatedparser.analizer.estructuras.Quoted;
import comaseparatedparser.analizer.estructuras.Start;
import tokenizer.Token;

/**
 *
 * @author ado
 */
public class MyListener implements ParserListener{
    private final Start start = new Start();

    @Override
    public void item(Token<TokenClass> t){
        start.getCurrentItemList().addItemBase(new Item(t));
    }
    @Override
    public void quoted(Token<TokenClass> t){
        start.getCurrentItemList().addItemBase(new Quoted(t));
    }
    @Override
    public void space_itemlist(Token<TokenClass> t){
    }
    @Override
    public void coma(Token<TokenClass> t){
        start.addNewItemlist();
    }
    @Override
    public void space_postcoma(Token<TokenClass> t){
    }

    public Start getStart() {
        return start;
    }
}
