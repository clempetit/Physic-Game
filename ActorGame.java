/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor;

import java.awt.Color;
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
	
	public void restart(ActorGame game) {
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
	
	public void showText(String text, float size, float abs, float ord, Color fillColor, Color outlineColor, boolean bold, boolean italic, float transparency) {
		if (size < 0) {size = -size;}
		if (size == 0.0f) {size = 0.01f;}
		TextGraphics message = new TextGraphics(text, size, fillColor, outlineColor, 0.02f, bold, italic, new Vector(0.5f, 0.5f), transparency, 100.0f);
 	 	message.setParent(getCanvas());
 	 	message.setRelativeTransform(Transform.I.translated(abs, ord));
 	 	message.draw(getCanvas());
	}
	
	public void victoryText() {
		showText(" YOU ", 0.3f, -0.55f,-0.6f, Color.YELLOW, Color.BLACK, true, false, 1.0f);
		showText(" WON ! ", 0.3f, 0.45f,-0.6f, Color.YELLOW, Color.BLACK, true, false, 1.0f);
		showText("PRESS ", 0.08f, -0.62f, -1.3f, Color.YELLOW, Color.BLACK, false, false, 1.0f);
		showText(" R ", 0.08f, -0.32f, -1.3f, Color.YELLOW, Color.BLACK, false, false, 1.0f);
		showText(" TO ", 0.08f, -0.15f, -1.3f, Color.YELLOW, Color.BLACK, false, false, 1.0f);
		showText(" PLAY ", 0.08f, 0.18f, -1.3f, Color.YELLOW, Color.BLACK, false, false, 1.0f);
	    showText(" AGAIN ", 0.08f, 0.55f, -1.3f, Color.YELLOW, Color.BLACK, false, false, 1.0f);
	}
	
	public void transitionText(float transparency) {
		showText(" NEXT ", 0.20f, -0.6f,-0.6f, Color.BLUE, Color.WHITE, true, false, transparency);
		showText(" LEVEL ! ", 0.20f, 0.4f,-0.6f, Color.BLUE, Color.WHITE, true, false, transparency);
	}
	
	
	
	
	public void defeatText() {
		 showText("WASTED !", 0.3f, -0.05f,-0.6f, Color.RED, Color.BLACK, true, false, 1.0f);
		 showText("PRESS ", 0.08f, -0.48f, -1.3f, Color.RED, Color.BLACK, false, false, 1.0f);
		 showText(" R ", 0.08f, -0.19f, -1.3f, Color.RED, Color.BLACK, false, false, 1.0f);
		 showText(" TO ", 0.08f, -0.02f, -1.3f, Color.RED, Color.BLACK, false, false, 1.0f);
		 showText(" RESTART ", 0.08f, 0.4f, -1.3f, Color.RED, Color.BLACK, false, false, 1.0f);
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
