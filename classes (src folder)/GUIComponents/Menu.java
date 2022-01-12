package GUIComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The main menu
 * @author mzperx
 */
public class Menu extends JPanel {

    /**
     * Buttons to be displayed
     */
    private JButton play,help,exit, back;

    /**
     * key listener
     */
    private Clicklistener click;

    /**
     * Background image
     */
    private BufferedImage image;

    /**
     * Help background
     */
    private BufferedImage help_img;

    /**
     * Used to switch to game panel
     */
    private OnPlayListener playListener;

    /**
     * Display help or not to display
     */
    private boolean help_bool = false;

    /**
     * Image resizer for resizing image
     */
    private ImageResizer ir = new ImageResizer();

    /**
     * Menu constructor
     * @param act Panel to change
     */
    public Menu(OnPlayListener act){
        playListener=act;
        click= new Clicklistener();
        play= new JButton("");
        help= new JButton("");
        exit= new JButton("");
        back= new JButton("");

        play.setIcon(new ImageIcon("pictures\\playcropped.png" ));
        play.setOpaque(false);
        play.setContentAreaFilled(false);
        //play.setBorderPainted(false);

        help.setIcon(new ImageIcon("pictures\\helpcropped.png" ));
        help.setOpaque(false);
        help.setContentAreaFilled(false);

        exit.setIcon(new ImageIcon("pictures\\exitcropped.png" ));
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);

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

        play.setBounds(28,50,800,123);
        help.setBounds(28,250,800,123);
        exit.setBounds(28,450,800,123);
        back.setBounds(300,550,267,41);

        play.setFocusable(false);
        help.setFocusable(false);
        exit.setFocusable(false);
        back.setFocusable(false);

        play.addActionListener(click);
        help.addActionListener(click);
        exit.addActionListener(click);
        back.addActionListener(click);

        play.setBorderPainted(false);
        help.setBorderPainted(false);
        exit.setBorderPainted(false);
        back.setBorderPainted(false);

        back.setVisible(false);

        this.add(play);
        this.add(help);
        this.add(exit);
        this.add(back);
        try {
            image = ImageIO.read(new File("pictures\\background.png"));
        } catch (IOException ex) {}
        try {
            help_img = ImageIO.read(new File("pictures\\help_szoveg.png"));
            help_img = ir.resize(help_img, 0.2);
        } catch (IOException ex) {}
    }

    /**
     * paint components to the menu
     * @param g Graphics will be displayed on this
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
        if(help_bool) g.drawImage(help_img, 20, 20, this);
    }

    /**
     * Key listener
     */
    class Clicklistener implements ActionListener {

        public void actionPerformed(ActionEvent e){
            if (e.getSource() == play){  //If play then game starts
                try {
                    playListener.ChangeToGame(1,false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if (e.getSource() == help){ //Display help

                help_bool=true;
                play.setVisible(false);
                help.setVisible(false);
                exit.setVisible(false);
                back.setVisible(true);


                System.out.println("help");
            }
            else if (e.getSource() == exit){ //Click on exit closes the window
                System.out.println("exit");
                //close the window
                System.exit(0);

            }

            else if (e.getSource() == back){ //Click on back to step back
                System.out.println("back");
                help_bool=false;

                play.setVisible(true);
                help.setVisible(true);
                exit.setVisible(true);
                back.setVisible(false);

            }
        }
    }

}