/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import city.cs.engine.CircleShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.awt.Color;

/**
 *
 * @author Chris
 */
public class HPtoken extends StaticBody {
     private static final Shape shape = new CircleShape(0.2f);
 
    public HPtoken(World world) {
        super(world, shape);
        setFillColor(Color.red);
        
    }
    
}
