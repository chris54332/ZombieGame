package game;

import game.objects.Soldier;
import city.cs.engine.*;
import game.objects.Door;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key handler to control an Walker.
 */
/**
 *
 * @author Chris
 */
public class Controller extends KeyAdapter {
    private static final float JUMPING_SPEED = 30;
    private static final float WALKING_SPEED = 4;
    
    private Game game;
    private Walker body;
    private Door door;
    
    public Controller(Walker body,Game game) {
        this.body = body;
        this.game = game;
    }
    public void doorSetter(Door door){
    this.door=door;
    }
    
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) { // Q = quit
            System.exit(0);
        } 
        //
        else if (code == KeyEvent.VK_W) { // J = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
               Soldier soldier=(Soldier) body;
               soldier.playSound();
            }
        } else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED);
            Soldier soldier=(Soldier) body;
            soldier.lookLeft();
        } else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED); 
            Soldier soldier=(Soldier) body;
            soldier.lookRight();
        } 
        /**checks if the player(soldier) is in the proximity of the door */
        else if (code == KeyEvent.VK_E) {
            /**if */
           if(game.checkNextDoor==true)
           {
               if(game.getLevel()<4)
               {
                game.goNextLevel();
                System.out.println("Going to next level...");
               }
           }
        }
    }
    
    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
@Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
        }
    }
    
    public void setBody(Walker body) {
        this.body = body;
    }
}
