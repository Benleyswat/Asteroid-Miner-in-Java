package Miners;

import Game_parts.Moveable;
import Materials.*;
import Objects.*;

import java.util.ArrayList;

/**
 *  Abstract class for Miners
 * @author Mzperx
 */
public abstract class Miner implements Moveable {

    /**
     *  Contains which asteroid contains player
     */
    protected Spacething spacething;
    /**
     *  Storage of Materials
     */
    protected ArrayList<Material> backpack;

    /**
     *  Name of the object
     */
    protected int id;

    /**
     *  Name of the object
     */
    protected String name;


    /**
     *  Miner constructor with id
     * @param s the Spacething that the miner is on.
     * @parma _id the id of the miner.
     */
    public Miner(Spacething s, int _id){

        //Sets the asteroid which contains the settler
        spacething = s;

        //Initialize storage
        backpack = new ArrayList<>();

        //Sets the miner to the asteroid
        s.addMiner(this);

        //Sets the id
        id = _id;
    }

    /**
     *  Miner constructor without id
     * @param s the Spacething that the miner is on.
     */
    public Miner(Spacething s){
        //Sets the asteroid which contains the settler
        spacething = s;

        //Initialize storage
        backpack = new ArrayList<>();

        //Sets the miner to the asteroid
        s.addMiner(this);
    }

    /**
     *  Miner moves to asteroid
     * @param asteroidID the ID of the asteroid
     */
    @Override
    public void Move(int asteroidID){
        if(spacething.isNeigbour(asteroidID)){          //Check if asteroid is in the neighbourhood
            Spacething to = null;
            for (Spacething s: spacething.getNeighbours()) {
                if(s.getId() == asteroidID){
                    to = s;                             //Change the asteroid of settler
                }
            }
            if(to.getId()!=-1){ /// ezmi? csak kivancsi vagyok-axel !!!!!!!!!
                spacething.removeMiner(this);
                to.addMiner(this);
                spacething = to;
            }
        }
    }

    /**
     *  Miner Die
     */
    public void Die(){
        spacething.removeMiner(this);
    }

    /**
     *  Miner drill
     * @return boolean if the drill was succesfull or not
     */
    public boolean Drill(){
        if(spacething.isAsteroid()){        //If miner is on asteroid
            Asteroid a = (Asteroid) spacething;
            if(a.getLayer()-a.getDigged()!= 0){     //If asteroids layer > 0
                a.removeLayer();                    //Drill the asteroid
                return true;           //Return true if successfull, return false if isn't successfull
            }
            return false;
        }
        return false;
    }

    /**
     *  Miners have different behavior when asteroid explodes
     */
    public abstract void Explode();

    /**
     *  Getter of backpack
     * @return backpack
     */
    public ArrayList<Material> getBackpack() {
        return backpack;
    }

    /**
     *  Getter of spacething
     * @return spacething
     */
    public Spacething getSpacething(){
        return spacething;
    }

    /**
     *  Geter of miner id
     *  @return id
     */
    public int getId(){return id;}

    /**
     *  Getter of miners steroid
     *  @return asteroid id
     */
    public int getAsteroid(){return spacething.getId();}

    /**
     *  Getter of name
     *  @return name
     */
    public String getName(){return name;}
}
