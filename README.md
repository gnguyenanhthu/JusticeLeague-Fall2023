# JusticeLeague-Fall2023
# SCP Escape Game - README
Welcome to the SCP Escape Game! In this text-based adventure, your goal is to navigate through the four floors of the SCP Foundation, solve puzzles, and escape safely. Below, you'll find information on commands, floor details, puzzles, and more.

# Commands in SCP Escape Game
## Movement Commands:
- North (N): Move the player character north and enter the new room.
- East (E): Move the player character east and enter the new room.
- South (S): Move the player character south and enter the new room.
- West (W): Move the player character west and enter the new room.

## Exploration Commands:
- Explore: Investigate the current room for any hidden details or clues.
- Solve: Start solving a puzzle in a room.
- Inventory Commands:
  - Inventory: Display the contents of the player's inventory.
  - Pickup [Item]: Collect an item from the current room.
  - Drop [Item]: Remove an item from the player's inventory.
  - Inspect [Item]: Examine an item in the player's inventory or the current room.
  - Combine: Combine two keycards in SCP-914 room to upgrade clearance level.
  - Unequip [Item]: Remove an equipped item from the player's inventory.
  - Equip [Item]: Equip an item from the player's inventory.
  - Use [Item]: Interact with doors, items, the elevator, and control switch.

## Player Information Commands:
- Help: Display the game's help menu.
- Location: Show the current location of the player.
- Weapon: List the weapons available to the player.
- Stats: Display the currently equipped items.
- Monster Interaction Commands:
  - Ex Monster: Inspect and gather information about monsters in the current room.
- Game Progression Commands:
  - Info: Display general information about the game.
  - Save: Save the current game progress to a file.
  - Load: Load a previously saved game state from a file.
  - Exit: Quit the game without saving.
- Other Commands:
  - Gallery:Displays the pictures of all monsters in the game.

# File Structure:
- MVC Design:
  - GameView.java: This contains the visuals of the main logic of the game.
  - GameController.java: This takes the inputs from user and pass to the game logic.
  - GameModel.java: This contains all models and business logics in game.
- Models:
1. Game.java: Initials MVC design and shows the stage.
2. Consumable.java: Represents consumable items in the game.
3. Equippable.java: Represents equippable items in the game, extending the Item class.
4. Item.java: Represents a generic item in the game.
5. Weapon.java: Represents weapons in the game, extending the Item class.
6. Monster.java: Represents monsters in the game.
7. Player.java: Represents the player character in the game.
8. Puzzle.java: Represents puzzles in the game.
9. Room.java: Represents rooms in the game.
10. Map.java: Represents the game map.
- Text File:
1. Map.txt: This file contains the data for each room. Each map has an ID, name, and description. The game reads this file to initialize the rooms.
2. SpawnRoom.txt: Room data file for the spawn room. Contains spawnID,roomID, name, and description.
3. Item.txt: This file contains the data of all items in the game. Contains RoomID, item ID, name, type and description.
4. Puzzle.txt: This file for defining puzzle attributes and solutions. 
5. Monster.txt: This file for defining monster attributes and behaviors and stats.
5. CombineRequired.txt: This file allows what item can be combine to to progress in the game.
6. Save1.bin: This is where the game is saved and loaded.

# Puzzles in SCP Escape Game:
1. The Locker (LC-#00):
- Description: A locker with a combination lock and a note nearby. The note instructs that the combination is related to the designations of SCP documents on the current level.
- Solution: Examine SCP documents on the current level, find the SCP designation mentioned in the note, and use it as the combination.
- Outcome: Successfully unlocking the locker reveals items and a note about secret contraband in LC-CD-02.

2. Your First Weapon (LC-CD-02):
- Description: A loose brick in the room with hidden contraband. A prompt appears when inspecting the brick, indicating it can be freed with a small amount of force.
- Solution: Use the brick to pull it free from the wall.
- Outcome: Pulling the brick reveals a Level 0 KeyCard and a document about SCP-173.

3. Stare At The Void (LC-SCP-173):
- Description: Encounter SCP-173 in a well-lit room, feeling strangled. The prompt suggests using the "stare" command.
- Solution: Use the "stare" command, followed by the "run" command.
- Outcome: Successfully escaping to the next room.

4. Smashing (HC-03):
- Description: A wall obstructing access to an air vent. The prompt suggests using the brick to smash the gate.
- Solution: Use the brick to bash the gate on the air vent.
- Outcome: Clearing the way to access the air vent (HC-04).

5. Amazing (HC-05):
- Description: A maze-like room with a note providing directions. The prompt suggests solving the maze.
- Solution: Input the correct sequence of directions provided in the note.
- Outcome: Successfully navigating through the maze to the next room.

6. Amazing 2 (HC-06):
- Description: Another maze requiring the reverse order of the previous note. The prompt suggests solving the maze.
- Solution: Input the reverse sequence of directions provided in the note.
- Outcome: Successfully navigating through the maze to the next room.

7. Nikola Tesla (EZ-03):
- Description: Room with a tesla gate and SCP-079 watching. The prompt suggests waiting for the camera to look away.
- Solution: Use the "wait" command until the camera looks away, then proceed.
- Outcome: Successfully passing through the tesla gate.

8. Got that dog in him (EZ-01):
- Description: Room with SCP-939, a dog-like creature. The prompt suggests distracting SCP-939 and making a run for it.
- Solution: Use the "sneak," "throw brick," and "run" commands in sequence.
- Outcome: Successfully distracting SCP-939 and reaching the next room.

9. MATH! (SZ-01):
- Description: Use the SCP numbers from the last 3 encounters and add them up. The prompt suggests using the sum as a code to access Room 5 on the fourth floor.
- Solution: Add the SCP numbers from the last 3 encounters.
- Outcome: Deactivating the nuclear device and accessing Room 5 on the fourth floor.

# SCP Escape Game Map and Room Descriptions
1. LC-CD-01 (Spawn Room):
- Description: Private cell in Zone 1.
- Connections: Leads to LC-#00.

2. LC-#00:
- Description: Security office with a locker and a note about an SCP designation.
- Connections: Leads to LC-01.

3. LC-CD-02:
- Description: Class-D cell with hidden contraband and documentation about SCP-049.
- Connections: Leads to LC-01.

4. LC-04:
- Description: Encounter with SCP-173 in a well-lit room.
- Connections: Leads to LC-05.

5. LC-05:
- Description: Room with SCP-914, a device for item upgrading and destruction.
- Connections: Leads to EX-LC.

6. EX-LC:
- Description: Elevator to Heavy Containment Zone (HC).
- Connections: Leads to HC-01.

7. HC-01:
- Description: Dimmed corridor with a humanoid carrying items, including a document and a green ring.
- Connections: Leads to HC-02.

8. HC-02:
- Description: Luxurious room with antiques and a humanoid figure wearing a Plague Doctor's mask.
- Connections: Leads to HC-03.

9. HC-03:
- Description: Dark room with a sealed door and a vent near the bottom middle.
- Connections: Leads to HC-04.

10. HC-04:
- Description: Maze-like room with a torn note on the floor.
- Connections: Leads to HC-05.

11. HC-05:
- Description: Room with a disfigured body containing useful items.
- Connections: Leads to EX-HC.

12. EX-HC:
- Description: Elevator to Entrance Zone (EZ).
- Connections: Leads to EZ-01.

13. EZ-01:
- Description: Room with SCP-939, a dog-like creature.
- Connections: Leads to EZ-02.

14. EZ-02:
- Description: Large room with documentation files, a pistol, and SCP-106, 096, and 079 documents.
- Connections: Leads to EZ-03.

15. EZ-03:
- Description: Secure room with a tesla gate and SCP-079 watching.
- Connections: Leads to EZ-04.

16. EZ-04:
- Description: Room with a computer and a lever switch.
- Connections: Leads to EZ-05.

17. EZ-05:
- Description: Brightly lit white room with medical beds and items.
- Connections: Leads to EX-SZ.

18. EX-EZ:
- Description: Elevator to Surface Zone (SZ).
- Connections: Leads to SZ-01.

19. SZ-01:
- Description: Room with a dark whirling hole, keycard, and Micro H.I.D.
- Connections: Leads to SZ-02, SZ-03.

20. SZ-02:
- Description: Main entrance fortified with a flashing gateway.
- Connections: Ends the game.

21. SZ-03:
- Description: Destroyed office cubicle room with a tall white skinny humanoid.
- Connections: Leads to SZ-04.

22. SZ-04:
- Description: Long corridor room with a dark humanoid figure and a secured metal door.
- Connections: Leads to SZ-05.

23. SZ-05:
- Description: Heavily fortified room with red lights, a bloody note, and a nuclear warhead device.
- Connections: Leads to SZ-04.
