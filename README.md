**Jane Kim**  
**jk3316**

#Gomoku

Gomoku playing agent using Minimax & Alpha-Beta Pruning

##Usage
To compile:

```
cd build
// path: assignment3/build
javac -sourcepath ../src -d ./ ../src/*
java GomokuPlay <board dimension> <winning chain length> <time limit> <game mode> 
```

##Evaluation function
The evaluation function counts chains of m-1, m-2, and m-3 length on the board and weights each count, m-1 being the most valuable. Generally, consecutive pieces for a player is a good measure of how good the board because then the player can continue the chains to win.

The nearWin methods are used to find these chains. nearWin(1) looks for chains of m-1 length and appends empty spaces on either end, since a chain of the same piece is only useful if the player is able to continue the chain.  
`nearWin(1)` for player x looks for `[. x x x x]` or `[x x x x .]` on the board.

Near the start of the game there are simply too many empty spaces to look at, so my agent only looks at moves adjacent to pieces it has already placed to save time. 

I use string manipulation to speed up runtime instead of traversing through the char[][]. When checking for win or near win states, I convert the row or column I'm looking at into a `String` and use methods (`contains` and `indexOf`). For diagonals, I have a function that travels the board from the just placed piece in a top left to bottom right fashion and top right to bottom left. 

I determined that it was a good evaluation function because it beats the RandomAgent almost every time. It does not hold up too well against a human, though. 

###Human vs SmartAgent
Because of the relatively weak evaluation function, it is fairly easy for a human to beat the smart agent. In the beginning of the game (more when the human goes second) the human does have to spend a lot of energy blocking the Smart agent since the smart agent immediately starts to form a chain. 

###RandomAgent vs SmartAgent
The SmartAgent usually wins against the RandomAgent. It takes slightly longer to win when it goes second because of the inherent disadvantage of not being first. Smart agent usually wins on diagonals. 

###SmartAgent vs SmartAgent
In this mode, which ever player goes first wins. Because I did not get a chance to implment the evaluation to check for nearwins in diagonals, the players do not take advantage of m-away chains on diagonals. The moves for each player are clustered in different areas of the board because I did not implment the evaluation function to look at the other players' pieces and thus try to block the opponent's chains. Increasing the lookahead leads to a faster game. 