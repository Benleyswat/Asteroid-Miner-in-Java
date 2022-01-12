package Objects;

import Game_parts.Moveable;
import Game_parts.Steppable;
import Miners.Miner;
import Miners.Settler;

import java.util.Random;

public class Teleport extends Spacething implements Moveable, Steppable {

    private boolean enable;         //igaz, ha használható
    private Teleport pair;          //a teleport párja
    boolean megkergult;             //megkergult-e

    // Teleport konstruktor
    public Teleport(int teleportID){
        super(teleportID);
    }

    @Override
    public void removeMiner(Miner m) {}

    @Override
    public void addMiner(Miner m) {}

    public void disable(){
        enable = false;
    }

    // pair settere
    public void setPair(Teleport pair) {
        this.pair = pair;
    }

    // pair gettere
    public Teleport getPair() {
        return pair;
    }

    //ha megkergült, meghívódik és random mozog.
    @Override
    public void Move(int asteroidID) {
        if (asteroidID == neighbours.get(0).getId()){
            System.out.println("Teleport " + id + " nem mozog");
            return;
        }

        for (int i = 0; i < neighbours.get(0).neighbours.size(); i++)
            if (neighbours.get(0).neighbours.get(i).getId() == asteroidID) {
                neighbours.get(0).neighbours.get(i).AddNeighbor(this);
                this.AddNeighbor(neighbours.get(0).neighbours.get(i));

                neighbours.get(0).RemoveNeighbor(this);
                this.RemoveNeighbor(neighbours.get(0));
                System.out.println("Teleport " + id + " A mozgás sikeres ide: Asteroid " + asteroidID + ".");
                return;
            }
        System.out.println("Teleport " + id + " nem mozog");
    }

    //ha megkergült, meghívódik és random mozog.
    @Override
    public void Step(String step) {
        Random r = new Random();
        if (megkergult)
            Move(r.nextInt(neighbours.get(0).neighbours.size()));
    }

    public int getX(){
        Asteroid a = (Asteroid) neighbours.get(0);
        return a.getX();
    }
    public int getY(){
        Asteroid a = (Asteroid) neighbours.get(0);
        return a.getY();
    }

}
