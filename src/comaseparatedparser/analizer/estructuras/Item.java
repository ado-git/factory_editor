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
public class Item extends ItemBase{

    public Item(Token<TokenClass> t) {
        super(t);
    }

    @Override
    public String getText() {
        return token.getText();
    }
}
