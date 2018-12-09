
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.*;

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
    ArrayList<Item> getAllItems()throws BattleGroundGameException;
    Item getItem(int idItem)throws BattleGroundGameException;
    ArrayList<Meteorite> getAllMeteorites() throws BattleGroundGameException;
    Meteorite getMeteorite(int idMeteorite) throws BattleGroundGameException;
    void removeMeteorite(int idMeteorite) throws BattleGroundGameException;
    void shoot(String username)throws BattleGroundGameException;
    void setId(int id);
    ArrayList<Ship> getAllShipsFromTeam(int team);
    ArrayList<LifeOrb> getAllLifeOrbs() throws BattleGroundGameException;
    LifeOrb getLifeOrb(int idLifeOrb) throws BattleGroundGameException;
    void removeLifeOrb(int idLifeOrb) throws BattleGroundGameException;
<<<<<<< HEAD

    void moveShip(String username,int key);
=======
    ArrayList<LifeOrb> getFlags() throws BattleGroundGameException;
    Flag getFlagTeam1() throws BattleGroundGameException;
    Flag getFlagTeam2() throws BattleGroundGameException;
>>>>>>> 5de4ae552d28835ad5e4693c1d8e3154d917caa8
}
