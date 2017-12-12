/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Pendulum extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private Polygon polygon;
	private Ball ball;
	private ImageGraphics blockGraphics;
    private ShapeGraphics ballGraphics;
    
    
    
    
    public Pendulum(ActorGame game, Vector position, float length) {
		super(game, true, position);
		partBuilder = getEntity().createPartBuilder();
		if (length <  1.f) {
			length = 1.f;
		}
		final float blockWidth = 2.0f;
        final float blockHeight = 1.0f;
        final float blockRadius = 1.0f;
		polygon = new Polygon(
				0.0f, 0.0f,
				2.0f, 0.0f,
				2.0f, 1.0f,
				0.0f, 1.0f
				);
		
			partBuilder.setShape(polygon);
			partBuilder.setGhost(false);
			partBuilder.setCollisionSignature(1);
	        partBuilder.build();
       
        ball = new Ball(getOwner(), "explosive.cracked.11.png", false, position.add(-length, 0.5f),  0.5f);
        ball.attach(getEntity(), new Vector(blockWidth/2, 0.f), length);
        
        blockGraphics = new ImageGraphics("stone.broken.4.png", blockWidth, blockHeight);
        blockGraphics.setParent(getEntity());
         	
	        getOwner().addActor(this);
		}
    
    @Override
	public void draw(Canvas canvas) {
		blockGraphics.draw(canvas);
		ball.draw(canvas);
	}

}
