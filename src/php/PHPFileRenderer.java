/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

import java.io.IOException;
import java.io.InputStream;
import utiles.FileIO;

/**
 *
 * @author ado
 */
public class PHPFileRenderer {
    private final String php_path;
    private final String php_file_path;

    /**
     * 
     * @param php_path Ruta de php
     * @param php_file_path Ruta del script php
     */
    public PHPFileRenderer(String php_path, String php_file_path) {
        this.php_path = php_path;
        this.php_file_path = php_file_path;
    }
    /**
     * Escribe el file del script.
     */
    public void writeDataFile(String text) throws Exception
    {
        FileIO.write(php_file_path, text);
    }
    
    
    public InputStream run() throws IOException
    {
        Process process = new ProcessBuilder(php_path, "-f", php_file_path).start();
        
        return process.getInputStream();
    }
}
