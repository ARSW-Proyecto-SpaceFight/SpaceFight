/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.Game.Imp;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.modelo.Item;
import edu.eci.arsw.spacefight.spacefight.modelo.Shoot;
import edu.eci.arsw.spacefight.spacefight.modelo.Team;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2125275
 */
@Service
public class BattleGround implements BattleGroundGame {
    private ArrayList<Shoot> disparos;
    private ArrayList<Item> items;
    private Team team1;
    private Team team2;
    
    
    
    
}
