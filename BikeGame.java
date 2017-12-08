/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Wheel;
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
		polygon = new Polygon(
				0.0f, 0.5f,
				0.5f, 1.0f,
				0.0f, 2.0f,
				-0.5f, 1.0f
				);
        
        terrain = new Terrain(this, polyline, Color.GRAY, Color.green, 1.0f);
        crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        bike = new Bike(this, false, polygon, new Vector(4.0f, 5.0f));
        
        this.setViewCandidates(bike);
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
    	 	super.update(deltaTime);
    }
    

    @Override
    public void end() {
    }

}
