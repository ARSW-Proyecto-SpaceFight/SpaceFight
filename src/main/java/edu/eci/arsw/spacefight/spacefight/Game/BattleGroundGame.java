
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.Ship;



public interface BattleGroundGame {
    void insertPlayerToTeam(Ship ship, int team) throws BattleGroundGameException;

    void removePlayerFromTeam(Ship ship, int team) throws BattleGroundGameException;
}
