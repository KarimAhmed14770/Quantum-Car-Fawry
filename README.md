# Quantum-Car-Fawry
Project Overview

This project implements a modular Car Factory system using Java. The architecture is designed to support multiple engine types—Gasoline, Electronic, and MixedHybrid—while allowing engines to be hot-swapped at runtime. The system focuses on strict state management, ensuring that car operations (start, stop, accelerate, brake) translate accurately to internal engine RPM.

Core Architecture

The system is built on three pillars of Object-Oriented Design:

Strategy Pattern: The Car maintains an Engine interface. This allows the car's behavior to change dynamically based on the installed engine without modifying the CarImpl class.

Abstraction: A BaseEngine abstract class handles the shared logic for speed tracking and the advice loop, reducing code duplication across ElectricEngine and GasEngine.

Encapsulation: All internal speeds are managed through controlled increments/decrements, ensuring the car cannot reach an invalid state (e.g., stopping while at high speed).

Key Technical Implementations

The 1:4 Speed Ratio: To bridge the requirement between the Car's 20 km/h steps and the Engine's 1 RPM increments, I implemented a SPEED_RATIO in the Speed Enum. The advice method uses a loop to "ramp" the engine speed up or down, providing a realistic simulation of acceleration.

Hybrid State Transitions: The HybridEngine acts as a state controller.

Threshold Management: At 50 km/h, the system performs a hand-off.

Cost Optimization: When transitioning, the system ensures the incoming engine is started and brought to speed before the outgoing engine is decelerated and fully stopped. This ensures the engines never run simultaneously in an unoptimized state.

Safety Constraints: The stopCar operation includes a conditional check. If the current speed is greater than 0, the operation is blocked, enforcing the requirement that the car must be fully decelerated before the engine is killed.

How to Run

Compile all .java files in the source directory.

Run the Main.java class.

The console will output a detailed log of the "Physical" transition, showing the Engine RPM and Car Km/h syncing in real-time during acceleration, braking, and hybrid engine switching.
