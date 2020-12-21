/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabs_management;

import editor.EditorManager;
import utiles.UserFriendlyException;
import java.io.File;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import php.PHPTokenParser;

/**
 *Controla los files abiertos y manda a abrir o cerrar un file en los tabs
 * 
 * @author ado
 */
public class TabsManager {
    private final HashMap<String, EditorManager> files_hash = new HashMap();
    private final JTabbedPane jTabbedPane1;
    private final PHPTokenParser token_parser;
    private final JPopupMenu popup_menu;

    public TabsManager(JTabbedPane jTabbedPane1, PHPTokenParser token_parser, JPopupMenu popup_menu) {
        this.jTabbedPane1 = jTabbedPane1;
        this.token_parser = token_parser;
        this.popup_menu = popup_menu;
    }


    
    /**
     * Convierte un path en un string desambiguado para el hash.
     */
    private String getCanonicalPath(String file_path) throws Exception
    {
        return new File(file_path).getCanonicalPath();
    }

    /**
     * Crea un JPanel con scrollpanel y editorpanel.
     */
    private JPanel createJPanel()
    {
        javax.swing.JPanel jPanel1;
        javax.swing.JScrollPane jScrollPane2;
        javax.swing.JTextPane jTextPane2;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();

        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        return jPanel1;
    }
    public void createTab(String file_path) throws Exception //hay que mejorar estas excepciones
    {
        file_path = getCanonicalPath(file_path);
        if(files_hash.containsKey(file_path))
            throw new UserFriendlyException("Este file ya esta abierto");
        
        JPanel p = createJPanel();
        
        EditorManager editor_mgr = new EditorManager(token_parser, file_path, p, popup_menu);
        
        editor_mgr.load();
        
        files_hash.put(file_path, editor_mgr);
        
        jTabbedPane1.addTab(new File(file_path).getName(), p);
        
        jTabbedPane1.setSelectedComponent(p);
        
    }
    /**
     * Busca la key correspondiente al EditorManager que contiene a este JPanel.
     */
    private String findKey(JPanel p)
    {
        for(String key : files_hash.keySet())
        {   
            if(files_hash.get(key).getPanel().equals(p))
            {
                return key;
            }
        }
        return null;
    }
    /**
     * Busca el EditorManager que contiene a este JPanel.
     */
    public EditorManager findEditorManager(JPanel p)
    {
        String key = findKey(p);
        if(key == null)
            return null;
        
        return files_hash.get(key);
    }
    /**
     * Cierra todos los tabs y vacia todos los registros dentro del TabManager
     */
    public void closeAll()
    {
        files_hash.clear();
        jTabbedPane1.removeAll();
    }
    public void closeSelectedTab() throws Exception
    {
        int index = jTabbedPane1.getSelectedIndex();
        if(index < 0)
            throw new Exception("No existe una pestaña activa");
        
        JPanel p = (JPanel)jTabbedPane1.getComponentAt(index);
        
        String file_path = findKey(p);
        if(file_path == null)
            assert false : "tab sin file correspondiente";
        
        files_hash.remove(file_path);
        jTabbedPane1.remove(index);
    }
    /**
     * Devuelve el JPanel asociado con el tab activo.
     */
    private JPanel getSelectedJPanel() throws Exception
    {
        return (JPanel)jTabbedPane1.getSelectedComponent();
    }
    public EditorManager getSelectedEditorManager() throws Exception
    {
        return findEditorManager(getSelectedJPanel());
    }
}
