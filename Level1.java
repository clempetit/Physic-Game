/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level1 extends Level{
	private Polyline polyline;
	private Terrain terrain;
	private Crate crate1;
	private Crate crate2;
	private Crate crate3;
	private Bike bike;
	private Finish finish;
	
	public Level1(ActorGame game) {
		super(game);
	}
	public void createAllActors() {
		final float crateWidth = 1.0f;
        final float crateHeight = 1.0f;
		polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f, 0.0f, 0.0f,
				3.0f, 1.0f,
				8.0f, 1.0f,
				15.0f, 3.0f,
				16.0f, 3.0f,
				25.0f, 0.0f,
				35.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				6500.0f, -1000.0f
				);
		
        
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 1.0f);
        crate1 = new Crate(getActorGame(), false, new Vector(0.0f, 5.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate2 = new Crate(getActorGame(), false, new Vector(0.2f, 7.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate3 = new Crate(getActorGame(), false, new Vector(2.0f, 6.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        bike = new Bike(getActorGame(), false, new Vector(4.0f, 5.0f));
        finish = new Finish(getActorGame(), new Vector(45.0f, -5.0f));
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
		crate1.draw(canvas);
		crate2.draw(canvas);
		crate3.draw(canvas);
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
