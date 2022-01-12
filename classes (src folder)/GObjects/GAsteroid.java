package GObjects;

import Objects.Asteroid;

import java.io.IOException;

/**
 * Graphical object for asteroid
 * @author mzperx
 */
public class GAsteroid extends GObject{

    /**
     * Default scale for image resize
     */
    public double scale = 0.05;

    /**
     * Constructor for asteroid graphical object without scale (default scale will be used)
     * @param x x value in pixel
     * @param y y value in pixel
     */
    public GAsteroid(int x, int y) throws IOException {
        loadImage("pictures\\a_teli_tavol.png", scale, x, y);
    }

    /**
     * Constructor for asteroid graphical object with scale
     * @param x x value in pixel
     * @param y y value in pixel
     * @param s scale value
     */
    public GAsteroid(int x, int y, int s) throws IOException {
        loadImage("pictures\\a_teli_tavol.png", s, x, y);
    }
    /**
     * Constructor for asteroid graphical object with Asteroid and scale
     * @param a Asteroid to be drawn
     * @param s scale value
     */
    //Constructor for asteroid graphical object with Asteroid and scale
    public GAsteroid(Asteroid a, double s) throws IOException{
        //If asteroid is empoty
        if(a.getLayer() == a.getDigged() && a.getMaterial() == null){
            if(a.getPerihelion())
                loadImage("pictures\\a_ures_kozel.png", s, a.getX(), a.getY());
            else
                loadImage("pictures\\a_ures_tavol.png", s, a.getX(), a.getY());
        }
        //If asteroid is not empty
        else{
            if(a.getPerihelion())
                loadImage("pictures\\a_teli_kozel.png", s, a.getX(), a.getY());
            else
                loadImage("pictures\\a_teli_tavol.png", s, a.getX(), a.getY());
        }//

    }
}