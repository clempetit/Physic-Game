/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.general; //GetOwner.createWheelConstraint...

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private ImageGraphics wheelGraphics;
	private Circle circle;
	private WheelConstraint constraint;
	public Wheel(ActorGame game, boolean fixed, Vector position, String image, float ballRadius) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		try {
			if (ballRadius <= 0) {
				throw new IllegalArgumentException("Paramètres invalides");
			}
			circle = new Circle(ballRadius);
			
			partBuilder.setShape(circle);
	        partBuilder.build();
	        
	        wheelGraphics = new ImageGraphics("explosive.11.png", 2*ballRadius, 2*ballRadius, new Vector(0.5f, 0.5f));
	        wheelGraphics.setParent(this);
	        getOwner().addActor(this);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		wheelGraphics.draw(canvas);
	}
	
	public void attach(Entity vehicle, Vector anchor, Vector axis) {
		WheelConstraintBuilder constraintBuilder = getOwner().createWheelConstraintBuilder();
		constraintBuilder.setFirstEntity(vehicle);
		// point d'ancrage du véhicule :
		constraintBuilder.setFirstAnchor(anchor);
		// Entity associée à la roue :
		constraintBuilder.setSecondEntity(getEntity());
		// point d'ancrage de la roue (son centre):
		constraintBuilder.setSecondAnchor(Vector.ZERO);
		// axe le long duquel la roue peut se déplacer :
		constraintBuilder.setAxis(axis);
		// fréquence du ressort associé
		constraintBuilder.setFrequency(3.0f); constraintBuilder.setDamping(0.5f);
		// force angulaire maximale pouvant être appliquée //à la roue pour la faire tourner :
		constraintBuilder.setMotorMaxTorque(10.0f);
		constraint = constraintBuilder.build();
	}
	
	public void power(float speed) {
		
	}
	
	public void relax() {
		
	}
	
	public void detach() {
		
	}
	
	/**
	@return relative rotation speed, in radians per second */
	public float getSpeed() {
		return 0.0f;
	}

}
