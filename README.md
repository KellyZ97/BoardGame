## How to run the game

### Step 1: Copy this project to your PC

### Step 2: Run the `GamePane`

### Step 3: Input player names to load the game

**Note**: Player 1 and Player 2 names must be different.

### Step 4: Click `Start game` button to run the game.

**Note**:
`Start game` button can only be clicked before the game start.

`move` button are clicked to move the player. If the player meet hazard, There will be a pop-up window for the player to choose the operation.

### Step 4: Game Information 

**Note**: 

The game information of current player, dice one(steps), dice two(direction) and leftSteps will -1 if `move` button are clicked and player moved. 

Player 1 is a pink haired girl in the game. Player 2 is a man in the game.

There are 5 types of obstacles in the game: the edge of board, another player, fire, hole, and fence.

**Hole** are accessible obstacles has special rules. If a player moved to the hole, he or she will be relocated to start area and end the turn.

#### The process of the game is below

1. The dice are rolled automatically when the player turn changed.
2. All you need is tp click the `Move` button, when the left step is zero game will switch to next player automatically.
3. When player stopped by an obstacle, a pop-up window will make you choose
   1) `Left` - move to the left until player run out of left steps or meet any obstacle
   2) `Right` - move to the right until player run out of left steps or meet any obstacle
   3) `Skip Turn` - Skip this turn
4. When one player go into the end area, the game end and announced the winner, the record are saved to file. 
5. Then a new game will automatically run.



## Unit tests
**Note**: Test for the GamePane are always failed so I tried to use same logic method in the Game class and use it to help test.


### DiceTest
testRoll - that the roll method updates the status and die values
testGetStatus - test getStatus method returns the correct direction
testGetDieOne - Test that the getDieOne method returns the correct value

### PlayerTest
testPlayerConstructorAndGetter - test for creating new instance and Getters
testSetPosX - test for change player's x position
testSetPosY - test for change player's y position
testAddAllSteps - test for change the player's total step by increase 1

### GameTest
isObstacaledTest - test if the player meet obstacles
moveStartAreaTest - test if the player can be relocated in start area
moveTest - test if the player can move in correct direction and steps. 

### ScoresHandleTest
clearScores - before test clear the files' content
saveRecordTest - test if the method can compare and put smaller records in the file
showTop10Test - test if the top10 records can be shown in descending order 

