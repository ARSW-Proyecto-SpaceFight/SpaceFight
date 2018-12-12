/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;

import java.util.Random;

/**
 *
 * @author User
 */
public class Flag {

    //Team team;
    int id;
    Boolean captured;
    public int Xpos;
    public int Ypos;
    public static final int size=30;

    /**
     * object creator
     * @param Xpos position in x
     * @param Ypos position in y
     * @param team owner of flag
     */
    public Flag(int Xpos,int Ypos,Team team){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        //this.team=team;
        id=team.getNumber();
        captured=false;
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

    /*public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/

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

    /**
     * boolean if a ship capture or not the flag
     * @return boolean captured
     */
    public Boolean getCaptured() {
        return captured;
    }

    /**
     * change the value of captured
     * @param captured true or false if a ship have or not the flag
     */
    public void setCaptured(Boolean captured) {
        this.captured = captured;
    }

    /**
     * allows the game that the flag move at the same velocity of a ship
     * @param key of the keyboard
     * @param velocity value of velocity
     */
    public void move(int key,int velocity){
        switch (key) {
            case 37:
                if(Xpos>0) Xpos-=velocity;
                break;
            case 38:
                if(Ypos>0) Ypos-=velocity;
                break;
            case 39:
                if(Xpos<830) Xpos+=velocity;
                break;
            case 40:
                if(Ypos<470) Ypos+=velocity;
                break;
            default:
                break;
        }
    }

    /**
     * get size of flag 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * bounce
     */
    public void bounce() {
        Random rn = new Random();
        int ran = rn.nextInt(100)+60;
        Xpos+=ran;
        Ypos+=ran;
        checkBounds();
    }

    /**
     * check the bounds
     */
    private void checkBounds(){
        if(Xpos<0){ Xpos=0;}

        if(Ypos<0){ Ypos=0;}

        if(Xpos>830){ Xpos=830;}

        if(Ypos>470){ Ypos=470;}
    }
}
