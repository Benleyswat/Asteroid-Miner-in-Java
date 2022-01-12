package Materials;

import Objects.Asteroid;

/**
 * Class to represent uranium material
 * @author mzperx
 */
public class Uranium extends Material{
    /**
     * Counting to 3 then explodes if the asteroid is near to sun
     */
    private int charged;

    /**
     * Uranium constructor
     * @param a the asteroid
     */
    public Uranium(Asteroid a){
        setName("Uranium");
        setAsteroid(a);
        charged = 0;
    }

    /**
     * Uranium explodes perihelion at the 3rd time
     * @return true if there is no explosion
     */
    @Override
    public boolean PeriMining(){
        charged++;

        if (charged == 3) {
            return true;
        }
        return false;
    }
}