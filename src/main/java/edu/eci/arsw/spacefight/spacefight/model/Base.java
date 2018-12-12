package edu.eci.arsw.spacefight.spacefight.model;

public class Base {

    private int id;

    private int Xpos;

    private int Ypos;

    public static final int Size=80;


    /**
     * object creator
     * @param xpos position in x
     * @param ypos position in y
     * @param id number to identify the base
     */
    public Base(int xpos, int ypos, int id) {
        Xpos = xpos;
        Ypos = ypos;
        this.id=id;


    }

    /**
     * get x position of base
     * @return value of x
     */
    public int getXpos() {
        return Xpos;
    }

    /**
     * set x position of base
     * @param xpos new value 
     */
    public void setXpos(int xpos) {
        Xpos = xpos;
    }

    /**
     * get y position of base
     * @return value of y
     */
    public int getYpos() {
        return Ypos;
    }

    /**
     * set y position of base
     * @param ypos new value
     */
    public void setYpos(int ypos) {
        Ypos = ypos;
    }

    /**
     * get the number that identifies the object
     * @return value that identifies
     */
    public int getId() {
        return id;
    }

    /**
     * set the number that identifies the object
     * @param id new value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get size of base 
     * @return size
     */
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
