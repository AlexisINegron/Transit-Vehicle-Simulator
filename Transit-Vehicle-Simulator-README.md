# Transit Vehicle Simulator

A Java project exploring object-oriented design through a class hierarchy that models different types of vehicles, their passengers, and the rules governing who can sit where.

## Overview

This project models a small transportation system using inheritance, interfaces, and custom exception handling. It was built as an exercise in applying core object-oriented programming principles to a real-world style scenario: seating people in vehicles according to age and height restrictions, validating drivers, and comparing vehicles by capacity.

## Features

- **Class hierarchy**: An abstract `Vehicle` base class is extended by `Car` and `Bicycle`. `Bus` further extends `Car`, demonstrating multi-level inheritance.
- **Passenger management**: Each vehicle tracks a grid of seats (`personsOnBoard`) and enforces boarding rules, for example preventing young or short passengers from sitting in the front row of a `Car` or `Bus`.
- **Custom exception handling**: `InvalidDriverException` is thrown when an invalid driver (e.g., someone without a license, or a bicycle rider under age 3) is assigned to a vehicle.
- **Interfaces and polymorphism**: `Car` implements `Comparable<Car>` (to compare vehicles by total seat capacity) and `Announcements` (departure, arrival, and safety messages), which `Bus` overrides with its own versions.
- **Object cloning and equality**: `Person` implements custom `clone()` and `equals()` methods to ensure passenger data is copied and compared correctly.
- **Recursion and binary search**: `RecursionQuestion.java` performs a recursive binary search over a sorted array of `Car` objects to locate a car matching a given seating configuration, demonstrating recursive algorithm design separate from the core class model.

## Project Structure

```
Vehicle Search/
├── Vehicle.java                 # Abstract base class for all vehicles
├── Car.java                     # Car implementation (Comparable, Announcements)
├── Bus.java                     # Bus, extends Car
├── Bicycle.java                 # Bicycle, extends Vehicle (Comparable)
├── Person.java                  # Passenger/driver model
├── InvalidDriverException.java  # Custom checked exception
├── Announcements.java           # Interface for vehicle announcements
└── RecursionQuestion.java       # Recursive binary search over Car objects
```

## Concepts Demonstrated

- Abstract classes and multi-level inheritance
- Interface implementation and method overriding
- Custom checked exceptions
- Defensive copying (`clone()`) and value-based equality (`equals()`)
- Recursive algorithms (binary search)

## How to Run

Compile and run `RecursionQuestion.java` to see the binary search demo:

```bash
javac *.java
java RecursionQuestion
```

The program reads a space-separated list of seat counts per row from standard input and searches for a matching `Car` configuration among a predefined set of vehicles.

## What I Learned

Working through this project helped solidify how inheritance and interfaces work together in Java, how to design exception handling that fails safely and clearly, and how to reason through a recursive algorithm step by step rather than just implementing it from memory.
