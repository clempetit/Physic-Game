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
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
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
	private final float puitWidth = 5.0f;
	private final float puitHeight = 7.0f;
	
	
	public GravityWell(ActorGame game, boolean fixed, Vector position, Vector force) {
		super(game, fixed, position);
		this.force = force;
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
		
		puit = new ShapeGraphics(polygon, Color.BLACK, Color.BLACK, 0, 0.5f, 0);
		puit.setParent(this);
		
		contactListener = new BasicContactListener();
	    getEntity().addContactListener(contactListener);
	    getOwner().addActor(this);
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