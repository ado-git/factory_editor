/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

import java.util.HashMap;
import variables.Variable;

/**
 *
 * @author ado
 */
public class PHPFileRendererBuilder {
    private final HashMap<String, Variable> variables = new HashMap<>();
    private String require_once = null;
    private String code = null;
    private final PHPFileRenderer renderer;

    public PHPFileRendererBuilder(PHPFileRenderer renderer) {
        this.renderer = renderer;
    }
    
    
    
    public void addVariable(Variable var) throws Exception
    {
        if(variables.containsKey(var.getName()))
            throw new Exception("variable ya existe");
        
        variables.put(var.getName(), var);
    }
    
    public void set_require_once(String file_path)
    {
        require_once = "<?php require_once('" + file_path + "');?>";
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String render()
    {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?php \n");
        for(Variable v : variables.values())
        {
            String value;
            if(v.getD_value() == null || v.getD_value().isEmpty())
                value = "null";
            else
                value = v.getD_value();
            
            builder.append("$").append(v.getName()).append("=").append(value).append(";").append("\n");
        }
        builder.append("?>");
        
        builder.append(require_once);
        
        builder.append(code);
        
        return builder.toString();
    }
    
    public PHPFileRenderer getFilerenderer() throws Exception
    {
        renderer.writeDataFile(render());
        
        return renderer;
    }
}
