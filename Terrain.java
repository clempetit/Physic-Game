/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private ShapeGraphics terrain;
	private Polyline polyline;
	
	public Terrain(ActorGame game, Polyline polyline, Color color1, Color color2, float friction) {
		super(game, true);
		partBuilder = getEntity().createPartBuilder();
				
		    partBuilder.setShape(polyline);
	        partBuilder.setFriction(friction);
	        partBuilder.build();
	        
	        terrain = new ShapeGraphics(polyline, color1, color2, .1f, 1.f, 0);
	        terrain.setParent(this);
	        getOwner().addActor(this);
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		terrain.draw(canvas);
	}

}
