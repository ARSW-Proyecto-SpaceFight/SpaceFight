package edu.eci.arsw.spacefight.spacefight.model;

public class Base {

    private int id;

    private int Xpos;

    private int Ypos;

    public static final int Size=80;


    public Base(int xpos, int ypos, int id) {
        Xpos = xpos;
        Ypos = ypos;
        this.id=id;


    }

    public int getXpos() {
        return Xpos;
    }

    public void setXpos(int xpos) {
        Xpos = xpos;
    }

    public int getYpos() {
        return Ypos;
    }

    public void setYpos(int ypos) {
        Ypos = ypos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getSize() {
        return Size;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", Xpos=" + Xpos +
                ", Ypos=" + Ypos +
                '}';
    }
}
