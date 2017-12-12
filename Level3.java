/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.general.GravityWell;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level3 extends Level {
	private Polyline polyline;
	private Terrain terrain;
	private Bike bike;
	private Finish finish;
	private GravityWell puit;
	private Checkpoint cp;
	private Vector bikePos = new Vector (4.0f, 5.0f);
	private Vector cpPos = new Vector (47.0f, 4.0f);
	public Level3(ActorGame game) {
		super(game);
	}
	
	public void createAllActors(boolean checkpoint) {
		if (checkpoint) {bikePos = cpPos;
        }
		polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 20.0f,
				-20.0f, 20.0f,
				-20.0f, 0.f,
				0.0f, 0.0f,
				39.0f, 0.0f,
				39.0f, 4.0f,
				70.0f, 4.0f,
				6500.0f, -1000.0f
				);
		
		
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 1.0f);
		bike = new Bike(getActorGame(), false, bikePos);
        finish = new Finish(getActorGame(), new Vector(65.0f, 4.0f));
        puit = new GravityWell(getActorGame(), true, new Vector(25.0f, 0.0f), new Vector(0.0f, 50.0f));
        cp = new Checkpoint(getActorGame(), cpPos);
        

        getActorGame().setViewCandidates(bike);
        
        
	}
		
	
	public void update(float deltaTime) {
		puit.gravityAction(bike.getBikeEntity());
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
	
	
	
	@Override
	public void draw(Canvas canvas) {
		terrain.draw(canvas);
		finish.draw(canvas);
		puit.draw(canvas);
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
