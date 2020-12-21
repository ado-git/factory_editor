/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

/**
 *
 * @author ado
 */
public class TokenPair {
    private final String clase;
    private final String token;

    public TokenPair(String clase, String token) {
        this.clase = clase;
        this.token = token;
    }

    public String getClase() {
        return clase;
    }

    public String getToken() {
        return token;
    }
    
    
    
}
