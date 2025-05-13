[Video Demonstration](https://cdn.discordapp.com/attachments/1024385339156086816/1371696775185825853/2025-05-12_22-51-31.mp4?ex=682413ac&is=6822c22c&hm=fde3af8dcd4661ded511312e49e4db3995cf23627a34f01a07d1237f1e1c210e&)
# VIBE
Craft your story's vibe. VIBE stands for Visual Interactive Book Engine. It is a Java Swing Framework designed to quickly whip up truly immersive stories within a single class, so you don't have to worry about all the technical fluff - just the story.

If you want to get started with the framework, just clone the repo and create a Main.java file inside src, or use the template provided.
The class should import vibe and implement the VibeApp class to be picked up by the engine. Within define() will be your story code.
```java
import vibe.*;
public class Main implements VibeApp {
    public void define(Vibe vibe) {}
}
```

Everything included within the configure block details imutable settings for the project, and must be done at the top of your code within define() before writing any additional code.
```java
vibe.configure(configure -> {
  configure.setTitle("My Vibe Project");
  configure.setSize(960, 960);
  configure.setFontSize(18);
  configure.setBoxHeight(200);
});
```

You can now begin scripting your story. VIBE uses frame-based scripting, where each scene update is grouped into one set of changes.
Call the methods you need, like `setBackground()`, `addCharacter()`, or whatever else you need changed, to stack changes.
Then, finalize the frame with `say()` to display dialogue. Everything before `say()` runs together as one frame.
The next update occurs when the player presses space, making the flow fully event-driven.

Example:
```java
// Frame 1 - sets the music and background
vibe.setBackground("my_background.png");
vibe.playAudioLoop("my_music.wav");
vibe.say("Hello, world!");

// Frame 2 - adds a character and changes only the music
vibe.addCharacter("sam", "sam.png", 130, 140, 100);
vibe.playAudio("your_music.wav");
vibe.say("Hello, Mars!");
```

Compile and run the game using the Runner class.
```bash
java -cp . vibe.Runner
```
