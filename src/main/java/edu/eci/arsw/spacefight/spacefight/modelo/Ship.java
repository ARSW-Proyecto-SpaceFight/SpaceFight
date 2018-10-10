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
public class Ship extends Position {

    private int Health;

    public Ship(int Xpos,int Ypos){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        Health=100;

    }

    public void Changehealth(int amount){
        Health+=amount;
    }


    public void shoot(){
        new Shoot(this,Xpos,Ypos);
    }
}
