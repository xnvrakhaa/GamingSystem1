# Assignment #3 — Bridge Pattern

## Project: Gaming System

This project is a Java console simulation of a gaming system. It uses the Bridge pattern to separate game modes from gaming platforms. The program prints messages to demonstrate its behavior; it does not launch a real game.

The system has two game modes: Story Mode and Competitive Mode. Each mode can run on PC or Console. The client can change the platform of an existing game mode while the program is running.

## Bridge Pattern Structure

| File | Bridge Role | Responsibility |
| --- | --- | --- |
| `GamePlatform.java` | Implementor | Declares `startGame(String mode)` and `handleInput()`. |
| `PCPlatform.java` | Concrete Implementor | Provides PC launch messages and keyboard/mouse controls. |
| `ConsolePlatform.java` | Concrete Implementor | Provides console launch messages and controller controls. |
| `GameMode.java` | Abstraction | Stores a private platform reference, delegates platform operations, and allows switching platforms. |
| `StoryMode.java` | Refined Abstraction | Defines the story mission and starts Story Mode. |
| `CompetitiveMode.java` | Refined Abstraction | Defines the ranked-match goal and starts Competitive Mode. |
| `Main.java` | Client | Creates modes and platforms and demonstrates runtime switching. |

The bridge is the `GamePlatform` reference inside `GameMode`. Game modes inherit from `GameMode`, while platforms implement `GamePlatform`. These two sides can be extended independently.

## Clean Code Principles and Justification

### 1. Clear Separation of Responsibilities

The abstraction side defines game-mode behavior. For example, `StoryMode.play()` describes the story mission, and `CompetitiveMode.play()` describes the ranked-match goal.

The implementation side defines platform behavior. `PCPlatform` handles PC launch messages and keyboard/mouse input messages. `ConsolePlatform` handles console launch messages and controller input messages.

`GameMode` calls the `GamePlatform` interface without checking whether the platform is PC or Console. The client selects concrete platforms when composing objects, but it does not need to manage their internal behavior. This keeps platform details out of game-mode logic and avoids platform-specific branches in the client.

### 2. Meaningful Names

Names clearly distinguish the two sides of the bridge. `GameMode` represents the abstraction, and `GamePlatform` represents the implementation interface. `StoryMode` and `CompetitiveMode` describe the available modes. `PCPlatform` and `ConsolePlatform` describe the available platforms.

Method names also express their purpose: `play()` runs a mode, `startGame()` performs the platform launch operation, `handleInput()` demonstrates platform controls, and `setPlatform()` changes the platform. These names make the code easier to read and explain.

### 3. Small, Focused Classes

Each class has one focused responsibility. The platform classes implement only two short operations. The mode subclasses contain only a constructor and their `play()` behavior. `GameMode` manages the platform reference and shared delegation. `Main` demonstrates object composition and switching.

This structure makes it easier to locate a change. A controller-related change belongs in `ConsolePlatform`, while a story-mission change belongs in `StoryMode`.

### 4. Avoid Duplicated Logic

The shared sequence of starting a game and handling input appears once in `GameMode.start(String mode)`:

```java
protected void start(String mode) {
    platform.startGame(mode);
    platform.handleInput();
}
```

Both mode subclasses reuse this method, so they do not repeat the platform-call sequence.

The concrete implementors contain only their distinct platform messages. They do not copy game-mode rules or the shared session sequence. Their matching method signatures are required by the interface. Although their printing structure is similar, the current implementations contain no shared calculation or processing algorithm to extract. If substantial common platform logic is added later, it should be factored out instead of copied.

### 5. Backward-Compatible and Extensible Design

`GameMode` depends on `GamePlatform`, not on `PCPlatform` or `ConsolePlatform`. A new platform can implement the same interface without modifying `GameMode`, `StoryMode`, or `CompetitiveMode`.

For example, a future `MobilePlatform` could implement `startGame()` and `handleInput()`. The client could then use:

```java
story.setPlatform(new MobilePlatform());
story.play();
```

This is an extension example; `MobilePlatform` is not included in the current seven-file project. Existing PC and Console combinations would continue to work. Only the client's composition needs to select the new platform.

### 6. Encapsulation

The platform reference in `GameMode` is private:

```java
private GamePlatform platform;
```

Other classes cannot directly replace this field. They use the constructor or `setPlatform()`. Mode subclasses access platform behavior through the shared `start()` method. This keeps management of the bridge inside `GameMode`.

