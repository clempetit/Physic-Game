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
        
        EntityBuilder entityBuilder;
        PartBuilder partBuilder;
        
        final float blockWidth = 1.0f;  // On remarque qu'en passant les dimensions de block à 2.0f, mais en laissant les dimensions
        final float blockHeight = 1.0f; // de l'image à 1.0f, l'un des carrés semble être en "lévitation" sur l'autre. 
        final float blockGaphicsWidth = 1.0f;
        final float blockGraphicsHeight = 1.0f;
        Polygon polygon = new Polygon( new Vector(0.0f, 0.0f), new Vector(blockWidth, 0.0f),
        		new Vector(blockWidth, blockHeight), new Vector(0.0f, blockHeight) );
        
     // building of block
        entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(1.0f, 0.5f));
        block = entityBuilder.build();
     // part building for block
        partBuilder = block.createPartBuilder();
        partBuilder.setShape(polygon);
        partBuilder.setFriction(0.5f);
        partBuilder.build();
        
     // building of crate
        entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(false);
        entityBuilder.setPosition(new Vector(0.2f, 4.0f));
        crate = entityBuilder.build();
     // part building for crate
        partBuilder = crate.createPartBuilder();
        partBuilder.setShape(polygon);
        partBuilder.build();
        
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
