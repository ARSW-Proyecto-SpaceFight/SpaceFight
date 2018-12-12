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
    private Base base;
    private ConcurrentHashMap<String,Ship> ships = new ConcurrentHashMap<>();

    /**
     * create a team with a number
     * @param number identify the team
     */
    public Team(int number){
        Score=0;
        this.number=number;
    }
    

    /**
     * add player to a team
     * @param ship new player
     * @throws ModelException 
     */
    public void addPlayer(Ship ship) throws ModelException {
        if(ships.size()>6){
            throw new ModelException("This Team is Full");
        }
        else {
            ships.put(ship.getUsername(), ship);
        }
    }

    /**
     * remove player of a team
     * @param username player name
     * @throws ModelException 
     */
    public void removePlayer(String username) throws ModelException {
        if(!ships.containsKey(username)){
            throw new ModelException("This team does not contain this player");
        }
        else {
            ships.remove(username);
        }
    }
    
    /**
     * get ship of player by username
     * @param username player name
     * @return ship
     * @throws ModelException 
     */
    public Ship getShip(String username)throws ModelException{
        if(!ships.containsKey(username)){
            throw new ModelException("That ship does not exist");
        }
        else{
            return ships.get(username);
        }


    }
    
    /**
     * look if a player is on a team
     * @param username player name
     * @return boolean
     */
    public boolean isInTeam(String username){
        if(ships.containsKey(username)){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * get score of a team
     * @return actual score
     */
    public int getScore() {
        return Score;
    }

    /**
     * set actual score
     * @param score new value
     */
    public void setScore(int score) {
        Score = score;
    }

    /**
     * get the team identification
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * set the team identification
     * @param number new value
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * set the ships that conform the team
     * @param ships  ships of the team
     */
    public void setShips(ConcurrentHashMap<String, Ship> ships) {
        this.ships = ships;
    }

    /**
     * get the ships of the team
     * @return list of ships
     */
    public ArrayList<Ship> getShips(){
        return new ArrayList<Ship>(ships.values());
    }

    /**
     * get the base of the team
     * @return base
     */
    public Base getBase() {
        return base;
    }

    /**
     * set the base of the team
     * @param base new base
     */
    public void setBase(Base base) {
        this.base = base;
    }
    
    /**
     * sum a point to he score
     */
    public void addPoint(){
        Score++;
    }
}
