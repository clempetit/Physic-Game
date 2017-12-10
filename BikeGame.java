/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame{
	private Polyline polyline;
	private Terrain terrain;
	private Polygon polygon;
	private Crate crate1;
	private Crate crate2;
	private Crate crate3;
	private Bike bike;
	private Finish finish;
	private TextGraphics message;
	private TextGraphics message1;
	private TextGraphics message_fixBug ; 
	private boolean finished;
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        finished = false;
        final float crateWidth = 1.0f;
        final float crateHeight = 1.0f;
        
        /* Message vide du départ pour pallier au problème
         * de latence lors de l'arrivée du cycliste (sur mac)
         */
        message_fixBug = new TextGraphics("", 0.3f, Color.RED, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
        message_fixBug.setParent(getCanvas());
        message_fixBug.draw(getCanvas());
        
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
		
        
        terrain = new Terrain(this, polyline, Color.GRAY, Color.green, 1.0f);
        crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        bike = new Bike(this, false, new Vector(4.0f, 5.0f));
        finish = new Finish(this, new Vector(45.0f, -5.0f));
        this.setViewCandidates(bike);
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
    	super.update(deltaTime);
    	 	
    	 if(bike.getHit()) {
    		       bike.destroy();
		       showText("PERDU !", 0.3f);
    	 }
    	 	
    	 if (this.window.getKeyboard().get(KeyEvent.VK_SPACE).isPressed()){
    		 if(!(finished)) {
    	 		bike.setOppositeDirection(bike.getDirection());
    		 }
    	 }
    	 	
    	 if(this.window.getKeyboard().get(KeyEvent.VK_UP).isDown()) {
     	 	if(bike.getDirection()) {     // Le cycliste avance dans différentes directions selon son orientation.
     	 		bike.MoveRight();		
     	 	}
     	 	else {
     	 		bike.MoveLeft();
     	 	}
    	 }
    	 else {
    		 bike.getLeftWheel().relax();
    		 bike.getRightWheel().relax();
    	 } 
    	 
    	 if (this.window.getKeyboard().get(KeyEvent.VK_DOWN).isDown()) {
    	 		bike.getRightWheel().power(0.f);
    	 		bike.getLeftWheel().power(0.f);
    	 }
   	
	 if (this.window.getKeyboard().get(KeyEvent.VK_R).isPressed()){
    		 removeAllActors();
    		 restart(this, deltaTime);
	 }
	    
    	 if(this.window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
    		 if(!(finished)) {
     	 		bike.getBike().applyAngularForce(-30.0f);
     	 	}
    	 }
    	 	
    	 if(this.window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
    		 if(!(finished)) {
      	 		bike.getBike().applyAngularForce(30.0f);
      	 	}
    	 }
    	 
    	 if (finish.getListener().hasContactWith(bike.getEntity())){
     	    finished = true;
     	   finish.destroy();
     }
    	 
    	 if (finished) {
     		showText("BRAVO !", 0.3f);
      	 	bike.getRightWheel().power(0.f);
      	 	bike.getLeftWheel().power(0.f);
    	 }
    }
    
    @Override
    public void end() {
    }
    
}
