/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

/**
 *
 * @author ado
 */
public class EditorPositions {
    private final int caret_pos;
    private final int v_scroll_post;
    private final int h_scroll_post;

    public EditorPositions(int caret_pos, int v_scroll_post, int h_scroll_post) {
        this.caret_pos = caret_pos;
        this.v_scroll_post = v_scroll_post;
        this.h_scroll_post = h_scroll_post;
    }

    public int getCaret_pos() {
        return caret_pos;
    }

    public int getV_scroll_post() {
        return v_scroll_post;
    }

    public int getH_scroll_post() {
        return h_scroll_post;
    }
    
    
    
}
