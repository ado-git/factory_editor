/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author ado
 */
public class Compresion {
    
    public static void unzip(String fileZip, File destDir) throws IOException {
        
        byte[] buffer = new byte[1024];
        
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            
            if(zipEntry.isDirectory())
            {
                newFile.mkdir();
            }
            else
            {
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
     
    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
         
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }    
}
