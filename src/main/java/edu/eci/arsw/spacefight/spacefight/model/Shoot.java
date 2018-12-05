/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;

import edu.eci.arsw.spacefight.spacefight.restcontrollers.SpaceFightMessageController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 *
 * @author User
 */
public class Shoot {



    private Ship Shooter;
    private int Damage;
    public int Xpos;
    public int Ypos;
    private char direction;
    public static final int VEL = 10;
    private int id;
    public static final int shootSize = 20;

    public Shoot(Ship s,int Xpos,int Ypos, char dir){
        this.Xpos =Xpos;
        this.Ypos=Ypos;
        Shooter=s;
        direction=dir;
        Random rn = new Random();
        id= rn.nextInt();
        this.Damage=10;
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

    public void setShooter(Ship shooter) {
        Shooter = shooter;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public static int getShootSize() {return shootSize; }


    public void move(){
        if(direction=='L'){
            Xpos-=VEL;
        }
        else if (direction=='U'){
            Ypos-=VEL;
        }
        else if(direction=='R'){
            Xpos+=VEL;
        }
        else if(direction=='D'){
            Ypos+=VEL;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
