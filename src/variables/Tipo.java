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
public abstract class Tipo {
    private final EnumBasico tipo_basico;
    private final String[] fields;

    public Tipo(EnumBasico tipo_basico, String[] fields) throws Exception{
        this.tipo_basico = tipo_basico;
        
        if(fields != null)
        {
            for (int i = 0; i < fields.length; i++) {
                fields[i] = Variable.cleanVariableName(fields[i]);
            }
        }
        
        this.fields = fields;
    }

    public EnumBasico getTipoBasico() {
        return tipo_basico;
    }

    public String[] getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return tipo_basico.toString();
    }
}
