/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import paneles.files.PanelAbrirProyecto;
import paneles.files.PanelAbrirFile;
import code.Operations;
import paneles.variables.PanelSolicitarVariable;
import paneles.variables.PanelVariables;
import config.Config;
import editor.EditorManager;
import editor.Lenguajes;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JTextPane;
import paneles.variables.PanelVariablesSinInicializar;
import php.PHPFileRenderer;
import php.PHPFileRendererBuilder;
import utiles.UserFriendlyException;
import tabs_management.TabsManager;
import utiles.Dialogos;

/**
 *
 * @author ado
 */
public class Main extends javax.swing.JFrame {
    private TabsManager tabs_mgr;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        try {
            tabs_mgr = new TabsManager(jTabbedPane1, Config.getPHPTokenParser_newInstance(), jPopupMenuEditor);
        } catch (Exception e) {
            Dialogos.showMessageDialog("Error grave de la aplicacion: " + e.getMessage());
            return;
        }
        
        
        nuevoProyecto();
    }
    /**
     * Retorna el dir_path
     */
    private String nuevoProyecto()
    {
        String dir_path = (new PanelAbrirProyecto()).showIt();
        
        if(dir_path == null)
            return null;
        
        try {

            Config.initialize(dir_path);
            
            this.setTitle(dir_path);
            
            tabs_mgr.closeAll();
            
            PanelVariables.getInstance().close();            
            
            return dir_path;

        } catch (Exception e) {
            Dialogos.showMessageDialog("no se pudo inicializar la configuracion "+ e.getMessage());
        }
        
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuEditor = new javax.swing.JPopupMenu();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemCut = new javax.swing.JMenuItem();
        jMenuItemPaste = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonCerrar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jButton5 = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jButtonEjecutar = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jButtonFormato = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemAbrirProyecto = new javax.swing.JMenuItem();
        jMenuItemCompilar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemVariables = new javax.swing.JMenuItem();
        jMenuItemAbrirFiles = new javax.swing.JMenuItem();
        jMenuItemFunciones = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemInsertarVariable = new javax.swing.JMenuItem();
        jMenuItemInsertIF = new javax.swing.JMenuItem();
        jMenuItemInsertar_foreach = new javax.swing.JMenuItem();
        jMenuItemInsertIfCommaSeparated = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemEtiquetasPHP = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemVariablesSinValor = new javax.swing.JMenuItem();
        jMenuItemUltimoRenderer = new javax.swing.JMenuItem();

        jMenuItemCopy.setText("Copy");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jPopupMenuEditor.add(jMenuItemCopy);

        jMenuItemCut.setText("Cut");
        jMenuItemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCutActionPerformed(evt);
            }
        });
        jPopupMenuEditor.add(jMenuItemCut);

        jMenuItemPaste.setText("Paste");
        jMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPasteActionPerformed(evt);
            }
        });
        jPopupMenuEditor.add(jMenuItemPaste);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.setFocusable(false);
        jButtonCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonCerrar);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setFocusable(false);
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGuardar);

        jButton4.setText("Guardar todo");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);
        jToolBar1.add(filler1);

        jButton6.setText("Deshacer");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jButton7.setText("Rehacer");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);
        jToolBar1.add(filler2);

        jButton5.setText("Buscar");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);
        jToolBar1.add(filler3);

        jButtonEjecutar.setText("Ejecutar");
        jButtonEjecutar.setFocusable(false);
        jButtonEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEjecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonEjecutar);
        jToolBar1.add(filler4);

        jButtonFormato.setText("Dar formato");
        jButtonFormato.setFocusable(false);
        jButtonFormato.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFormato.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFormatoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonFormato);

        jMenu4.setText("Proyecto");

        jMenuItemAbrirProyecto.setText("Abrir");
        jMenuItemAbrirProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirProyectoActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemAbrirProyecto);

        jMenuItemCompilar.setText("Compilar");
        jMenuItemCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCompilarActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemCompilar);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Componentes");

        jMenuItemVariables.setText("Variables");
        jMenuItemVariables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVariablesActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemVariables);

        jMenuItemAbrirFiles.setText("Files");
        jMenuItemAbrirFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirFilesActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemAbrirFiles);

        jMenuItemFunciones.setText("Funciones");
        jMenuItemFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuncionesActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemFunciones);

        jMenuItem9.setText("Metadata");
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Codigo");

        jMenuItemInsertarVariable.setText("Insertar variable");
        jMenuItemInsertarVariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertarVariableActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemInsertarVariable);

        jMenuItemInsertIF.setText("Insertar if");
        jMenuItemInsertIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertIFActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemInsertIF);

        jMenuItemInsertar_foreach.setText("Insertar foreach");
        jMenuItemInsertar_foreach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertar_foreachActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemInsertar_foreach);

        jMenuItemInsertIfCommaSeparated.setText("Insertar for comma separated");
        jMenuItemInsertIfCommaSeparated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertIfCommaSeparatedActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemInsertIfCommaSeparated);
        jMenu2.add(jSeparator1);

        jMenuItem1.setText("Definir lenguaje de file(-php-)");
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator2);

        jMenuItemEtiquetasPHP.setText("Remplazar etiquetas php");
        jMenuItemEtiquetasPHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEtiquetasPHPActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemEtiquetasPHP);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Debug");

        jMenuItemVariablesSinValor.setText("Ver variables sin valor");
        jMenuItemVariablesSinValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVariablesSinValorActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemVariablesSinValor);

        jMenuItemUltimoRenderer.setText("Ver ultimo renderer");
        jMenuItemUltimoRenderer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUltimoRendererActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemUltimoRenderer);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAbrirFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirFilesActionPerformed
        // TODO add your handling code here:
        try {
            
            String filename = (new PanelAbrirFile()).showIt();
            
            if(filename != null)
                tabs_mgr.createTab(filename);
            
        }
        catch (UserFriendlyException e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemAbrirFilesActionPerformed

    private void jMenuItemAbrirProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirProyectoActionPerformed
        // TODO add your handling code here:
        nuevoProyecto();
    }//GEN-LAST:event_jMenuItemAbrirProyectoActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        // TODO add your handling code here:
        
        try {
            tabs_mgr.closeSelectedTab();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        
        try {
            tabs_mgr.getSelectedEditorManager().save();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jMenuItemInsertarVariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertarVariableActionPerformed
        // TODO add your handling code here:
        
        try {
            
            String varname = (new PanelSolicitarVariable()).showIt();

            if(varname == null)
                return;
            
            tabs_mgr.getSelectedEditorManager().replaceSelection(Operations.insertVariable(varname));
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemInsertarVariableActionPerformed

    private void jMenuItemInsertar_foreachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertar_foreachActionPerformed
        // TODO add your handling code here:
        
        try {
            
            String varname = (new PanelSolicitarVariable()).showIt();

            if(varname == null)
                return;
            
            String[] fields = Config.getInstance().getVariableIO_newInstance().getVariable(varname).getTipo().getFields();
            
            EditorManager editor = tabs_mgr.getSelectedEditorManager();
            
            String text = editor.getSelectedText();
            if(text == null)
                text = "";
            
            editor.replaceSelection(Operations.insertForeach(varname, fields, text));
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
        
    }//GEN-LAST:event_jMenuItemInsertar_foreachActionPerformed

    private void jButtonFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFormatoActionPerformed
        // TODO add your handling code here:
        try {
            tabs_mgr.getSelectedEditorManager().format();
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
    }//GEN-LAST:event_jButtonFormatoActionPerformed

    private void jMenuItemVariablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVariablesActionPerformed
        // TODO add your handling code here:

        try {
            PanelVariables.getInstance().showIt();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemVariablesActionPerformed

    private void jMenuItemVariablesSinValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVariablesSinValorActionPerformed
        // TODO add your handling code here:
        
        try {
            (new PanelVariablesSinInicializar()).showIt();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
        
    }//GEN-LAST:event_jMenuItemVariablesSinValorActionPerformed

    private void jMenuItemFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFuncionesActionPerformed
        // TODO add your handling code here:
        
        try {
            

            tabs_mgr.createTab(Config.getInstance().getFuncionesFilePath());
            
        }
        catch (UserFriendlyException e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemFuncionesActionPerformed

    private void Ejecutar()
    {
        try {

            String text = tabs_mgr.getSelectedEditorManager().getSelectedText();
            if(text == null || text.isEmpty())
                text = tabs_mgr.getSelectedEditorManager().getText();
            
            PHPFileRendererBuilder builder = new PHPFileRendererBuilder(Config.getPHPFileRenderer_newInstance());
            
            Config.getInstance().getVariableIO_newInstance().getVariables().forEach((t) -> {
                try {
                    builder.addVariable(t);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            
            builder.set_require_once(Config.getInstance().getFuncionesFilePath());
            
            builder.setCode(text);
            
            PHPFileRenderer renderer = builder.getFilerenderer();
            
            try (InputStream input = renderer.run()) {
                (new PanelPreview(input)).showIt();
            }
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }        
    }
    private void jMenuItemUltimoRendererActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUltimoRendererActionPerformed
        // TODO add your handling code here:
        try {
            try (InputStream i = new FileInputStream(Config.PHP_RENDERER_FILE_PATH)) {
                (new PanelPreview(i)).showIt();
            }
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }        
        
        
    }//GEN-LAST:event_jMenuItemUltimoRendererActionPerformed

    private void jButtonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutarActionPerformed
        // TODO add your handling code here:
        Ejecutar();
    }//GEN-LAST:event_jButtonEjecutarActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        // TODO add your handling code here:
        try {
            tabs_mgr.getSelectedEditorManager().getJTextPane().copy();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
        
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jMenuItemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCutActionPerformed
        // TODO add your handling code here:
        try {
            tabs_mgr.getSelectedEditorManager().getJTextPane().cut();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemCutActionPerformed

    private void jMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPasteActionPerformed
        // TODO add your handling code here:
        try {
            tabs_mgr.getSelectedEditorManager().getJTextPane().paste();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
        
    }//GEN-LAST:event_jMenuItemPasteActionPerformed

    private void jMenuItemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCompilarActionPerformed
        // TODO add your handling code here:
        
        try {
            Config.getCompiladorDeAsistente().Compilar();
            
            Dialogos.showMessageDialog("Listo.");
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemCompilarActionPerformed

    private void jMenuItemInsertIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertIFActionPerformed
        // TODO add your handling code here:
        try {
            
            String varname = (new PanelSolicitarVariable()).showIt();

            if(varname == null)
                return;
            
            EditorManager editor = tabs_mgr.getSelectedEditorManager();
            
            String text = editor.getSelectedText();
            if(text == null)
                text = "";
            
            editor.replaceSelection(Operations.insertIF(varname, text));
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemInsertIFActionPerformed

    private void jMenuItemInsertIfCommaSeparatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertIfCommaSeparatedActionPerformed
        // TODO add your handling code here:
        try {
            
            String varname = (new PanelSolicitarVariable()).showIt();

            if(varname == null)
                return;
            
            EditorManager editor = tabs_mgr.getSelectedEditorManager();
            
            
            editor.replaceSelection(Operations.insertFor_CommaSeparated(varname));
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemInsertIfCommaSeparatedActionPerformed

    private void jMenuItemEtiquetasPHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEtiquetasPHPActionPerformed
        // TODO add your handling code here:
        try {
            
            JTextPane editor = tabs_mgr.getSelectedEditorManager().getJTextPane();
            
            String text = editor.getText();
            if(text == null || text.isEmpty())
                return;
            
            editor.setText(Operations.replacePHPtags(Config.getPHPTokenParser_newInstance(), text));
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemEtiquetasPHPActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonEjecutar;
    private javax.swing.JButton jButtonFormato;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemAbrirFiles;
    private javax.swing.JMenuItem jMenuItemAbrirProyecto;
    private javax.swing.JMenuItem jMenuItemCompilar;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemCut;
    private javax.swing.JMenuItem jMenuItemEtiquetasPHP;
    private javax.swing.JMenuItem jMenuItemFunciones;
    private javax.swing.JMenuItem jMenuItemInsertIF;
    private javax.swing.JMenuItem jMenuItemInsertIfCommaSeparated;
    private javax.swing.JMenuItem jMenuItemInsertarVariable;
    private javax.swing.JMenuItem jMenuItemInsertar_foreach;
    private javax.swing.JMenuItem jMenuItemPaste;
    private javax.swing.JMenuItem jMenuItemUltimoRenderer;
    private javax.swing.JMenuItem jMenuItemVariables;
    private javax.swing.JMenuItem jMenuItemVariablesSinValor;
    private javax.swing.JPopupMenu jPopupMenuEditor;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
