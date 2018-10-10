/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.modelo;

/**
 *
 * @author 2125275
 */
public class Flag extends Position {

    Team team;
    Boolean captured;
    public Flag(int Xpos,int Ypos,Team team){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.team=team;
        captured=false;
    }

    public Team getTeam() {
        return team;
    }
}
