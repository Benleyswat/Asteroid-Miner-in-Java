package GUIComponents;


import Proto.Proto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * Change to game
 * @author mzperx
 */
interface OnPlayListener {

    void ChangeToGame(int i, boolean b) throws IOException;
}

/**
 * Main frame
 * @author mzperx
 */
public class MainFrame extends JFrame implements OnPlayListener{

    /**
     * Menu variable
     */
    private Menu menu;

    /**
     * Game Panel variable
     */
    private GamePanel gamePanel;

    /**
     * Map variable
     */
    private MapView map;

    /**
     * Seed of the game
     */
    private Proto p;

    /**
     * MainFrame constructor
     */
    public MainFrame() {
        this.add(new Menu(this));
        this.setTitle("Asteroid Game");
        this.setSize(new Dimension(840+16,630+39));//kristof thing:igy lett 1200x900 a benne levo megjelenitett cucc mert ez beleszamolja a szurke savokat
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        p = new Proto();
    }


    /**
     * Change to main game function
     * @param x type of panel
     * @param wine true if somebody wins the game
     * @throws IOException
     */
    @Override
    public void ChangeToGame(int x,boolean wine) throws IOException { changePanel(x,wine); }

    /**
     * Change panel
     * @param x which panel to change
     * @throws IOException
     */
    public void changePanel(int x,boolean wine) throws IOException {
        this.getContentPane().removeAll();
        if(x==1){
            this.getContentPane().add(new GamePanel(this,p));
        }
        else if(x==2){
            this.getContentPane().add(new MapView(this,p));
        }
        else if(x==3){
            this.getContentPane().add(new Menu(this));
        }
        else if(x==4){
            p = new Proto();
            this.getContentPane().add(new EndScreen(this, p,wine));
        }
        this.revalidate();
    }

}