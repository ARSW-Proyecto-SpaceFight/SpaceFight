/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.restcontrollers;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.model.Flag;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.spacefight.spacefight.model.Shoot;
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
    
    ConcurrentHashMap<String, Boolean> desconectados = new ConcurrentHashMap<>();
    
    @MessageMapping("/move.{room}")
    public void move(Movement movement, @DestinationVariable String room) throws BattleGroundGameException{       
        spc.movePlayer(Integer.parseInt(room), movement.getUsername(), movement.getKey(), movement.getTeam());        
        msgt.convertAndSend("/topic/move." +room, spc.getPlayer(room, movement.getUsername()).getBody());
        spc.playerOnline(Integer.parseInt(room), movement.getUsername());
    }
    
    @MessageMapping("/new.{room}")
    public void newShip(Ship newShip, @DestinationVariable String room){        
        spc.addPlayer(Integer.valueOf(room), newShip, spc.getNextTeam(Integer.valueOf(room)));
        msgt.convertAndSend("/topic/new."+room, spc.getPlayer(room, newShip.getUsername()).getBody());
    }
    
    @MessageMapping("/conectado.{room}")
    public void conectado(String username, @DestinationVariable String room) throws BattleGroundGameException{                
        desconectados.put(username, Boolean.TRUE);
        spc.playerOnline(Integer.parseInt(room), username);
    }
    @MessageMapping("/shoot.{room}")
    public void shoot(int roomId,String username){
        spc.shoot(roomId,username);

    }

    public void sendshoot(int roomId,Shoot s){
        msgt.convertAndSend("/topic/shoot." +roomId,s);
    }

    public void deleteShoot(int roomId, Shoot s){ msgt.convertAndSend("/topic/deleteshoot."+roomId,s);}

    public void damage(int roomId, Ship s){ msgt.convertAndSend("/topic/damage."+roomId,s);};

    public void flag(int roomId, Flag f){ msgt.convertAndSend("/topic/flag."+roomId,f);};

    public boolean conectado(String username, int room){
        desconectados.put(username, Boolean.FALSE);
        Thread temp = new Thread(){
            @Override
            public void run(){
                msgt.convertAndSend("/topic/conectado."+room, username);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SpaceFightMessageController.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        };        
        try {
            temp.start();
            temp.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SpaceFightMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean conectado = desconectados.get(username);
        System.out.println(desconectados);
        desconectados.remove(username);
        
        if(!conectado){ 
            msgt.convertAndSend("/topic/delete."+room, username);  
            System.out.println("Eliminar :/topic/conectado."+room+ username);
        }
        return conectado;
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

