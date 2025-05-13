package vibe;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        String className = System.getProperty("game.main", "Main");

        try {
            Class<?> appClass = Class.forName(className);
            
            if (!VibeApp.class.isAssignableFrom(appClass)) {
                throw new IllegalArgumentException("Class " + className + " does not implement VibeApp.");
            }

            VibeApp app = (VibeApp) appClass.getDeclaredConstructor().newInstance();
            app.define(Vibe.getInstance());

            SwingUtilities.invokeLater(() -> {
                VibeFrame frame = new VibeFrame(Vibe.getInstance().getProcessor(), Vibe.getInstance().getConfig());
                Vibe.getInstance().setVibeFrame(frame);
                frame.setVisible(true);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}