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
public class Enemy extends Walker {
    private static final Shape shape = new PolygonShape(
-0.03f,1.5f, 0.23f,-0.39f, 0.18f,-1.65f, -0.71f,-1.69f, -1.26f,0.66f, -0.73f,1.5f);

    private static final BodyImage image =
        new BodyImage("data/enemy.png", 3.5f);
    public int tag;
    

    public Enemy(World world) {
        super(world, shape);
        addImage(image);
       
    }
}
