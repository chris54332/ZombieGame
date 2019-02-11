package game;

import game.objects.Soldier;
import city.cs.engine.*;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if the current level is complete the game is advanced to the next level. 
 */
/**
 *
 * @author Chris
 */
public class DoorListener implements CollisionListener {
    private Game game;
    
    public DoorListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        Soldier player = game.getSoldier();
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            
            if(game.getLevel()<4)
                game.checkNextDoor();
            else 
                game.goNextLevel();
        }else
            game.uncheckNextDoor();
    }
}
