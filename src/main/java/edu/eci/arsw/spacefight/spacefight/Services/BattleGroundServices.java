package edu.eci.arsw.spacefight.spacefight.Services;
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattleGroundServices {
    @Autowired
    BattleGroundGame bgg;
}
