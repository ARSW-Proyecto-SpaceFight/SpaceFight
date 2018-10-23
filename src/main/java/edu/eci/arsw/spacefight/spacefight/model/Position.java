package edu.eci.arsw.spacefight.spacefight.model;

public abstract class Position {
    protected int Xpos;
    protected int Ypos;

    public int getXpos() {
        return Xpos;
    }

    public int getYpos() {
        return Ypos;
    }

    public void setYpos(int ypos) {
        Ypos = ypos;
    }

    public void setXpos(int xpos) {
        Xpos = xpos;
    }
}
