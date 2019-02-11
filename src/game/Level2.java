package game;

import game.objects.HPtoken;
import game.objects.Enemy;
import game.objects.CritEnemy;
import game.objects.IncreasedHPGiver;
import city.cs.engine.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel implements ActionListener {

    private static final int NUM_ORANGES = 3;
    private Enemy enemy;
    private CritEnemy critEnemy;
    private Timer timer;
    private IncreasedHPGiver increasedHPGiver;
    private int secondsLeft=30;
    
    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);
        soldier.setCount(1);
        timer = new Timer(1000, this);
        timer.start();

         // make the ground
        Shape groundShape = new BoxShape(26, 0.75f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -12f));
        ground.setFillColor(Color.blue);
        
      
        // make platforms
        Shape boxShape = new BoxShape(4, 0.5f);
        Body platform2 = new StaticBody(this, boxShape);
        platform2.setPosition(new Vec2(5, -5f));
        platform2.setFillColor(Color.blue);
        
        
        // make a character
       
        
         enemy = new Enemy(this);
        enemy.setPosition(new Vec2(8, -8));
        enemy.addCollisionListener(new Pickup(getSoldier()));
        critEnemy = new CritEnemy(this);
        critEnemy.setPosition(new Vec2(8, -8));
        critEnemy.addCollisionListener(new Pickup(getSoldier()));
        increasedHPGiver = new IncreasedHPGiver(this);
        increasedHPGiver.setPosition(new Vec2(5, -2));
        increasedHPGiver.addCollisionListener(new Pickup(getSoldier()));
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
