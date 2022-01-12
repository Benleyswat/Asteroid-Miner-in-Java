package GObjects;

import java.io.IOException;

/**
 * Graphical object for uranium
 * @author mzperx
 */
public class GUranium extends GObject{

    /**
     * Default scale for image resize
     */
    public double scale = 1;

    /**
     * Constructor for uranium graphical object without scale (default scale will be used)
     * @param x x value in pixel
     * @param y y value in pixel
     */
    public GUranium(int x, int y) throws IOException {
        loadImage("pictures\\uranium70x70.png", scale, x, y);
    }

    /**
     * Constructor for uranium graphical object with scale
     * @param x x value in pixel
     * @param y y value in pixel
     * @param s scale value
     */
    public GUranium(int x, int y, int s) throws IOException {
        loadImage("pictures\\uranium70x70.png", s, x, y);
    }
}