# Java TicTacToe Game

This is a simple 2-player Tic-Tac-Toe game with a desktop user interface (GUI). We built this project to practice object-oriented programming (OOP) principles in Java for our Lab Final.

## Developers
* **Rafi Amin-2023831007**
* **Rijby Sarkar-2023831023**

---

## What We Used
* **Java Swing:** To create the 3x3 game window, buttons, and layouts.
* **OOP Design:** Divided the game logic, players, and interface into different classes.

---

## OOP Rules Followed in This Project

1. **Encapsulation:** Player data like name and scores are kept private in the `Player` class and can only be accessed using getter methods.
2. **Inheritance:** The `Player` class extends `End` abstract class, and the `Window` class extends `Start` abstract class to share properties.
3. **Abstraction:** Used abstract classes (`Start`, `End`) and a `Restart` interface to plan the structure of the game safely.
4. **Polymorphism:** Overrode methods like `initLayout()` and `refreshGame()` to reset or change the screen during gameplay.

---

## Folders
* `src/game/Main.java` - Run this file to start the game.
* `src/game/` - Contains all other classes like player data, graphics, and win-loss logic.

---

## How to Play
1. Download or clone this project.
2. Open the project in your IDE (like VS Code).
3. Run the `Main.java` file. 
