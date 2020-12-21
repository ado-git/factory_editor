/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

/**
 *
 * @author ado
 */
public class TipoArray extends Tipo{

    public TipoArray(String[] fields)  throws Exception{
        super(EnumBasico.ARRAY, fields);
    }
}
