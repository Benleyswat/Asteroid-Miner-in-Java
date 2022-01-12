package GUIComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;

import GObjects.GAsteroid;
import Game_parts.Map;
import Miners.Settler;
import Objects.Asteroid;
import Objects.Spacething;
import Objects.Teleport;
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
 * manages the map view
 */
public class MapView extends JPanel {

    /**
     * the button that zooms back in to the gamePanel view
     */
    private JButton zoomin;
    /**
     * button press actionlistener
     */
    private Clicklistener click;
    /**
     * background picture
     */
    private BufferedImage image;
    /**
     * prototype class that we reused
     */
    private Proto p;
    /**
     * current player
     */
    private Settler currentPlayer;
    /**
     * manages the view change
     */
    private OnPlayListener backToGameView;
    /**
     * the map (pretty self-explanatory)
     */
    private Map map;
    /**
     * contains graphical representation of asteroids on the map
     */
    private ArrayList<GAsteroid> asteroids = new ArrayList<GAsteroid>();
    /**
     * contains labels that display the asteroids' id number above them
     */
    private ArrayList<JLabel> idlabels = new ArrayList<JLabel>();
    /**
     * asteroid buttons for navigating the map
     */
    private ArrayList<JButton> asteroidButtons = new ArrayList<JButton>();
    /**
     * contains a texture highlighting the current asteroid
     */
    private JLabel highLight;

    /**
     * sets up a button
     * @param btn the button it sets up
     */
    public void InitButton(JButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.addActionListener(click);
        btn.setFocusable(false);
    }

    /**
     * constructor of object
     * @param act manages view change
     * @param val proto
     * @throws IOException
     */
    public MapView(OnPlayListener act,Proto val) throws IOException{
        ImageResizer res = new ImageResizer();
        p = val;
        backToGameView=act;
        map =p.getMap();
        currentPlayer = (Settler)p.getCurrent();

        click = new Clicklistener();
        zoomin = new JButton("");
        BufferedImage im = ImageIO.read(new File("pictures\\feny.png"));
        im = res.resize(im, 0.125);
        highLight = new JLabel(new ImageIcon(im));

        //back.setIcon();
        zoomin.setIcon(new ImageIcon("pictures\\zoomin54x54.png"));

        zoomin.setBounds(770, 20, 54, 54);
        highLight.setBounds(((Asteroid)currentPlayer.getSpacething()).getX()-12, ((Asteroid)currentPlayer.getSpacething()).getY()-12, 40, 40);

        this.add(highLight);

        for (int i = 0; i < 50; i++){
            Asteroid current = map.getAsteroids().get(i);
            try {
                asteroids.add(new GAsteroid(current, 0.05));
            } catch (IOException ex) { /*mindig jó, nincs error köszi.*/ }

            this.add(asteroids.get(i).getButton());
            asteroids.get(i).getButton().addActionListener(click);
            asteroidButtons.add(asteroids.get(i).getButton());

            idlabels.add( new JLabel(String.valueOf(current.getId())));
            idlabels.get(i).setBounds(current.getX(), current.getY()-20, 20, 20);
            idlabels.get(i).setForeground(Color.yellow);
            this.add(idlabels.get(i));
        }




        InitButton(zoomin);
        this.add(zoomin);

        this.add(highLight);

        this.setLayout(null);                 //?

        try {
            image = ImageIO.read(new File("pictures\\background.png"));
        } catch (IOException ex) { /*mindig jó, nincs error köszi.*/ }
    }

    /**
     * draws lines among other things
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(image , 0, 0, this); // see javadoc for more info on the parameters

        //g.setColor(Color.BLUE);

        for (int i = 0; i < 50; i++){
            Asteroid current = map.getAsteroids().get(i);

            for (int j = 0; j < current.getNeighbours().size(); j++){
                g.setColor(Color.BLUE);
                Spacething neighbor = current.getNeighbours().get(j);
                if (!neighbor.isAsteroid()){
                    g.setColor(Color.pink);
                    neighbor = ((Teleport) neighbor).getPair();
                    neighbor = neighbor.getNeighbours().get(0);
                }

                g2.drawLine(current.getX()+7, current.getY()+7, neighbor.getX()+7, neighbor.getY()+7);
            }

        }

    }

    /**
     * gives control to the next player
     */
    public void nextPlayer() {
        for (int i = 0; i < p.getMap().getSettlers().size(); i++) {
            if (i == p.getMap().getSettlers().size() - 1) {
                p.step(); // léptet mindenkit ha az utolso player lelépte a lépését
                p.setCurrent(p.getMap().getSettlers().get(0)) ;
                return;
            }
            if (p.getMap().getSettlers().get(i) == p.getCurrent()) {
                p.setCurrent(p.getMap().getSettlers().get(i+1)) ;
                return;
            }
        }
    }

    /**
     * manages buttonpresses
     */
    private class Clicklistener implements ActionListener {

        /**
         * buttonpresses
         * @param e
         */
        public void actionPerformed(ActionEvent e){

            if (e.getSource() == zoomin){
                System.out.println("zoomin");
                try {
                    backToGameView.ChangeToGame(1,false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else{

                for(int i=0; i<50; i++){
                    if(e.getSource() == asteroidButtons.get(i)){
                        if(p.moveSettler(currentPlayer.getId(), i)==true){
                            nextPlayer();
                            try {
                                backToGameView.ChangeToGame(1,false);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }

            }
        }
    }
}