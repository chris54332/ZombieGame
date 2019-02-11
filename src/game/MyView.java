package game;

import game.objects.Soldier;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import city.cs.engine.*;

/**
 * extended view
 */
public class MyView extends UserView {
    Soldier soldier;
    public Image background;
    public Image finish;   
    private boolean x=false;
    public MyView(World world, Soldier soldier, int width, int height) {
        super(world, width, height);
        this.soldier = soldier;
        /**sets the background image*/
        this.background = new ImageIcon("data/lvl1.png").getImage();
        /**sets the gameOver image for the finish panel*/
        this.finish= new ImageIcon("data/gameOver.png").getImage();
        
    }
    /**paints the background*/
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
        
    }
    /**paints the foreground*/
    @Override
    protected void paintForeground(Graphics2D g) {
        GameLevel world=(GameLevel)this.getWorld();
        g.drawString("Score: "+soldier.getCount(),487,75);
        g.drawString("HP: "+soldier.getHP(),488,90);
        g.drawString("Time left: "+world.getSecondsLeft(),480,60);
        /**if the time runs out, the parameter x turns true for the gameOver panel to set*/
        if(world.getSecondsLeft()==0)
        {
        x=true;
        world.stop();
        }
        if(x==true)
        { 
            g.drawImage(finish,210 ,200, this);
        }
    }
    /**sets the data of the soldier from the world */
    public void setSoldier(Soldier x){
      this.soldier=x;
        
    }
    /**the counter for the gameOver panel*/
    public void setFinish(boolean x){
    this.x=x;
   
    }
}
