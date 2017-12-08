/*
 *	Author:      Yanis Berkani
 *	Date:        7 déc. 2017
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike extends GameEntity implements Actor{
	private final float MAX_WHEEL_SPEED = 20.0f;
	private boolean toRight = true; // Orientation du cycliste
	private PartBuilder partBuilder;
	private ShapeGraphics BikeGraphics;
	private Polygon polygon;
	private Wheel leftWheel;
	private Wheel rightWheel;
	
	private ShapeGraphics headGraphics;
	private ShapeGraphics armGraphics;
	private ShapeGraphics bottomGraphics;
	private ShapeGraphics kneeGraphics;
	private ShapeGraphics leg1Graphics;
	private ShapeGraphics leg2Graphics;
	
	
	public Bike(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		polygon = new Polygon(
				0.0f, 0.5f,
				0.5f, 1.0f,
				0.0f, 2.0f,
				-0.5f, 1.0f
				);
		
			partBuilder.setShape(polygon);
			partBuilder.setGhost(true);
	        partBuilder.build();
	        
	        leftWheel = new Wheel(getOwner(), false, position.add(-1.0f, 0.f),  0.5f);
	        rightWheel = new Wheel(getOwner(), false, position.add(1.0f, 0.f), 0.5f);
	        
	        leftWheel.attach(getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f)); 
	        rightWheel.attach(getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
	        
	        getOwner().addActor(this);
	        
	        leftWheel.relax();
	        rightWheel.relax();
	        
	}
	
	public Entity getBike() {
		return getEntity();
	}
	
	public Wheel getLeftWheel() {
		return leftWheel;
	}
	
	public Wheel getRightWheel() {
		return rightWheel;
	}
	
	public void MoveLeft() {
		if(rightWheel.getSpeed() < MAX_WHEEL_SPEED) {
			rightWheel.power(MAX_WHEEL_SPEED);
		}
		else {
			leftWheel.relax();
			rightWheel.relax();
		}
	}
	
	public void MoveRight() {
		if(leftWheel.getSpeed() > -MAX_WHEEL_SPEED) {
			leftWheel.power(-MAX_WHEEL_SPEED);
		}
		else {
			leftWheel.relax();
			rightWheel.relax();
		}
	}
	
	 // Voir la méthode bike game update (premier if) : 
	// cela permet de changer l'orientation du cycliste 
	// (en métant toRight en argument).
	public void setOppositeDirection(boolean Dir) { 
		toRight = !(Dir);  
	}
	
	public boolean getDirection() {
		return toRight;
	}
	
	private Vector getHeadLocation() {
		return new Vector(0.0f, 1.75f) ;
	}
	
	private Vector getShoulderLocation() {
		if(toRight == true) {
			return new Vector(-0.15f,1.5f);
		}
		else {
			return new Vector(0.15f,1.5f);
		}
	}
	
	private Vector getHandLocation() {
		if(toRight == true) {
			return new Vector(0.55f, 0.95f);
		}
		else {
			return new Vector(-0.55f, 0.95f);
		}
	}
	
	private Vector getBackLocation() {
		if(toRight == true) {
			return new Vector(-0.55f, 0.95f);
		}
		else {
			return new Vector(0.55f, 0.95f);
		}
	}
	
	private Vector getKneeLocation() {
		return new Vector(0.0f,0.65f);
	}
	private Vector getFoot1Location() {
		if(toRight == true) {
			return new Vector(-0.25f, 0.1f);
		}
		else {
			return new Vector(0.25f, 0.1f);
		}
	}
	
	private Vector getFoot2Location() {
		if(toRight == true) {
			return new Vector(0.25f, 0.1f);
		}
		else {
			return new Vector(-0.25f, 0.1f);			
		}
	}
	
	
	
	
	@Override
	public void draw(Canvas canvas) {
		// Draw head
				Circle head = new Circle(0.2f, getHeadLocation()) ;
				headGraphics = new ShapeGraphics(head, Color.BLACK, Color.BLACK, 0.1f);
				headGraphics.setParent(this);
				
				// Draw arm
				Polyline arm = new Polyline(getShoulderLocation(),getHandLocation()) ;
				armGraphics = new ShapeGraphics(arm, Color.BLACK, Color.BLACK, 0.1f);
				armGraphics.setParent(this);
				
				Polyline bottom = new Polyline(getShoulderLocation(), getBackLocation());
				bottomGraphics = new ShapeGraphics(bottom, Color.RED, Color.RED, 0.1f);
				bottomGraphics.setParent(this);
				
				Polyline knee = new Polyline(getBackLocation(), getKneeLocation());
				kneeGraphics = new ShapeGraphics(knee, Color.BLUE, Color.BLUE, 0.1f);
				kneeGraphics.setParent(this);
				
				Polyline leg1 = new Polyline(getKneeLocation(), getFoot1Location());
				leg1Graphics = new ShapeGraphics(leg1, Color.BLACK, Color.BLACK, 0.1f);
				leg1Graphics.setParent(this);
				
				Polyline leg2 = new Polyline(getKneeLocation(), getFoot2Location());
				leg2Graphics = new ShapeGraphics(leg2, Color.BLACK, Color.BLACK, 0.1f);
				leg2Graphics.setParent(this);
				
				headGraphics.draw(canvas);
				armGraphics.draw(canvas);
				bottomGraphics.draw(canvas);
				kneeGraphics.draw(canvas);
				leg1Graphics.draw(canvas);
				leg2Graphics.draw(canvas);
				
	}
	
	
	

}