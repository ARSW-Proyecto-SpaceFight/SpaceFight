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
    public static final int VEL = 25;
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

    /**
     * get x position of flag
     * @return value of x
     */
    public int getXpos() {
        return Xpos;
    }

    /**
     * set x position of flag
     * @param xpos new value 
     */
    public void setXpos(int xpos) {
        Xpos = xpos;
    }


    /**
     * get y position of flag
     * @return value of y
     */
    public int getYpos() {
        return Ypos;
    }

    /**
     * set y position of flag
     * @param ypos new value
     */
    public void setYpos(int ypos) {
        Ypos = ypos;
    }

    /**
     * get the ship that shoot
     * @return ship
     */
    public Ship getShooter() {
        return Shooter;
    }

    /**
     * set the ship that shoot
     * @param shooter new ship
     */
    public void setShooter(Ship shooter) {
        Shooter = shooter;
    }

    /**
     * get shoot value of damage
     * @return actual damage value
     */
    public int getDamage() {
        return Damage;
    }

    /**
     * get damage shoot value
     * @param damage new value
     */
    public void setDamage(int damage) {
        Damage = damage;
    }

    /**
     * direction for movement
     * @return direction
     */
    public char getDirection() {
        return direction;
    }

    /**
     * set direction for movement
     * @param direction new value
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }

    /**
     * get size of shoot 
     * @return size
     */
    public static int getShootSize() {return shootSize; }


    /**
     * move
     */
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

    /**
     * get the number that identifies the object
     * @return value that identifies
     */
    public int getId() {
        return id;
    }

    /**
     * set the number that identifies the object
     * @param id new value
     */
    public void setId(int id) {
        this.id = id;
    }
}
