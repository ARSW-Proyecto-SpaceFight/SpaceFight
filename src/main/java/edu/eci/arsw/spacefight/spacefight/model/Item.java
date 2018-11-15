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

    public int getIdItem(){
        return idItem;
    }

    protected int getXpos(){
        return Xpos;
    }

    protected int getYpos(){
        return Ypos;
    }

    protected void Impact(Ship s){

    }
    
}
