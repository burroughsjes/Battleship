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
    
    // creates a ship with a title and length
    public Ship (String title, int length) {
        this.title = title;
        this.length = length;
    }
    
    // returns an int value representing the amount of spaces the ship occupies
    public int getLength() {
        return length;
    }
    
    // returns a String representing the ship's name
    public String getTitle() {
        return title;
    }
}
