/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import compilador.CompiladorDeAsistente;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import php.PHPFileRenderer;
import php.PHPTokenParser;
import variables.Variable;
import variables.VariableIO;

/**
 *
 * @author ado
 */
public class Config {
    private static Config INSTANCE;
    public static String PROYECT_TEMPLATE_ZIP_PATH = "data\\nuevo_proyecto.zip";
    public static String PHP_PATH = "external\\php5.6.35\\php.exe";
    public static String PHPFORMATER_SCRIPT_PATH = "external\\php_parser\\runner.php";
    public static String PHPFORMATER_DATA_OUTPUT_PATH = "external\\php_parser\\data\\ipc_file.txt";
    public static String PHP_RENDERER_FILE_PATH = "external\\php_renderer\\data\\renderer.php";
    
    
    private String project_path = null;
    
    private Config(String project_path) throws Exception
    {
        //chequear si la ruta es valida
        this.project_path = project_path;
    }
    
    public static void initialize(String project_path) throws Exception
    {
        INSTANCE = new Config(project_path);
    }
    
    public static Config getInstance() throws Exception
    {
        if(INSTANCE == null)
            throw new Exception("sin inicializar");
        
        return Config.INSTANCE;
    }

    public String getProjectPath() {
        return project_path;
    }
    
    public String getFilesPath()
    {
        return project_path + "\\files";
    }
    
    public String getVariablesPath()
    {
        return project_path + "\\editor\\variables.xml";
    }
    
    public String getFuncionesFilePath()
    {
        return project_path + "\\FUNCIONES_PROYECTO.PHP";
    }
    
    public String getAsistenteFilePath()
    {
        return project_path + "\\asistente.php";
    }
    
    public VariableIO getVariableIO_newInstance() throws ParserConfigurationException, SAXException, IOException, Exception
    {
        return new VariableIO(getVariablesPath());
    }
    public static PHPFileRenderer getPHPFileRenderer_newInstance()
    {
        return new PHPFileRenderer(Config.PHP_PATH, Config.PHP_RENDERER_FILE_PATH);
    }
    public static PHPTokenParser getPHPTokenParser_newInstance()
    {
        return new PHPTokenParser(Config.PHP_PATH, Config.PHPFORMATER_SCRIPT_PATH, Config.PHPFORMATER_DATA_OUTPUT_PATH);
    }
    public static CompiladorDeAsistente getCompiladorDeAsistente() throws ParserConfigurationException, Exception
    {
        ArrayList<Variable> vlist = Config.getInstance().getVariableIO_newInstance().getVariables();
        
        Variable[] var_array = vlist.toArray(new Variable[vlist.size()]);
        
        return new CompiladorDeAsistente(Config.getInstance().getAsistenteFilePath(), var_array);
    }
}
