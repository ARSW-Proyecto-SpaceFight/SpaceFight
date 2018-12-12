
package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.ModelException;
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.model.*;



import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.model.Item;
import edu.eci.arsw.spacefight.spacefight.model.Shoot;
import edu.eci.arsw.spacefight.spacefight.model.Team;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.eci.arsw.spacefight.spacefight.restcontrollers.SpaceFightMessageController;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */

public class BattleGroundImp extends Thread implements BattleGroundGame {
    private ArrayList<Shoot> shoots;
    private ArrayList<Item> items = new ArrayList<Item>();
    private HashMap<Integer,Team> teamsmap= new HashMap<Integer, Team>();
    //private Team team1;
    //private Team team2;
    private  int id;
    //private Flag flagTeam1;
    //private Flag flagTeam2;
    private HashMap<Integer,Flag> flagsmap= new HashMap<Integer, Flag>();
    private final int numberOfTeams= 2;
    SpaceFightMessageController msgt;
    private boolean active=true;
    
    /**
     * responsible for creating all the logical elements on the battleground
     * @param msgt message of the controller
     */
    public BattleGroundImp(SpaceFightMessageController msgt) {

        shoots= new ArrayList<>();
        for(int i=1;i<numberOfTeams+1;i++){
            teamsmap.put(i,new Team(i));
        }
        this.msgt=msgt;
        insertMeteorites();
        insertFlags();
        insertLifeOrbs();



    }

    /**
     * assigns random positions of n meteorites
     */
    public void insertMeteorites(){
        try {
            for(int i=1; i<=8;i++){
                Random rn = new Random();
                int posx = rn.nextInt(140)+344;
                int posy = 1 + (int)(Math.random() * 469);
                insertItemInBatlleGround(new Meteorite(posx,posy,5,i));
            }

        } catch (BattleGroundGameException e) {
            e.printStackTrace();
        }
    }

    /**
     * assigns random positions for flags of 2 teams
     */
    public void insertFlags(){
        Random rn = new Random();
        int posxt1 = rn.nextInt(340)+1;
        int posy1 = 1 + (int)(Math.random() * 469);
        int posy2 = 1 + (int)(Math.random() * 469);
        int posxt2 = rn.nextInt(341)+488;
        ArrayList<Integer> posx=new ArrayList<>();
        ArrayList<Integer> posy=new ArrayList<>();
        posx.add(posxt1);
        posx.add(posxt2);
        posy.add(posy1);
        posy.add(posy2);
        for(int i=1;i<numberOfTeams+1;i++){
            Flag fg=new Flag(posx.get(i-1),posy.get(i-1),teamsmap.get(i));
            flagsmap.put(i,fg);
            Base bs= new Base(posx.get(i-1)-30,posy.get(i-1)-30,teamsmap.get(i).getNumber());
            teamsmap.get(i).setBase(bs);
            //msgt.sendBase(id,bs);
            //msgt.sendflag(id,fg);
        }

    }



    /**
     * assigns random positions of n Life Orbs
     */
    public void insertLifeOrbs(){
        try {
            int identificador = 13;
            for(int i=1; i<=4;i++){
                Random rn = new Random();
                int posx = 1 + (int)(Math.random() * 830);
                int posy = 1 + (int)(Math.random() * 469);
                insertItemInBatlleGround(new LifeOrb(posx,posy,7,identificador));
                identificador+=1;
            }
            msgt.sendCreateOrbs(id,getAllLifeOrbs());

        } catch (BattleGroundGameException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method inserts a player to a given team
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
    
    /**
     * Get list of ships on the battleground
     * @return arraylist with the ships of both teams
     */
    public ArrayList<Ship> getAllShips(){
        ArrayList<Team> teams= new ArrayList<Team>(teamsmap.values());
        ArrayList<Ship> sp= new ArrayList<>();
        for(int i=0;i<teams.size();i++) {
            sp.addAll(teams.get(i).getShips());
        }
        return sp;
    }

    /**
     * get the ship with the name of the person that is playing
     * @return hashmap relation between the name of the player and the ship
     */
    public HashMap<String,Ship> getAllShipsAsMap(){
        HashMap<String,Ship> spmap = new HashMap<>();
        ArrayList<Ship> sp= getAllShips();
        for(int i=0;i<sp.size();i++){
            spmap.put(sp.get(i).getUsername(),sp.get(i));
        }
        return spmap;
    }

    /**
     * get the number team by ship
     * @param sp ship that consult 
     * @return the number team of a ship
     * @throws BattleGroundGameException 
     */
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
    
    /**
     * get the number team by username
     * @param username name of the player
     * @return the number team of a player
     * @throws BattleGroundGameException 
     */
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
    
    /**
     * get the ship of a team by username
     * @param username player name
     * @return the ship of player
     * @throws BattleGroundGameException 
     */
    public Ship getShip(String username) throws BattleGroundGameException{
        try {
            return teamsmap.get(findShipTeam(username)).getShip(username);
        } catch (ModelException e) {
            throw new BattleGroundGameException("Couldn't find ship");
        }

    }

    /**
     * add item on the battlegroung
     * @param item ship/meteorite/lifeorbs/flags
     * @throws BattleGroundGameException 
     */
    @Override
    public void insertItemInBatlleGround(Item item) throws BattleGroundGameException {
        if(!items.contains(item)){
            items.add(item);
        }
        else{
            throw new BattleGroundGameException("The item is in the ground");
        }
    }

    /**
     * delete item on the battleground
     * @param item ship/meteorite/lifeorbs/flags
     * @throws BattleGroundGameException 
     */
    @Override
    public void removeItemFromBatleGround(Item item) throws BattleGroundGameException {
        if(!items.contains(item)){
            throw new BattleGroundGameException("The item isn't in the ground");
        }
        else{
            items.remove(item);
        }

    }

    /**
     * list of items on the battleground
     * @return list with items
     * @throws BattleGroundGameException 
     */
    @Override
    public ArrayList<Item> getAllItems() throws BattleGroundGameException {
        return items;
    }

    /**
     * get an specific item by id
     * @param idItem number that identify items
     * @return the item
     * @throws BattleGroundGameException 
     */
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

    /**
     * get the list of meteorites
     * @return
     * @throws BattleGroundGameException 
     */
    @Override
    public ArrayList<Meteorite> getAllMeteorites() throws BattleGroundGameException {
        ArrayList<Meteorite> m = new ArrayList<Meteorite>();
        for(int i=0;i<items.size();i++) {
            if(items.get(i).getClass().getName().toString().equals("edu.eci.arsw.spacefight.spacefight.model.Meteorite")){
                m.add((Meteorite) items.get(i));
            }
        }

        if(m.isEmpty()){
            throw new BattleGroundGameException("Non exist a meteorites in the items list");
        }

        return m;

    }

    /**
     * get specific meteorite by id
     * @param idMeteorite number of meteorite
     * @return the item
     * @throws BattleGroundGameException 
     */
    @Override
    public Meteorite getMeteorite(int idMeteorite) throws BattleGroundGameException {
        try{
            return getAllMeteorites().get(idMeteorite);
        }catch (Exception e){
            throw new BattleGroundGameException(e.getMessage());
        }
    }


    /**
     *relation between shoot and ship by username
     * @param username player name
     * @throws BattleGroundGameException 
     */
    @Override
    public void shoot(String username)throws BattleGroundGameException {
        HashMap<String,Ship> map=getAllShipsAsMap();
        if(!map.containsKey(username)){
            throw new BattleGroundGameException("Ship not found");
        }
        else{
            Ship s = map.get(username);
            int amount=0;
            for(int i=0;i<shoots.size() && amount<3;i++){
                if(shoots.get(i).getShooter().getUsername().equals(username)){
                    amount++;
                }
            }
            if(amount<5) {
                Shoot shot = new Shoot(s, (int) s.getX()+(s.getShipSize()/2)-10, (int) s.getY()+(s.getShipSize()/2)-10, s.getDirection());
                shoots.add(shot);
            }
        }
    }
    
    /**
     * run method 
     */
    @Override
    public void run(){
        //System.out.println("IM RUNNING");
        while(active) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // System.out.println("IM WHILING");
            moveShoots();
            DamageShoots();
            damageMeteorites();
            lifeorbesgainlife();
            checkTakeFlag();
            checkCapture();
            checkDeath();
            checkEnd();
            //moveFlags();


        }


    }

    /**
     * verifica si la puntuación es 5 para acabar el juego
     */
    private synchronized void checkEnd() {
        ArrayList<Team> teams = new ArrayList<>(teamsmap.values());
        for(int i=0;i<teams.size();i++){
            if(teams.get(i).getScore()>=5){
                msgt.sendOver(id);
                active=false;
                
            }
        }

    }

    
    /**
     * verifica si una nave ha muerto o no.
     */
    private synchronized void checkDeath() {
        ArrayList<Ship> shiplist = getAllShips();
        synchronized (shiplist) {
            for (int i =0;i<shiplist.size();i++){
                if(shiplist.get(i).getHealth()<=0){
                    if(shiplist.get(i).getCarryingFlag()!=null){
                        shiplist.get(i).dropFlag();
                    }
                    shiplist.get(i).setHealth(100);

                    shiplist.get(i).setX(teamsmap.get(shiplist.get(i).getTeam()).getBase().getXpos());
                    shiplist.get(i).setY(teamsmap.get(shiplist.get(i).getTeam()).getBase().getYpos());
                    msgt.damage(id,shiplist.get(i));
                    msgt.sendDeath(id,shiplist.get(i));
                }
            }
        }

    }

    /**
     * verify if a ship capture a flag
     */
    private synchronized void checkCapture() {
        ArrayList<Ship> shiplist = getAllShips();
        ArrayList<Base> bases =getBases();
        synchronized (shiplist) {
            for(int i=0;i<shiplist.size();i++){
                for(int j=0;j<bases.size();j++){
                    if(colideBase(shiplist.get(i),bases.get(j))){
                        Flag fg= shiplist.get(i).getCarryingFlag();
                        fg.setXpos(teamsmap.get(fg.getId()).getBase().getXpos()+30);
                        fg.setYpos(teamsmap.get(fg.getId()).getBase().getYpos()+30);
                        fg.setCaptured(false);
                        msgt.sendflag(id,fg);
                        shiplist.get(i).setCarryingFlag(null);

                        teamsmap.get(shiplist.get(i).getTeam()).addPoint();
                        msgt.sendScore(id, teamsmap.get(1), teamsmap.get(2));
                    }
                }
            }


        }
    }

    /**
     * relation between a ship with a base to start again if a player die
     * @param ship ship of the player
     * @param base base of the team
     * @return 
     */
    private boolean colideBase(Ship ship, Base base) {
        boolean bol= false;
        if(ship.getX()-Base.Size<base.getXpos() && base.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Base.Size<base.getYpos() && base.getYpos()<ship.getY()+Ship.shipSize ){
            if(base.getId()==ship.getTeam() && ship.getCarryingFlag()!=null){
                bol=true;
            }
        }
        return bol;
    }

    /**
     * verify if a player took the flag of the other team
     */
    private synchronized void checkTakeFlag() {
        ArrayList<Ship> shiplist = getAllShips();
        synchronized (shiplist) {
            synchronized (flagsmap){
                for(int j=0; j<shiplist.size();j++) {
                    for(int i=1;i<numberOfTeams+1;i++){
                        if(colideFlag(shiplist.get(j),flagsmap.get(i)) && shiplist.get(j).getCarryingFlag()== null && flagsmap.get(i).getCaptured()==false){
                            shiplist.get(j).setCarryingFlag(flagsmap.get(i));
                            flagsmap.get(i).setCaptured(true);

                        }
                    }

                }
            }
        }
    }

    /**
     * This method calculates the colition between shoots and ships and damage if required
     */
    private synchronized void DamageShoots() {
        ArrayList<Ship> shiplist = getAllShips();
        synchronized (shiplist){
            synchronized (shoots){
                ArrayList<Shoot> del= new ArrayList<>();
                for(int j=0; j<shiplist.size();j++) {
                    for (int i = 0; i < shoots.size(); i++) {
                        if(colide(shiplist.get(j),shoots.get(i))) {
                            if (shiplist.get(j).getTeam() != shoots.get(i).getShooter().getTeam()) {
                                shiplist.get(j).damage(shoots.get(i).getDamage());
                                if(shiplist.get(j).getCarryingFlag()!=null){
                                    Flag fg=shiplist.get(j).getCarryingFlag();
                                    shiplist.get(j).dropFlag();
                                    msgt.sendflag(id,fg);
                                }
                                //System.out.println("DAMAGE DONE");
                                msgt.damage(id,shiplist.get(j));
                            }
                            del.add(shoots.get(i));
                        }

                    }
                }
                shoots.removeAll(del);
                for(Shoot s : del){msgt.deleteShoot(id, s);}
            }

        }
    }

    /**
     * this method checks the coordinates between a ship and a shoot also omits the collision with the shooter
     * @param ship the ship about to be checked if was hit
     * @param shoot the current shoot to be checked
     * @return
     */
    private boolean colide(Ship ship, Shoot shoot) {
        boolean bol=false;
        if(ship.getX()-Shoot.shootSize<shoot.getXpos() && shoot.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Shoot.shootSize<shoot.getYpos() && shoot.getYpos()<ship.getY()+Ship.shipSize ){
            if(!shoot.getShooter().getUsername().equals(ship.getUsername())) {
                bol = true;
            }
        }
        return bol;
    }
    
    /**
     * check the position of the ship and the flag to capture it
     * @param ship ship that capture th flag
     * @param flag flag of the other team
     * @return if the position of flag and ship are equals is true, else false 
     */
    private boolean colideFlag(Ship ship,Flag flag){
        boolean bol=false;
        if(ship.getX()-Flag.size<flag.getXpos() && flag.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Flag.size<flag.getYpos() && flag.getYpos()<ship.getY()+Ship.shipSize ){
            if(flag.getId()!=ship.getTeam()){
                bol=true;
            }
        }
        return bol;
    }

    /**
     * reduces the life of the ships
     */
    private synchronized void damageMeteorites(){
        try {
            for(Meteorite m : getAllMeteorites()){
                for(Ship s : getAllShips()){
                    if(m.colide(s)){
                        m.Impact(s);
                        msgt.damage(id,s);
                    }

                }
            }
        } catch (BattleGroundGameException e) {
            e.printStackTrace();
        }

    }

    /**
     *  increase the life of the ship
    */
    private synchronized void lifeorbesgainlife(){

            for(LifeOrb o : getAllLifeOrbs()){
                for(Ship s : getAllShips()){
                    if(o.orbecolide(s)){
                        o.Impact(s);
                        msgt.damage(id,s);
                        msgt.sendOrb(id,o);
                        items.remove(o);

                    }
                }
            }

            if(getAllLifeOrbs().size()==0){
                insertLifeOrbs();
            }



    }



    /**
     * Allows the movement of the shoots on the battleground
     */
    private synchronized void moveShoots(){
        synchronized(shoots) {
            ArrayList<Shoot> found = new ArrayList<>();
            for (int i = 0; i < shoots.size(); i++) {
                Shoot s = shoots.get(i);
                s.move();
                //System.out.println("IM SENDING ID="+id+"SHOOT"+s);
                msgt.sendshoot(id, s);
                //System.out.println("SENT");

            }
            for (int i = 0; i < shoots.size(); i++) {
                //System.out.println("IM REMOVING");
                Shoot s = shoots.get(i);
                if (s.getXpos() < 0 || s.getXpos() > Ship.BOUNDX || s.getYpos() < 0 || s.getYpos() > Ship.BOUNDY) {
                    found.add(s);

                }
            }
            shoots.removeAll(found);
            for(Shoot s : found){msgt.deleteShoot(id, s);}
        }
    }
    /*private synchronized void moveFlags(){
        synchronized (flagsmap){
            for(int i=1;i<numberOfTeams+1;i++){
                msgt.sendflag(id,flagsmap.get(i));
            }

        }
    }*/

    /**
     * set id of the room
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
        this.start();
    }

    /**
     * get the ships on a team
     * @param team number of the team
     * @return list of ships
     */
    @Override
    public ArrayList<Ship> getAllShipsFromTeam(int team) {
        return teamsmap.get(team).getShips();
    }

    /**
     * get the lifeorbs on the battleground
     * @return list with this items
     */
    @Override
    public ArrayList<LifeOrb> getAllLifeOrbs()  {
        ArrayList<LifeOrb> o = new ArrayList<LifeOrb>();
        for(int i=0;i<items.size();i++) {
            if(items.get(i).getClass().getName().toString().equals("edu.eci.arsw.spacefight.spacefight.model.LifeOrb")){
                o.add((LifeOrb) items.get(i));
            }
        }

        return o;
    }

    /**
     * get the life orb like object
     * @param idLifeOrb id of the item
     * @return the item
     */
    @Override
    public LifeOrb getLifeOrb(int idLifeOrb){
            return getAllLifeOrbs().get(idLifeOrb);

    }



    /**
     * allows the movement of a ship on the battleground
     * @param username player name
     * @param key key of the keyboard
     */
    @Override
    public void moveShip(String username,int key) {
        HashMap<String, Ship> sp = getAllShipsAsMap();
        Ship s = sp.get(username);
        s.move(key);
        if (s.getCarryingFlag() != null) {
            s.getCarryingFlag().move(key, Ship.velocity);
            msgt.sendflag(id, s.getCarryingFlag());
        }


    }

    /**
     * get flags of teams
     * @return list  with flags
     */
    @Override
    public ArrayList<Flag> getFlags() {
        return new ArrayList<Flag>(flagsmap.values());
    }

    /**
     * get bases of teams
     * @return list with bases
     */
    @Override
    public ArrayList<Base> getBases() {
        ArrayList<Team> teams = new ArrayList<>(teamsmap.values());
        ArrayList<Base> bases = new ArrayList<>();
        for(int i=0; i<teams.size();i++){
            bases.add(teams.get(i).getBase());
        }
        return bases;
    }
}
