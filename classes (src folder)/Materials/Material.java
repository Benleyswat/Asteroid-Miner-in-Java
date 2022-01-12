package Materials;

import GObjects.*;
import Objects.Asteroid;

import javax.swing.*;
import java.io.IOException;

/**
 * It represents the materials in the game
 *  @author mzperx
 */
public abstract class Material {

    /**
     * name of the material
     */
    protected String name;
    /**
     * It stores which asteroids on it
     */
    protected Asteroid asteroid;

    /**
     * Getter for name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Setter for name
     * @param n name
     */
    public void setName(String n){
        name = n;
    }

    /**
     * Setter for asteroid
     * @param a asteroid
     */
    public void setAsteroid(Asteroid a){asteroid = a;}

    /**
     * If the material is water or uranium it's true
     * @return true or false
     */
    public boolean PeriMining(){
        return false;
    }

    /**
     * Draw the given material to a x,y point
     * Returns with a Jbutton which can be drawn to panel
     * @param x x coordinate
     * @param y y coordinate
     * @param mat material
     * @return button
     * @throws IOException
     */
    public JButton drawMaterial(int x, int y,  Material mat) throws IOException {

        JButton toDraw = new JButton();

        switch(mat.getName()){
            case("Iron"):{
                GIron iron = new GIron(x, y);
                toDraw = iron.getButton();
            }break;

            case("Uranium"):{
                GUranium uranium = new GUranium(x, y);
                toDraw = uranium.getButton();
            }break;

            case("Water"):{
                GWater water = new GWater(x, y);
                toDraw = water.getButton();
            }break;

            case("Coal"):{
                GCoal coal = new GCoal(x, y);
                toDraw = coal.getButton();
            }break;
        }

        return toDraw;
    }
}