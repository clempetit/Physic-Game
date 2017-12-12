/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.GravityWell;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level4 extends Level {
	private Polyline polyline;
	private Terrain terrain;
	private Bike bike;
	private Finish finish;
	private Checkpoint cp;
	private GravityWell puit1;
	private GravityWell puit2;
	private GravityWell puit3;
	private GravityWell puit4;
	private Vector bikePos = new Vector (0.0f, 0.0f);
	private Vector cpPos = new Vector(31.0f, -5.0f);
	
	public Level4(ActorGame game) {
		super(game);
	}
	
	public void createAllActors(boolean checkpoint) {
		if (checkpoint) {bikePos = cpPos;
        }
		polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f, 
				-20.0f, 0.0f,
				-10f, 30.0f,
				-5.0f, 30.0f,
				-5.0f, 0.0f,
				15.0f, 0.0f,
				25.0f, -5.0f,
				150.0f, -5.0f,
				6500.0f, -1000.0f
				);
		
        
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 0.3f);
		bike = new Bike(getActorGame(), false, bikePos);
        finish = new Finish(getActorGame(), new Vector(140.0f, -5.0f));
        cp = new Checkpoint(getActorGame(), cpPos);
        puit1 = new GravityWell(getActorGame(), true, new Vector(35.0f, -5.0f), new Vector(-10.0f, 40.0f), 0.1f, 8.0f, 15.0f);
        puit2 = new GravityWell(getActorGame(), true, new Vector(43.0f, -5.0f), new Vector(0.0f, -30.0f), 0.1f, 8.0f, 15.0f);
        puit3 = new GravityWell(getActorGame(), true, new Vector(35.0f, -5.0f), new Vector(30.0f, 0.0f), 0.1f, 8.0f, 15.0f);
        puit4 = new GravityWell(getActorGame(), true, new Vector(43.0f, -5.0f), new Vector(0.0f, -30.0f), 0.1f, 8.0f, 15.0f);
        getActorGame().setViewCandidates(bike);
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
	public Checkpoint getCp() {
		return cp;
	}
	
	public void update(float deltaTime) {
		puit1.gravityAction(bike.getBikeEntity());
		puit2.gravityAction(bike.getBikeEntity());
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
