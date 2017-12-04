/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class SimpleCrateGame implements Game {
	
	private Window window;
	private World world;
	
	private Entity block;
	private Entity crate;
	
	private ImageGraphics blockGraphics;
    private ImageGraphics crateGraphics;
	
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        this.window = window;

        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
        
        final float blockWidth = 2.0f;
        final float blockHeight = 2.0f;
        final float blockGaphicsWidth = 1.0f;
        final float blockGraphicsHeight = 1.0f;
        
     // building of block
        EntityBuilder entityBuilder1 = world.createEntityBuilder();
        entityBuilder1.setFixed(true);
        entityBuilder1.setPosition(new Vector(1.0f, 0.5f));
        block = entityBuilder1.build();
     // part building for block
        PartBuilder partBuilder1 = block.createPartBuilder();
        Polygon polygon = new Polygon( new Vector(0.0f, 0.0f), new Vector(blockWidth, 0.0f),
        		new Vector(blockWidth, blockHeight), new Vector(0.0f, blockHeight) );
        partBuilder1.setShape(polygon);
        partBuilder1.setFriction(0.5f);
        partBuilder1.build();
        
     // building of crate
        EntityBuilder entityBuilder2 = world.createEntityBuilder();
        entityBuilder2.setFixed(false);
        entityBuilder2.setPosition(new Vector(0.2f, 4.0f));
        crate = entityBuilder2.build();
     // part building for crate
        PartBuilder partBuilder2 = crate.createPartBuilder();
        partBuilder2.setShape(polygon);
        partBuilder2.build();
        
     // image for block
        blockGraphics = new ImageGraphics("stone.broken.4.png", blockGaphicsWidth, blockGraphicsHeight);
        blockGraphics.setAlpha(1.0f);
        blockGraphics.setDepth(0.0f);
        blockGraphics.setParent(block);
        
     // image for crate
        crateGraphics = new ImageGraphics("box.4.png", blockGaphicsWidth, blockGraphicsHeight);
        crateGraphics.setAlpha(1.0f);
        crateGraphics.setDepth(0.0f);
        crateGraphics.setParent(crate);
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
        
    	world.update(deltaTime);
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f));

    	blockGraphics.draw(window);
    	crateGraphics.draw(window);

    }

    @Override
    public void end() {
    }

}
