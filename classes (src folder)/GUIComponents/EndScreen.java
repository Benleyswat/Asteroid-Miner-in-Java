package GUIComponents;

import Proto.Proto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Screen for displaying endgame win or loose
 * @author mzperx
 */
public class EndScreen extends JPanel {

    /**
     * Back button to go back to menu
     */
    private OnPlayListener backToMenu;

    /**
     * Background
     */
    private BufferedImage image;

    /**
     * Click Listener
     */
    private Clicklistener click;
    /**
     * Bool for decide win or loose
     */
    private boolean wine;

    /**
     * Back button
     */
    private JButton back;

    /**
     * Image resizer for resizing pictures
     */
    private ImageResizer ir = new ImageResizer();

    /**
     * EndScreen constructor
     * @param act Watching screen change
     * @param val Tartalmazza a játék magját
     * @param win Win or loose screen
     * @throws IOException
     */
    public EndScreen(OnPlayListener act, Proto val ,boolean win) throws IOException {
        backToMenu=act;
        wine=win;
        click= new Clicklistener();
        back = new JButton("");

        //Loading back button
        try {
            BufferedImage bi = ImageIO.read(new File("pictures\\back.png"));
            bi = ir.resize(bi, 0.082);
            back.setIcon(new ImageIcon(bi));
            back.setOpaque(false);
            back.setContentAreaFilled(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(null);
        back.setBounds(300,550,267,41);
        back.setFocusable(false);
        back.addActionListener(click);
        back.setBorderPainted(false);
        this.add(back);

        //Set background
        try {
            image = ImageIO.read(new File("pictures\\background.png"));
        } catch (IOException ex) { /*mindig jó, nincs error köszi.*/ }

    }

    /**
     * Paint components to the screen
     * @param g Graphic variable to display images
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // kirajzolja a képet
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.setColor(Color.BLUE);

        //If the game won
        if(wine){
            g.drawString("Gratulálunk nyertél." , 300, 300);
        }

        //If the game won't
        else{
            g.drawString("Vesztettél." , 300, 300);
        }
    }

    /**
     * Watching the key actions
     */
    private class Clicklistener implements ActionListener { //gombok megnyomását kezeli

        public void actionPerformed(ActionEvent e){
            if (e.getSource() == back){ //ha a backre kattintanak akkor a menü megnyilik
                try {
                    backToMenu.ChangeToGame(3,false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}