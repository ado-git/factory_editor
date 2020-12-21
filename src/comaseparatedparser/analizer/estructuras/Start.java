/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comaseparatedparser.analizer.estructuras;

import java.util.ArrayList;

/**
 *
 * @author ado
 */
public class Start {
    private final ArrayList<Itemlist> itemlist = new ArrayList<>();
    private int index = -1;

    public Start() {
        addNewItemlist();
    }
    
    public Itemlist getCurrentItemList()
    {
        return itemlist.get(index);
    }
    
    public final void addNewItemlist()
    {
        itemlist.add(new Itemlist());
        index++;
    }

    public ArrayList<Itemlist> getItemlist() {
        return itemlist;
    }
}
