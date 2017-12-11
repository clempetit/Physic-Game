/*
 *	Author:      Clément Petit 
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.Trigger;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Finish extends Trigger implements Actor{
	private ImageGraphics FinishGraphics;
	private Polygon polygon;
	private final float FinishHeight = 2.0f;
	private final float FinishWidth = 2.0f;
	private boolean finished;
	
	public Finish(ActorGame game, Vector position) {
		super(game, true, position);
		finished = false;
			
		polygon = new Polygon(
				0.0f, 0.0f,
				FinishWidth, 0.0f,
				FinishWidth, FinishHeight,
				0.0f, FinishHeight
				);
		
		getPartBuilder().setShape(polygon);
	    getPartBuilder().build();
	        
	    FinishGraphics = new ImageGraphics("flag.red.png", FinishWidth, FinishHeight);
	    FinishGraphics.setParent(this);
        getOwner().addActor(this);
        }
	
	
	@Override
	public void draw(Canvas canvas) {
		FinishGraphics.draw(canvas);
	}
	@Override
	public void destroy() {
		getEntity().destroy();
		getOwner().removeActor(this);
	}
	
}
