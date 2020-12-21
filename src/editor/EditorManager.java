/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import php.PHPTokenParser;

/**
 *
 * @author ado
 */
public class EditorManager
{
    private final PHPTokenParser runner;
    private final String file_path;
    private final JPanel panel;
    private Lenguajes lang = null;
    private final JPopupMenu popup_menu;

    public EditorManager(PHPTokenParser runner, String file_path, JPanel panel, JPopupMenu popup_menu) {
        this.runner = runner;
        this.file_path = file_path;
        this.panel = panel;
        this.popup_menu = popup_menu;
        
        StyleContext sc = StyleContext.getDefaultStyleContext();
        getJTextPane().setCharacterAttributes(sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.FontSize, 14), true);
        

        getJTextPane().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == 3)
                    popup_menu.show(getJTextPane(), evt.getX(), evt.getY());
            }
        });
    }

    
    /**
     * Devuelve el JEditorPane hijo.
     */
    public final JTextPane getJTextPane()
    {
        JScrollPane scroll = (JScrollPane)panel.getComponent(0);
        
        JViewport port = (JViewport)scroll.getComponent(0);
        
        return (JTextPane)port.getComponent(0);
    }
    /**
     * Devuelve el JScrollPane
     */
    private JScrollPane getJScrollPane()
    {
        return (JScrollPane)panel.getComponent(0);
    }
    
    /**
     * Devuelve las posiciones actuales del caret y los scrollbars
     */
    private EditorPositions getCurrentPositions()
    {
        JScrollPane sp = getJScrollPane();
        JTextPane editor = getJTextPane();
        
        return new EditorPositions(editor.getCaretPosition(), sp.getVerticalScrollBar().getValue(), sp.getHorizontalScrollBar().getValue());
    }
    
    private void setCurrentPositions(EditorPositions p)
    {
        JScrollPane sp = getJScrollPane();
        JTextPane editor = getJTextPane();
        
        editor.setCaretPosition(p.getCaret_pos());
//        sp.getVerticalScrollBar().setValue(p.getV_scroll_post());
//        sp.getHorizontalScrollBar().setValue(p.getH_scroll_post());
    }

    /**
     * Devuelve el panel padre de el JEditorPane
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Devuelve el lenguaje que se ha definido para el contenido del JEditorPane
     */
    public Lenguajes getLanguaje() {
        return lang;
    }

    /**
     * Especifica el lenguaje para el contenido del JEditorPane
     */
    public void setLanguage(Lenguajes lang)
    {
        this.lang = lang;
    }
    
    /**
     * Abre el file y carga el editor de texto.
     * @throws java.io.IOException
     */
    public void load() throws IOException
    {
        FileReader f_reader = new FileReader(file_path);
        
        getJTextPane().read(f_reader, null);
        
        f_reader.close();
    }
    /**
     * Guarda al file desde el JTextPane
     * @throws java.io.IOException
     */
    public void save() throws IOException
    {
        write(file_path);
    }
    
    /**
     * Escribe el contenido del JTextPane al file especificado.
     */
    private void write(String path) throws IOException
    {
        FileWriter f_writer = new FileWriter(path, false);
        
        getJTextPane().write(f_writer);
        
        f_writer.close();
    }
    
    public String getText()
    {
        return getJTextPane().getText();
    }
    
    /**
     * Lo mismo que getJTextPane.getSelectedText()
     */
    public String getSelectedText()
    {
        return getJTextPane().getSelectedText();
    }
    /**
     * Lo mismo que getJTextPane.replaceSelection(String content)
     */
    
    public void replaceSelection(String content)
    {
        getJTextPane().replaceSelection(content);
    }
    
    public void format() throws IOException, Exception
    {
        runner.writeDataFile(getJTextPane().getText());
        
        JTextPane editor = getJTextPane();
        
        EditorPositions current_pos = getCurrentPositions();
        
        
        editor.setText("");
        
        runner.parse((t) -> {

            try {
                AttributeManager attrib_mgr = new AttributeManager(t);

                int len = editor.getDocument().getLength();

                editor.getDocument().insertString(len, t.getToken(), attrib_mgr.getAttributeSet());
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        setCurrentPositions(current_pos);
    }
}
