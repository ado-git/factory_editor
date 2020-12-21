/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import php.PHPFileRendererBuilder;
import utiles.FileIO;
import variables.TipoArray;
import variables.TipoBoolean;
import variables.TipoInt;
import variables.TipoString;
import variables.Variable;
import variables.VariableIO;

/**
 *
 * @author ado
 */
public class tester_otro {
    public static void main(String[] args)
    {
        try {
            config.Config.initialize("C:\\Users\\ado\\Desktop\\test_project");
            
            PHPFileRendererBuilder b = new PHPFileRendererBuilder(config.Config.getPHPFileRenderer_newInstance());
            
            config.Config.getInstance().getVariableIO_newInstance().getVariables().forEach((t) -> {
                try {
                    b.addVariable(t);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            
            b.set_require_once(config.Config.getInstance().getFuncionesFilePath());
            
            b.setCode(FileIO.read(config.Config.getInstance().getFilesPath()+"/testfile.php",null)) ;
            
            System.out.println(b.render());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
