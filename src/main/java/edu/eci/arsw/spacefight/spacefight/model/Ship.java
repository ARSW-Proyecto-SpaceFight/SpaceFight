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

    private int id;
    private int x, y;
    private float health;
    public static final int velocity = 10;
    private static final int shipSize = 50;
    private char direction = 'U';
    //private Position position;
    
    public Ship(){
    }
    
    public Ship(int id){
        this.id = id;
        health=100;
    }

    public Ship(int id, int x, int y, Position position) {
        this.id = id;
        this.x = x;
        this.y = y;
        //this.position = position;
        health=100;

    }
    
    public Ship(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        health=100;
    }

    public int getId() {
        return this.id;
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

   // public Position getPosition() {
       // return position;
    //}

    //public void setPosition(Position position) {
        //this.position = position;
    //}

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
    
    @Override
    public int compareTo(Ship o) {
        return 1;
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
                x+=velocity;
                direction = 'R';
                break;
            case 40:
                y+=velocity;
                direction = 'D';
                break;
            default:
                break;
        }
    }
}
