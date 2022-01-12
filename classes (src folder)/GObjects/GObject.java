package GObjects;

import GUIComponents.ImageResizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Graphical object for everything on the map
 * @author mzperx
 */

public class GObject {
    /**
     * ImageResizer
     */
    protected ImageResizer imgRes;

    /**
     * Represents a button which can be drawn multiple times
     */
    protected JButton object;

    /**
     * Constructor for GObject
     */
    public GObject(){
        //Initialize ImageResizer
        imgRes = new ImageResizer();
    }

    /**
     * Function to load an image from filepath and make a button with init
     * @param x x coordinate of the image
     * @param y y coordinate of the image
     * @param path where the image is found
     * @param scale the scale of the image (scale = 1 means that the image will be loaded in original size)
     */
    protected void loadImage(String path, double scale, int x, int y) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));         //load image from path
        if(scale != 1)
            image = imgRes.resize(image, scale);                        //resize image if necessary
        object = new JButton(new ImageIcon(image));
        object.setOpaque(false);
        object.setContentAreaFilled(false);
        object.setBounds(x,y,image.getWidth(),image.getHeight());
        object.setBorderPainted(false);                                 //disable border
    }

    /**
     * Get the button from the class
     */
    public JButton getButton(){
        return object;
    }
}