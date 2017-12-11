/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
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
	private ShapeGraphics knee1Graphics;
	private ShapeGraphics knee2Graphics;
	private ShapeGraphics leg1Graphics;
	private ShapeGraphics leg2Graphics;
	private boolean hit;
	
	
	private Vector handLocation;
	private Vector knee1Location;
	private Vector knee2Location;
	private Vector foot1Location;
	private Vector foot2Location;
	
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
			partBuilder.setGhost(false);
			partBuilder.setCollisionSignature(1);
	        partBuilder.build();
	        
	        leftWheel = new Wheel(getOwner(), false, position.add(-1.0f, 0.f),  0.5f);
	        rightWheel = new Wheel(getOwner(), false, position.add(1.0f, 0.f), 0.5f);
	        
	        leftWheel.attach(getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f)); 
	        rightWheel.attach(getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
	        
	        getOwner().addActor(this);
	        
	        leftWheel.relax();
	        rightWheel.relax();
	        
			handLocation = new Vector(0.55f, 0.95f);
			knee1Location = new Vector(0.0f,0.65f);
			knee2Location = new Vector(0.0f,0.65f);
			foot1Location = new Vector(-0.25f, 0.1f);
			foot2Location = new Vector(0.25f, 0.1f);
			
	        ContactListener listener = new ContactListener() {
				@Override
				public void beginContact(Contact contact) {
					if (contact.getOther().isGhost()) {
						return ;
					}
					// si contact avec les roues
					if (contact.getOther().getEntity().equals (leftWheel.getEntity())) {
						return ;
					}	
				hit = true ;	
				}
				@Override
				public void endContact(Contact contact) {}
			};
			this.getEntity().addContactListener(listener);
	}
	
	public Entity getBikeEntity() {
		return getEntity();
	}
	
	public boolean getHit() {
		return hit ;
	}
	
	public void destroy() {
		getEntity().destroy();
		rightWheel.destroy();
		leftWheel.destroy(); //Ici, on aurait pu detach.
		getOwner().removeActor(this);
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
	}
	
	public void MoveRight() {
		if(leftWheel.getSpeed() > -MAX_WHEEL_SPEED) {
			leftWheel.power(-MAX_WHEEL_SPEED);
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
			return handLocation;
		}
		else {
			return new Vector(-handLocation.getX(), handLocation.getY());
		}
	}
	
	public void setFinishedHandLocation() {
		handLocation = new Vector(0.1f, 2.4f);
	}
	
	private Vector getBackLocation() {
		if(toRight == true) {
			return new Vector(-0.55f, 0.95f);
		}
		else {
			return new Vector(0.55f, 0.95f);
		}
	}
	
	private Vector getKnee1Location() {
		if(toRight == true) {
			return knee1Location;
		}
		else
		{
			return new Vector(-knee1Location.getX(),knee1Location.getY());
		}
	}
	
	public void setKnee1Location(Vector location) {
		knee1Location = location;
	}
	
	private Vector getKnee2Location() {
		if(toRight == true) {
			return knee2Location;
		}
		else
		{
			return new Vector(-knee2Location.getX(),knee2Location.getY());
		}
	}
	
	public void setKnee2Location(Vector location) {
		knee2Location = location;
	}
	
	
	
	
	private Vector getFoot1Location() {
		if(toRight == true) {
			return foot1Location;
		}
		else {
			return new Vector(-foot1Location.getX(), foot1Location.getY());
		}
	}
	
	public void setFoot1Location(Vector location) {
		foot1Location = location;
	}
	
	private Vector getFoot2Location() {
		if(toRight == true) {
			return foot2Location;
		}
		else {
			return new Vector(-foot2Location.getX(), foot2Location.getY());			
		}
	}
	
	public void setFoot2Location(Vector location) {
		foot2Location = location;
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		// Draw head
				Circle head = new Circle(0.2f, getHeadLocation()) ;
				headGraphics = new ShapeGraphics(head, Color.BLACK, Color.BLACK, 0.1f);
				headGraphics.setParent(this);
				
				// Draw arm
				Polyline arm = new Polyline(getShoulderLocation(),getHandLocation()) ;
				armGraphics = new ShapeGraphics(arm, Color.RED, Color.RED, 0.1f);
				armGraphics.setParent(this);
				
				Polyline bottom = new Polyline(getShoulderLocation(), getBackLocation());
				bottomGraphics = new ShapeGraphics(bottom, Color.RED, Color.RED, 0.1f);
				bottomGraphics.setParent(this);
				
				Polyline knee1 = new Polyline(getBackLocation(), getKnee1Location());
				knee1Graphics = new ShapeGraphics(knee1, Color.BLUE, Color.BLUE, 0.1f);
				knee1Graphics.setParent(this);
				
				Polyline knee2 = new Polyline(getBackLocation(), getKnee2Location());
				knee2Graphics = new ShapeGraphics(knee2, Color.BLUE, Color.BLUE, 0.1f);
				knee2Graphics.setParent(this);
				
				Polyline leg1 = new Polyline(getKnee1Location(), getFoot1Location());
				leg1Graphics = new ShapeGraphics(leg1, Color.BLACK, Color.BLACK, 0.1f);
				leg1Graphics.setParent(this);
				
				Polyline leg2 = new Polyline(getKnee2Location(), getFoot2Location());
				leg2Graphics = new ShapeGraphics(leg2, Color.BLACK, Color.BLACK, 0.1f);
				leg2Graphics.setParent(this);
				
				headGraphics.draw(canvas);
				armGraphics.draw(canvas);
				bottomGraphics.draw(canvas);
				knee1Graphics.draw(canvas);
				knee2Graphics.draw(canvas);
				leg1Graphics.draw(canvas);
				leg2Graphics.draw(canvas);
				
	}
	
}
