/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 *
 * @author User
 */
public class Ship implements Comparable<Ship> {
    
    private int x, y;
    private float health = 100;
    public static final int velocity = 10;
    public static final int shipSize = 50;
    private char direction = 'U';
    private int online = 100;
    private String username;
    private int team;
    //private Position position;
    public static final int BOUNDX = 830;
    public static final int BOUNDY = 470;
    private Flag carryingFlag;
    
    public Ship(){


    }

    public Ship(int x, int y, String username, int team) {
        this.x = x;
        this.y = y;
        this.username = username;
        this.team = team;
        this.carryingFlag=null;
    }
    
    
    /**
     * get player name
     * @return name of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the name of the player
     * @param username new value
     */
    public void setUsername(String username) {
        this.username = username;
    }
  
    /**
     * get x position of ship
     * @return value of x
     */
    public float getX() {
        return this.x;
    }

    /**
     * set x position of ship
     * @param x new value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * get y position of ship
     * @return value of y
     */
    public float getY() {
        return this.y;
    }

    /**
     * set y position of ship
     * @param y new value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * get the health of a ship
     * @return actual health
     */
    public float getHealth() {
        return health;
    }

    /**
     * set actual heath
     * @param health new value
     */
    public void setHealth(float health) {
        this.health = health;
    }

    /**
     * get size of ship
     * @return size
     */
    public int getShipSize() {
        return shipSize;
    }     

    /**
     * get direction of ship about its actual position
     * @return direction
     */
    public char getDirection() {
        return direction;
    }

    /**
     * change health for impact with some objects
     * @param damage value of damage
     */
    public void damage(int damage){
        this.health-=damage;

    }

    /**
     * drop the flag if the ship die or if the ship is on the base
     */
    public void dropFlag(){
        carryingFlag.setCaptured(false);
        carryingFlag.bounce();
        carryingFlag=null;
    }
    
    /**
     * compare username
     * @param o ship
     * @return 0 if is equals,  if not
     */
    @Override
    public int compareTo(Ship o) {
        if(o.getUsername().equals(this.username)){
            return 0;
        }else{
            return 1;
        }        
    }

    /**
     * move the ship in specific direction
     * @param key direction by keyboard
     */
    public void move(int key){
        switch (key) {
            case 37:
                if(x>0) x-=velocity;
                direction = 'L';
                break;
            case 38:
                if(y>0) y-=velocity;
                direction = 'U';
                break;
            case 39:
                if(x<830) x+=velocity;
                direction = 'R';
                break;
            case 40:
                if(y<470) y+=velocity;
                direction = 'D';
                break;
            default:
                break;
        }
    }
    
    /**
     * if the player is on the room playing assign 100
     */
    public synchronized void isOnline(){
        online = 100;
    }
    
    /**
     * if the player is on the room but not is playing change the value
     * @return new value
     */
    public synchronized boolean notOnline(){
        online -= 1;
        if(online <= 0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * get the team of the ship
     * @return number team
     */
    public int getTeam(){
        return team;
    }
    
    /**
     * set the team of the ship
     * @param team new value
     */
    public void setTeam(int team){
        this.team = team;
    }

    /**
     * get the value of velocity of movement
     * @return actual value 
     */
    public static int getVelocity() {
        return velocity;
    }

    /**
     * change the direction of movement ship
     * @param direction new value
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }

    /**
     * get value of connection
     * @return actual value online
     */
    public int getOnline() {
        return online;
    }

    /**
     * set value of connection
     * @param online new value
     */
    public void setOnline(int online) {
        this.online = online;
    }

    /**
     * get bound on x of ship
     * @return actual value
     */
    public static int getBOUNDX() {
        return BOUNDX;
    }

    /**
     * get bound on y of ship
     * @return actual value
     */
    public static int getBOUNDY() {
        return BOUNDY;
    }

    /**
     * get the flag that carry a ship if have it
     * @return flag
     */
    public Flag getCarryingFlag() {
        return carryingFlag;
    }

    /**
     * set the flag
     * @param carryingFlag flag that could be carried
     */
    public void setCarryingFlag(Flag carryingFlag) {
        this.carryingFlag = carryingFlag;
    }
}
