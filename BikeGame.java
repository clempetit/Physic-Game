/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame{
	private Polyline polyline;
	private Terrain terrain;
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        
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
