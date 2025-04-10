import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Raf extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int ballPosX = 120, ballPosY = 350;
    private int ballDirX = -1, ballDirY = -2;
    private BrickMap map;

    public Raf() {
        map = new BrickMap(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);

        // Draw bricks
        map.draw((Graphics2D) g);

        // Borders
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(691, 0, 3, 592);

        // Paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);

        // Ball
        g.setColor(Color.RED);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        // Scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 550, 30);

        // Win Condition
        if (totalBricks == 0) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Win!", 260, 300);
            g.drawString("Press Enter to Restart", 230, 350);
        }

        // Game Over Condition
        if (ballPosY > 570) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over!", 260, 300);
            g.drawString("Score: " + score, 270, 350);
            g.drawString("Press Enter to Restart", 230, 400);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            ballPosX += ballDirX;
            ballPosY += ballDirY;

            // Ball collision with left, top, and right walls
            if (ballPosX < 0) ballDirX = -ballDirX;
            if (ballPosY < 0) ballDirY = -ballDirY;
            if (ballPosX > 670) ballDirX = -ballDirX;

            // Ball and paddle collision
            if (new Rectangle(ballPosX, ballPosY, 20, 20)
                    .intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballDirY = -ballDirY;
            }

            // Ball and brick collision
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                                ballDirX = -ballDirX;
                            } else {
                                ballDirY = -ballDirY;
                            }
                            break;
                        }
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                play = true;
                playerX += 20;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 10) {
                playerX = 10;
            } else {
                play = true;
                playerX -= 20;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballDirX = -1;
                ballDirY = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new BrickMap(3, 7);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Raf game = new Raf();
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle("Brick Breaker Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
    }
}

// Brick Map Class (Creates the bricks)
class BrickMap {
    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public BrickMap(int row, int col) {
        map = new int[row][col];
        for (int[] rowArray : map) {
            java.util.Arrays.fill(rowArray, 1);
        }
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
