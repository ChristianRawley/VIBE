package vibe;

public interface Command {
    void execute();

    static Command of(Runnable action) {
        return action::run;
    }
}