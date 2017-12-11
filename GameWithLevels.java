/*
 *	Author:      Clément Petit
 *	Date:        15.10.2015
 */

package ch.epfl.cs107.play.game.actor;

public interface GameWithLevels {
	// gère ce qui se passe lorsque la transition au niveau suivant doit se faire :
	public abstract void nextLevel();
	// gère ce qui se passe lorsque l'on veut recommencer le niveau courant :
	public abstract void resetLevel();
}
