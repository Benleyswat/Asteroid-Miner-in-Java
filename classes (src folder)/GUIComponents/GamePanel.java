package GUIComponents;

import Materials.Material;
import Miners.Miner;
import Miners.Settler;
import Objects.Asteroid;
import Proto.Proto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Game Panel is the main game component
 * @author mzperx
 */
public class GamePanel extends JPanel {

    /**
     * Buttons to be displayed
     */
    private JButton tp, robot, dig, mine, move, zoomin, zoomout, skip, tp1, tp2, tp3;

    /**
     * The backpack of the settler
     */
    private JLabel resourceInventory, tpInventory;

    /**
     * Asteroid kinds
     */
    private JLabel a_teli_kozel, a_teli_tavol, a_ures_kozel, a_ures_tavol;

    /**
     * Materials of the asteroids
     */
    private JLabel water, coal, iron, uranium;

    /**
     * Key listener
     */
    private Clicklistener click;

    /**
     * Background of the game
     */
    private BufferedImage image,robotImage,settlerImage,ufoImage;

    /**
     * The steps of the game
     */
    private Proto p;

    /**
     * Containing the list of things to be displayed on screen
     */
    private ArrayList<JButton> Things = new ArrayList<>();

    /**
     * The inventory of a settler
     */
    private ArrayList<JButton> inventory = new ArrayList<>();

    /**
     * Change the screen to map
     */
    private OnPlayListener toMapView;

    /**
     * Initialize button
     * @param btn the button to be set
     */
    public void InitButton(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.addActionListener(click);
        btn.setFocusable(false);
    }


    /**
     * GamePanel constructor
     * @param act screen to be displayed
     * @param val The seed of the game
     * @throws IOException
     */
    public GamePanel(OnPlayListener act,Proto val) throws IOException {
        p=val;
        toMapView=act;

        //Add materials to backpack
        p.addToBackpack("Water", 51);
        p.addToBackpack("Water", 51);
        p.addToBackpack("Iron", 51);
        p.addToBackpack("Water", 51);
        p.addToBackpack("Water", 51);
        p.addToBackpack("Iron", 51);
        p.addToBackpack("Uranium", 51);
        p.addToBackpack("Coal", 51);

        //Init buttons
        click = new Clicklistener();
        zoomout = new JButton("");
        skip = new JButton("");
        zoomin = new JButton("");
        tp = new JButton("");
        robot = new JButton("");
        dig = new JButton("");
        mine = new JButton("");
        move = new JButton("");
        tp1 = new JButton("");
        tp2 = new JButton("");
        tp3 = new JButton("");

        //Load pictures
        resourceInventory = new JLabel(new ImageIcon("pictures\\resourceinventory191x386.png"));
        tpInventory = new JLabel(new ImageIcon("pictures\\tpinventory210x63.png"));

        a_teli_kozel = new JLabel(new ImageIcon("pictures\\a_teli_kozel.png"));
        a_teli_tavol = new JLabel(new ImageIcon("pictures\\a_teli_tavol.png"));
        a_ures_kozel = new JLabel(new ImageIcon("pictures\\a_ures_kozel.png"));
        a_ures_tavol = new JLabel(new ImageIcon("pictures\\a_ures_tavol.png"));

        water = new JLabel(new ImageIcon("pictures\\water70x70.png"));
        coal = new JLabel(new ImageIcon("pictures\\coal70x70.png"));
        iron = new JLabel(new ImageIcon("pictures\\iron70x70.png"));
        uranium = new JLabel(new ImageIcon("pictures\\uranium70x70.png"));

        //Set pictures
        skip.setIcon(new ImageIcon("pictures\\skip200x53.png"));
        zoomout.setIcon(new ImageIcon("pictures\\zoomout54x54.png"));
        zoomin.setIcon(new ImageIcon("pictures\\zoomin54x54.png"));
        tp.setIcon(new ImageIcon("pictures\\tp200x53.png"));
        robot.setIcon(new ImageIcon("pictures\\robot200x53.png"));
        dig.setIcon(new ImageIcon("pictures\\dig200x53.png"));
        mine.setIcon(new ImageIcon("pictures\\mine200x53.png"));
        move.setIcon(new ImageIcon("pictures\\move200x53.png"));
        tp1.setIcon(new ImageIcon("pictures\\tp.png"));
        tp2.setIcon(new ImageIcon("pictures\\tp.png"));
        tp3.setIcon(new ImageIcon("pictures\\tp.png"));

        //Set bounds
        zoomout.setBounds(770, 20, 54, 54);
        zoomin.setBounds(700, 20, 54, 54);
        tp.setBounds(50, 570, 200, 53);
        robot.setBounds(300, 570, 200, 53);
        dig.setBounds(50, 500, 200, 53);
        mine.setBounds(300, 500, 200, 53);
        move.setBounds(550, 500, 200, 53);
        resourceInventory.setBounds(50, 20, 191, 386);
        tpInventory.setBounds(40, 420, 210, 63);
        skip.setBounds(550, 570, 200, 53);
        tp1.setBounds(65, 435, 30, 30);
        tp2.setBounds(125, 435, 30, 30);
        tp3.setBounds(195, 435, 30, 30);

        a_teli_kozel.setBounds(350, 180, 300, 300);
        a_teli_tavol.setBounds(350, 180, 300, 300);
        a_ures_kozel.setBounds(350, 180, 300, 300);
        a_ures_tavol.setBounds(350, 180, 300, 300);

        water.setBounds(465, 300, 70, 70);
        coal.setBounds(465, 300, 70, 70);
        iron.setBounds(465, 300, 70, 70);
        uranium.setBounds(465, 300, 70, 70);

        //Add buttons to the Things list
        Things.add(zoomout);
        //Things.add(zoomin);
        Things.add(tp);
        Things.add(robot);
        Things.add(dig);
        Things.add(mine);
        Things.add(move);
        Things.add(skip);
        Things.add(tp1);
        Things.add(tp2);
        Things.add(tp3);

        for (JButton jb : Things) {
            InitButton(jb);
            this.add(jb);
        }

        //Set opaque to false
        resourceInventory.setOpaque(false);
        tpInventory.setOpaque(false);
        a_teli_kozel.setOpaque(false);
        a_teli_tavol.setOpaque(false);
        a_ures_kozel.setOpaque(false);
        a_ures_tavol.setOpaque(false);
        water.setOpaque(false);
        coal.setOpaque(false);
        iron.setOpaque(false);
        uranium.setOpaque(false);

        this.setLayout(null);

        //Add buttons to the gamepanel
        this.add(resourceInventory);
        this.add(tpInventory);
        this.add(water);
        this.add(coal);
        this.add(iron);
        this.add(uranium);
        this.add(a_teli_kozel);
        this.add(a_teli_tavol);
        this.add(a_ures_kozel);
        this.add(a_ures_tavol);

        //Load images
        try {
            image = ImageIO.read(new File("pictures\\background.png"));
            settlerImage = ImageIO.read(new File("pictures\\settler45x75.png"));
            ufoImage = ImageIO.read(new File("pictures\\ufo139x75.png"));
            robotImage = ImageIO.read(new File("pictures\\robot112x75.png"));
        } catch (IOException ex) {

        }

        //Refresh backpack
        refreshBp();
    }

    /**
     * Paint component to the screen
     * @param g Grapic variable to print on
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Set default params of graphic
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
        g.drawImage(settlerImage, 710, 150, this);
        g.drawImage(ufoImage, 650, 230, this);
        g.drawImage(robotImage, 660, 310, this);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(p.getPlayerCount()) , 790, 210);
        g.drawString(String.valueOf(p.getUfoCount()) , 790, 290);
        g.drawString(String.valueOf(p.getRobotCount()) , 790, 370);
        g.setColor(Color.YELLOW);
        g.drawString(String.valueOf(p.getCurrent().getSpacething().getId()) , 470, 160);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.RED);
        g.drawString("Current Player is Agent" + String.valueOf(p.getCurrent().getId()) , 270, 40);

        //Set buttons visible false
        a_teli_kozel.setVisible(false);
        a_teli_tavol.setVisible(false);
        a_ures_kozel.setVisible(false);
        a_ures_tavol.setVisible(false);

        water.setVisible(false);
        coal.setVisible(false);
        iron.setVisible(false);
        uranium.setVisible(false);

        //Set current asteroids grapic
        Asteroid curr_a = (Asteroid) p.getCurrent().getSpacething();
        if (curr_a.getDigged() == curr_a.getLayer()) {  // If asteroid is digged
            if (curr_a.getPerihelion()) { // if its near sun
                a_ures_kozel.setVisible(true);
            } else { // if it isn't near to sun
                a_ures_tavol.setVisible(true);
            }

            //Display material if digged
            if (curr_a.getMaterial() != null && curr_a.getMaterial().getName() == "Water") water.setVisible(true);
            if (curr_a.getMaterial() != null && curr_a.getMaterial().getName() == "Iron") iron.setVisible(true);
            if (curr_a.getMaterial() != null && curr_a.getMaterial().getName() == "Coal") coal.setVisible(true);
            if (curr_a.getMaterial() != null && curr_a.getMaterial().getName() == "Uranium") uranium.setVisible(true);
        } else {
            if (curr_a.getPerihelion()) { // If asteroid is digged
                a_teli_kozel.setVisible(true);
            } else { // if it isn't near to sun
                a_teli_tavol.setVisible(true);
            }
        }
        Settler s = (Settler)p.getCurrent();
        tp1.setVisible(false);
        tp2.setVisible(false);
        tp3.setVisible(false);

        if(s.getTeleports().size()>2){
            tp3.setVisible(true);
        }
        if(s.getTeleports().size()>1){
            tp2.setVisible(true);
        }
        if(s.getTeleports().size()>0){
            tp1.setVisible(true);
        }
    }

    /**
     * Refresh backpack on the screen to see the current settler always
     */
    public void refreshBp() {
        for (JButton b : inventory) {
            this.remove(b);
        }
        int y = 0;
        int x = 0;


        //Check material in the current backpack and display it
        for (Material m : p.getCurrent().getBackpack()) {
            if (y == 5) {
                x = 1;
                y = 0;
            }

            try {
                JButton b = m.drawMaterial(x * 90 + 70, y * 75 + 30,  m);
                inventory.add(b);
                this.add(b);
            } catch (IOException e) {
                e.printStackTrace();
            }

            y++;

        }
        repaint();
    }

    /**
     * Switch to the next player
     */
    public void nextPlayer() {
        if (p.EndGame() == true) {  //Check if settlers won
            try { //If loose, then go to the loose screen
                toMapView.ChangeToGame(4,true);
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(p.getCurrent()==p.getMap().getSettlers().get(p.getMap().getSettlers().size()-1)){
            if(p.step()==true){ //Move everything if the last player moved
                try { //If loose then go to loose screen
                    toMapView.ChangeToGame(4,false);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
            if(p.getMap().getSettlers().isEmpty()==false){
                p.setCurrent(p.getMap().getSettlers().get(0)) ;
            }

        }
        else{
            int kovetkezo=0;
            for (int i = 0; i < p.getMap().getSettlers().size(); i++) {
                if (p.getMap().getSettlers().get(i)==p.getCurrent()){
                    kovetkezo=i+1;
                }
            }
            p.setCurrent(p.getMap().getSettlers().get(kovetkezo)) ;
        }
        refreshBp();
    }

    /**
     * Key listener for the game panel
     */
    private class Clicklistener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == tp) {   //Try to build teleport
                if (p.buildTeleport()) {
                    nextPlayer();
                }
                else {
                    new BadMovement();
                }
            } else if (e.getSource() == robot) { //Try to build robot
                if (p.buildRobot()) {
                    nextPlayer();
                } else {
                    new BadMovement();
                }
            } else if (e.getSource() == dig) {  //Try to drill
                if (p.drillMiner()) {
                    nextPlayer();
                }
                else {
                    new BadMovement();
                }
            } else if (e.getSource() == mine) {  //Try to dig
                if (p.mineMiner()) {
                    nextPlayer();
                }
                else {
                    new BadMovement();
                }
            } else if (e.getSource() == move) {  //Try to move (switch to map view)
                try {
                    toMapView.ChangeToGame(2,false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == zoomout) {
                try {
                    toMapView.ChangeToGame(2,false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == skip) {
                nextPlayer();
            }else if (e.getSource() == tp1) {
                Settler s = (Settler)p.getCurrent();
                if (p.placeTeleport(s.getTeleports().get(0).getId())) {
                    nextPlayer();
                }
            }else if (e.getSource() == tp2) {
                Settler s = (Settler)p.getCurrent();
                if (p.placeTeleport(s.getTeleports().get(1).getId())) {
                    nextPlayer();
                }
            }else if (e.getSource() == tp3) {
                Settler s = (Settler)p.getCurrent();
                if (p.placeTeleport(s.getTeleports().get(2).getId())) {
                    nextPlayer();
                }
            }
        }
    }
}