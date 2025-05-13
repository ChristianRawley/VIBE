package vibe;

import java.util.*;

public class CommandProcessor {
    private Queue<List<Command>> commandBatches = new LinkedList<>();
    private List<Command> currentCommands = new ArrayList<>();

    public void addCommand(Command cmd) {
        currentCommands.add(cmd);
    }

    public void completeBatch() {
        if (!currentCommands.isEmpty()) {
            commandBatches.add(new ArrayList<>(currentCommands));
            currentCommands.clear();
        }
    }

    public void runNextBatch() {
        if (commandBatches.isEmpty()) {
            Runnable finalCommand = Vibe.getInstance().getFinalCommand();
            if (finalCommand != null) finalCommand.run();
        }
        List<Command> batch = commandBatches.poll();
        batch.forEach(Command::execute);
    }
}