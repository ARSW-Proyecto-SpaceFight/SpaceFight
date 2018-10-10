/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.modelo;

/**
 *
 * @author 2125275
 */
public class LifeOrb extends Item {

    private int Health;
    public LifeOrb(int Xpos,int Ypos,int Health){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.Health=Health;
    }

    @Override
    public void Impact(Ship s){s.Changehealth(Health);}

}
