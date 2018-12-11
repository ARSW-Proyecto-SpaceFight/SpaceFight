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
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
  
    public float getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getShipSize() {
        return shipSize;
    }     

    public char getDirection() {
        return direction;
    }

    public void damage(int damage){
        this.health-=damage;

    }

    public void dropFlag(){
        carryingFlag.setCaptured(false);
        carryingFlag.bounce();
        carryingFlag=null;
    }
    
    @Override
    public int compareTo(Ship o) {
        if(o.getUsername().equals(this.username)){
            return 0;
        }else{
            return 1;
        }        
    }

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
    
    public synchronized void isOnline(){
        online = 100;
    }
    
    public synchronized boolean notOnline(){
        online -= 1;
        if(online <= 0){
            return true;
        }else{
            return false;
        }
    }
    
    public int getTeam(){
        return team;
    }
    
    public void setTeam(int team){
        this.team = team;
    }

    public static int getVelocity() {
        return velocity;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public static int getBOUNDX() {
        return BOUNDX;
    }

    public static int getBOUNDY() {
        return BOUNDY;
    }

    public Flag getCarryingFlag() {
        return carryingFlag;
    }

    public void setCarryingFlag(Flag carryingFlag) {
        this.carryingFlag = carryingFlag;
    }
}
