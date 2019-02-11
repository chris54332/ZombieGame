package game;

import game.objects.HPtoken;
import game.objects.Enemy;
import game.objects.Soldier;
import game.objects.CritEnemy;
import city.cs.engine.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jbox2d.common.Vec2;

/**
 * Level 1 of the game
 */
public class Level1 extends GameLevel implements ActionListener {
    /**points required to complete the level*/
    private static final int NUM_ORANGES = 1;
    
    /**the small enemy*/
    private Enemy enemy;
       /**the big enemy*/
    private CritEnemy critEnemy;
    private Timer timer;
        int counter;
        /**the time for the current level*/
        private int secondsLeft=30;
    
        
   
    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game ) {
        super.populate(game);
        /**sets the points counter of the soldier to 0*/
        soldier.setCount(0);
        /**create the timer*/
        timer = new Timer(1000, this);
        timer.start();
         // make the ground
        Shape groundShape = new BoxShape(26, 0.75f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -12f));
        
       
        // make a character
         enemy = new Enemy(this);
        enemy.setPosition(new Vec2(8, -8));
        enemy.addCollisionListener(new Pickup(getSoldier()));
        critEnemy = new CritEnemy(this);
        critEnemy.setPosition(new Vec2(8, -8));
        critEnemy.addCollisionListener(new Pickup(getSoldier()));
        Body hptoken = new HPtoken(this);
        hptoken.setPosition(new Vec2(-5, -3));
        hptoken.addCollisionListener(new Pickup(getSoldier()));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-7, -8);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(18, -8.7f);
    }
    @Override
    public boolean isCompleted() {
        return getSoldier().getCount() >= NUM_ORANGES;
    } 
    @Override
    public void actionPerformed(ActionEvent ae) {
        secondsLeft--;
    }
    @Override
    public int getSecondsLeft() {
        return secondsLeft;
    }
    
}
