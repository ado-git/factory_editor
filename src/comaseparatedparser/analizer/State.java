/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser.analizer;

import java.util.HashMap;
import tokenizer.Token;

/**
 *
 * @author ado
 */
public class State {
    private final HashMap<TokenClass, State> states = new HashMap<>();
    private final boolean isFinal;
    private final StateEvent event;

    public State(boolean isFinal, StateEvent event) {
        this.isFinal = isFinal;
        this.event = event;
    }

    
    public void addState(TokenClass c, State s) throws AnalizerException
    {
        if(states.containsKey(c))
            throw new AnalizerException("clase ya definida");
        
        states.put(c, s);
    }
    
    public State getState(TokenClass c) throws AnalizerException
    {
        if(!states.containsKey(c))
            throw new AnalizerException("Unexpected character class: " + c.toString());
        
        return states.get(c);
    }

    public boolean isFinal() {
        return isFinal;
    }
    
    public void fireEvent(Token<TokenClass> t)
    {
        event.Consume(t);
    }
}
