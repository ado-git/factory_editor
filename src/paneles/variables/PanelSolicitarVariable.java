/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles.variables;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import utiles.Dialogos;
import utiles.PanelModal;
import variables.Variable;

/**
 *
 * @author ado
 */
public class PanelSolicitarVariable extends PanelModal<String> {

    /**
     * Creates new form PanelVariables
     */
    public PanelSolicitarVariable() throws ParserConfigurationException, SAXException, IOException, Exception{
        super("Variables");
        initComponents();
        
        populateList();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jListVariables = new javax.swing.JList<>();

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
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jListVariablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListVariablesMouseClicked
        // TODO add your handling code here:
        String val = jListVariables.getSelectedValue();
        if(val == null || val.isEmpty())
            return;
       
        try {
            result = val;
            
            close();
            
        } catch (Exception e) {
            Dialogos.showMessageDialog(e.getMessage());
        }
        
    }//GEN-LAST:event_jListVariablesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jListVariables;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}