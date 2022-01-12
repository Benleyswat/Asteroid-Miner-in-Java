package GObjects;

import java.io.IOException;

/**
 * Graphical object for coal
 * @author mzperx
 */
public class GCoal extends GObject{

    /**
     * Default scale for image resize
     */
    public double scale = 1;

    /**
     * Constructor for coal graphical object without scale (default scale will be used)
     * @param x x value in pixel
     * @param y y value in pixel
     */
    public GCoal(int x, int y) throws IOException {
        loadImage("pictures\\coal70x70.png", scale, x, y);

    }

    /**
     * Constructor for coal graphical object with scale
     * @param x x value in pixel
     * @param y y value in pixel
     * @param s scale value
     */
    public GCoal(int x, int y, int s) throws IOException {
        loadImage("pictures\\coal70x70.png", s, x, y);
    }
}