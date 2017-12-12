/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;


public class CrateGame extends ActorGame{
	private Crate crate1;
	private Crate crate2;
	private Crate crate3;
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        
		final float crateWidth = 1.0f;
        final float crateHeight = 1.0f;
        
        crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
        crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), "box.4.png", crateWidth, crateHeight, 1.0f);
 
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
