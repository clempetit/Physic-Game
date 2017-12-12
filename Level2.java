/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.general.Bascule;
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
	private Bascule bascule;
	private Bike bike;
	private Finish finish;
	private Vector bikePos = new Vector (4.0f, 5.0f);
	private Vector cpPos = new Vector(55.0f, -5.0f);
	private Checkpoint cp;
	
	public Level2(ActorGame game) {
		super(game);
	}
	
	public void createAllActors(boolean checkpoint) {
		if (checkpoint) {bikePos = cpPos;
        }
       
		polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, -500.0f,
				0.0f, 0.0f,
				8.0f,0.0f,
				25.0f, 4.0f,
				25.0f, 0.0f,
				35.0f, -5.0f,
				60.0f, -5.0f,
				80.0f, -8.0f,
				83.0f, -8.0f,
				83.0f, -20.0f,
				89.0f, -20.0f,
				89.0f, -7.0f,
				95.0f, -7.0f,
				105.0f, -5.0f,
				106.0f, -5f,
				106.0f, -1000.0f
				);
		
        
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 1.0f);
		pendulum = new Pendulum(getActorGame(), new Vector(50.0f, 0.0f), 3.0f);
		bascule = new Bascule(getActorGame(), new Vector(83.0f, -8.2f), 0.8f, 5.0f);
		bike = new Bike(getActorGame(), false, bikePos);
        finish = new Finish(getActorGame(), new Vector(105.0f, -5.0f));
        cp = new Checkpoint(getActorGame(), cpPos);
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
