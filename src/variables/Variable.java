/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

import java.util.HashMap;

/**
 *
 * @author ado
 */
public class Variable {
    private final String name;
    private final String descripcion;
    private final Tipo tipo;
    private final String d_value;

    public static String cleanVariableName(String var_name) throws Exception
    {
        if(!var_name.matches("\\s*[a-zA-Z]\\w*\\s*"))
            throw new Exception("nombre de variable invalido");
        
        return var_name.trim();
    }
    public Variable(String name, String descripcion, Tipo tipo, String d_value) throws Exception {
        this.name = cleanVariableName(name);
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.d_value = d_value;
    }
    
    public Variable(String var_name, String descripcion, String tipo, String d_value, String[] fields) throws Exception
    {   
        this.name = cleanVariableName(var_name);
        this.descripcion = descripcion;
        this.d_value = d_value;
        
        HashMap<String, EnumBasico> hash_tipos = new HashMap<>();
        
        hash_tipos.put(EnumBasico.BOOLEAN.toString(), EnumBasico.BOOLEAN);
        hash_tipos.put(EnumBasico.INT.toString(), EnumBasico.INT);
        hash_tipos.put(EnumBasico.FLOAT.toString(), EnumBasico.FLOAT);
        hash_tipos.put(EnumBasico.STRING.toString(), EnumBasico.STRING);
        hash_tipos.put(EnumBasico.ARRAY.toString(), EnumBasico.ARRAY);
        
        
        switch (hash_tipos.get(tipo)) {
            case BOOLEAN:                
                this.tipo = new TipoBoolean();
                break;
            case INT:                
                this.tipo = new TipoInt();
                break;
            case FLOAT:                
                this.tipo = new TipoFloat();
                break;
            case STRING:
                this.tipo = new TipoString();
                break;
            case ARRAY:
                this.tipo = new TipoArray(fields);
                break;
            default:
                throw new Exception("tipo desconocido");
        }        
    }    

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getD_value() {
        return d_value;
    }
}
