# Battleship
This is a Battleship game implementation in Java.

## Rules:

Battleship is a war-themed board game for two players in which the opponents try to guess the location of the other's various ships. The object of Battleship is to try  guess the location of the ships each player and sink all of the other player's before they sink all of your ships. All of the other player's ships are somewhere on his/her board.  You try and hit them by calling out the coordinates of one of the squares on the board.  The other player also tries to hit your ships by calling out coordinates.  All the players play in turns. Neither you nor the other player can see the other's board so you must try to guess where they are.  Each board in the physical game has two grids:  the horizontal section for the player's ships and the vertical during play for recording the player's guesses.

Each player places the 5 ships somewhere on their board.  The ships can only be placed vertically or horizontally. Diagonal placement is not allowed. No part of a ship may hang off the edge of the board.  Ships may not overlap each other.  No ships may be placed on another ship. Each player receives a game board with two grids, one of each type of ship with holes where the "hit" pegs are inserted, and a supply of hit and miss markers (white and red pegs). 
Once the guessing begins, the players may not move the ships.
The 5 ships are:  Carrier (occupies 5 spaces), Battleship (4), Cruiser (3), Submarine (3), and Destroyer (2).  

Players take turns firing shots (by calling out a grid coordinate) to attack enemy ships.
On your turn, call out a letter and a number that identifies a row and column on your target grid. Your opponent checks that coordinate on his ocean grid and verbally responds "miss" if there is no ship there, or "hit" if you have correctly guessed a space that is occupied by one of his ships.

Mark each of your shots (guesses) on your upper target grid using white pegs to document your misses and red pegs to register your hits. As the game proceeds, the red pegs will gradually identify the size and location of your opponent's ships.

When it is your opponent's turn to fire shots at you, each time one of your ships receives a hit, put a red peg into the hole on the ship corresponding to the grid space. The first player to sink all five of his opponent's ships wins the game.

## How to play:

From the starting menu the user can choose one of 2 options: Single player or Multiplayer. In the single player mode of the game the user first has to place their ships. The ships can be rotated by pressing "R" or right clicking on the mouse. 5 ships has to be placed for the game to start.  User has to guess where the enemy ships are and after the user chooses a coordinate and attacks if the attack is a hot it will be marked with a "X" or if it is a miss it will be marked with a ".". When either side destroys all the enemy ships the game finishes. 
If the user chooses the multiplayer option a "ready" button appears on the screen. This is for preventing the other side from knowing the placement of the ships. After one of the users presses ready they start placing their ships and when they are done the "ready" button appears again  and after pressing it the other player can place their ships and the game begins. The same rules as the single player applies here only difference being users has to press the "ready" button after every attack in order to prevent them from seeing eachother's ships.
