/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles.variables;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import utiles.Dialogos;
import variables.Variable;

/**
 *
 * @author ado
 */
public class PanelVariables extends JPanel {
    
    private boolean isShown = false;
    private JDialog configModal = null;
    
    
    private static PanelVariables INSTANCE = null;
    
    public static PanelVariables getInstance() throws Exception
    {
        if(INSTANCE != null)
            return INSTANCE;
        
        INSTANCE = new PanelVariables();
        return INSTANCE;
    }

    /**
     * Creates new form PanelVariables
     */
    private PanelVariables() throws ParserConfigurationException, SAXException, IOException, Exception{
        initComponents();
        
        populateList();
    }
    public void showIt()
    {
        if(isShown)
            return;
        
        JFrame f = null;
        
        configModal = new JDialog(f, "Variables", false);
        
        configModal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                isShown = false;
                INSTANCE = null;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                isShown = false;
                INSTANCE = null;
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        configModal.setAlwaysOnTop(true);
        configModal.getContentPane().add(this);
        configModal.pack();
        configModal.setVisible(true);
        
        isShown = true;
    }
    
    public void close()
    {
        Dialogos.closeContainingJDialog(this);
    }
    
    
    private void populateList() throws ParserConfigurationException, SAXException, IOException, Exception
    {
        ArrayList<Variable> vars = config.Config.getInstance().getVariableIO_newInstance().getVariables();        

        vars.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });

        String[] list_data = new String[vars.size()];
        
        for (int i = 0; i < vars.size(); i++) {
            list_data[i] = vars.get(i).getName();
        }
        
        jListVariables.setListData(list_data);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonAñadir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListVariables = new javax.swing.JList<>();

        jButtonAñadir.setText("Añadir");
        jButtonAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAñadirActionPerformed(evt);
            }
        });

        jListVariables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListVariablesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListVariables);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 158, Short.MAX_VALUE)
                .addComponent(jButtonAñadir))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButtonAñadir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jListVariablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListVariablesMouseClicked
        // TODO add your handling code here:
        String val = jListVariables.getSelectedValue();
        if(val == null || val.isEmpty())
            return;
        
        configModal.setAlwaysOnTop(false);
       
        try {
            (new PanelVariableEditor(val)).showIt();
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        finally{
            configModal.setAlwaysOnTop(true);
        }
        
    }//GEN-LAST:event_jListVariablesMouseClicked

    private void jButtonAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAñadirActionPerformed
        // TODO add your handling code here:

        try {
            
            (new PanelVariableEditor(null)).showIt();
            populateList();
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }

    }//GEN-LAST:event_jButtonAñadirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAñadir;
    private javax.swing.JList<String> jListVariables;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
