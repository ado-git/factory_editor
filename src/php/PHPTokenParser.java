/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import org.apache.commons.text.StringEscapeUtils;
import utiles.FileIO;

/**
 *
 * @author ado
 */
public class PHPTokenParser {
    private final String php_path;
    private final String php_file_path;
    private final String data_file_path;

    /**
     * 
     * @param php_path Ruta de php
     * @param php_file_path Ruta del script php
     * @param data_file_path Ruta del file que el script usara como input.
     */
    public PHPTokenParser(String php_path, String php_file_path, String data_file_path) {
        this.php_path = php_path;
        this.php_file_path = php_file_path;
        this.data_file_path = data_file_path;
    }
    /**
     * Escribe el file de entrada de datos del script.
     */
    public void writeDataFile(String text) throws Exception
    {
        FileIO.write(data_file_path, text);
    }
    public InputStream run() throws IOException
    {
        Process process = new ProcessBuilder(php_path, "-f", php_file_path, data_file_path).start();
        
        return process.getInputStream();
    }
    
    /**
     * Parsea la salida generada en php y llama al consumer por cada tokenpair generado.
     */
    public void parse(Consumer<TokenPair> consumer) throws IOException, Exception
    {
        InputStream i = run();
        Scanner s = new Scanner(i);
        Pattern p;
        
        do {
            p = Pattern.compile("\\w+");
            String clase = s.findWithinHorizon(p, 0);
            if(clase == null)
                throw new Exception("no se encontro la clase del token");

            if(s.findWithinHorizon("<", 0) == null)
                throw new Exception("no se encontro el prefijo del token");

            p = Pattern.compile(".*?>", Pattern.DOTALL);
            String token = s.findWithinHorizon(p, 0);
            if(token == null)
                throw new Exception("no se encontro el token");
            
            token = token.substring(0, token.length() - 1);//se quita el ">"
            token =  StringEscapeUtils.unescapeHtml3(token);

            consumer.accept(new TokenPair(clase, token));
            
        } while (s.hasNext());
        
        i.close();
    }    
}
