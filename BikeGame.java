/*
 *	Author:      Yanis Berkani
 *	Date:        7 déc. 2017
 */


package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.actor.ActorGame;
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
	
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        
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
		
        
        terrain = new Terrain(this, polyline, Color.GRAY, Color.green, 1.0f);
        crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        bike = new Bike(this, false, new Vector(4.0f, 5.0f));
        
        this.setViewCandidates(bike);
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
    	 	super.update(deltaTime);
    	 	
    	 	if (this.window.getKeyboard().get(KeyEvent.VK_SPACE).isPressed()){
    	 		bike.setOppositeDirection(bike.getDirection());
    	 	}
    	 	
    	 	if(this.window.getKeyboard().get(KeyEvent.VK_DOWN).isDown()) {
    	 		bike.getRightWheel().power(0.f);
    	 		bike.getLeftWheel().power(0.f);
    	 	}
    	 	
    	 	if(this.window.getKeyboard().get(KeyEvent.VK_UP).isDown()) {
    	 		if(bike.getDirection()) {     // Le cycliste avance dans différentes directions selon son orientation.
    	 			bike.MoveRight();		
    	 		}
    	 		else {
    	 			bike.MoveLeft();
    	 		}
    	 	}
    	 	

    	 	if(this.window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
    	 		bike.getBike().applyAngularForce(-40.0f);
    	 	}
    	 	
    	 	if(this.window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
    	 		bike.getBike().applyAngularForce(40.0f);
    	 	}
    	 	
    }
    

    @Override
    public void end() {
    }
    
    
}