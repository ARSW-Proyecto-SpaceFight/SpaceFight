/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.restcontrollers;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author David Rodriguez
 */
@Controller
public class SpaceFightMessageController {
    
    @Autowired
    SpaceFightRESTController spc;
    
    @Autowired
    SimpMessagingTemplate msgt;
    
    @MessageMapping("/move.{room}")
    public void move(Movement movement, @DestinationVariable String room) throws BattleGroundGameException{       
        spc.movePlayer(Integer.parseInt(room), movement.getUsername(), movement.getKey(), movement.getTeam());        
        msgt.convertAndSend("/topic/move." +room, spc.getPlayer(room, movement.getUsername()).getBody());
        spc.playerOnline(Integer.parseInt(room), movement.getUsername());
    }
    
    @MessageMapping("/new.{room}")
    public void newShip(Ship newShip, @DestinationVariable String room){        
        spc.addPlayer(Integer.valueOf(room), newShip, newShip.getTeam());
        msgt.convertAndSend("/topic/new."+room, spc.getPlayer(room, newShip.getUsername()).getBody());
    }            
    
    static class Movement{
        private String username;
        private int key;
        private int team;

        public Movement() {
        }

        public Movement(String username, int key, int team) {
            this.username= username;
            this.key = key;
            this.team = team;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
        
        public void setTeam(int team){
            this.team = team;
        }
        
        public int getTeam(){
            return team;
        }
        
    }
}

