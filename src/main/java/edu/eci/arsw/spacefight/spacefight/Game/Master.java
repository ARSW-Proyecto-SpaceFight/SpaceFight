
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.Ship;

import java.util.HashMap;


/**
 *
 * @author ARSW Proyecto
 */
public class Master {

    private HashMap<Integer, BattleGroundGame> rooms = new HashMap<>();

    public HashMap<Integer, BattleGroundGame> getRoomsMap() {
        return rooms;
    }

    public void insertRoom(int roomid,BattleGroundGame bg) throws MasterException {
        if(rooms.containsKey(roomid)) {
           throw  new MasterException("Room "+roomid+" already registered in the server.");
        }
        else {
            rooms.put(roomid, bg);
        }
    }
    public void removeRoom(int roomid) throws MasterException{
        if(!rooms.containsKey(roomid)) {
            throw  new MasterException("Room "+roomid+" does not exist.");
        }
        else {
            rooms.remove(roomid);
        }
    }
    public void registerPlayerToRoom(int roomId, Ship ship, int team) throws MasterException{
        if(!rooms.containsKey(roomId)) {
            throw  new MasterException("Room "+roomId+" does not exist.");
        }
        else {
            try {
                rooms.get(roomId).insertPlayerToTeam(ship, team);
            } catch (BattleGroundGameException e) {
                throw new MasterException("Could not Insert player to Team");
            }
        }


    }
    public void removePlayerFromRoom(int roomId, Ship ship, int team)throws MasterException{
        if(!rooms.containsKey(roomId)) {
            throw  new MasterException("Room "+roomId+" does not exist.");
        }
        else {
            try {
                rooms.get(roomId).removePlayerFromTeam(ship, team);
            } catch (BattleGroundGameException e) {
                throw new MasterException("Could not Insert player to Team");
            }
        }
    }
}
