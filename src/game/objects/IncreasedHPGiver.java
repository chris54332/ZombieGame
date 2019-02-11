/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

/**
 *
 * @author Chris
 */
public class IncreasedHPGiver extends Walker {
    private static final Shape shape = new PolygonShape(
0.57f,1.63f, 0.79f,-1.1f, 0.5f,-1.47f, -0.18f,-1.4f, -0.11f,1.46f, 0.03f,1.69f);

    private static final BodyImage image =
        new BodyImage("data/hpgiver.png", 3.5f);
    public int tag;
    

    public IncreasedHPGiver(World world) {
        super(world, shape);
        addImage(image);
       
    }
}
