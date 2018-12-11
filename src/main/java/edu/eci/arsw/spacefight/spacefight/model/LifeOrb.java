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
public class LifeOrb extends Item {


    private int Recovery;
    private static final int orbeSize = 20;

    public LifeOrb(int Xpos,int Ypos,int Recovery,int idLifeOrb){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.Recovery=Recovery;
        this.idItem=idLifeOrb;
    }

    @Override
    public void Impact(Ship s){
        if(s.getHealth()>0 && s.getHealth()<100){
            s.setHealth(s.getHealth()+Recovery);
            if(s.getHealth()>100){
                s.setHealth(100);
            }
        }

    }

    public int getIdLifeOrb(){return idItem;}

    public int getLifeOrbSize(){return orbeSize;}

    public boolean orbecolide(Ship s){
        return s.getX()-orbeSize<Xpos && Xpos<s.getX()+s.getShipSize() && s.getY()-orbeSize<Ypos && Ypos<s.getY()+s.getShipSize();
    }




}
