package vibe;

public class Config {
    private String title = "Game";
    private int width = 1920;
    private int height = 1080;
    private int fontSize = 18;
    private int boxHeight = 150;
    private boolean locked = false;

    public void setSize(int width, int height) {
        checkLocked();
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setTitle(String title) {
        checkLocked();
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public void setFontSize(int fontSize) {
    	checkLocked();
    	this.fontSize = fontSize;
    }
    
    public int getFontSize() {
    	return fontSize;
    }
    
    public void setBoxHeight(int boxHeight) {
    	checkLocked();
    	this.boxHeight = boxHeight;
    }
    
    public int getBoxHeight() {
    	return boxHeight;
    }

    void lock() {
        locked = true;
    }

    private void checkLocked() {
        if (locked) throw new IllegalStateException("Cannot modify config after game starts");
    }
}