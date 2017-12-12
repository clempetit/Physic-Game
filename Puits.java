/*
 *	Author:      Yanis Berkani
 *	Date:        11 déc. 2017
 */

package ch.epfl.cs107.play.game.actor.bike;

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

public class Puits extends GameEntity implements Actor{
	private ShapeGraphics puit;
	private PartBuilder partBuilder;
	private Polygon polygon;
	private BasicContactListener contactListener;
	private final float puitWidth = 5.0f;
	private final float puitHeight = 7.0f;
	
	
	public Puits(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
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
		
		puit = new ShapeGraphics(polygon, Color.BLACK, Color.BLACK, 1.0f);
		puit.setParent(this);
		
		contactListener = new BasicContactListener();
	    getEntity().addContactListener(contactListener);
	}
	
	public BasicContactListener getListener() {
		return contactListener;
	}
	
	public boolean hasContactWith(Entity entity) {
		return contactListener.hasContactWith(entity);
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
}
