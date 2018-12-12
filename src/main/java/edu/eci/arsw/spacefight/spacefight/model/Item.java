/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;

/**
 *
 * @author User
 */
public class Item {

    protected int idItem;
    protected int Xpos;
    protected int Ypos;

    /**
     * get identification of item
     * @return value of item
     */
    public int getIdItem(){
        return idItem;
    }

    /**
     * get x position of item
     * @return value of x
     */
    public int getXpos() {
        return Xpos;
    }

    /**
     * set x position of item
     * @param xpos new value 
     */
    public void setXpos(int xpos) {
        Xpos = xpos;
    }

    /**
     * get y position of item
     * @return value of y
     */
    public int getYpos() {
        return Ypos;
    }

    /**
     * set y position of item
     * @param ypos new value
     */
    public void setYpos(int ypos) {
        Ypos = ypos;
    }

    /**
     * verify if ship impact with item
     * @param s ship
     */
    protected void Impact(Ship s){

    }


    
}
