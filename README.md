# 🎮 Java TicTacToe Game

A lightweight, interactive 2-player Tic-Tac-Toe game developed using Java Swing (GUI). This project was designed and implemented as a practical application of **Object-Oriented Programming (OOP)** principles for our Lab Final evaluation.

---

## 👥 Developers
* **Rafi Amin** (Reg No: 2023831007)
* **Rijby Sarkar** (Reg No: 2023831013)

---

## 🛠️ Technology Stack & Architecture
* **Java Swing & AWT:** Utilized for building the 3x3 grid system, custom window states, and dynamic view revalidation.
* **Modular OOP Architecture:** Cleanly decoupled core game mechanics, user interface, and player state management across separate classes.

---

## 🏛️ OOP Principles Implemented

1. **Encapsulation:** Player properties (names, symbols, and scores) are strictly encapsulated using `private` fields, exposed safely via public getter/setter mechanisms.
2. **Inheritance:** Promoted code reusability and structured hierarchy by extending the `Player` class from `End` and the main `Window` class from `Start`.
3. **Abstraction:** Defined structural blueprints safely using abstract classes (`Start`, `End`) and a dedicated `Restart` interface to enforce strict contract compliance.
4. **Polymorphism:** Implemented runtime polymorphism via Method Overriding (`@Override`) on core functions like `initLayout()` and `refreshGame()` to dynamically update UI states.

---

## 📁 Project Structure
* `src/game/Main.java` - The main entry point to launch the application.
* `src/game/` - Core directory containing game engines, UI frame logic, and player entities.

---

## 🚀 Getting Started

1. **Open in IDE:** Open the project folder in your preferred IDE (such as VS Code, IntelliJ IDEA, or NetBeans).
2. **Run Application:** Locate and execute the `Main.java` file to launch and start playing the game.
