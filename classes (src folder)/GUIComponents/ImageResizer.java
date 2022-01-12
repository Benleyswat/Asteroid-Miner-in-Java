package GUIComponents;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for resizing image
 * @author mzperx
 */
public class ImageResizer {
    /**
     * Function to resize image
     * @param img Image to resize
     * @param percent Double value for resize (1% = 0.01)
     * @return Output image
     */
    public static BufferedImage resize(BufferedImage img, double percent) {

        BufferedImage inputImage = img;

        //Calculate the scaled width and height
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);

        //Making BufferedImage for the output
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        //Create the resized image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        //Return resized image
        return outputImage;
    }
}