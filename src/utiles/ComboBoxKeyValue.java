/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

/**
 *
 * @author ado
 */
public class ComboBoxKeyValue {
    private final String key;
    private final Integer value;

    public ComboBoxKeyValue(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key;
    }
}
