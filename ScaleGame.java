/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.tutorial;

import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ScaleGame implements Game{
	
	private Window window;
	private World world;
	
	private Entity block;
	private Entity ball;
	private Entity plank;
	
	private ImageGraphics blockGraphics;
    private ImageGraphics ballGraphics;
    private ImageGraphics plankGraphics;
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        this.window = window;

        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
        
        EntityBuilder entityBuilder;
        PartBuilder partBuilder;
        
        final float blockWidth = 10.0f;
        final float blockHeight = 1.0f;
        final float plankWidth = 5.0f;
        final float plankHeight = 0.2f;
        final float ballRadius = 0.5f;
        
     // builds block and sets it shape
        entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(-5.0f, -1.0f));
        block = entityBuilder.build();
        partBuilder = block.createPartBuilder();
        Polygon polygon = new Polygon( new Vector(0.0f, 0.0f), new Vector(blockWidth, 0.0f), new Vector(blockWidth, blockHeight),
        		new Vector(0.0f, blockHeight) );
        partBuilder.setShape(polygon);
        partBuilder.build();
        
     // builds plank and sets it shape
        entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(false);
        entityBuilder.setPosition(new Vector(-2.5f, 0.8f));
        plank = entityBuilder.build();
        partBuilder = plank.createPartBuilder();
        Polygon polygon2 = new Polygon( new Vector(0.0f, 0.0f), new Vector(plankWidth, 0.0f), new Vector(plankWidth, plankHeight),
                new Vector(0.0f, plankHeight) );
        partBuilder.setShape(polygon2);
        partBuilder.build();
        
     // builds ball and sets it shape
        entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(false);
        entityBuilder.setPosition(new Vector(0.5f, 4.f));
        ball = entityBuilder.build();
        partBuilder= ball.createPartBuilder();
        Circle circle = new Circle(ballRadius);
        partBuilder.setShape(circle);
        partBuilder.build();
        
           
     // image for block
        blockGraphics = new ImageGraphics("stone.broken.4.png", blockWidth, blockHeight);
        blockGraphics.setAlpha(1.0f);
        blockGraphics.setDepth(0.0f);
        blockGraphics.setParent(block);
        
     // image for plank
        plankGraphics = new ImageGraphics("wood.3.png", plankWidth, plankHeight);
        plankGraphics.setAlpha(1.0f);
        plankGraphics.setDepth(0.0f);
        plankGraphics.setParent(plank);
        
     // image for ball
        ballGraphics = new ImageGraphics("explosive.11.png", 2*ballRadius, 2*ballRadius, new Vector(0.5f, 0.5f));
        ballGraphics.setAlpha(1.0f);
        ballGraphics.setDepth(0.0f);
        ballGraphics.setParent(ball);
        
        RevoluteConstraintBuilder revoluteConstraintBuilder =
        		world.createRevoluteConstraintBuilder();
        revoluteConstraintBuilder.setFirstEntity(block);
        revoluteConstraintBuilder.setFirstAnchor(new Vector(blockWidth/2,
        		(blockHeight*7)/4));
        revoluteConstraintBuilder.setSecondEntity(plank);
        revoluteConstraintBuilder.setSecondAnchor(new Vector(plankWidth/2,
        		plankHeight/2));
        revoluteConstraintBuilder.setInternalCollision(true);
        revoluteConstraintBuilder.build();
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
    	
    	if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
    		ball.applyAngularForce(10.0f);
    	} else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
    	ball.applyAngularForce(-10.0f);
    }
        
    	world.update(deltaTime);
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f));

    	blockGraphics.draw(window);
    	plankGraphics.draw(window);
    	ballGraphics.draw(window);

    }

    @Override
    public void end() {
    }

}
