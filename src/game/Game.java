package game;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import game.objects.Soldier;
import city.cs.engine.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

/**
 * The computer game.
 */

public class Game {

    /** The World in which the bodies move and interact. */
    protected GameLevel world;
      private int level;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = WIDTH/5*3;  
    /** @param HP the health points of the player; */
    private int HP;
    private Controller controller;
    /** A graphical display of the world (a specialised JPanel). */
    private MyView view;
    private boolean gameMusicTracker;
    private boolean gameMusic2Tracker;
    /** @param gameMusic param for first background track of the game*/
    private static SoundClip gameMusic;
    /** @param gameMusic2 param for second background track of the game*/
    private static SoundClip gameMusic2;
    protected boolean checkNextDoor=false;
    /** Initialise a new Game. */
    static{
    try {
           gameMusic = new SoundClip("data/track1.wav");   // Open an audio input stream
           gameMusic2 = new SoundClip("data/track2.wav");
           
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }  
           
    }
    public Game() {
           /*the HP counter*/
        HP=100;
        /* the level counter*/
        level = 1;
        /**make the new world set to level 1 */
        world = new Level1();
        /**populate the world*/
        world.populate(this);
        /**to play the track continuously*/
        gameMusic.loop();
        /**set the volume of the first background track*/
        gameMusic.setVolume(0.01);
        /**set the volume of the second background track*/
        gameMusic2.setVolume(0.05);
        gameMusicTracker=true;
          
        /** make a view*/
        view = new MyView(world,getSoldier(), WIDTH, HEIGHT);
        view.setZoom(25);

        /** display the view in a frame*/
       JFrame frame = new JFrame("Multi-level game");
        Container buttons = new ControlPanel(this);
        frame.add(buttons, BorderLayout.SOUTH);
        /** quit the application when the game window is closed*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        /** display the world in the window*/
        frame.add(view);
        /**don't let the game window be resized*/
        frame.setResizable(false);
        /**size the game window to fit the world view*/
        frame.pack();
        /** make the window visible*/
        frame.setVisible(true);
        /** get keyboard focus*/
        frame.requestFocus();
        /**give keyboard focus to the frame whenever the mouse enters the view*/
        view.addMouseListener(new GiveFocus(frame));
        
        controller = new Controller(getSoldier(),this);
        frame.addKeyListener(controller);
       //  world.addStepListener(new Tracker(view, world.getSoldier()));
       //JFrame debugView = new DebugViewer(world, 500, 500);

            System.out.println("Level: 1"); 
            System.out.println("Needed score: 1"); 
        // start!
        world.start();
    }
/**
 * 
 * @return 
 */
    /**returns the current level*/
    public int getLevel() {
        return level;
    }
    /**returns the current soldier's data*/
    public Soldier getSoldier() {
        return world.getSoldier();
    }
    /**plays the first track*/
    public void track1(){
        /**checks if the first track is already playing*/
        if(gameMusicTracker!=true)
        {
            gameMusic2.stop();
            gameMusic.loop();
            /**sets the counter for the first track to true*/
        gameMusicTracker=true;
        /**sets the counter for the second track to false*/
        gameMusic2Tracker=false;
        }
    }
    /**plays the second track*/
    public void track2(){
        /**checks if the second track is already playing*/
        if(gameMusic2Tracker!=true)
        {
        gameMusicTracker=false;
        gameMusic.stop();
        
        gameMusic2.loop();
        gameMusic2Tracker=true;
        gameMusicTracker=false;
        }
    }
    /**method for pausing the game*/
    public void pause(){
    world.stop();
    }
    /**method for resuming the game*/
    public void start(){
    
    world.start();
    }
    /**method for restarting the game to level 1 */
    public void restart(){
    level=1;
    world= new Level1();
    world.populate(this);
    view.setSoldier(getSoldier());
    controller.setBody(getSoldier());
    /**counter for gameOver panel set to false*/
            view.setFinish(false);
    
    view.setWorld(world);
    view.setZoom(25);
    world.start();
    /**plays the first track*/
    track1();
    }
    /**to start to level 2*/
    public void level2(){
    level = 2;
    world= new Level2();
    world.populate(this);
    view.setSoldier(getSoldier());
    controller.setBody(getSoldier());
    view.setWorld(world);
    view.setZoom(25);
    view.setFinish(false);
    world.start();
    /**plays the second track*/
    track2();
    }
    /**to start to level 3*/
    public void level3(){
     level = 3;
     world= new Level3();
   
    world.populate(this);
    view.setSoldier(getSoldier());
    controller.setBody(world.getSoldier());
    view.setWorld(world);
    view.setZoom(25);
    view.setFinish(false);
    world.start();
    
    track1();
    }
    /**to start to level 4 */
    public void level4(){
     level = 4;
     world= new Level4();
    world.populate(this);
    view.setSoldier(getSoldier());
    controller.setBody(world.getSoldier());
    view.setWorld(world);
    view.setZoom(25);
    view.setFinish(false);
    world.start();
    
    track2();
    }
    /** Is the current level of the game finished? */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    /** Sets the checker true if the player is near the door. */
    public void checkNextDoor(){
        checkNextDoor=true;
    }
    /** Sets the checker false if the player is not near the door. */
    public void uncheckNextDoor(){
        checkNextDoor=false;
    }
    /** Advance to the next level of the game. */
    public void goNextLevel() {
        
        if (level ==4) {
            controller.setBody(world.getSoldier());
            System.out.println("game finished");
            view.setFinish(true);
            world.stop();
        } else if(level==1) {
            /**sets the checker for the door listener to off to not be able to go to the next level through the 'E' key button*/
            uncheckNextDoor();
            System.out.println("Level: 2"); 
            System.out.println("Needed score: 3"); 
            view.setFinish(false);
            
            level++;
            /** get a new world*/
            world = new Level2();
            /**fill it with bodies */
            
            track2();
            world.populate(this);
            /** switch the keyboard control to the new player*/
            
            controller.setBody(world.getSoldier());
           
           
            view.setSoldier(getSoldier());
             /**show the new world in the view*/
            view.setWorld(world);
           
            view.setZoom(25);
            world.start();
        }
        else if(level==2){
            uncheckNextDoor();
            System.out.println("Level 3"); 
            System.out.println("Needed score: 5"); 
            /** set the gameOver counter to off */
            view.setFinish(false);
            level++;
            /**  get a new world*/
            world = new Level3();
            /**  fill it with bodies*/
            world.populate(this);
            /** switch the keyboard control to the new player*/
            controller.setBody(world.getSoldier());
            
            view.setSoldier(getSoldier());
           
            // show the new world in the view
            view.setWorld(world);
            view.setZoom(25);
            world.start();
            track1();
        }
        else if(level==3){
            uncheckNextDoor();
            System.out.println("Level 4"); 
            System.out.println("Needed score: 8"); 
            view.setFinish(false);
            level++;
            // get a new world
            world = new Level4();
            // fill it with bodies
            world.populate(this);
            // switch the keyboard control to the new player
            controller.setBody(world.getSoldier());
            view.setSoldier(getSoldier());
           
            // show the new world in the view
            view.setWorld(world);
            view.setZoom(25);
            world.start();
        }
        
    }
    /** Run the game. */
   
    public static void main(String[] args) {
        new Game();
     
        
    }
}
