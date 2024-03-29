/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ContactGame implements Game{
	
	private Window window;
	private World world;
	
	private Entity block;
	private Entity ball;
	
	private ImageGraphics blockGraphics;
    private ShapeGraphics ballGraphics;
    
    private BasicContactListener contactListener;
    
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        this.window = window;

        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
        
        EntityBuilder entityBuilder;
        PartBuilder partBuilder;
        
        final float blockWidth = 10.0f;
        final float blockHeight = 1.0f;
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
        
     // builds ball and sets it shape
        entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(false);
        entityBuilder.setPosition(new Vector(0.0f, 2.0f));
        ball = entityBuilder.build();
        
        partBuilder = ball.createPartBuilder();
        Circle circle = new Circle(ballRadius);
        partBuilder.setShape(circle);
        partBuilder.build();
        
        contactListener = new BasicContactListener();
        ball.addContactListener(contactListener);
        
     // image for block
        blockGraphics = new ImageGraphics("stone.broken.4.png", blockWidth, blockHeight);
        blockGraphics.setAlpha(1.0f);
        blockGraphics.setDepth(0.0f);
        blockGraphics.setParent(block);
        
     // image for ball
        ballGraphics = new ShapeGraphics(circle,Color.BLUE, Color.BLUE, .1f, 1, 0);
        ballGraphics.setAlpha(1.0f);
        ballGraphics.setDepth(0.0f);
        ballGraphics.setParent(ball);
        
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
        
    	world.update(deltaTime);
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f));

    	blockGraphics.draw(window);
    	// contactListener is associated to ball
    	// contactListener.getEntities() returns the list of entities in	collision with ball
    	int numberOfCollisions = contactListener.getEntities().size();
    	if (numberOfCollisions > 0){
    		ballGraphics.setFillColor(Color.RED);
    		}
    
    	ballGraphics.draw(window);

    }

    @Override
    public void end() {
    }
}
