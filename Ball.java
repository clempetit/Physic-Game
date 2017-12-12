/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Ball extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private ImageGraphics ballGraphics;
	private Circle circle;
	
	public Ball(ActorGame game, String image, boolean fixed, Vector position, float ballRadius) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		if (ballRadius == 0) {
			ballRadius = 0.5f;
		}
			circle = new Circle(Math.abs(ballRadius));
			partBuilder.setFriction(0.5f);
			partBuilder.setShape(circle);
	        partBuilder.build();
	        
	        ballGraphics = new ImageGraphics(image, 2*ballRadius, 2*ballRadius, new Vector(0.5f, 0.5f));
	        ballGraphics.setParent(this);
	        getOwner().addActor(this);
		}
	
	@Override
	public Entity getEntity() {
		return super.getEntity();
	}
	
	@Override
	public void draw(Canvas canvas) {
		ballGraphics.draw(canvas);
	}
	
	public void attach(Entity support, Vector anchor, float length) {
	
		RopeConstraintBuilder ropeConstraintBuilder = getOwner().createRopeConstraintBuilder();
        ropeConstraintBuilder.setFirstEntity(support);
        ropeConstraintBuilder.setFirstAnchor(anchor);
        ropeConstraintBuilder.setSecondEntity(getEntity());
        ropeConstraintBuilder.setSecondAnchor(Vector.ZERO);
        ropeConstraintBuilder.setMaxLength(length);
        ropeConstraintBuilder.setInternalCollision(false);
        ropeConstraintBuilder.build();
        
	}
	
	public Vector getBallLocation() {
		return new Vector(0.0f,0.0f) ;
	}
	
	@Override
	public void destroy() {
		getEntity().destroy();
		getOwner().removeActor(this);
	}
	

}
