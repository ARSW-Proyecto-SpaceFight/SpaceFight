/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.restcontrollers;

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
    public void move(Movement movement, @DestinationVariable String room){       
        spc.movePlayer(Integer.parseInt(room), movement.getId(), movement.getKey());
        msgt.convertAndSend("/topic/move." +room, spc.getPlayer(room, movement.id).getBody());
        spc.playerOnline(Integer.parseInt(room), movement.id);
    }
    
    @MessageMapping("/new.{room}")
    public void newShip(Ship newShip, @DestinationVariable String room){        
        spc.addPlayer(Integer.valueOf(room), newShip);
        msgt.convertAndSend("/topic/new."+room, spc.getPlayer(room, newShip.getId()).getBody());
    }            
    
    static class Movement{
        private int id;
        private int key;

        public Movement() {
        }

        public Movement(int id, int key) {
            this.id = id;
            this.key = key;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
        
    }
}

