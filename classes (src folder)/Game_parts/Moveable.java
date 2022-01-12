package Game_parts;

/**
 * Interface for moveable objects
 * @author mzperx
 */
public interface Moveable {

    /**
     * Responsible for all moveable objects moving
     * @param asteroidID id of asteroid
     */
    void Move(int asteroidID);
}
