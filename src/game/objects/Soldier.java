/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris
 */
public class Soldier extends Walker {
     private static final Shape shape = new PolygonShape(
-0.18f,1.88f, 1.59f,1.06f, 0.28f,-1.67f, -1.08f,-1.73f, -1.19f,0.83f);
/** image facing right*/
    private static final BodyImage imageRight =
        new BodyImage("data/soldierRight.png", 5f);
    /** image facing left*/
    private static final BodyImage imageLeft =
        new BodyImage("data/soldierLeft.png", 5f);
    /** the HP counter*/
    private int HP;
    /** the points counter*/
    private int count;
    /** the sounds related to the player(soldier)*/
    /** token sound*/
    private static SoundClip tokenSound;
    /** the jumping player(soldier) sound*/
    private static SoundClip jumpSound;
    /** the enemy and healer sound*/
    private static SoundClip hpgiverSound;
    private static SoundClip enemySound;
    public Soldier(World world) {
        /** create the soldier*/
        super(world, shape);
        addImage(imageRight);
        /** set the hp of the player(soldier)*/
        HP = 100;
        /** the initial points of the player(soldier)*/
        count=0;
        
       
        
    }
    static {
    try {
            /** Open an audio input stream for all the sounds the player(soldier)*/
            tokenSound = new SoundClip("data/token-taken.wav");   
            jumpSound = new SoundClip("data/jump.wav");
           hpgiverSound= new SoundClip("data/hpgiverSound.wav");
           enemySound= new SoundClip("data/enemySound.wav");
             
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }
    /** play the jumping sound of the player*/
    public void playSound(){
    jumpSound.play();
    }
    /** set the points of the soldier*/
    public void setCount(int count) {
        this.count = count;
    }

   
    /**when the player(soldier) interacts with the small enemy*/
    public void Damage() {
        HP=HP-25;
        System.out.println("Enemy hit: " + 25+"; HP : "+ HP+"; ");
        /** plays the small enemy sound*/
        enemySound.play();
    }
    /**when the player(soldier) interacts with the big enemy*/
    public void CritDamage() {
        HP=HP-35;
        System.out.println("Critical Enemy hit: " + 35+";  HP : "+ HP+";");
         /** plays the big enemy sound*/
        enemySound.play();
    }
    /** when the player(soldier) interacts with the heal token*/
    public void HPHeal() {
        HP=HP+15;
        count++;
        System.out.println("HP added: " + 15+";  HP : "+ HP+";");
        System.out.println("Score added: " + 1 +";  Score : "+ count+";");
        tokenSound.play();
    }
    /** when the player(soldier) interacts with the healer*/
    public void HPExtraHeal(){
        hpgiverSound.play();
        HP=HP+20;
        count++;
      System.out.println("HP added: " + 20+";  HP : "+ HP+";");
        System.out.println("Score added: " + 1 +";  Score : "+ count+";");
    }
    /** when the player(soldier) interacts with the extra HP giving healer*/
    public void HPDoubleExtraHeal(){
        hpgiverSound.play();
        HP=HP+30;
        count++;
      System.out.println("HP added: " + 40+";  HP : "+ HP+";");
        System.out.println("Score added: " + 1 +";  Score : "+ count+";");
    }
    /*to change the image of the player to face it to left*/
    public void lookLeft(){
        /**removes the current image*/
    removeAllImages();
    /**sets the image facing left*/
    addImage(imageLeft);
    }
    /*to change the image of the player to face it to right*/
    public void lookRight(){
        /**removes the current image*/
    removeAllImages();
    /**sets the image facing right*/
        addImage(imageRight);
    
    }
    /** returns the score of the player*/
    public int getCount() {
        return count;
    }

    /** returns the HP of the player*/
    public int getHP() {
        return HP;
    }
}
