/*
 *	Author:      Yanis Berkani
 *	Date:        7 déc. 2017
 */

package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private ShapeGraphics terrainGraphics;
	private Polyline polyline;
	
	public Terrain(ActorGame game, Polyline polyline, Color color1, Color color2, float friction) {
		super(game, true);
		partBuilder = getEntity().createPartBuilder();
				
		    partBuilder.setShape(polyline);
	        partBuilder.setFriction(friction);
	        partBuilder.build();
	        
	        terrainGraphics = new ShapeGraphics(polyline, color1, color2, .1f, 1.f, 0);
	        terrainGraphics.setParent(this);
	        getOwner().addActor(this);
		
	}
	
	@Override
	public Entity getEntity() {
		return super.getEntity();
	}
	
	@Override
	public void draw(Canvas canvas) {
		terrainGraphics.draw(canvas);
	}

}
