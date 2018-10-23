package edu.eci.arsw.spacefight.spacefight.Game;
<<<<<<< Updated upstream
=======

import edu.eci.arsw.spacefight.spacefight.model.Ship;
>>>>>>> Stashed changes

public interface BattleGroundGame {
    void insertPlayerToTeam(Ship ship, int team) throws BattleGroundGameException;

    void removePlayerFromTeam(Ship ship, int team) throws BattleGroundGameException;
}
