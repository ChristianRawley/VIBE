import vibe.*;

public class Main implements VibeApp {
    public void define(Vibe game) {
        game.configure(configure -> {
            configure.setTitle("My Vibe Project");
            configure.setSize(960, 960);
            configure.setFontSize(18);
            configure.setBoxHeight(200);
        });

        game.say("Hello, world!");

        game.onComplete(() -> {

        });
    }
}
