/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.actor.bike.Bike;
import ch.epfl.cs107.play.game.actor.bike.Checkpoint;
import ch.epfl.cs107.play.game.actor.bike.Finish;
import ch.epfl.cs107.play.math.Vector;


public abstract class Level implements Actor{
	
	private ActorGame actorGame;
	public Level(ActorGame game) {
		actorGame = game;
	}
	
	public ActorGame getActorGame() {
		return actorGame;
	}
	
	public abstract void createAllActors(Vector bikePos);
	public abstract Finish getFinish();
	public abstract Bike getBike();
	public abstract Checkpoint getCp();	
	public abstract Vector getCpPos();
}
