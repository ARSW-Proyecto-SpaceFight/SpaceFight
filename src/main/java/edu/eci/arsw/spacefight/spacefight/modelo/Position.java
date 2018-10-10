package edu.eci.arsw.spacefight.spacefight.modelo;

public abstract class Position {
    protected int Xpos;
    protected int Ypos;

    protected int getXpos() {
        return Xpos;
    }

    protected int getYpos() {
        return Ypos;
    }

    protected void setYpos(int ypos) {
        Ypos = ypos;
    }

    protected void setXpos(int xpos) {
        Xpos = xpos;
    }
}
