/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bascule extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private Polygon polygon;
	private Crate bridge;
	private ImageGraphics blockGraphics;
    private ShapeGraphics bridgeGraphics;
    
    public Bascule(ActorGame game, Vector position, float width, float length) {
		super(game, true, position);
		partBuilder = getEntity().createPartBuilder();
		if (length <  2.f) {
			length = 2.f;
		}
		
		polygon = new Polygon(
				0.0f, 0.0f,
				2.0f, 0.0f,
				2.0f, 0.2f,
				0.0f, 0.2f
				);
		
			partBuilder.setShape(polygon);
			partBuilder.setGhost(false);
	        partBuilder.build();
       
        bridge = new Crate(getOwner(), false, position.add(0.0f, 1.0f), "stone.7.png", width, length, 1.0f);
        //ball.attach(getEntity(), new Vector(blockWidth/2, 0.f), length);
        
        blockGraphics = new ImageGraphics("stone.2.png", 2.0f, 0.2f);
        blockGraphics.setParent(getEntity());
        
        RevoluteConstraintBuilder revoluteConstraintBuilder =
        		getOwner().createRevoluteConstraintBuilder();
        revoluteConstraintBuilder.setFirstEntity(getEntity());
        revoluteConstraintBuilder.setFirstAnchor(new Vector(1.0f,
        		0.2f));
        revoluteConstraintBuilder.setSecondEntity(bridge.getEntity());
        revoluteConstraintBuilder.setSecondAnchor(new Vector(width,
        		0.0f));
        revoluteConstraintBuilder.setInternalCollision(true);
        revoluteConstraintBuilder.build();
         	
	        getOwner().addActor(this);
		}
    
    @Override
	public void draw(Canvas canvas) {
		blockGraphics.draw(canvas);
		bridge.draw(canvas);
	}
    
    
}

