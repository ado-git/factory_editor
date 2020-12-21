/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.awt.Container;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;

/**
 *
 * @author ado
 */
public class Dialogos {
    private Dialogos(){}
    
    public static void showMessageDialog(String mensaje)
    {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    /**
     * Muestra un si o no y devuelve true si el usuario selecciono si.
     */
    public static boolean showConfirmationDialog(String mensaje)
    {
        return JOptionPane.showConfirmDialog(null, mensaje, "Consulta", YES_NO_OPTION) == 0;
    }
    
    public static JDialog showPanelDialogModal(JPanel panel ,Frame owner, String titulo)
    {
        JDialog configModal = new JDialog(owner, titulo, true);
        configModal.getContentPane().add(panel);
        configModal.pack();
        configModal.setVisible(true);
        
        return configModal;
    }
    public static void closeContainingJDialog(Container parentContainer)
    {
        while(true)
        {
            if(parentContainer instanceof JDialog)
            {
                JDialog d = (JDialog)parentContainer;
                d.dispose();
                break;
            }
            
            if(parentContainer.getParent() != null)
            {
                parentContainer = parentContainer.getParent();
            }
            else
            {
                break;
            }
        }        
    }
    
}
