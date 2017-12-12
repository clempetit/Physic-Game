/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Pendulum;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level2 extends Level {
	private Polyline polyline;
	private Terrain terrain;
	private Pendulum pendulum;
	private Bike bike;
	private Finish finish;
	
	public Level2(ActorGame game) {
		super(game);
	}
	
	public void createAllActors() {
		final float crateWidth = 1.0f;
        final float crateHeight = 1.0f;
		polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, -500.0f,
				0.0f, 0.0f,
				8.0f,0.0f,
				25.0f, 4.0f,
				25.0f, 0.0f,
				35.0f, -5.0f,
				70.0f, -5.0f,
				85.0f, -4.0f,
				100.0f, 0.0f,
				101.0f, 0.0f,
				101.0f, -1000.0f
				);
		
        
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 1.0f);
		pendulum = new Pendulum(getActorGame(), new Vector(50.0f, 0.0f), 3.0f);
		bike = new Bike(getActorGame(), false, new Vector(4.0f, 5.0f));
        finish = new Finish(getActorGame(), new Vector(100.0f, 0.0f));
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
