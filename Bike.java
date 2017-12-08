/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike extends GameEntity implements Actor{
	private final float MAX_WHEEL_SPEED = 20.0f;
	private boolean toRight; // Orientation du cycliste
	private PartBuilder partBuilder;
	private ShapeGraphics BikeGraphics;
	private Polygon polygon;
	private Wheel leftWheel;
	private Wheel rightWheel;
	
	public Bike(ActorGame game, boolean fixed, Polygon polygon, Vector position) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		
			partBuilder.setShape(polygon);
			partBuilder.setGhost(true);
	        partBuilder.build();
	        
	        leftWheel = new Wheel(getOwner(), false, position.add(-1.0f, 0.f), "explosive.11.png", 0.5f);
	        rightWheel = new Wheel(getOwner(), false, position.add(1.0f, 0.f), "explosive.11.png", 0.5f);
	        
	        leftWheel.attach(getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f));
	        rightWheel.attach(getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
	        
	        getOwner().addActor(this);
	}
	
	@Override
	public void draw(Canvas canvas) {
	}
	

}
