
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.Item;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import edu.eci.arsw.spacefight.spacefight.model.Team;

import java.util.ArrayList;


public interface BattleGroundGame {
    void insertPlayerToTeam(Ship ship, int team) throws BattleGroundGameException;
    void removePlayerFromTeam(Ship ship, int team) throws BattleGroundGameException;
    Team getTeam(int id)throws BattleGroundGameException;
    ArrayList<Ship> getAllShips();
    int findShipTeam(Ship sp)throws BattleGroundGameException;
    Ship getShip(String username) throws BattleGroundGameException;
    void insertItemInBatlleGround(Item item) throws BattleGroundGameException;
    void removeItemFromBatleGround(Item item) throws BattleGroundGameException;
}
