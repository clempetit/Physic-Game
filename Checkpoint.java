/*
 *	Author:      Yanis Berkani
 *	Date:        12 déc. 2017
 */

package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.Trigger;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Checkpoint extends Trigger implements Actor{
	private ImageGraphics cpGraphics;
	private Polygon polygon;
	private final float cpHeight = 2.0f;
	private final float cpWidth = 2.0f;
	private boolean arrived;
	
	public Checkpoint(ActorGame game, Vector position) {
		super(game, true, position);
		arrived = false;
			
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
        getOwner().addActor(this);
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