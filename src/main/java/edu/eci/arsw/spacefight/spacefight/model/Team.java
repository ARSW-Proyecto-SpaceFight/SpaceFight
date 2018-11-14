/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;


import java.util.HashMap;

/**
 *
 * @author User
 */
public class Team {

    private int Score;
    private HashMap<String,Ship> ships;

    public Team(){
        Score=0;
    }
    

    public void addPlayer(Ship ship) throws ModelException {
        if(ships.size()>6){
            throw new ModelException("This Team is Full");
        }
        else {
            ships.put(ship.getUsername(), ship);
        }
    }

    public void removePlayer(String username) throws ModelException {
        if(!ships.containsKey(username)){
            throw new ModelException("This team does not contain this player");
        }
        else {
            ships.remove(username);
        }
    }
    public Ship getShip(String username)throws ModelException{
        if(!ships.containsKey(username)){
            throw new ModelException("That ship does not exist");
        }
        else{
            return ships.get(username);
        }

    }
}
