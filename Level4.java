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
	private GravityWell puitBasGauche;
	private GravityWell puitBasDroite;
	private GravityWell puitHautGauche;
	private GravityWell puitHautDroite;
	private Crate crateMurGauche;
	private Crate cratePlafond;
	private Crate crateMurDroite;
	private Crate crateMurMilieu;
	private Vector bikePos = new Vector (0.0f, 0.0f);
	private Vector cpPos = new Vector(100.0f, -5.0f);
	private Vector posPuitBasGauche;
	private Vector posPuitBasDroite;
	private Vector posPuitHautGauche;
	private Vector posPuitHautDroite;
	
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
		
		posPuitBasGauche = new Vector(105.0f, -5.0f);
		posPuitBasDroite = new Vector(115.0f, -5.0f);
		posPuitHautGauche = new Vector(105.0f, 10.0f);
		posPuitHautDroite = new Vector(115.0f, 10.0f);
				
		terrain = new Terrain(getActorGame(), polyline, Color.GRAY, Color.green, 0.3f);
		bike = new Bike(getActorGame(), false, bikePos);
        finish = new Finish(getActorGame(), new Vector(140.0f, -5.0f));
        cp = new Checkpoint(getActorGame(), cpPos);
        puitBasGauche = new GravityWell(getActorGame(), true, posPuitBasGauche, new Vector(-10.0f, 40.0f), 0.f, 10.0f, 15.0f);
        puitBasDroite = new GravityWell(getActorGame(), true, posPuitBasDroite, new Vector(20.0f, 0.0f), 0.f, 10.0f, 15.0f);
        puitHautGauche = new GravityWell(getActorGame(), true, posPuitHautGauche, new Vector(20.0f, 0.0f), 0.f, 10.0f, 15.0f);
        puitHautDroite = new GravityWell(getActorGame(), true, posPuitHautDroite, new Vector(0.0f, -20.0f), 0.f, 10.0f, 15.0f);
        crateMurGauche = new Crate(getActorGame(), true, new Vector(posPuitBasGauche.getX()-2.0f, posPuitBasGauche.getY()+4.f), "box.4.png", 2.f, 28.0f, 0.3f);
        cratePlafond = new Crate(getActorGame(), true, new Vector(posPuitBasGauche.getX(), posPuitBasGauche.getY()+30.f), "box.4.png",20.f , 2.f, 0.3f);
        crateMurDroite = new Crate(getActorGame(), true, new Vector(posPuitBasDroite.getX()+10.0f, posPuitBasDroite.getY()+4.f), "box.4.png", 2.f, 28.0f, 0.3f);
        crateMurMilieu = new Crate(getActorGame(), true, new Vector(posPuitBasGauche.getX()+9.0f, posPuitBasGauche.getY()), "box.4.png", 2.f, 25.0f, 0.3f);
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
		puitBasGauche.gravityAction(bike.getBikeEntity());
		puitBasDroite.gravityAction(bike.getBikeEntity());
		puitHautGauche.gravityAction(bike.getBikeEntity());
		puitHautDroite.gravityAction(bike.getBikeEntity());
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
