/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author ado
 */
public class FileIO {
    private FileIO(){}
    
    public static void write(String file_path, String text) throws IOException
    {
        try (FileWriter w = new FileWriter(file_path)) {
            w.write(text);
        }
    }
    
    public static String read(String file_path, String encoding) throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        String res;
        try (FileInputStream i = new FileInputStream(file_path)) {
            res = Streams.StreamToString(i, encoding);
        }
        
        return res;
    }
}
