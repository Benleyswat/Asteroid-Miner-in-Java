package Miners;

import Materials.*;
import Objects.*;

import java.util.ArrayList;

/**
 *  Object for Settker
 * @author Mzperx
 */
public class Settler extends Miner{

    /**
     *  A telepesnél lévő telepotokat tárolja
     */
    private ArrayList<Teleport> teleports;

    /**
     *  Settler constructor with id
     * @param a the Asteroid that the Settler is on.
     * @param _id of the Settler.
     */
    public Settler(Asteroid a, int _id){
        super(a, _id);
        teleports = new ArrayList<Teleport>();
        name = "settler";

    }

    /**
     *  Settler konstruktor
     * @param a the Asteroid that the Settler is on.
     */
    public Settler(Asteroid a){
        super(a);
        teleports = new ArrayList<Teleport>();
    }

    /**
     *  If asteroid explodes, settler dies
     */
    @Override
    public void Explode() {
        Die();
    }

    /**
     *  Settler mines on the Asteroid if its available
     *  @return boolean if the mine was successfull or not
     */
    public boolean Mine(){
        if(spacething.isAsteroid()) {
            Asteroid asteroid = (Asteroid) spacething;

            //ha nincs kibányászva és nem üres
            if (asteroid.getLayer() == asteroid.getDigged() && asteroid.getMaterial() != null) {

                AddMaterial(asteroid.getMaterial());
                asteroid.setMaterial(null);

                return true;
            }
            return false;
        }
        return false;
    }

    /**
     *  ellenőrzi a teleport építéséhez szükséges nyersanyagot, és ha tudja, megépíti
     * @param id1 id of the first teleport
     * @param id2 id of the pair
     * @return if the teleport build was succsefull or not
     */
    public boolean BuildTp(int id1, int id2){

        if(teleports.size()<=1){  // csak akkor tud építeni, ha nulla vagy egy teleport van a táskájában
            int iron = 0;
            int water = 0;
            int uranium = 0;

            for(int i = 0; i < backpack.size(); i++){  //keresi

                if(backpack.get(i).getName()=="Uranium"){
                    uranium++;
                }
                else if(backpack.get(i).getName()=="Water"){
                    water++;
                }
                if(backpack.get(i).getName()=="Iron"){
                    iron++;
                }
            }

            if(iron>=2 && water>=1 && uranium>=1){  // ha megvan az anyagmennyiség

                for (int i = 0; i < backpack.size(); i++){ // kiveszi

                    if(backpack.get(i).getName()=="Uranium"){
                        backpack.remove(i);
                        break;
                    }
                }

                for (int i = 0; i < backpack.size(); i++){

                    if(backpack.get(i).getName()=="Uranium"){
                        backpack.remove(i);
                        break;
                    }
                }

                for (int i = 0; i < backpack.size(); i++){

                    if(backpack.get(i).getName()=="Water"){
                        backpack.remove(i);
                        break;
                    }
                }

                for (int i = 0; i < backpack.size(); i++){

                    if(backpack.get(i).getName()=="Iron"){
                        backpack.remove(i);
                        break;
                    }
                }

                Teleport t1 = new Teleport(id1);
                Teleport t2 = new Teleport(id2);

                t1.setPair(t2); // egymmás párjai lesznek
                t2.setPair(t1);

                teleports.add(t1); //bekerül a táskába
                teleports.add(t2);

                return true;
            }
            return false;
        }
        return false;
    }

    //ellenőrzi, hogy az adott aszteroidán van-e a lerakni kívánt telepotkapu párja, ha nem, akkor lerakja
    /**
     *  ellenőrzi, hogy az adott aszteroidán van-e a lerakni kívánt telepotkapu párja, ha nem, akkor lerakja
     * @param t the teleport that is going to be placed on the asteroid.
     * @param asteroid_id  id of the Asteroid that the settler is on.ű
     * @return if the place was succesfull
     */
    public boolean PlaceTp(Teleport t, int asteroid_id) {

        if(spacething.isAsteroid()) {  // ha aszteroidán van
            Asteroid a = (Asteroid) spacething;
            if (t.getPair().getNeighbours().size()==0 || asteroid_id!=t.getPair().getNeighbours().get(0).getId()) {  // ha a párja nincs az adott aszteroidán
                t.AddNeighbor(spacething);
                spacething.AddNeighbor(t);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     *  ellenőzi a nyersanyagokat és megépíti a robotot
     *  @return if the build was succesfull
     */
    public boolean BuildRobot(){

        int iron = 0;
        int coal = 0;
        int uranium = 0;

        for(int i = 0; i < backpack.size(); i++){  //keresi

            if(backpack.get(i).getName()=="Uranium"){
                uranium++;
            }
            else if(backpack.get(i).getName()=="Coal"){
                coal++;
            }
            if(backpack.get(i).getName()=="Iron"){
                iron++;
            }
        }

        if(iron>0 && coal>0 && uranium>0){

            for (int i = 0; i < backpack.size(); i++){ // kiveszi

                if(backpack.get(i).getName()=="Uranium"){
                    backpack.remove(i);
                    break;
                }
            }
            for (int i = 0; i < backpack.size(); i++){

                if(backpack.get(i).getName()=="Coal"){
                    backpack.remove(i);
                    break;
                }
            }
            for (int i = 0; i < backpack.size(); i++){

                if(backpack.get(i).getName()=="Iron"){
                    backpack.remove(i);
                    break;
                }
            }

            Robot r = new Robot(spacething);
            spacething.addMiner(r); // lerakja az adott aszteroidára a robotot
            return true;
        }
        return false;
    }

    /**
     *  Ellenőzi a nyersanyagokat és megépíti a robotot megadott ID-val
     * @param _id the id of the new robot.
     * @return if the build was successfull
     */
    public boolean BuildRobot(int _id){

        int iron = 0;
        int coal = 0;
        int uranium = 0;

        for(int i = 0; i < backpack.size(); i++){  //keresi

            if(backpack.get(i).getName()=="Uranium"){
                uranium++;
            }
            else if(backpack.get(i).getName()=="Coal"){
                coal++;
            }
            if(backpack.get(i).getName()=="Iron"){
                iron++;
            }
        }

        if(iron>0 && coal>0 && uranium>0){

            for (int i = 0; i < backpack.size(); i++){ // kiveszi

                if(backpack.get(i).getName()=="Uranium"){
                    backpack.remove(i);
                    break;
                }
            }
            for (int i = 0; i < backpack.size(); i++){

                if(backpack.get(i).getName()=="Coal"){
                    backpack.remove(i);
                    break;
                }
            }
            for (int i = 0; i < backpack.size(); i++){

                if(backpack.get(i).getName()=="Iron"){
                    backpack.remove(i);
                    break;
                }
            }

            Robot r = new Robot(spacething, _id);
            spacething.addMiner(r); // lerakja az adott aszteroidára a robotot
            return true;
        }
        return false;
    }

    /**
     *  ha kap be valódi anyagot, akkor hozzáadja a táskához
     * @param m the Material that is added to the settler's backpack.
     * @return returns if the add was successfull
     */
    public boolean AddMaterial(Material m){

        if(backpack.size()<10 && m != null) {  // csak akkor adja be, ha van hely még neki
            backpack.add(m);
            return true;
        }
        return false;
    }
    /**
     *  Move of the Settler
     * @param asteroidID the ID of the Asteroid
     */
    @Override
    public void Move(int asteroidID){
        Teleport t;
        boolean tpkapcsolat = false;
        for (int i = 0; i < spacething.getNeighbours().size(); i++){
            if (!spacething.getNeighbours().get(i).isAsteroid()) {
                t = (Teleport) spacething.getNeighbours().get(i);
                t = t.getPair();
                if (t.getNeighbours().get(0).getId() == asteroidID)
                    tpkapcsolat = true;
            }
        }
        if(spacething.isNeigbour(asteroidID) || tpkapcsolat){
            Spacething to = null;
            for (Spacething s: spacething.getNeighbours()) {
                if(s.getId() == asteroidID){
                    to = s;
                }
            }
            if(tpkapcsolat){
                for (int i = 0; i < spacething.getNeighbours().size(); i++) {
                    if (!spacething.getNeighbours().get(i).isAsteroid()) {
                        t = (Teleport) spacething.getNeighbours().get(i);
                        t = t.getPair();
                        to = t.getNeighbours().get(0);
                    }
                }

            }

            if(to.getId()!=-1){
                spacething.removeMiner(this);
                to.addMiner(this);
                spacething = to;
            }
        }

    }

    /**
     *  Getter of the Teleport
     *  @return returns tp
     */
    public Teleport getTP () {
        return teleports.get(0);
    }

    /**
     *  Getter of the Teleports
     *  @return returns teleports
     */
    public ArrayList<Teleport> getTeleports(){return teleports;}


}
