package vibe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class VibeFrame extends JFrame {
    private Image backgroundImage;
    private JLabel box;
    private JPanel backgroundPanel;
    private final Map<String, Character> characters = new HashMap<>();

    public VibeFrame(CommandProcessor processor, Config config) {
        setTitle(config.getTitle());
        setSize(config.getWidth(), config.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        backgroundPanel = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        	    super.paintComponent(g);
        	    if (backgroundImage != null) {
        	        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        	    }
        	    for (Character c : characters.values()) {
        	        g.drawImage(c.getImage(), c.getX(), c.getY(), this);
        	    }
        	}

        };
        backgroundPanel.setLayout(new BorderLayout());

        box = new JLabel();
        box.setOpaque(true);
        box.setBackground(new Color(0, 0, 0, 180));
        box.setForeground(Color.WHITE);
        box.setFont(new Font("Serif", Font.PLAIN, config.getFontSize()));
        box.setVerticalAlignment(SwingConstants.CENTER);
        box.setHorizontalAlignment(SwingConstants.CENTER);
        box.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        box.setPreferredSize(new Dimension(getWidth(), config.getBoxHeight()));
        box.setText("<html></html>");

        backgroundPanel.add(box, BorderLayout.SOUTH);
        setContentPane(backgroundPanel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    processor.runNextBatch();
                }
            }
        });

        setFocusable(true);
        setVisible(true);
        setResizable(false);
    }

    public void setBackgroundImage(Image image) {
        backgroundImage = image;
        backgroundPanel.repaint();
    }

    public void setBoxText(String text) {
        box.setText("<html>" + text.replaceAll("\n", "<br>") + "</html>");
        backgroundPanel.repaint();
    }
    
    public void addOrUpdateCharacter(String id, Image image, int x, int y, int scale) {
        characters.put(id, new Character(id, image, x, y, scale));
        repaint();
    }

    public void moveCharacter(String id, int x, int y) {
        Character c = characters.get(id);
        if (c != null) {
            c.setX(x);
            c.setY(y);
            repaint();
        }
    }

    public void removeCharacter(String id) {
        characters.remove(id);
        repaint();
    }

}
