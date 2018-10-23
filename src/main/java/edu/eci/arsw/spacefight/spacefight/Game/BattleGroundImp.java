/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.model.*;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BattleGroundImp implements BattleGroundGame {
    private ArrayList<Shoot> disparos;
    private ArrayList<Item> items;
    private Team team1;
    private Team team2;


    @Override
    public void insertPlayerToTeam(Ship ship, int team) throws BattleGroundGameException {
        if(team == 1){
            try {
                team1.addPlayer(ship);
            } catch (ModelException e) {
                throw new BattleGroundGameException("The team could not accept the player");
            }
        }
        else if(team == 2){
            try {
                team2.addPlayer(ship);
            } catch (ModelException e) {
                throw new BattleGroundGameException("The team could not accept the player");
            }
        }
        else{
            throw new BattleGroundGameException("The selected team is incorrect");
        }
    }

    @Override
    public void removePlayerFromTeam(Ship ship, int team) throws BattleGroundGameException {
        if(team == 1){
            try {
                team1.removePlayer(ship.getUsername());
            } catch (ModelException e) {
                throw new BattleGroundGameException("The team could not accept the player");
            }
        }
        else if(team == 2){
            try {
                team2.removePlayer(ship.getUsername());
            } catch (ModelException e) {
                throw new BattleGroundGameException("The team could not accept the player");
            }
        }
        else{
            throw new BattleGroundGameException("The selected team is incorrect");
        }
    }
}
