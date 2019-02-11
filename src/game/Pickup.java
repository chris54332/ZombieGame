package game;

import game.objects.HPtoken;
import game.objects.Enemy;
import game.objects.Soldier;
import game.objects.CritEnemy;
import game.objects.IncreasedHPGiver;
import game.objects.IncreasedDoubleHPGiver;
import city.cs.engine.*;

/**
 * Collision listener that allows the bird to collect things.
 */
public class Pickup implements CollisionListener {
    private Soldier soldier;
   
    
    public Pickup(Soldier soldier) {
        this.soldier = soldier;
        
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Enemy && e.getOtherBody() == soldier) {
            soldier.Damage();
            e.getReportingBody().destroy();
        }else if(e.getReportingBody() instanceof CritEnemy && e.getOtherBody() == soldier) {
            soldier.CritDamage();
            e.getReportingBody().destroy();
        }else if(e.getReportingBody() instanceof HPtoken && e.getOtherBody() == soldier) {
            soldier.HPHeal();
            e.getReportingBody().destroy();}
         else if(e.getReportingBody() instanceof IncreasedHPGiver && e.getOtherBody() == soldier) {
            soldier.HPExtraHeal();
            e.getReportingBody().destroy();
        }else if(e.getReportingBody() instanceof IncreasedDoubleHPGiver && e.getOtherBody() == soldier) {
            soldier.HPDoubleExtraHeal();
            e.getReportingBody().destroy();
        }
                
    }
    
}
