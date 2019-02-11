package game;

import game.objects.Door;
import game.objects.Soldier;
import city.cs.engine.*;
import java.awt.Image;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    protected Soldier soldier;
    private static volatile boolean running = false;
    public static volatile boolean gameOver = false;
    public static volatile boolean paused = false;
    public static long lastLoopTime = System.currentTimeMillis();
    
    public Soldier getSoldier() {
        return soldier;
    }
    
    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        /** create the player*/
        soldier = new Soldier(this);
        /**set its gravity/position*/
        soldier.setGravityScale(5);
        soldier.setPosition(startPosition());
        /**create the lvl up door*/
        Door door = new Door(this);
        door.setPosition(doorPosition());
        /** setting the collision listener for the door*/
        door.addCollisionListener(new DoorListener(game));
    }
    
    /** The initial position of the player. */
    public abstract Vec2 startPosition();
    
    /** The position of the exit door. */
    public abstract Vec2 doorPosition();
    
    /** Is this level complete? */
    public abstract boolean isCompleted();
    
    /** seconds left from the timer*/
    public abstract int getSecondsLeft();
            
}
