/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import javax.swing.JDialog;

/**
 *
 * @author ado
 */
public class PanelModal <T> extends javax.swing.JPanel
{
    protected T result = null;
    protected final String titulo;
    protected JDialog configModal = null;
    
    public PanelModal(String titulo) {
        this.titulo = titulo;
    }
    
    public T showIt()
    {
        configModal = Dialogos.showPanelDialogModal(this, null, titulo);
        
        return result;
    }
    
    public void close()
    {
        Dialogos.closeContainingJDialog(this.getParent());
    }
}
