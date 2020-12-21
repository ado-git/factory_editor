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
public class Itemlist {
    private final ArrayList<ItemBase> items = new ArrayList<>();
    
    public void addItemBase(ItemBase i)
    {
        items.add(i);
    }

    public ArrayList<ItemBase> getItems() {
        return items;
    }
}
