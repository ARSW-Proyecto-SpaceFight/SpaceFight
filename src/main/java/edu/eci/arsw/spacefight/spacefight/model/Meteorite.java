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
public class Meteorite extends Item {

    private int Damage;
    private static final int meteoriteSize = 30;

    public Meteorite(int Xpos,int Ypos,int Damage, int id){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.Damage=Damage;
        this.idItem = id;

    }
    @Override
    public void Impact(Ship s){
        s.setHealth(s.getHealth()-Damage);
    }

    public int getMeteoriteSize(){return meteoriteSize;}

    public int getIdMeteorite(){return idItem;}
    //ship.getX()-Shoot.shootSize<shoot.getXpos() && shoot.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Shoot.shootSize<shoot.getYpos() && shoot.getYpos()<ship.getY()+Ship.shipSize
    public boolean colide(Ship s){ return s.getX()-meteoriteSize<Xpos && Xpos<s.getX()+s.getShipSize() && s.getY()-meteoriteSize<Ypos && Ypos<s.getY()+s.getShipSize(); }


}
