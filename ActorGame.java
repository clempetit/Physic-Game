/*
 *	Author:      Yanis Berkani
 *	Date:        2 déc. 2017
 */

package ch.epfl.cs107.play.game.actor;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.DefaultFileSystem;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.FolderFileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public abstract class ActorGame implements Game {
	
	protected Window window;
	private World world;
	
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	FileSystem fileSystem = new FolderFileSystem(new ResourceFileSystem(DefaultFileSystem.INSTANCE));
	
	// Viewport properties
	private Vector viewCenter;
	private Vector viewTarget;
	private Positionable viewCandidate;
	private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
	private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f;
	private static final float VIEW_SCALE = 10.0f;
	
	public void removeActor(Actor actor) {
		actors.remove(actor);
	}
	
	public void removeAllActors() {
		actors.removeAll(actors);
	}
	
	public void restart(ActorGame game, float deltaTime) {
		game.begin(window, fileSystem);
	}
	
	public void addActor(Actor actor) {
		actors.add(actor);
	}
	
	public Keyboard getKeyboard(){
		return window.getKeyboard();
	}
	
	public Canvas getCanvas(){
		return window;
	}
	
	public void setViewCandidates(Positionable viewCandidates) {
		this.viewCandidate = viewCandidates;
	}
	
	public EntityBuilder createEntityBuilder() {
		return world.createEntityBuilder();
	}
	
	public WheelConstraintBuilder createWheelConstraintBuilder() {
		return world.createWheelConstraintBuilder();
	}
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        this.window = window;

        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
        
        viewCenter = Vector.ZERO;
        viewTarget = Vector.ZERO;
        return true;
	}
	
	@Override
    public void update(float deltaTime) {
		world.update(deltaTime);
		
		for(int i = 0; i<actors.size(); i++) {
			actors.get(i).update(deltaTime);
			}
		
		// Update expected viewport center
		if (viewCandidate != null) {
    		viewTarget = viewCandidate.getPosition().add(viewCandidate.getVelocity() .mul(VIEW_TARGET_VELOCITY_COMPENSATION));
    		}
		
		// Interpolate with previous location
		float ratio = (float)Math.pow(VIEW_INTERPOLATION_RATIO_PER_SECOND, deltaTime);
		viewCenter = viewCenter.mixed(viewTarget, ratio);
		
		// Compute new viewport
		Transform viewTransform = Transform.I.scaled(VIEW_SCALE).translated(viewCenter);
		window.setRelativeTransform(viewTransform);
		
		for(int i = 0; i<actors.size(); i++) {
    		actors.get(i).draw(window);
    		}
		
	}

	@Override
	public void end() {
	}

}
