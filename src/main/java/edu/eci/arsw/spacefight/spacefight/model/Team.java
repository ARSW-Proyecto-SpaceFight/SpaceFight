/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author User
 */
public class Team {

    private int Score;
    private int number;
    private ConcurrentHashMap<String,Ship> ships = new ConcurrentHashMap<>();

    public Team(int number){
        Score=0;
        this.number=number;
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
    public boolean isInTeam(String username){
        if(ships.containsKey(username)){
            return true;
        }
        else{
            return false;
        }

    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setShips(ConcurrentHashMap<String, Ship> ships) {
        this.ships = ships;
    }

    public ArrayList<Ship> getShips(){
        return new ArrayList<Ship>(ships.values());
    }

}
