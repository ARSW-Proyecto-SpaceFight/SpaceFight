/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.restcontrollers;

<<<<<<< HEAD
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
=======
import edu.eci.arsw.spacefight.spacefight.game.BattleGroundGameException;
>>>>>>> master
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import java.util.logging.Level;
import java.util.logging.Logger;

<<<<<<< HEAD
import edu.eci.arsw.spacefight.spacefight.Services.SpaceFightServices;
=======
import edu.eci.arsw.spacefight.spacefight.services.BattleGroundServices;
>>>>>>> master
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@Service
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/spacefight")
public class SpaceFightRESTController{

    @Autowired
<<<<<<< HEAD
    SpaceFightServices bgs;
=======
    BattleGroundServices bgs;
>>>>>>> master

   
    
    @RequestMapping(path = "/{roomId}/players",method = RequestMethod.GET)
    public ResponseEntity<?> getRoomPlayers(@PathVariable(name = "roomId") int roomId, int player) {

         try {
             bgs.playerOnline(roomId,player);
             return new ResponseEntity<>(bgs.getRegisteredPlayers(roomId),HttpStatus.ACCEPTED);
         } catch (BattleGroundGameException ex) {
             Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
         
         } catch (NumberFormatException ex){
             Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
         }
     
    }
    
    @RequestMapping(path = "/{roomId}",method = RequestMethod.PUT)
    public ResponseEntity<?> movePlayer(@PathVariable(name = "roomId") int roomId, int id,  int key){
        try {
            bgs.moveShip(roomId, id, key);
            //bgs.registerPlayerToRoom(Integer.parseInt(roomId), ship);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);        
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }

    }
    
    
    @RequestMapping(path = "/{roomId}/players",method = RequestMethod.PUT)
    public ResponseEntity<?> addPlayer(@PathVariable(name = "roomId") Integer roomId,@RequestBody Ship ship){
        try {
            bgs.registerPlayerToRoom(roomId, ship);
                    return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(path = "/{roomId}/players",method = RequestMethod.DELETE)
    public ResponseEntity<?> removePlayer(@PathVariable(name = "roomId") String roomId,@RequestBody Ship ship) {
        try {
            bgs.removePlayerFromRoom(Integer.valueOf(roomId), ship);
                    return new ResponseEntity<>(HttpStatus.OK);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }

    }
   
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getRooms() {

         try {
             return new ResponseEntity<>(bgs.getTotalRooms() ,HttpStatus.ACCEPTED);
         } catch (BattleGroundGameException ex) {
             Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
         } catch (NumberFormatException ex){
             Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>("A NumberFormat error has occurred.",HttpStatus.BAD_REQUEST);
         }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createNewRoom(int room) {
        try {
            //System.out.println(Integer.parseInt(room.get("id")));
            bgs.createRoom(room);
                    return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(path = "/{roomId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> removeRoom(@PathVariable(name = "roomId") String roomId) {
        try {
            bgs.removeRoom(Integer.parseInt(roomId));
                    return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }
    }
    
}
