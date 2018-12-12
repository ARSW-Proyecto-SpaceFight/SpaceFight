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
    private static final int meteoriteSize = 25;

    /**
     * object creator
     * @param Xpos position in x
     * @param Ypos position in y
     * @param Damage damage for a ship
     * @param id number to identify life orb
     */
    public Meteorite(int Xpos,int Ypos,int Damage, int id){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.Damage=Damage;
        this.idItem = id;

    }
    
    /**
     * reduce the health of a ship
     * @param s ship
     */
    @Override
    public void Impact(Ship s){
        if(s.getHealth()>0 && s.getHealth()<=100){
            s.setHealth(s.getHealth()-Damage);
            if(s.getHealth()<0){
                s.setHealth(0);
            }
        }
    }

    /**
     * get size of meteorite
     * @return size 
     */
    public int getMeteoriteSize(){return meteoriteSize;}

    /**
     * get identification of item
     * @return value that identifies the item
     */
    public int getIdMeteorite(){return idItem;}
    //ship.getX()-Shoot.shootSize<shoot.getXpos() && shoot.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Shoot.shootSize<shoot.getYpos() && shoot.getYpos()<ship.getY()+Ship.shipSize
    
    /**
     * verify the impact of a ship with a meteorite
     * @param s ship
     * @return boolean
     */
    public boolean colide(Ship s){ return s.getX()-meteoriteSize<Xpos && Xpos<s.getX()+s.getShipSize() && s.getY()-meteoriteSize<Ypos && Ypos<s.getY()+s.getShipSize(); }


}
