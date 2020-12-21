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
public abstract class ItemBase {
    protected final Token<TokenClass> token;

    public ItemBase(Token<TokenClass> t) {
        this.token = t;
    }

    public abstract String getText();
}
