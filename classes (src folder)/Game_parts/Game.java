package Game_parts;

import GUIComponents.*;
import  Proto.*;

import java.io.FileNotFoundException;

/**
* This is our main class where we start the game, here we create the game window
* @author mzperx
 */

public class Game {

    /**
     * This is our main function
     * @exception FileNotFoundException if there is no music
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        MusicStuff musicObject = new MusicStuff();
        musicObject.playMusic("hansZimmer.wav");
        new MainFrame();
    }
}
