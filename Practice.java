import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Practice extends JPanel implements ActionListener,KeyListener{

    private int playerX=200;
    private final int playerWidth=50;
    private final int playerHeight=20;
    private final int windowWidth=400;
    private final int windowHeight=500;

    private Timer timer;
    private ArrayList<Rectangle> blocks;
    private Random rand;
    private boolean gameOver=false;

    public Practice(){
        setPreferredSize(new Dimension(windowWidth,windowHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        rand=new Random();
        blocks=new ArrayList<>();

        timer=new Timer(20,this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e){
        if(!gameOver){
            //below condition is for blocks to appear
            if(rand.nextInt(25)==0){
                int x=rand.nextInt(windowWidth-50);//for random blocks to appear in x axis
                blocks.add(new Rectangle(x,0,50,20));//blocks size and position
            }

            //below is for moving blocks  towards down
            for(int i=0;i<blocks.size();i++){//blocks.size is used instead of height because to count every blocks height wont 
                Rectangle block=blocks.get(i);
                block.y+=5;//this is main it will takes the blocks toward down

                //to check collision  //windowHeight-40 means 500-40
                Rectangle player=new Rectangle(playerX,windowHeight-40,playerWidth,playerHeight);
                if(block.intersects(player)){
                    gameOver=true;
                    timer.stop();
                }

                //remove ofScreen blocks
                if(block.y>windowHeight){//if blocks appear below the screen
                    blocks.remove(i);//
                    i--;//if any blocks goes off screen it'll be removed or index will be reset
                }

            }
        }
        repaint();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(playerX, windowHeight-40, playerWidth, playerHeight);

        g.setColor(Color.DARK_GRAY);
        for(Rectangle block:blocks){
            g.fillRect(block.x, block.y, block.width, block.height);
        }

        if(gameOver){
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g.drawString("Game Over", 120, windowHeight/2);
        }
    }

    public void keyPressed(KeyEvent e){
        if(!gameOver){
            if(e.getKeyCode()==KeyEvent.VK_LEFT && playerX>0){
                playerX-=20;
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                playerX+=20;
            }
        }

        if(gameOver && e.getKeyCode()==KeyEvent.VK_ENTER  && playerX < windowWidth - playerWidth){
            playerX=200;
            blocks.clear();
            gameOver=false;
            timer.start();
        }

    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dodge the Blocks");
        Practice game = new Practice();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}