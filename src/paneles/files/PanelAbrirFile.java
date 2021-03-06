/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles.files;

import config.Config;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import utiles.ComboBoxBasicManager;
import utiles.Dialogos;
import utiles.PanelModal;
import variables.Variable;

/**
 *
 * @author ado
 */
public class PanelAbrirFile extends PanelModal<String> {
    private final String working_files_dir;


    /**
     * Creates new form Files
     */
    public PanelAbrirFile() throws Exception{
        super("Files");
        initComponents();
        
        
        if(Config.getInstance().getFilesPath()== null)
            throw new RuntimeException("files path es null");
        
        working_files_dir = Config.getInstance().getFilesPath();
        
        jFileChooser1.setCurrentDirectory(new File(Config.getInstance().getFilesPath()));
        
        ArrayList<Variable> v_list = Config.getInstance().getVariableIO_newInstance().getVariables();
        
        ComboBoxBasicManager comboIncluye_mgr = new ComboBoxBasicManager(jComboBoxIncluyeSi);
        ComboBoxBasicManager comboNombre_mgr = new ComboBoxBasicManager(jComboBoxNombreDepende);
        
        comboIncluye_mgr.init();
        comboNombre_mgr.init();
        
        for(Variable v : v_list)
        {
            comboIncluye_mgr.addItem(v.getName());
            comboNombre_mgr.addItem(v.getName());
        }
    }
    
    /**
     * Chequea si el path dado es hijo del directorio files.
     */
    private boolean isFileChildOfRoot(String file_path)
    {
        Path parent = Paths.get(working_files_dir).toAbsolutePath();
        
        Path child = Paths.get(file_path);
        
        return  child.startsWith(parent);
    }
    
    private void generateFileName()
    {
        StringBuilder b = new StringBuilder();
        
        try {
            String value = new ComboBoxBasicManager(jComboBoxIncluyeSi).getValue("");
            b.append("[&include=").append(value).append("]");
        } catch (Exception e) {
        }
        
        try {
            String value = new ComboBoxBasicManager(jComboBoxNombreDepende).getValue("");
            b.append("[#").append(value).append("]");
        } catch (Exception e) {
        }
        
        if(jFileChooser1.getSelectedFile() != null)
        {
            String[] res = jFileChooser1.getSelectedFile().getName().split("\\.", 0);
            
            if(res.length > 1)
                b.append(".").append(res[res.length - 1]);
        }
        
        jTextFieldFilename.setText(b.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxIncluyeSi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxNombreDepende = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFilename = new javax.swing.JTextField();
        jButtonRenombrar = new javax.swing.JButton();
        jButtonDeseleccionar = new javax.swing.JButton();

        jFileChooser1.setApproveButtonText("Abrir");
        jFileChooser1.setApproveButtonToolTipText("");
        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Se incluye si:");

        jComboBoxIncluyeSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIncluyeSiActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre depende de:");

        jComboBoxNombreDepende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNombreDependeActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre final:");

        jButtonRenombrar.setText("Renombrar/Crear");
        jButtonRenombrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRenombrarActionPerformed(evt);
            }
        });

        jButtonDeseleccionar.setText("Deseleccionar");
        jButtonDeseleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeseleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonDeseleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRenombrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxNombreDepende, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxIncluyeSi, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxIncluyeSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxNombreDepende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFilename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRenombrar)
                    .addComponent(jButtonDeseleccionar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:

        if(evt.getActionCommand().compareTo("ApproveSelection") != 0)
            return;
        
        File f = jFileChooser1.getSelectedFile();
        
        if(f.isDirectory())
            return;

        String path = f.getPath();

        if(!isFileChildOfRoot(path))
        {
            Dialogos.showMessageDialog("file fuera del directorio raiz");
            return;
        }

        if(!f.exists())
        {
            try {
                f.createNewFile();

            } catch (IOException e) {
                Dialogos.showMessageDialog("No se pudo crear el file");
                return;
            }

        }

        result = path;
        
        close();
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jButtonRenombrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRenombrarActionPerformed
        // TODO add your handling code here:
        
        if(!isFileChildOfRoot(jFileChooser1.getCurrentDirectory().getAbsolutePath()))
        {
            Dialogos.showMessageDialog("ruta fuera de la raiz de files");
            return;
        }
        
        if(jTextFieldFilename.getText().isEmpty())
        {
            Dialogos.showMessageDialog("No se ha creado un nombre de file");
            return;
        }
        

        try {
            File newFile = new File(jFileChooser1.getCurrentDirectory().getAbsolutePath() + "\\" +jTextFieldFilename.getText());
            
            if(jFileChooser1.getSelectedFile() != null)
            {
                if(!Dialogos.showConfirmationDialog("Se renombraría el file seleccionado. ¿Desea proceder?"))
                    return;
                
                if(!jFileChooser1.getSelectedFile().renameTo(newFile))
                    throw new Exception("error al renombrar");
            }
            else
            {
                if(!Dialogos.showConfirmationDialog("Se crearía un file nuevo. ¿Desea proceder?"))
                    return;
                
                if(!newFile.createNewFile())
                    throw new Exception("No se pudo crear el file");
            }
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
            return;
        }
        
        Dialogos.showMessageDialog("Hecho! Actualice para ver los cambios");
    }//GEN-LAST:event_jButtonRenombrarActionPerformed

    private void jComboBoxIncluyeSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIncluyeSiActionPerformed
        // TODO add your handling code here:
        generateFileName();
    }//GEN-LAST:event_jComboBoxIncluyeSiActionPerformed

    private void jComboBoxNombreDependeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNombreDependeActionPerformed
        // TODO add your handling code here:
        generateFileName();
    }//GEN-LAST:event_jComboBoxNombreDependeActionPerformed

    private void jButtonDeseleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeseleccionarActionPerformed
        // TODO add your handling code here:
        jFileChooser1.setSelectedFile(null);
    }//GEN-LAST:event_jButtonDeseleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeseleccionar;
    private javax.swing.JButton jButtonRenombrar;
    private javax.swing.JComboBox<String> jComboBoxIncluyeSi;
    private javax.swing.JComboBox<String> jComboBoxNombreDepende;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextFieldFilename;
    // End of variables declaration//GEN-END:variables
}
