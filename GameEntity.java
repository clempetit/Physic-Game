/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Vector;

public abstract class GameEntity {
	private Entity entity;
	private ActorGame actorGame;
	
	public GameEntity(ActorGame game, boolean fixed, Vector position) {
		actorGame = game;
		EntityBuilder entityBuilder = game.createEntityBuilder();
        entityBuilder.setFixed(fixed);
        entityBuilder.setPosition(position);
        entity = entityBuilder.build();
	}
	
	public GameEntity(ActorGame game, boolean fixed) {
		this(game, fixed, Vector.ZERO);
	}
	
	public void destroy() {
        entity.destroy();
    }
	
	// pas sûr, return entity ou sa représentation graphique ?
	protected Entity getEntity() {
		return entity;
	}
	
	protected ActorGame getOwner() {
		return actorGame;
	}

}
