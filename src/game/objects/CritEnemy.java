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
public class CritEnemy extends Walker{
    private static final Shape shape = new PolygonShape(
-0.16f,2.45f, 0.35f,-0.61f, 0.26f,-2.65f, -1.43f,-2.69f, -1.82f,1.39f);

    private static final BodyImage image =
        new BodyImage("data/critenemy.png", 5.5f);
    public int tag;
    

    public CritEnemy(World world) {
        super(world, shape);
        addImage(image);
      
    }
}
