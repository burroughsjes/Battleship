/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jbjb2
 */
public class Ship {
    private int length;
    private String title;
    
    public Ship (String title, int length) {
        this.title = title;
        this.length = length;
    }
    
    public int getLength() {
        return length;
    }
    
    public String getTitle() {
        return title;
    }
}
