package Materials;

import Objects.Asteroid;

/**
 * Class to represent coal material
 * @author mzperx
 */
public class Coal extends Material{

    /**
     * Coal constructor
     * @param a the asteroid
     */
    public Coal(Asteroid a)  {
        setName("Coal");
        setAsteroid(a);         //Containing asteroid
    }
}