package game.objects;

import city.cs.engine.*;

/**
 * Doors in a game. When the actor collides with a door, if
 * the current level is complete the game is advanced to the
 * next level. 
 */
/**
 *
 * @author Chris
 */
public class Door extends StaticBody {   
    
    /**
     * Initialise a new door.
     * @param world The world.
     */
    private static final BodyImage door =
        new BodyImage("data/door.png", 5.6f);
    public Door(World world) {
        super(world, new BoxShape(1.1f, 2.8f));
        addImage(door);
    }
}
