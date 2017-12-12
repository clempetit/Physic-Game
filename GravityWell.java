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
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class GravityWell extends GameEntity implements Actor{
	
	private PartBuilder partBuilder;
	private BasicContactListener contactListener;
	private Polygon polygon;
	private ShapeGraphics puit;
	private Vector force;
	private float puitWidth;
	private float puitHeight;
	
	
	public GravityWell(ActorGame game, boolean fixed, Vector position, Vector force, float transparency, float width, float height) {
		super(game, fixed, position);
		try{
			if(width <= 0.0f|| height <= 0.0f) {
				throw new NullPointerException();
			}
		this.force = force;
		puitWidth = width ;
		puitHeight = height ;
		partBuilder = getEntity().createPartBuilder();
		polygon = new Polygon(
				0.0f, 0.0f,
				puitWidth, 0.0f,
				puitWidth, puitHeight,
				0.0f, puitHeight
				);
		partBuilder.setShape(polygon);
		partBuilder.setGhost(true);
		partBuilder.build();
		
		puit = new ShapeGraphics(polygon, Color.BLACK, Color.BLACK, 0, transparency, 0);
		puit.setParent(this);
		
		contactListener = new BasicContactListener();
	    getEntity().addContactListener(contactListener);
	    getOwner().addActor(this);
		} catch(NullPointerException e) {
			System.out.println("Paramètre(s) indispensable(s) erronés");
		}
	}
	
	public BasicContactListener getListener() {
		return contactListener;
	}
	
	public boolean hasContactWith(Entity entity) {
		return contactListener.hasContactWith(entity);
	}
	
	public void gravityAction(Entity entity) {
		if (hasContactWith(entity)) {
		entity.applyForce(force, null);
		}
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		puit.draw(canvas);
		
	}
}
