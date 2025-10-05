import java.awt.*;
import java.awt.Image;        
import javax.swing.ImageIcon; 

public class Bird {
    private int x, y;
    private int width = 30, height = 30;
    private double velocity = 0;
    private final double gravity = 0.5;
    private final int jumpStrength = -8;
    private Image birdImage;
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
         birdImage = new ImageIcon("bird.png").getImage();
    
    }

    public void update() {
        velocity += gravity;
        y += velocity;
    }

    public void jump() {
        velocity = jumpStrength;
    }

    public void draw(Graphics g) {
        g.drawImage(birdImage, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }
}
