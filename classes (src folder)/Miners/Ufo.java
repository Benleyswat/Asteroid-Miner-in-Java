package Miners;

import Game_parts.Steppable;
import Objects.Asteroid;
import Objects.Spacething;

import java.util.Random;
/**
 *  Object for Ufo
 * @author Mzperx
 */

public class Ufo extends Miner implements Steppable {

    /**
     *  Constructor of the Ufo with id
     * @param _id the id of the new Ufo.
     * @param s the spacething that the Ufo is on.
     */
    public Ufo(Spacething s, int _id) {
        super(s, _id);
        name = "ufo";
    }

    /**
     *  Constructor of the Ufo without id
     * @param s the spacething that the Ufo is on.
     */
    public Ufo(Spacething s) {
        super(s);
    }

    /**
     *  Az ufó nem tud fúrni, igy nála ez egy üres függvény.
     *  @return false
     */
    public boolean Drill() {
        return false;
    }

    /**
     *  Az ufó ellopja a nyersanyagot a  kibányászatlan aszteroidáról, ha nincs felette köpeny.
     *  @return if it did steal
     */
    public boolean Steal() {
        //remove material, ha felvesz valamit.
        if(spacething.isAsteroid()) {
            Asteroid asteroid = (Asteroid) spacething;

            //ha nincs kibányászva és nem üres
            if (asteroid.getLayer() == asteroid.getDigged() && asteroid.getMaterial() != null) {
                asteroid.setMaterial(null);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     *  Aszteroida felrobbanásánál
     */
    @Override
    public void Explode() {
        Die();
    }

    /**
     *  Ufo moves or stays on the same Asteroid that it is currently on or steals the material from the asteroid if it can.
     * @param step it is a word for which action he should do
     */
    @Override
    public void Step(String step) {
        Random r = new Random();
        int randomNeighbor_id = spacething.getNeighbours().get(r.nextInt(spacething.getNeighbours().size())).getId();
        switch (step) {
            case "Move":
                Move(randomNeighbor_id);
                break;

            case "Steal":
                if (Steal()) {
                    Asteroid a = (Asteroid) spacething;
                }

                break;


            case "":
                if (r.nextInt(2) == 0){
                    Move(randomNeighbor_id);
                }
                else{
                    if (Steal()) {
                        Asteroid a = (Asteroid) spacething;
                    }

                }
                break;

            default: System.out.println("Helytelen parancs! Formatum: ehh nem tudom hol a parancsbeolvasos resz"); break;
        }
    }
}
