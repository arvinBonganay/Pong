
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Created by Arvin
 */

public class Game extends JPanel {

    Ball ball = new Ball(this);
    Racquet racquet1 = new Racquet(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
    Racquet racquet2 = new Racquet(this, KeyEvent.VK_W, KeyEvent.VK_S);
    private int p1Score;
    private int p2Score;

    public Game() {
        p1Score = 0;
        p2Score = 0;
        setSize(700, 400);
        
        JLabel background=new JLabel(new ImageIcon("pong-bg.jpg"));
        add(background);

        racquet1.setPos(30, getHeight() / 2 - racquet1.getHeight());
        racquet2.setPos(getWidth() - 70, getHeight() / 2 - racquet2.getHeight());
        ball.setPos(getWidth() / 2 - ball.getDiameter(), getHeight() / 2 - ball.getDiameter());

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet1.keyReleased(e);
                racquet2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet1.keyPressed(e);
                racquet2.keyPressed(e);
            }
        });
        setFocusable(true);
    }
    
    private void move() {
        ball.move();
        racquet1.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(92, 47, 182));
        g2d.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(p1Score + "", 275, 60); 
        g2d.drawString(p2Score + "", 375, 60); 
       
        ball.paint(g2d);
        racquet1.paint(g2d);
        racquet2.paint(g2d);
    }
    
    public void goal(int x) {
        if (x == 1){
            p1Score++;
        } else if (x == 2){
            p2Score++;
        }
        ball.setPos(getWidth() / 2, getHeight() / 2);
        repaint();  
        try {Thread.sleep(1000);}
        catch (InterruptedException e){}
    }

    public void play() {
        try { Thread.sleep(1000);}
        catch (InterruptedException e) {}

        p1Score = 0;
        p2Score = 0;
       
        while (p1Score < 3 && p2Score < 3) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            move();
            repaint();
        }

        String msg = "Try again?";
        if (p1Score == 3){ 
            msg = "Player 1 won\n" + msg;
        }else {
            msg = "Player 2 won\n" + msg;
        }
        int response = JOptionPane.showConfirmDialog(null, msg, "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            play();
        }
        if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("PONG");        
        Game game = new Game();
        frame.add(game);
        frame.setSize(700, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showConfirmDialog(frame, "Player 1\n Up = Up arrow\nDown = Down arrow\n\n Player 2\n Up = W\n Down = S", "Controls", JOptionPane.CANCEL_OPTION);

        game.play();
    }
}
