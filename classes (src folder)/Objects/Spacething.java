package Objects;

import Miners.Miner;

import java.util.ArrayList;

public abstract class Spacething {   //Az égitesteket reprezentálja (a játékban aszteroidák és teleportkapuk).

    protected ArrayList<Spacething> neighbours = new ArrayList<Spacething>();
    protected int id;                 //ID


    // Spacething konstruktor
    public Spacething(int asteroidID){
        id = asteroidID;
    }
    //ha a megadott égitest az adott égitest szomszédja, akkor igaz értéket vesz fel, ha nem, akkor hamis
    public boolean isNeigbour(int spacethingID){
        for (Spacething n: neighbours) {
            if(n.getId() == spacethingID) return true;
        }
        return false;
    }

    // neighbours gettere
    public ArrayList<Spacething> getNeighbours() {
        return neighbours;
    }

    // hozzáad egy új szomszédot
    public void AddNeighbor(Spacething s){neighbours.add(s);}

    // eltávolít egy szomszédot
    public void RemoveNeighbor(Spacething s){

        for (int i = 0; i < neighbours.size(); i++){
            if(s== neighbours.get(i)){
                neighbours.remove(i);
            }
        }
    }

    // id gettere
    public int getId(){
        return id;
    }
    // id settere
    public void setId(int _id){id= _id;}
    // eltávolít egy minert
    public abstract void removeMiner(Miner m);
    // hozzáad egy minert
    public abstract void  addMiner(Miner m);
    // asteroid gettere
    public boolean isAsteroid(){return false;}

    public abstract int getX();
    public abstract int getY();
}
