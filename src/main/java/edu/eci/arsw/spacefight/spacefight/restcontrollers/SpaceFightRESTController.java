
package edu.eci.arsw.spacefight.spacefight.restcontrollers;


import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.Game.MasterException;


import edu.eci.arsw.spacefight.spacefight.model.Ship;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.spacefight.spacefight.Services.SpaceFightServices;

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
    SpaceFightServices bgs;

    
    @RequestMapping(path = "/{roomId}/players",method = RequestMethod.GET)
    public ResponseEntity<?> getRoomPlayers(@PathVariable(name = "roomId") int roomId, String username) {

         try {
             bgs.playerOnline(roomId,username);            
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
    public ResponseEntity<?> movePlayer(@PathVariable(name = "roomId") int roomId, String username,  int key, int team){
        try {            
            bgs.moveShip(roomId, username, key, team);
            //bgs.registerPlayerToRoom(Integer.parseInt(roomId), ship);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);        
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        } catch (MasterException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    
    
    @RequestMapping(path = "/{roomId}/players",method = RequestMethod.PUT)
    public ResponseEntity<?> addPlayer(@PathVariable(name = "roomId") Integer roomId,@RequestBody Ship ship, int team){
        try {
            bgs.registerPlayerToRoom(roomId, ship, team);            
                    return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException ex){
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{roomId}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = "/{roomId}/shoot",method = RequestMethod.PUT)
    public ResponseEntity<?> shoot(@PathVariable(name = "roomId") Integer roomId,String username){
        try {
            bgs.shoot(roomId,username);
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
    public ResponseEntity<?> removePlayer(@PathVariable(name = "roomId") String roomId,@RequestBody Ship ship, int team) {
        try {
            bgs.removePlayerFromRoom(Integer.valueOf(roomId), ship, team);            
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
             return new ResponseEntity<>(bgs.getRooms() ,HttpStatus.ACCEPTED);         
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
    
    @RequestMapping(path = "/{roomId}/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getPlayer(@PathVariable(name = "roomId") String roomId, @PathVariable(name = "id") String username){
        try {            
            return new ResponseEntity<>(bgs.getPlayer(Integer.parseInt(roomId), username), HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    
    public void playerOnline(int roomId, String username) throws BattleGroundGameException{
        bgs.playerOnline(roomId, username);        
    }


     @RequestMapping(path = "/{roomId}/meteorites",method = RequestMethod.GET)
    public ResponseEntity<?> getMeteoriteFromRoom(@PathVariable(name = "roomId") String roomId){
        try {
            return new ResponseEntity<>(bgs.getMeteoriteFromRoom(Integer.parseInt(roomId)), HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        } catch (MasterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{roomId}/flags",method = RequestMethod.GET)
    public ResponseEntity<?> getFlagsFromRoom(@PathVariable(name = "roomId") String roomId){
        return new ResponseEntity<>(bgs.getFlagsFromRoom(Integer.parseInt(roomId)), HttpStatus.CREATED);
    }


    public int getNextTeam(int room){
        return bgs.getNextTeam(room);
    }

    @RequestMapping(path = "/{roomId}/lifeorbs",method = RequestMethod.GET)
    public ResponseEntity<?> getLifeOrbsFromRoom(@PathVariable(name = "roomId") String roomId){
        try {
            return new ResponseEntity<>(bgs.getLifeOrbFromRoom(Integer.parseInt(roomId)), HttpStatus.CREATED);
        } catch (BattleGroundGameException ex) {
            Logger.getLogger(SpaceFightRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        } catch (MasterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
    

