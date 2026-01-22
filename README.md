# Command Pattern Drawing App

A JavaFX drawing application demonstrating the **Command Design Pattern**, created as a workshop task for a Design Patterns module.

## About

This project implements a simple drawing application where each drawing operation is encapsulated as a command object. This allows for undo/redo functionality - a classic use case for the Command Pattern.

## Design Patterns Used

- **Command Pattern** - Core pattern enabling undo/redo of drawing operations
- **Observer Pattern** - JavaFX event handling for UI interactions
- **Strategy Pattern** - Different shape drawing behaviours

## Features

- Draw with different shapes (Rectangle, Circle, Triangle)
- Full stroke undo/redo (undoes entire drawing strokes, not individual points)
- UI toolbar and keyboard shortcuts

## Controls

| Input | Action |
|-------|--------|
| Click & Drag | Draw a stroke |
| Undo button / U | Undo last stroke |
| Redo button / R | Redo stroke |
| Rectangle button / 1 | Select rectangle shape |
| Circle button / 2 | Select circle shape |
| Triangle button / 3 | Select triangle shape |

## Running

Requires Java 11+ with JavaFX.

```bash
mvn javafx:run
```

## Acknowledgements

Workshop task from Design Patterns module - University of Salford.
