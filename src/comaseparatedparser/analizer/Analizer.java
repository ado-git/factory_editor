/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser.analizer;

import java.util.ArrayList;
import tokenizer.Token;
import tokenizer.TokenRule;
import tokenizer.Tokenizer;

/**
 *
 * @author ado
 */
public class Analizer {
    
    private final State start;
    private final Tokenizer<TokenClass> tokenizer;
    private final ParserListener listener;

    public Analizer(String input, ParserListener listener) throws AnalizerException{
        
        ArrayList<TokenRule<TokenClass>> alist = new ArrayList<>();
        
        alist.add(new TokenRule<>(TokenClass.coma, ","));
        alist.add(new TokenRule<>(TokenClass.quoted, "\"(|(.*?[^\\\\]))\""));//dejar el quoted para saber que hay que quitarle las comillas, como item no sabriamos.
        alist.add(new TokenRule<>(TokenClass.whitespace, "\\s+"));
        alist.add(new TokenRule<>(TokenClass.item, "[^\\s,\"\\r\\n\\t]+"));
        
        
        this.tokenizer = new Tokenizer<>(alist, input);
        
        this.listener = listener;
        
        start = new State(false, (t) -> {
        });
        State item = new State(true, (t) -> {
            this.listener.item(t);
        });
        State quoted = new State(true, (t) -> {
            this.listener.quoted(t);
        });
        State space_itemlist = new State(true, (t) -> {
            this.listener.space_itemlist(t);
        });
        State coma = new State(false, (t) -> {
            this.listener.coma(t);
        });
        State space_postcoma = new State(false, (t) -> {
            this.listener.space_postcoma(t);
        });

        start.addState(TokenClass.item, item);
        start.addState(TokenClass.quoted, quoted);
        
        item.addState(TokenClass.whitespace, space_itemlist);
        item.addState(TokenClass.coma, coma);
        
        quoted.addState(TokenClass.whitespace, space_itemlist);
        quoted.addState(TokenClass.coma, coma);
        
        space_itemlist.addState(TokenClass.item, item);
        space_itemlist.addState(TokenClass.quoted, quoted);
        space_itemlist.addState(TokenClass.coma, coma);
        
        coma.addState(TokenClass.whitespace, space_postcoma);
        coma.addState(TokenClass.item, item);
        coma.addState(TokenClass.quoted, quoted);
        
        space_postcoma.addState(TokenClass.item, item);
        space_postcoma.addState(TokenClass.quoted, quoted);
    }
    
    public void parse() throws AnalizerException, Exception
    {
        State s = start;
            
        for(Token<TokenClass> token : tokenizer)
        {
            s = s.getState(token.getToken_class());
            s.fireEvent(token);
        }

        if(!s.isFinal())
            throw new Exception("unexpected end of input");
    }
}
