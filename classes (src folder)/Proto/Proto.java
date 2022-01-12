package Proto;

import Game_parts.*;
import Materials.*;
import Miners.*;
import Objects.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Prototype class that we use for the visual game as well.
 * @author Mzperx
 */
public class Proto {

    /**
     * A map witch contains all the objects that exists in the game.
     */
    private Map map = new Map();

    /**
     * Marks the current player who needs to choose its next move.
     */
    private Settler currentPlayer;

    /**
     * Default Constructor for a proto object that sets the current player to first
     */
    public Proto(){
        currentPlayer=map.getSettlers().get(0);
        //loadMap("");
    }

    /**
     * Reads from a file and loads it in to the map
     * @param mapName   the map which gets the file
     */
    public void loadMap(String mapName){
        try{
            File f = new File("maps\\" + mapName);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                if (line == null) {
                    break;
                }
                String array[] = line.split(" ");

                switch(array[0]){
                    /*case "*":
                        Asteroid a = new Asteroid(space_id, Integer.parseInt(array[1]), Integer.parseInt(array[2]), array[3]);
                        //System.out.println("Aszteroida: " + space_id);
                        space_id++;
                        asteroids.add(a);
                        break;*/
                    /*case "+":
                        Teleport t1 = null, t2 = null;
                        for (int i = 0; i < asteroids.size(); i++) {
                            if (Integer.parseInt(array[1]) == asteroids.get(i).getId()) {
                                t1 = new Teleport(space_id, asteroids.get(i));
                                //System.out.println("Teleport t1: " + space_id);
                                space_id++;
                            }
                            if (Integer.parseInt(array[2]) == asteroids.get(i).getId()) {
                                t2 = new Teleport(space_id, asteroids.get(i));
                                //System.out.println("Teleport t2: " + space_id);
                                space_id++;
                            }
                        }
                        t1.setPair(t2);
                        t2.setPair(t1);
                        teleports.add(t1);
                        teleports.add(t2);
                        break;*/
                    /*case "=":
                        int egyik = -1, masik = -1;
                        for (int i = 0; i < asteroids.size(); i++) {
                            if (Integer.parseInt(array[1]) == asteroids.get(i).getId()) {
                                egyik = i;
                            }
                            if (Integer.parseInt(array[2]) == asteroids.get(i).getId()) {
                                masik = i;
                            }
                        }
                        asteroids.get(egyik).AddNeighbor(asteroids.get(masik));
                        asteroids.get(masik).AddNeighbor(asteroids.get(egyik));
                        //System.out.println("Asteroid " + asteroids.get(masik).getId() + " es Asteroid " + asteroids.get(egyik).getId() + " szomszedok");
                        break;*/
                    /*case "s":
                        for (int i = 0; i < asteroids.size(); i++) {
                            if (Integer.parseInt(array[1]) == asteroids.get(i).getId()) {
                                Settler s = new Settler(asteroids.get(i), miner_id);
                                //System.out.println("Settler: " + miner_id);
                                miner_id++;
                                settlers.add(s);
                                break;
                            }
                        }
                        break;*/
                    /*case "r":
                        for (int i = 0; i < asteroids.size(); i++) {
                            if (Integer.parseInt(array[1]) == asteroids.get(i).getId()) {
                                Robot r = new Robot(asteroids.get(i), miner_id);
                                //System.out.println("Robot: " + miner_id);
                                miner_id++;
                                robots.add(r);
                                break;
                            }
                        }
                        break;*/
                    /*case "u":
                        for (int i = 0; i < asteroids.size(); i++) {
                            if (Integer.parseInt(array[1]) == asteroids.get(i).getId()) {
                                Ufo u = new Ufo(asteroids.get(i), miner_id);
                                //System.out.println("Ufo: " + miner_id);
                                miner_id++;
                                ufos.add(u);
                                break;
                            }
                        }
                        break;*/
                }
            }

            //map.setAsteroids(asteroids);
            //map.setTeleports(teleports);
            //map.setSettlers(settlers);
            //map.setRobots(robots);
            //map.setUfos(ufos);
            //System.out.println("A pálya betöltése sikeres");
            sc.close();
        }
        catch (FileNotFoundException e){
            System.out.println("A pálya betöltése sikertelen");
        }
        currentPlayer=map.getSettlers().get(0);
    }

    /**
     * Moves a Settler to the chosen (adjacent) Asteroid
     * @param settler_id        The moving settler
     * @param asteroid_id       The destination is this asteroid
     * @return (true or false)  If the move was successful return true else return false
     */
    public boolean moveSettler(int settler_id, int asteroid_id){
        for (int i = 0; i < map.getSettlers().size(); i++) {
            if (map.getSettlers().get(i).getId() == settler_id) {
                map.getSettlers().get(i).Move(asteroid_id);
                if (asteroid_id == map.getSettlers().get(i).getAsteroid()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A miner drills off a layer on the Asteroid
     * @return  (true or false)  If the move was successful return true else return false
     */
    public boolean drillMiner(){
        int settler_id= currentPlayer.getId();
        for (int i = 0; i < map.getSettlers().size(); i++) {
            if (map.getSettlers().get(i).getId() == settler_id) {
                if (map.getSettlers().get(i).Drill()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * A miner mines the material from the Asteroid
     * @return  (true or false)  If the move was successful return true else return false
     */
    public boolean mineMiner(){
        int settler_id= currentPlayer.getId();
        for (int i = 0; i < map.getSettlers().size(); i++){
            if (map.getSettlers().get(i).getId() == settler_id) {
                return map.getSettlers().get(i).Mine();
            }
        }
        return false;
    }

    /**
     * A settler build a pair of teleports and adds it to its backpack
     * @return  (true or false)  If the move was successful return true else return false
     */
    public boolean buildTeleport(){
        int settler_id= currentPlayer.getId();
        int _id;
        if(map.getTeleports().size()<2){
            _id = 0;
        }
        else{
            _id = map.getTeleports().get(map.getTeleports().size()-1).getId()+2;
        }
        for (int i = 0; i < map.getSettlers().size(); i++) {
            if(map.getSettlers().get(i).getId()==settler_id){
                if(map.getSettlers().get(i).BuildTp(_id, _id+1)){
                    Teleport t = new Teleport(_id);
                    Teleport t2 = new Teleport(_id+1);
                    t.setPair(t2);
                    t2.setPair(t);
                    map.AddTeleport(t);
                    map.AddTeleport(t2);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * A settler places a teleport at the Asteroid
     * @param teleport_id   The chosen teleport
     * @return      (true or false)  If the move was successful return true else return false
     */
    public boolean placeTeleport( int teleport_id){
        int settler_id= currentPlayer.getId();
        for (int i = 0; i < map.getSettlers().size(); i++) {
            if(map.getSettlers().get(i).getId()==settler_id){
                for (int j = 0; j < map.getTeleports().size(); j++) {
                    if(map.getTeleports().get(j).getId()==teleport_id){
                        if(map.getSettlers().get(i).PlaceTp(map.getTeleports().get(j), map.getSettlers().get(i).getAsteroid())){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Adds a material to the current settler if there is space for it
     * @param material      The material which will be added to backpack
     * @param settler_id    The active settler
     */
    public void addToBackpack(String material, int settler_id){
        for (int i = 0; i < map.getSettlers().size(); i++) {
            if(map.getSettlers().get(i).getId()==settler_id){
                switch (material){
                    case "Water":
                        Water w = new Water(null);
                        map.getSettlers().get(i).AddMaterial(w);
                        break;
                    case "Iron":
                        Iron ir = new Iron(null);
                        map.getSettlers().get(i).AddMaterial(ir);
                        break;
                    case "Coal":
                        Coal c = new Coal(null);
                        map.getSettlers().get(i).AddMaterial(c);
                        break;
                    case "Uranium":
                        Uranium u = new Uranium(null);
                        map.getSettlers().get(i).AddMaterial(u);
                        break;
                }
            }
        }
    }

    /**
     * A settler build a robot and let it live
     * @return  (true or false)  If the move was successful return true else return false
     */
    public boolean buildRobot(){
        int settler_id= currentPlayer.getId();
        Settler settler = map.getSettlers().get(settler_id);

        //Calculates the highest _id in the game and adds one to it
        int new_id = 0;
        for (int z = 0; z < map.getRobots().size();z++) {
            int temp = map.getRobots().get(z).getId();
            if(temp > new_id) new_id = temp;
        }
        new_id += 1;

        if (settler.BuildRobot(new_id)) {
            Miner robot = map.getAsteroids().get(settler.getAsteroid()).getMiners().get(map.getAsteroids().get(settler.getAsteroid()).getMiners().size() - 1);
            map.getRobots().add((Robot) robot);

            return true;
        }
        else {return false;}
    }

    /**
     * Save the map param to a (new or existing) file
     * @param saveName      The file name
     */
    public void save(String saveName){
        File f = new File("maps\\" + saveName + ".txt");
        try {
            FileWriter fWriter = new FileWriter(f);
            for (Settler s: map.getSettlers()){         // Settler;Position;Backpack
                fWriter.write("s;" + s.getAsteroid());
                for(Material m: s.getBackpack()){
                    fWriter.write(" " + m.getName());
                }
                fWriter.write("\n");
            }

            for (Robot r: map.getRobots()){             //Robot;Position
                fWriter.write("r;" + r.getAsteroid()+"\n");
            }

            for (Ufo u: map.getUfos()){                 //Ufo;Position
                fWriter.write("u;" + u.getAsteroid()+"\n");
            }

            for (Teleport t: map.getTeleports()){       //Ufo;Position
                fWriter.write("t;" + t.getNeighbours().get(0).getId());
                fWriter.write(";" + t.getPair().getId()+"\n");
            }

            for (Asteroid a: map.getAsteroids()){       //Asteroid;Neighbours;Material;Layer;Digged
                fWriter.write("*;");
                for(Spacething s: a.getNeighbours()){
                    fWriter.write(s.getId() + " ");
                }
                if(a.getMaterial() != null)
                    fWriter.write(";" + a.getMaterial().getName());
                else
                    fWriter.write(";null");
                fWriter.write(";" + a.getLayer());
                fWriter.write(";" + a.getDigged());
                fWriter.write("\n");
            }

            fWriter.close();
            //System.out.println("Mentés sikeres");
        } catch (IOException e) {
            //System.out.println("Sikertelen mentés");
            e.printStackTrace();
        }
    }

    /**
     * At the end of every turn moves with all the Moveable objects (Robots Ufos and Teleports)
     * @return      Only returns with true if no settlers left on the map (the players lose)
     */
    public boolean step(){

        for (int i = 0; i < map.getRobots().size(); i++) {
            map.getRobots().get(i).Step("");
        }
        for (int i = 0; i < map.getUfos().size(); i++) {
            map.getUfos().get(i).Step("");
        }
        for (int i = 0; i < map.getTeleports().size(); i++) {
            map.getTeleports().get(i).Step("");
        }
        for (int i = 0; i < map.getAsteroids().size(); i++){
            map.getAsteroids().get(i).Step("");
        }

        for(int j = 0; j < map.getAsteroids().size();j++){
            Asteroid a = map.getAsteroids().get(j);
            if (a.getPerihelion()==true && a!=null){
                if(a.getMaterial()!= null && a.getDigged() == a.getLayer()){
                    if(a.getMaterial().PeriMining() == true) {
                        a.Explode();
                        for (int i = 0; i < a.getNeighbours().size(); i++) {
                            if (a.getNeighbours().get(i).isAsteroid() == false) {
                                Teleport t = (Teleport) a.getNeighbours().get(i);
                                map.getTeleports().remove(t.getPair());
                                map.getTeleports().remove(t);
                            }
                        }
                        map.getAsteroids().remove(a);
                    }
                }
            }
        }
        for(Settler s: map.getSettlers()){
            Asteroid as = (Asteroid) s.getSpacething();
            if(as.getPerihelion()==true){
                for(Material m: s.getBackpack()){
                    if(m.getName()=="Uranium"){
                        m.PeriMining();
                    }

                }
            }
        }

        map.Step("");
        if(map.getSettlers().isEmpty()==true){
            return true;
        }
        return false;
    }

    /**
     * Getter for getting the map from the proto
     * @return  The whole map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Checks the win condition events, if there is one ends the game.
     * @return      Returns with true if the players win.
     */
    public boolean EndGame() {
        for(Asteroid a: map.getAsteroids()){
            if(a.checkWin()){
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for the current player
     * @return  With the current player param.
     */
    public Miner getCurrent() {
        return currentPlayer;
    }

    /**
     * Sets the current player to the chosen player
     * @param s     Sets currentPlayer to this.
     */
    public void setCurrent(Settler s) {
        currentPlayer=s;
    }

    /**
     * Counts the players alive
     * @return  With the number of players (integer)
     */
    public int getPlayerCount(){
        int count = 0;
        for(Settler s: map.getSettlers()) {
            if (s.getAsteroid()==currentPlayer.getAsteroid()){
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the robots alive
     * @return  With the number of robots (integer)
     */
    public int getRobotCount(){
        int count = 0;
        for(Robot r: map.getRobots()) {
            if (r.getAsteroid()==currentPlayer.getAsteroid()){
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the ufos alive
     * @return  With the number of ufos (integer)
     */
    public int getUfoCount(){
        int count = 0;
        for(Ufo u: map.getUfos()) {
            if (u.getAsteroid()==currentPlayer.getAsteroid()){
                count++;
            }
        }
        return count;
    }

}