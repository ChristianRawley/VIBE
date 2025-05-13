package vibe;

import java.awt.Image;

public class Character {
    private String id;
    private Image image;
    private int x;
    private int y;
    private int scale;

    public Character(String id, Image image, int x, int y, int scale) {
        this.id = id;
        this.scale = scale;
        setImage(image);
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image.getScaledInstance(image.getWidth(null) * scale / 100, image.getHeight(null) * scale / 100, Image.SCALE_SMOOTH);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
