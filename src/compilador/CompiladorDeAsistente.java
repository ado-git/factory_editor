/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;
import utiles.FileIO;
import variables.Variable;

/**
 *
 * @author ado
 */
public class CompiladorDeAsistente {
    private final String file_path;
    private final Variable[] variables;

    public CompiladorDeAsistente(String file_path, Variable[] variables) {
        this.file_path = file_path;
        this.variables = variables;
    }
    
    private String createFieldsArray(String[] fields)
    {
        StringBuilder b = new StringBuilder();
        
        b.append("array(");
        
        if(fields != null && fields.length > 0)
        {
            for(String s:fields)
            {
                b.append("'").append(s).append("'").append(",");
            }

            b.deleteCharAt(b.length() - 1);
        }
        
        b.append(")");
        
        return b.toString();
    }
    
    private String CreateVariableEntry(Variable v)
    {
        StringBuilder b = new StringBuilder();
        
        b.append("        $a[] = new Variable(\"").append(v.getName());
        b.append("\", \"").append(v.getTipo().getTipoBasico().toString());
        b.append("\", ").append(createFieldsArray(v.getTipo().getFields()));
        b.append(", null);");
        
        return b.toString();
    }
    public void Compilar() throws IOException
    {
        StringBuilder b = new StringBuilder();
        
        b.append("<?php");
        b.append("\n\n");
        b.append("require_once LIB_PATH.'/softfactory/assistant/asistente_abstract.php';\n");
        b.append("require_once LIB_PATH.'/softfactory/assistant/variable.php';\n");
        b.append("\n");
        b.append("class Asistente extends AsistenteAbstract\n");
        b.append("{\n");
        b.append("    public function __construct($basePath, $php_path, $sandboxfilepath) {\n");
        b.append("        $a = array();\n");
        
        for(Variable v: variables)
        {
            b.append(CreateVariableEntry(v));
            b.append("\n");
        }
        
        b.append("\n");
        b.append("        parent::__construct($basePath, $php_path, $sandboxfilepath, $a);\n");
        b.append("    }\n");
        b.append("}");
        
        FileIO.write(file_path, b.toString());
    }
}
