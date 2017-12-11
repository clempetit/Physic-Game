/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;

public abstract class Trigger {
	private Entity entity;
	private ActorGame game;
	private EntityBuilder entityBuilder;
	private PartBuilder partBuilder;
	private BasicContactListener contactListener;
	
	public Trigger(ActorGame game, boolean fixed, Vector position) {
		try{
			if(game == null|| position == null) {
				throw new NullPointerException();
			}
			this.game = game;
			entityBuilder = game.createEntityBuilder();
			entityBuilder.setFixed(fixed);
		    entityBuilder.setPosition(position);
		    entity = entityBuilder.build();
		    
		    partBuilder = getEntity().createPartBuilder();
		    partBuilder.setGhost(true);
		    
		    contactListener = new BasicContactListener();
		    getEntity().addContactListener(contactListener);
		} catch(NullPointerException e) {
			System.out.println("Paramètre(s) indispensable(s) valant null");
		}
	}
	
	public Trigger(ActorGame game, boolean fixed) {
		try {
			if (game == null) {
				throw new NullPointerException();
			}
			this.game = game;
			entityBuilder = game.createEntityBuilder();
			entityBuilder.setFixed(fixed);
			entity = entityBuilder.build();
			
			partBuilder = getEntity().createPartBuilder();
			partBuilder.setGhost(true);
			
			contactListener = new BasicContactListener();
		    getEntity().addContactListener(contactListener);
		} catch (NullPointerException e) {
			System.out.println("Paramètre(s) indispensable(s) valant null");
		}
	}
	
	
	public PartBuilder getPartBuilder() {
		return partBuilder;
	}
	
	public BasicContactListener getListener() {
		return contactListener;
	}
	
	public boolean hasContactWith(Entity entity) {
		return contactListener.hasContactWith(entity);
	}
	
	public void destroy() {
        entity.destroy();
    }
	

	protected Entity getEntity() {
		return entity;
	}
	
	protected ActorGame getOwner() {
		return game;
	}
	
	public  boolean compare(Entity compared) {
		if(getEntity()==compared) {
			return true;
		}
		else {
			return false;
		}
	}
	public Transform getTransform() {
		return getEntity().getTransform();
	}
	public Vector getVelocity() {
		return getEntity().getVelocity();
	}

}
