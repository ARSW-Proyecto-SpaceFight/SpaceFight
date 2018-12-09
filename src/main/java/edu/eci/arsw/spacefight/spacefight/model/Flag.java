/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.model;

/**
 *
 * @author User
 */
public class Flag {

    Team team;
    int id;
    Boolean captured;
    public int Xpos;
    public int Ypos;

    public Flag(int Xpos,int Ypos,Team team){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.team=team;
        id=team.getNumber();
        captured=false;
    }

    public int getXpos(){
        return Xpos;
    }

    public void setXpos(int xpos){
        Xpos = xpos;
    }


    public int getYpos(){
        return Ypos;
    }

    public void setYpos(int ypos){
        Ypos = ypos;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getCaptured() {
        return captured;
    }

    public void setCaptured(Boolean captured) {
        this.captured = captured;
    }
}
