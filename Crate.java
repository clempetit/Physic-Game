/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Crate extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics crateGraphics;
	private Polygon polygon;
	public Crate(ActorGame game, boolean fixed, Vector position, String image, float width, float height, float friction) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		try {
			if (width <= 0 || height <= 0) {
				throw new IllegalArgumentException("Paramètres invalides");
			}
			polygon = new Polygon( new Vector(0.0f, 0.0f), new Vector(width, 0.0f),
	        		new Vector(width, height), new Vector(0.0f, height) );
			
			partBuilder.setShape(polygon);
	        partBuilder.setFriction(friction);
	        partBuilder.build();
	        
	        crateGraphics = new ImageGraphics(image, width, height);
	        crateGraphics.setParent(this);
	        getOwner().addActor(this);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public Entity getEntity() {
		return super.getEntity();
	}
	
	@Override
	public void draw(Canvas canvas) {
		crateGraphics.draw(canvas);
	}
	
}
