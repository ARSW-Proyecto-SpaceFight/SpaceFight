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
public class Meteorite extends Item {

    private int Damage;

    public Meteorite(int Xpos,int Ypos,int Damage){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.Damage=-Damage;

    }
    @Override
    public void Impact(Ship s){
        s.Changehealth(Damage);
    }

}
