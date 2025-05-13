package vibe;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public final class Vibe {
    private static final Vibe instance = new Vibe();
    private final CommandProcessor processor = new CommandProcessor();
    private final Config config = new Config();
    private Runnable finalCommand = null;
    private VibeFrame vibeFrame;
    private Clip clip;

    private Vibe() {}

    public static Vibe getInstance() {
        return instance;
    }

    public void configure(Consumer<Config> configBlock) {
        configBlock.accept(config);
        config.lock();
    }

    public void setVibeFrame(VibeFrame frame) {
        this.vibeFrame = frame;
    }
    
    public void say(String text) {
    	processor.addCommand(() -> {
    		vibeFrame.setBoxText(text);
        });
    	processor.completeBatch();
    }
    
    public void setBackground(String path) {
    	processor.addCommand(() -> {
    		try {
    			ImageIcon icon = new ImageIcon(path);
                Image image = icon.getImage();
                vibeFrame.setBackgroundImage(image);
                vibeFrame.repaint();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	});
    }
    
    public void playAudio(String path) {
    	processor.addCommand(() -> {
    		try {
    			if (clip != null && clip.isRunning()) clip.stop();
    			File file = new File(path);
    			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    			clip = AudioSystem.getClip();
    			clip.open(audioStream);
    			clip.start();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	});
    }
    
    public void playAudioLoop(String path) {
    	processor.addCommand(() -> {
    		try {
    			if (clip != null && clip.isRunning()) clip.stop();
    			File file = new File(path);
    			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    			clip = AudioSystem.getClip();
    			clip.open(audioStream);
    			clip.start();
    			clip.loop(Clip.LOOP_CONTINUOUSLY);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	});
    }

    public void onComplete(Runnable command) {
        if (finalCommand != null) {
            throw new IllegalStateException("onComplete has already been set.");
        }
        finalCommand = command;
    }

    CommandProcessor getProcessor() {
        return processor;
    }

    Config getConfig() {
        return config;
    }

    Runnable getFinalCommand() {
        return finalCommand;
    }
    
    public void showCharacter(String id, String imagePath, int x, int y, int scale) {
        processor.addCommand(() -> {
            try {
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();
                vibeFrame.addOrUpdateCharacter(id, image, x, y, scale);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void moveCharacter(String id, int x, int y) {
        processor.addCommand(() -> vibeFrame.moveCharacter(id, x, y));
    }

    public void removeCharacter(String id) {
        processor.addCommand(() -> vibeFrame.removeCharacter(id));
    }

}
