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
public class Shoot extends Position {
    private Ship Shooter;
    private int Damage;
    Shoot(Ship s,int Xpos,int Ypos){
        this.Xpos =Xpos;
        this.Ypos=Ypos;
        Shooter=s;

    }

    public Ship getShooter() {
        return Shooter;
    }
}
