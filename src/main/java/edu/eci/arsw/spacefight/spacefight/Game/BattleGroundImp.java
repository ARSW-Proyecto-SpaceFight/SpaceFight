
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.ModelException;
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.model.*;



import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.model.Item;
import edu.eci.arsw.spacefight.spacefight.model.Shoot;
import edu.eci.arsw.spacefight.spacefight.model.Team;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BattleGroundImp implements BattleGroundGame {
    private ArrayList<Shoot> disparos;
    private ArrayList<Item> items;
    private HashMap<Integer,Team> teamsmap= new HashMap<Integer, Team>();
    private Team team1;
    private Team team2;

    public BattleGroundImp() {
        teamsmap.put(1,new Team());
        teamsmap.put(2,new Team());

    }

    /**
     * This method inserts a player toa given team
     * @param ship Object OF the ship to be inserted
     * @param team Number of the team
     * @throws BattleGroundGameException
     */
    @Override
    public void insertPlayerToTeam(Ship ship, int team) throws BattleGroundGameException {
        if(!teamsmap.containsKey(team)){
            throw new BattleGroundGameException("Team not found");
        }else{
            try {
                teamsmap.get(team).addPlayer(ship);
            } catch (ModelException e) {
                throw new BattleGroundGameException(e.getMessage());
            }
        }
    }

    /**
     * This method removes a Ship from a given team
     * @param ship Object of the ship to be removed
     * @param team Number of the team to be removed
     * @throws BattleGroundGameException
     */
    @Override
    public void removePlayerFromTeam(Ship ship, int team) throws BattleGroundGameException {
        if(!teamsmap.containsKey(team)){
            throw new BattleGroundGameException("Team not found");
        }else{
            try {
                teamsmap.get(team).removePlayer(ship.getUsername());
            } catch (ModelException e) {
                throw new BattleGroundGameException(e.getMessage());
            }
        }
    }

    /**
     * Gets an specific team
     * @param id Number of the The team
     * @return
     * @throws BattleGroundGameException
     */
    @Override
    public Team getTeam(int id)throws BattleGroundGameException {

        if(!teamsmap.containsKey(id)){
            throw new BattleGroundGameException("Team not found");
        }
        else{
            return teamsmap.get(id);

        }
    }
    public ArrayList<Ship> getAllShips(){
        ArrayList<Team> teams= new ArrayList<Team>(teamsmap.values());
        ArrayList<Ship> sp= new ArrayList<>();
        for(int i=0;i<teams.size();i++) {
            sp.addAll(teams.get(i).getShips());
        }
        return sp;
    }
    public int findShipTeam(Ship sp)throws BattleGroundGameException{
        ArrayList<Integer> keys = new ArrayList<>(teamsmap.keySet());
        int team= Integer.MIN_VALUE;
        for(int i=0;i<keys.size();i++){
            if(teamsmap.get(keys.get(i)).isInTeam(sp.getUsername())){
                team=keys.get(i);
                break;
            }
        }
        if(team==Integer.MIN_VALUE){
            throw new BattleGroundGameException("Couldn't find ship's team");
        }
        return team;

    }
    public int findShipTeam(String username)throws BattleGroundGameException{
        ArrayList<Integer> keys = new ArrayList<>(teamsmap.keySet());
        int team= Integer.MIN_VALUE;
        for(int i=0;i<keys.size();i++){
            if(teamsmap.get(keys.get(i)).isInTeam(username)){
                team=keys.get(i);
                break;
            }
        }
        if(team==Integer.MIN_VALUE){
            throw new BattleGroundGameException("Couldn't find ship's team");
        }
        return team;

    }
    public Ship getShip(String username) throws BattleGroundGameException{
        try {
            return teamsmap.get(findShipTeam(username)).getShip(username);
        } catch (ModelException e) {
            throw new BattleGroundGameException("Couldn't find ship");
        }

    }

    @Override
    public void insertItemInBatlleGround(Item item) throws BattleGroundGameException {
        if(!items.contains(item)){
            items.add(item);
        }
        else{
            throw new BattleGroundGameException("The item is in the ground");
        }
    }

    @Override
    public void removeItemFromBatleGround(Item item) throws BattleGroundGameException {
        if(!items.contains(item)){
            throw new BattleGroundGameException("The item isn't in the ground");
        }
        else{
            items.remove(item);
        }

    }

    @Override
    public ArrayList<Item> getAllItems() throws BattleGroundGameException {
        return items;
    }

    @Override
    public Item getItem(int idItem) throws BattleGroundGameException {
        Item it= null;
        for(int i=0;i<items.size();i++) {
            if(items.get(i).getIdItem()==(idItem)){
                it = items.get(i);
                break;
            }
        }
        if(it.equals(null)){
            throw new BattleGroundGameException("The item selected isn't in the battle ground");
        }
        return it;
    }

    @Override
    public ArrayList<Meteorite> getAllMeteorites() throws BattleGroundGameException {
        ArrayList<Meteorite> m = new ArrayList<Meteorite>();
        for(int i=0;i<items.size();i++) {
            if(items.get(i).getClass().getName()=="Meteorite"){
                m.add((Meteorite) items.get(i));
            }
        }

        if(m.isEmpty()){
            throw new BattleGroundGameException("Non exist a meteorites in the items list");
        }

        return m;

    }

    @Override
    public Meteorite getMeteorite(int idMeteorite) throws BattleGroundGameException {
        try{
            return getAllMeteorites().get(idMeteorite);
        }catch (Exception e){
            throw new BattleGroundGameException(e.getMessage());
        }
    }

    @Override
    public void removeMeteorite(int idMeteorite) throws BattleGroundGameException {
        try{
           getAllMeteorites().remove(getMeteorite(idMeteorite));
        }catch(Exception e){
            throw new BattleGroundGameException(e.getMessage());
        }
    }


}
