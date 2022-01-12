
package Materials;

import Objects.Asteroid;

/**
 * Class to represent iron material
 * @author mzperx
 */
public class Iron extends Material{

    /**
     * Iron constructor
     * @param a the asteroid
     */
    public Iron(Asteroid a){
        setName("Iron");
        setAsteroid(a);         //Containing asteroid
    }
}