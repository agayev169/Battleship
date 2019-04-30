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

In the first version of game we have no GUI there,means we play in terminal only.Also there 2 choises to play :
1) Single-player, means versus BOT.
2) Multi-player, means versus another person.

For Single-player:

We choose which ship we want to put on our grid (with 2/3/4/5 segments).Then we build our ships on our grid. To put a ship on grid, we need to write his x and y coordinates (for example b4),then V(for Vertical) and H(for Horizontal).We do this operation 5 times, because of number of ships. After this operation, BOT puts his ships on his grid.

When we finish putting ships on grid, phase of destruction starts and we play our game. To choose which cell we want to check, we write two coordinates with the space between them (for example a 5, b 3). At the end of our game, displayed player, which won and all grids that we have, means:
1) user's grid with his ships.
2) empty grid for user to note, where he shoot.
3) bot's grid with his ships. 
4) empty grid for bot to note.

For Multi-player:

The same configuration as for single-player, but need to write "ready" at the beginning of each turn. For example first user put his ships on grid, second user comes and types "ready", only after this word he can begin to put his ships on his grid. We used this command to not let users cheat and see other's grid while playing.

User1:(ready) (a 5);
User2:(ready) (b 3);
User1:(ready) (b 8);
