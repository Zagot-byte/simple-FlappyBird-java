import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Pipe {
    private int x, width = 60;
    private int gap = 150;
    private int topHeight, bottomY;
    private int speed = 3;

    private Image topImage, bottomImage;

    public Pipe(int startX) {
        x = startX;
        Random rand = new Random();
        topHeight = 100 + rand.nextInt(200);   // random height for top pipe
        bottomY = topHeight + gap;             // gap for bottom pipe

        // Load images
        topImage = new ImageIcon("pipe_top.png").getImage();
        bottomImage = new ImageIcon("pipe_bottom.png").getImage();
    }

    // Move pipe left
    public void update() {
        x -= speed;
    }

    // Draw both top and bottom pipes
    public void draw(Graphics g) {
        // Top pipe
        g.drawImage(topImage, x, 0, width, topHeight, null);
        // Bottom pipe
        g.drawImage(bottomImage, x, bottomY, width, GamePanel.HEIGHT - bottomY, null);
    }

    // Collision detection with bird
    public boolean collidesWith(Bird b) {
        Rectangle birdRect = b.getBounds();
        Rectangle topPipe = new Rectangle(x, 0, width, topHeight);
        Rectangle bottomPipe = new Rectangle(x, bottomY, width, GamePanel.HEIGHT - bottomY);
        return birdRect.intersects(topPipe) || birdRect.intersects(bottomPipe);
    }

    // Getters
    public int getX() { return x; }
    public int getWidth() { return width; }
}
