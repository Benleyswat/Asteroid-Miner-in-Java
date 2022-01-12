package GObjects;

import java.io.IOException;

/**
 * Graphical object for water
 * @author mzperx
 */
public class GWater extends GObject{

    /**
     * Default scale for image resize
     */
    public double scale = 1;

    /**
     * Constructor for water graphical object without scale (default scale will be used)
     * @param x x value in pixel
     * @param y y value in pixel
     */
    public GWater(int x, int y) throws IOException {
        loadImage("pictures\\water70x70.png", scale, x, y);
    }

    /**
     * Constructor for water graphical object with scale
     * @param x x value in pixel
     * @param y y value in pixel
     * @param s scale value
     */
    public GWater(int x, int y, int s) throws IOException {
        loadImage("pictures\\water70x70.png", s, x, y);
    }
}