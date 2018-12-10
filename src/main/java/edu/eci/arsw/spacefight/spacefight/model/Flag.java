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

    //Team team;
    int id;
    Boolean captured;
    public int Xpos;
    public int Ypos;
    public static final int size=30;

    public Flag(int Xpos,int Ypos,Team team){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        //this.team=team;
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

    /*public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/

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

    public void move(int key,int velocity){
        switch (key) {
            case 37:
                if(Xpos>0) Xpos-=velocity;
                break;
            case 38:
                if(Ypos>0) Ypos-=velocity;
                break;
            case 39:
                if(Xpos<830) Xpos+=velocity;
                break;
            case 40:
                if(Ypos<470) Ypos+=velocity;
                break;
            default:
                break;
        }
    }

    public int getSize() {
        return size;
    }

}
