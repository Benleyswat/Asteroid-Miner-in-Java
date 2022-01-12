package Game_parts;


import Miners.*;
import Objects.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is our main class where we start the game, here we create the game window
 * @author mzperx
 */
public class Map implements Steppable {
    /**
     * List of settlers
     */
    private ArrayList<Settler> settlers;
    /**
     * List of robots
     */
    private ArrayList<Robot> robots;
    /**
     * List of ufos
     */
    private ArrayList<Ufo> ufos;
    /**
     * List of asteroids
     */
    private ArrayList<Asteroid> asteroids;
    /**
     * List of teleports
     */
    private ArrayList<Teleport> teleports;
    /**
     * Count of different materials on map
     */
    private int diffMat;
    /**
     * CRandom number
     */
    private Random rand = new Random();

    /**
     * Map constructor without parameters
     */
    public Map(){
        asteroids = new ArrayList<>();      //Initialize list containing all asteroids on map
        settlers = new ArrayList<>();       //Initialize list containing all settlers on map
        ufos = new ArrayList<>();           //Initialize list containing all ufos on map
        robots = new ArrayList<>();         //Initialize list containing all robots on map
        teleports = new ArrayList<>();      //Initialize list containing all teleports on map

        generateMap();
    }

    /**
     * Map constructor without parameters
     * @param noAsteroids number of asteroids to create
     * @param noSettler number of settler
     */
    public Map(int noAsteroids, int noSettler) {
        asteroids = new ArrayList<>();      //Initialize list containing all asteroids on map
        settlers = new ArrayList<>();       //Initialize list containing all settlers on map
        ufos = new ArrayList<>();           //Initialize list containing all ufos on map
        robots = new ArrayList<>();         //Initialize list containing all robots on map
        teleports = new ArrayList<>();      //Initialize list containing all teleports on map


        //Create asteroids
        for (int i = 0; i < noAsteroids; i++) {
            Asteroid ball = new Asteroid(i);    //Create new asteroid
            asteroids.add(ball);                //Add asteroid to asteroid list
        }

        //Create settlers
        for (int i = 0; i < noSettler; i++){
            int r = rand.nextInt(noAsteroids);          //Add settler to a random asteroid
            Settler s = new Settler(asteroids.get(r));  //Create new settler
            settlers.add(s);                            //Add settler to Settlers list
        }

        //Fill neighbours
        for (int i = 0;i < noAsteroids; i++){
            if(i!=0 && i!=noAsteroids-1){
                asteroids.get(i).AddNeighbor(asteroids.get(i-1));
                asteroids.get(i).AddNeighbor(asteroids.get(i+1));
            }else if(i == noAsteroids-1){
                asteroids.get(i).AddNeighbor(asteroids.get(i-1));
                asteroids.get(i).AddNeighbor(asteroids.get(0));
                asteroids.get(0).AddNeighbor(asteroids.get(i));
                asteroids.get(0).AddNeighbor(asteroids.get(1));
            }
        }

        diffMat = 4;
    }

    /**
     * Map constructor without parameters
     * Sunstorm
     * If target given, than only that asteroid, if target is -1 than all asteroid
     * @param target tha id og the targeted asteroid
     */

    public void SolarStorm(int target) {

        //Call sunstorm for all asteroid
        if (target == -1) {
            for (int i = 0; i < asteroids.size(); i++) {  //Check if settler is hidden on all asteroid
                if (asteroids.get(i).getLayer() != asteroids.get(i).getDigged() && asteroids.get(i).getMaterial() != null) {
                        for (int j = 0; j < asteroids.get(i).getMiners().size(); j++) {     //If settler on the asteroid is not hidden than...
                            if(asteroids.get(i).getMiners().get(j).getName() == "settler"){
                                settlers.remove(asteroids.get(i).getMiners().get(j));       //...Settler dies
                                asteroids.get(i).getMiners().get(j).Die();
                            }
                    }
                }
            }
        }else{
        //Cal sunstorm on the target asteroid
            if (asteroids.get(target).getLayer() != asteroids.get(target).getDigged() && asteroids.get(target).getMaterial() != null) {     //Check if settler is hidden
                for (int j = 0; j < asteroids.get(target).getMiners().size(); j++) {
                    if(asteroids.get(target).getMiners().get(j).getName()=="settler") {     //If settler on the asteroid is not hidden than...
                        settlers.remove(asteroids.get(target).getMiners().get(j));          //...Settler dies
                        asteroids.get(target).getMiners().get(j).Die();
                    }
                }
            }
        }
    }

    /**
     * One step of the map (sunstorm)
     * @param step the type of step
     */
    @Override
    public void Step(String step) {
        Random r = new Random();
        if (r.nextInt(10) < 2)
            SolarStorm(-1);
    }

    /**
     * Getter for the list of settlers
     * @return list of settlers
     */
    public  ArrayList<Settler> getSettlers(){return settlers;}

    /**
     * Getter for the list of robots
     * @return list of robots
     */
    public ArrayList<Robot> getRobots(){return robots;}

    /**
     * Getter for the list of asteroids
     * @return list of asteroids
     */
    public ArrayList<Asteroid> getAsteroids(){return asteroids;}

    /**
     * Getter for the list of teleports
     * @return list of teleports
     */
    public ArrayList<Teleport> getTeleports(){return teleports;}

    /**
     * Getter for the list of ufos
     * @return list of ufos
     */
    public ArrayList<Ufo> getUfos() {return ufos;}

    /**
     * Add teleport to the list of teleports
     * @param t teleport
     */
    public void AddTeleport(Teleport t){teleports.add(t);}

    /**
     * List the things on the map
     * @param n type of list
     */
    public void list(String n){
        boolean vandolog=false;
        if( n.equals("Settlers") || n.equals("Map")){
            for (Settler s: settlers){
                vandolog=true;
            }
        }
        if( n.equals("Asteroids") || n.equals("Map")){
            for (Asteroid a: asteroids){
                vandolog=true;
            }
        }
        if( n.equals("Teleport") || n.equals("Map")){
            for (Teleport t: teleports){
                vandolog=true;
            }
        }
        if( n.equals("Robots") || n.equals("Map")){
            for (Robot r: robots){
                vandolog=true;
            }
        }
        if( n.equals("Ufos") || n.equals("Map")){
            for (Ufo u: ufos){
                vandolog=true;
            }
        }
        if(vandolog==false){
        }
    }

    /**
     * Generate map
     */
    public void generateMap(){
        int id = 0;
        for (int i = 0; i < 9; i++){
            asteroids.add(randomAsteroid(id++, "Uranium"));
            asteroids.add(randomAsteroid(id++, "Water"));
            asteroids.add(randomAsteroid(id++, "Coal"));
            asteroids.add(randomAsteroid(id++, "Iron"));
        }//9*4=36 generated material
        for (int i = 0; i < 14; i++){
            switch (rand.nextInt(5)){
                case 0:
                    asteroids.add(randomAsteroid(id++, "no"));
                    break;
                case 1:
                    asteroids.add(randomAsteroid(id++, "Uranium"));
                    break;
                case 2:
                    asteroids.add(randomAsteroid(id++, "Water"));
                    break;
                case 3:
                    asteroids.add(randomAsteroid(id++, "Coal"));
                    break;
                case 4:
                    asteroids.add(randomAsteroid(id++, "Iron"));
                    break;
            }
        }//14 random
        Asteroid n;
        for(int i = 0; i < 50; i++){
            if (asteroids.get(i).getNeighbours().size() == 0){
                n = findClosest(asteroids.get(i), 1);
                asteroids.get(i).AddNeighbor(n);
                n.AddNeighbor(asteroids.get(i));
            }
            if (asteroids.get(i).getNeighbours().size() == 1 && rand.nextInt(10) < 9){
                n = findClosest(asteroids.get(i), 2);
                asteroids.get(i).AddNeighbor(n);
                n.AddNeighbor(asteroids.get(i));
            }
            if (asteroids.get(i).getNeighbours().size() == 2 && rand.nextInt(10) < 7){
                n = findClosest(asteroids.get(i), 3);
                asteroids.get(i).AddNeighbor(n);
                n.AddNeighbor(asteroids.get(i));
            }
            if (asteroids.get(i).getNeighbours().size() == 3 && rand.nextInt(10) < 5){
                n = findClosest(asteroids.get(i), 4);
                asteroids.get(i).AddNeighbor(n);
                n.AddNeighbor(asteroids.get(i));
            }
        }//szomszedkotogetos magic

        for (int i = 0; i < 10; i++){

            settlers.add(new Settler(asteroids.get(rand.nextInt(50)), i));
        }//10 settler random helyen

        for (int i = 0; i < 5; i++){
            ufos.add(new Ufo(asteroids.get(rand.nextInt(50)), i));
        }
    }

    /**
     * Pick a random asteroid
     * @param id asteroid id
     * @param mat
     * @return the asteroid
     */
    private Asteroid randomAsteroid(int id, String mat) {

        int digged = 0;
        if (rand.nextInt(10) < 1) digged = 1;

        boolean kozel;
        Asteroid a;
        int cnt = 0;
        do{
            kozel = false;
            a = new Asteroid(id, rand.nextInt(9), digged, mat, rand.nextInt(820)+10, rand.nextInt(530)+90);
            for (int i = 0; i < id; i++)
                if (aDistanceSq(asteroids.get(i), a) < 2500)
                    kozel = true;
            cnt++;
        }while(kozel || cnt == 20);
        return a;
    }

    //Find the closest asteroid to a asteroid
    private Asteroid findClosest(Asteroid a, int n){
        Asteroid first = new Asteroid(1000, 0, 0, "", 10000, 10000);
        Asteroid second = new Asteroid(1000, 0, 0, "", 10000, 10000);
        Asteroid third = new Asteroid(1000, 0, 0, "", 10000, 10000);
        Asteroid fourth = new Asteroid(1000, 0, 0, "", 10000, 10000);

        for (int i = 0; i < 50; i++){
            if (a.getId() != asteroids.get(i).getId()){
                if (aDistanceSq(asteroids.get(i), a) < aDistanceSq(a, fourth)){
                    if (aDistanceSq(asteroids.get(i), a) < aDistanceSq(a, third)) {
                        if (aDistanceSq(asteroids.get(i), a) < aDistanceSq(a, second)){
                            if (aDistanceSq(asteroids.get(i), a) < aDistanceSq(a, first)){
                                fourth = third;
                                third = second;
                                second = first;
                                first = asteroids.get(i);
                            }
                            else {
                                fourth = third;
                                third = second;
                                second = asteroids.get(i);
                            }
                        }
                        else {
                            fourth = third;
                            third = asteroids.get(i);
                        }
                    }
                    else
                        fourth = asteroids.get(i);
                }
            }
        }
        switch (n){
            case 1: return first;
            case 2: return second;
            case 3: return third;
            case 4: return fourth;
            default: return null;
        }
    }

    //Distance between two asteroids
    private double aDistanceSq(Asteroid a, Asteroid b){
        return (a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY());
    }
}
