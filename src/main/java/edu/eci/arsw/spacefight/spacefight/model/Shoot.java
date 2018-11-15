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
public class Shoot {
    private Ship Shooter;
    private int Damage;
    public int Xpos;
    public int Ypos;

    Shoot(Ship s,int Xpos,int Ypos){
        this.Xpos =Xpos;
        this.Ypos=Ypos;
        Shooter=s;

    }

    public int getXpos(){
        return Xpos;
    }

    public void setXpos(int xpos){
        Xpos = xpos;
    }


    public int getYpos(){
        return Ypos;
    }

    public void setYpos(int ypos){
        Ypos = ypos;
    }

    public Ship getShooter() {
        return Shooter;
    }
}
