import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Timer timer;
    private int score = 0;
    private boolean gameOver = false;

    public static final int WIDTH = 400, HEIGHT = 600;

    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.cyan);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    private void startGame() {
        bird = new Bird(100, HEIGHT / 2);
        pipes = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
        score = 0;
        gameOver = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bird.draw(g);
        for (Pipe pipe : pipes) pipe.draw(g);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over!", 100, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press R to restart", 130, HEIGHT / 2 + 40);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();
            addPipes();
            checkCollision();
            repaint();
        }
    }

    private void addPipes() {
        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < 200) {
            pipes.add(new Pipe(WIDTH));
        }
        Iterator<Pipe> it = pipes.iterator();
        while (it.hasNext()) {
            Pipe p = it.next();
            p.update();
            if (p.getX() + p.getWidth() < 0) {
                it.remove();
                score++;
            }
        }
    }

    private void checkCollision() {
        for (Pipe pipe : pipes) {
            if (pipe.collidesWith(bird)) {
                gameOver = true;
                timer.stop();
            }
        }
        if (bird.getY() > HEIGHT - 30 || bird.getY() < 0) {
            gameOver = true;
            timer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) bird.jump();
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) startGame();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
