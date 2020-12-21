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
public interface StateEvent {
    public void Consume(Token<TokenClass> t);
}
