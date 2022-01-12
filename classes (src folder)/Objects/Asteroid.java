package Objects;

import Game_parts.Steppable;
import Materials.*;
import Miners.Miner;

import java.util.ArrayList;
import java.util.Random;

public class Asteroid extends Spacething implements Steppable {
    /**
     * layer of the asteroid
     */
    private int layer;              // Sziklaréteg
    /**
     * digged layer of the asteroid
     */
    private int digged;             // Kiásott rétegek
    /**
     * true if perihelion
     */
    private boolean perihelion;     // napközelség
    /**
     * contained material
     */
    private Material material;
    /**
     * miners on it
     */
    private ArrayList<Miner> miners;
    /**
     * x coordinate
     */
    private int x;
    /**
     * y coordinate
     */
    private int y;

    /**
     * random number
     */
    private Random rand = new Random();

    /**
     * Asteroid constructor
     * @param asteroidID id
     * @param lay layer
     * @param d digged
     * @param mat material
     */
    public Asteroid(int asteroidID, int lay, int d, String mat){
        super(asteroidID);
        layer = lay;
        digged = d;
        miners = new ArrayList<Miner>();
        if(mat.equals( "Coal")){material = new Coal(this);}
        if(mat.equals("Iron")){material = new Iron(this);}
        if(mat.equals("Water")){material = new Water(this);}
        if(mat.equals("Uranium")){material = new Uranium(this);}
        if(mat.equals("null")){material = null;}
    }

    /**
     * Asteroid constructor
     * @param asteroidID id
     */
    public Asteroid(int asteroidID) {
        super(asteroidID);
        layer = (int) rand.nextInt(7)+3;    //Min: 3, Max: 9

        digged = 0;     //0 réteg van eddig ásva

        miners = new ArrayList<Miner>();

        //Random anyag kerül az aszteroida közepébe
        int mat = (int) rand.nextInt(5);
        switch (mat){
            case 0: material = new Coal(this); break;
            case 1: material = new Iron(this); break;
            case 2: material = new Uranium(this); break;
            case 3: material = new Water(this); break;
            case 4: material = null;
        }

    }

    /**
     * Asteroid constructor
     * @param asteroidID id
     * @param lay layer
     * @param d digged
     * @param mat material
     * @param x x coordiante
     * @param y y coordinate
     */
    public Asteroid(int asteroidID, int lay, int d, String mat, int x, int y){
        super(asteroidID);
        layer = lay;
        digged = d;
        miners = new ArrayList<Miner>();
        if(mat.equals( "Coal")){material = new Coal(this);}
        if(mat.equals("Iron")){material = new Iron(this);}
        if(mat.equals("Water")){material = new Water(this);}
        if(mat.equals("Uranium")){material = new Uranium(this);}
        if(mat.equals("null")){material = null;}
        this.x = x;
        this.y = y;
    }

    /**
     * Asteroid constructor
     * @param asteroidID id
     * @param layer layer
     * @param perihelion perihelion
     * @param material material
     */
    public Asteroid(int asteroidID, int layer, boolean perihelion, Material material) {
        super(asteroidID);

        this.material = material;
        this.layer = layer;
        this.digged = 0;
        this.miners = new ArrayList<Miner>();
        this.id = asteroidID;
        this.perihelion = perihelion;
    }

    /**
     * Decrease the layers on the asteroid
     */
    public void removeLayer(){
        digged++;
    }

    /**
     * If the asteroid contains material, it removes
     */
    public void RemoveMaterial() {
        digged = layer;
        material = null;
    }

    /**
     * Remove a miner from the asteroid
     * @param m miner
     */
    public void removeMiner(Miner m){
        miners.remove(m);
    }

    /**
     * Add a miner to the asteroid
     * @param m miner
     */
    public void addMiner(Miner m){
        miners.add(m);
    }

    /**
     * Explodes the asteroid and the miners on it
     */
    public void Explode(){
        for (Miner m: miners) {
            m.Explode();
        }
    }

    /**
     * getter of layers
     * @return layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * getter of material
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * getter of perihelion
     * @return perihelion
     */
    public boolean getPerihelion(){
        return perihelion;
    }

    /**
     * getter of digged
     * @return digged
     */
    public int getDigged() {
        return digged;
    }

    /**
     * getter of id
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * getter of miners
     * @return miners
     */
    public ArrayList<Miner> getMiners(){
        return  miners;
    }

    /**
     * setter of layer
     * @param layer num of layer
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    /**
     * setter of material
     * @param m material
     */
    public void setMaterial(Material m){
        this.material = m;
    }

    /**
     * Add a neighbor to the asteroid
     * @param s spacething
     */
    @Override
    public void AddNeighbor(Spacething s) {
        super.AddNeighbor(s);
    }

    public void listNeighbors(){
        if(neighbours.isEmpty()==true){
            System.out.println("Hiba: nincs szomszed "+this.getId());
        }
        for(Spacething s: neighbours){
            if(s.isAsteroid()==true)
            System.out.println("Asteroid "+s.getId());
            else{
                System.out.println("Teleport "+s.getId());
            }
        }
    }

    /**
     * If ist asteroid true
     * @return true
     */
    @Override
    public boolean isAsteroid(){return true;}

    /**
     * Step function
     * @param step type of step
     */
    @Override
    public void Step(String step){
        Random r = new Random();
        if (r.nextInt(2) == 0) {
            if (!perihelion)
            perihelion = true;
        }
        else {
            if (perihelion)
            perihelion = false;
        }
    }

    /**
     * Is somebody wins the game true
     * @return boolean
     */
    public boolean checkWin(){
        ArrayList<Material> bolygon= new ArrayList<>();
        for(Miner m: miners){
            for(Material mat: m.getBackpack()){
                bolygon.add(mat);
            }
        }
        int[] matTomb =new int[]{ 0,0,0,0 };
        for (Material mat: bolygon){
            if (mat.getName().equals("Uranium")){
                matTomb[0]++;
            }
            else if(mat.getName().equals("Water")){
                matTomb[1]++;
            }
            else if(mat.getName().equals("Coal")){
                matTomb[2]++;
            }
            else if(mat.getName().equals("Iron")){
                matTomb[3]++;
            }
        }
        for(int i=0; i<4; i++){
            if(matTomb[i]<3){
                return false;
            }
        }
        return true;
    }

    /**
     * Getter of x coordinate
     * @return x
     */
    public int getX(){return x;}
    /**
     * Getter of y coordinate
     * @return y
     */
    public int getY(){return y;}
}
