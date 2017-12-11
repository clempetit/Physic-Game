/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;  
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameWithLevels;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class LevelGame extends ActorGame implements GameWithLevels{
	
	
	private TextGraphics message_fixBug ; 
	private boolean finished;
	private Level level;
	private int progression = 0;
	private float time = 2.0f;
	private float transparency =1.f;
	protected List<Level> createLevelList() {
		return Arrays.asList(
				new Level1(this),
				new Level2(this),
				new Level3(this),
				new Level4(this)
				);
	}
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        finished = false;
        
        /* Message vide du départ pour pallier au problème
         * de latence lors de l'arrivée du cycliste (sur mac)
         */
        message_fixBug = new TextGraphics("", 0.3f, Color.RED, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
        message_fixBug.setParent(getCanvas());
        message_fixBug.draw(getCanvas());
        
       level = createLevelList().get(progression);
       level.createAllActors();
       
        return true;
    }
    
    @Override
    public void update(float deltaTime) {
    	super.update(deltaTime);
    		 	
    	 if(level.getBike().getHit() && !(finished)) {
    		 level.getBike().destroy();
		       defeatText();
    	 }
    	 	
    	 if (this.window.getKeyboard().get(KeyEvent.VK_SPACE).isPressed()){
    		 if(!(finished)) {
    			 level.getBike().setOppositeDirection(level.getBike().getDirection());
    		 }
    	 }
    	 	
    	 if(this.window.getKeyboard().get(KeyEvent.VK_UP).isDown()) {
     	 	if(level.getBike().getDirection()) {     // Le cycliste avance dans différentes directions selon son orientation.
     	 		level.getBike().MoveRight();		
     	 	}
     	 	else {
     	 		level.getBike().MoveLeft();
     	 	}
    	 }
    	 else {
    		 level.getBike().getLeftWheel().relax();
    		 level.getBike().getRightWheel().relax();
    	 } 
    	 
    	 if (this.window.getKeyboard().get(KeyEvent.VK_DOWN).isDown()) {
    		 level.getBike().getRightWheel().power(0.f);
    		 level.getBike().getLeftWheel().power(0.f);
    	 }
   	
	 if (this.window.getKeyboard().get(KeyEvent.VK_R).isPressed()){
    		 if(finished) {
    			 progression = 0;
    			 resetLevel();
    		 } else {
    			 resetLevel();
    		 }
	 }
	    
    	 if(this.window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
    		 if(!(finished)) {
    			 level.getBike().getBikeEntity().applyAngularForce(-30.0f);
     	 	}
    	 }
    	 	
    	 if(this.window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
    		 if(!(finished)) {
    			 level.getBike().getBikeEntity().applyAngularForce(30.0f);
      	 	}
    	 }
    	 
    	 if (level.getFinish().hasContactWith(level.getBike().getBikeEntity())
    			 || level.getFinish().hasContactWith(level.getBike().getRightWheel().getEntity())
    			 || level.getFinish().hasContactWith(level.getBike().getLeftWheel().getEntity()) ){
    		 if (progression == createLevelList().size() - 1) {
    			 level.getBike().setFinishedHandLocation();
    			 finished = true;
    			 level.getFinish().destroy();
    		 } else {
    			 nextLevel();
    		 }
     }
	  if (time<=1.0f && !(level.getBike().getHit())) {
 		 	transitionText(transparency);
 		 	time += deltaTime;
 		 	transparency -= deltaTime/2;
 	 }
    	 
    	 if (finished) {
     		victoryText();
      	 	level.getBike().getRightWheel().power(0.f);
      	 	level.getBike().getLeftWheel().power(0.f);
    	 }
    	 if(level.getBike().getDirection()) {
    		 level.getBike().setFoot1Location(new Vector((float)Math.cos(-(level.getBike().getLeftWheel().getAngularPosition()/2))*0.25f, (float)(-(Math.sin(-(level.getBike().getLeftWheel().getAngularPosition()/2))))*0.25f + 0.1f));
    		 level.getBike().setFoot2Location(new Vector((float)(-(Math.cos(-(level.getBike().getLeftWheel().getAngularPosition()/2))))*0.25f, (float)Math.sin(-(level.getBike().getLeftWheel().getAngularPosition()/2))*0.25f +0.1f));
		
    		 level.getBike().setKnee1Location(new Vector((float)Math.sin(level.getBike().getLeftWheel().getAngularPosition()/2)*0.2f, (float)Math.sin(level.getBike().getLeftWheel().getAngularPosition()/2)*0.2f + 0.65f));
    		 level.getBike().setKnee2Location(new Vector((float)(-(Math.sin(level.getBike().getLeftWheel().getAngularPosition()/2)))*0.2f, (float)(-(Math.sin(level.getBike().getLeftWheel().getAngularPosition()/2)))*0.2f +0.65f)); 
    	 
    	 }
    	 else {	
    		 level.getBike().setFoot1Location(new Vector((float)Math.cos((level.getBike().getRightWheel().getAngularPosition()/2))*0.25f, (float)(-(Math.sin((level.getBike().getRightWheel().getAngularPosition()/2))))*0.25f +0.1f));
    		 level.getBike().setFoot2Location(new Vector((float)(-(Math.cos((level.getBike().getRightWheel().getAngularPosition()/2))))*0.25f, (float)Math.sin((level.getBike().getRightWheel().getAngularPosition()/2))*0.25f +0.1f));

    		 level.getBike().setKnee1Location(new Vector((float)(-(Math.sin(level.getBike().getRightWheel().getAngularPosition()/2)))*0.2f, (float)(-(Math.sin(level.getBike().getRightWheel().getAngularPosition()/2)))*0.2f + 0.65f));
    		 level.getBike().setKnee2Location(new Vector((float)Math.sin(level.getBike().getRightWheel().getAngularPosition()/2)*0.2f, (float)Math.sin(level.getBike().getRightWheel().getAngularPosition()/2)*0.2f +0.65f)); 
    	 }
    }
    
    @Override
    public void end() {
    }
    
    @Override
    public void nextLevel() {
    removeAllActors();
    	progression++;
    	restart(this);
    	if (progression>=1){
    		time = -3.f;
    		transparency = 1.f;
    	}
    }
    
    @Override
    public void resetLevel() {
    removeAllActors();
    restart(this);
    }
    
}
