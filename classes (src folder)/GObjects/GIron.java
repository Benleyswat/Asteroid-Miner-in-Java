package GObjects;

import java.io.IOException;

/**
 * Graphical object for iron
 * @author mzperx
 */
public class GIron extends GObject{

    /**
     * Default scale for image resize
     */
    public double scale = 1;

    /**
     * Constructor for iron graphical object without scale (default scale will be used)
     * @param x x value in pixel
     * @param y y value in pixel
     */
    public GIron(int x, int y) throws IOException {
        loadImage("pictures\\iron70x70.png", scale, x, y);

    }

    /**
     * Constructor for iron graphical object with scale
     * @param x x value in pixel
     * @param y y value in pixel
     * @param s scale value
     */
    public GIron(int x, int y, int s) throws IOException {
        loadImage("pictures\\iron70x70.png", s, x, y);
    }


}