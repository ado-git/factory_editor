/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author ado
 */
public class Streams {
    private Streams(){}
    
    /**
     * 
     * @param format si es null por defecto se usa UTF-8
     */
    public static String StreamToString(InputStream i, String format) throws UnsupportedEncodingException, IOException
    {
        if(format == null)
            format = "UTF-8";
        
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        
        final StringBuilder out = new StringBuilder();
        
        Reader in = new InputStreamReader(i, format);
        
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            
            out.append(buffer, 0, rsz);
        }
        
        return out.toString();
    }
}
