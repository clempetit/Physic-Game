Clément PETIT - Yanis BERKANI
EPFL - Bachelor 1 Section Informatique
Introduction à la Programmation

Projet 2 : Physic Game

Pour lancer le jeu BikeGame, il suffit de lancer ch.epfl.cs107.play/Program.java
Cela va démarrer la méthode main fournie avec l'attribut game prenant la valeur new BikeGame() .

Contrôles du cycliste: 
   - HAUT pour avancer
   - BAS pour freiner
   - DROITE et GAUCHE pour faire pivoter le vélo dans un sens ou dans l’autre
   - SPACE pour changer le sens du cycliste.

Le jeu BikeGame comporte quatre niveaux. Le but du jeu est de faire parvenir le cycliste à la fin du parcours (drapeau rouge) sans lui faire toucher le sol ou des obstacles. Seules les roues peuvent toucher ces derniers.
Les obstacles en question peuvent être de différentes natures : caisses, pendule, pont…

- Niveau 1 : Niveau sans difficulté, avancer simplement jusqu'au drapeau rouge.
- Niveau 2 : Niveau comportant un pendule à éviter et un pont à baisser. A noter qu'il faut une force suffisante pour abaisser le pont.
- Niveau 3 : Niveau introduisant le puit de gravité, il faut arriver dedans avec une certaine vitesse pour atteindre le rebord sur la gauche.
- Niveau 4 : Niveau avec terrain glissant, les pendules sont un peu plus durs à éviter et la fin du niveau nécessite une certaine agilité avec les puits de gravité.

Si le cycliste touche le sol ou un obstacle, le niveau est perdu. Il suffit d’appuyer sur R pour le réessayer.

Quand un niveau est réussi, le niveau suivant démarre automatiquement, et une fois tous les niveaux réussis, le jeu est gagné et le personnage lève les bras en l’air. Il est alors possible de recommencer le jeu du début en appuyant sur R.

Bien que les niveaux soient relativement simples à effectuer, on notera que la fin du niveau 4 est un peu plus compliquée, mais toutefois faisable.
