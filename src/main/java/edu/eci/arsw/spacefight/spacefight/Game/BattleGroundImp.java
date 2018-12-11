
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


    public void insertFlags(){
        Random rn = new Random();
        int posxt1 = rn.nextInt(340)+1;
        int posy = 1 + (int)(Math.random() * 469);
        int posxt2 = rn.nextInt(341)+488;
        ArrayList<Integer> pos=new ArrayList<>();
        pos.add(posxt1);
        pos.add(posxt2);
        for(int i=1;i<numberOfTeams+1;i++){
            Flag fg=new Flag(pos.get(i-1),posy,teamsmap.get(i));
            flagsmap.put(i,fg);
            Base bs= new Base(pos.get(i-1)-30,posy-30,teamsmap.get(i).getNumber());
            teamsmap.get(i).setBase(bs);
            //msgt.sendBase(id,bs);
            //msgt.sendflag(id,fg);
        }

    }



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
    public ArrayList<Ship> getAllShips(){
        ArrayList<Team> teams= new ArrayList<Team>(teamsmap.values());
        ArrayList<Ship> sp= new ArrayList<>();
        for(int i=0;i<teams.size();i++) {
            sp.addAll(teams.get(i).getShips());
        }
        return sp;
    }

    public HashMap<String,Ship> getAllShipsAsMap(){
        HashMap<String,Ship> spmap = new HashMap<>();
        ArrayList<Ship> sp= getAllShips();
        for(int i=0;i<sp.size();i++){
            spmap.put(sp.get(i).getUsername(),sp.get(i));
        }
        return spmap;
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
            if(items.get(i).getClass().getName().toString().equals("edu.eci.arsw.spacefight.spacefight.model.Meteorite")){
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
            //return shot;
        }
    }
    @Override
    public void run(){
        //System.out.println("IM RUNNING");
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("IM WHILING");
            moveShoots();
            DamageShoots();
            damageMeteorites();
            lifeorbesgainlife();
            checkTakeFlag();
            checkCapture();
            checkDeath();
            //moveFlags();


        }


    }

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

    private boolean colideBase(Ship ship, Base base) {
        boolean bol= false;
        if(ship.getX()-Base.Size<base.getXpos() && base.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Base.Size<base.getYpos() && base.getYpos()<ship.getY()+Ship.shipSize ){
            if(base.getId()==ship.getTeam() && ship.getCarryingFlag()!=null){
                bol=true;
            }
        }
        return bol;
    }

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
    private boolean colideFlag(Ship ship,Flag flag){
        boolean bol=false;
        if(ship.getX()-Flag.size<flag.getXpos() && flag.getXpos()<ship.getX()+Ship.shipSize && ship.getY()-Flag.size<flag.getYpos() && flag.getYpos()<ship.getY()+Ship.shipSize ){
            if(flag.getId()!=ship.getTeam()){
                bol=true;
            }
        }
        return bol;
    }

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

    public void setId(int id) {
        this.id = id;
        this.start();
    }

    @Override
    public ArrayList<Ship> getAllShipsFromTeam(int team) {
        return teamsmap.get(team).getShips();
    }

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

    @Override
    public LifeOrb getLifeOrb(int idLifeOrb){
            return getAllLifeOrbs().get(idLifeOrb);

    }




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

    @Override
    public ArrayList<Flag> getFlags() {
        return new ArrayList<Flag>(flagsmap.values());
    }

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
