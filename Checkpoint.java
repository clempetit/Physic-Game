/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.Trigger;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Checkpoint extends Trigger implements Actor{
	private ImageGraphics cpGraphics;
	private Polygon polygon;
	private final float cpHeight = 2.0f;
	private final float cpWidth = 2.0f;
	private boolean arrived;
	private BasicContactListener contactListener;
	private Vector location;
	
	public Checkpoint(ActorGame game, Vector location) {
		super(game, true, location);
		arrived = false;
		this.location = location;
		polygon = new Polygon(
				0.0f, 0.0f,
				cpWidth, 0.0f,
				cpWidth, cpHeight,
				0.0f, cpHeight
				);
		
		getPartBuilder().setShape(polygon);
	    getPartBuilder().build();
	        
	    cpGraphics = new ImageGraphics("flag.yellow.png", cpWidth, cpHeight);
	    cpGraphics.setParent(this);
	    
	    contactListener = new BasicContactListener();
	    getEntity().addContactListener(contactListener);
	    
        getOwner().addActor(this);
        }
	
	public boolean hasContactWith(Entity entity) {
		return contactListener.hasContactWith(entity);
	}
	
	public void changeSpown(Entity entity, Vector vector1, Vector vector2) {
		if (hasContactWith(entity)) {
		//setLocation(vector1, vector2);
		}
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		cpGraphics.draw(canvas);
	}
	@Override
	public void destroy() {
		getEntity().destroy();
		getOwner().removeActor(this);
	}
	
}
