/*
 *	Author:      Yanis Berkani
 *	Date:        11 déc. 2017
 */


package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level3 extends Level {
	private Polyline polyline;
	private Terrain terrain;
	private Bike bike;
	private Finish finish;
	private Puits puit1;
	private Puits puit2;
	private boolean insidePuit1;
	private boolean insidePuit2;
	
	public Level3(ActorGame game) {
		super(game);
		
		
		
		
	}
	
	public Puits getPuit1() {
		return puit1;
	}
	
	public Puits getPuit2() {
		return puit2;
	}
	
	public boolean getInsidePuit1() {
		return insidePuit1;
	}
	public boolean getInsidePuit2() {
		return insidePuit2;
	}
	
	public void createAllActors() {
		
		polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 20.0f,
				-20.0f, 20.0f,
				-20.0f, 0.f,
				0.0f, 0.0f,
				100.0f, 0.0f,
				6500.0f, -1000.0f
				);
		
		
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 1.0f);
		bike = new Bike(getActorGame(), false, new Vector(0.0f, 0.0f));
        finish = new Finish(getActorGame(), new Vector(40.0f, 0.0f));
        puit1 = new Puits(getActorGame(), true, new Vector(25.0f, 0.0f));
        puit2 = new Puits(getActorGame(), true, new Vector(30.0f, 0.0f));
        getActorGame().setViewCandidates(bike);
	}
	
	public void changeGravity() {
		if(puit1.hasContactWith(bike.getBikeEntity()) || puit1.hasContactWith(bike.getLeftWheel().getEntity())|| puit1.hasContactWith(bike.getRightWheel().getEntity())) {
			bike.getBikeEntity().destroy();
		}
		
		if(puit2.hasContactWith(bike.getBikeEntity()) || puit2.hasContactWith(bike.getLeftWheel().getEntity())|| puit2.hasContactWith(bike.getRightWheel().getEntity())) {
			bike.getBikeEntity().applyAngularForce(10.0f );
		} 
	}
	
	@Override
	public Finish getFinish() {
		return finish;
	}
	
	@Override
	public Bike getBike() {
		return bike;
	}
	
	@Override
	public void draw(Canvas canvas) {
		terrain.draw(canvas);
		finish.draw(canvas);
	}
	
	//la redéfinition de getTransform() et getVelocity() est obligatoire ici comme dans chaque autre level,
	//car la classe Level implémente l'interface Actor.
	//Cependant elle n'ont pas ici d'utilité, chaque acteur du Level ayant ses propres fonctions.
	//Le choix de return terrain.getEntity()... est donc arbitraire et n'a pas d'impact sur le jeu.
    
	@Override
	public Transform getTransform() {
		return terrain.getEntity().getTransform();
	} 
	
	@Override
	public Vector getVelocity() {
		return terrain.getEntity().getVelocity();
	}

}
