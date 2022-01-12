
package Materials;

import Objects.Asteroid;

/**
 * Class to represent water material
 * @author mzperx
 */
public class Water extends Material{

    /**
     * Water constructor
     * @param a the asteroid
     */
    public Water(Asteroid a){
        setName("Water");
        setAsteroid(a);         //Containing asteroid
    }

    /**
     * Water disapears if it is near sun
     * @return false if there is sublimation
     */
    @Override
    public boolean PeriMining(){
        asteroid.RemoveMaterial();
        return false;
    }
}