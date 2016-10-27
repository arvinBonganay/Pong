
import javax.swing.*;
import java.awt.*;

/**
 * Created by Arvin 
 */

// Class for the Ball
public class Ball {
    private static final int DIAMETER = 40;
    private int x; // position of the ball in the x-axis
    private int y; // position of the ball in the y-axis
    private int xSpeed = 3; //  Sets the Horizontal speed to 1
    private int ySpeed = 2; //  Sets the Vertical speed to 1
    private Game game; 

       public Ball(Game game){
        this.game = game;
    }

    public void move(){
        // reverses the direction of the ball if it touches an edge
        // bounce
        if (x + xSpeed < 0 || x + xSpeed == game.racquet1.getTop()){
            game.goal(2);
        } if (x + xSpeed > game.getWidth() - DIAMETER || x + xSpeed == game.racquet2.getTop() + game.racquet2.getWidth())
            game.goal(1);
        if (y + ySpeed< 0)
            ySpeed = 3;
        if (y + ySpeed > game.getHeight() - DIAMETER)
            ySpeed = -3;
        
        if(collision(game.racquet1)){
            xSpeed = 3;
            x = game.racquet1.getTop() + 20;
        } 

        if(collision(game.racquet2)){
            xSpeed = -3;
            x = game.racquet2.getTop() - DIAMETER;
        } 

        x += xSpeed;
        y += ySpeed;
    }


     public void paint(Graphics2D g) {
        // draws an oval with height and width equal to DIAMETER
        // located at point (x, y)
        g.setColor(new Color(51, 255, 102));
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    // checks if the ball collides with the racquet
    private boolean collision(Racquet racquet) {
        return racquet.getBounds().intersects(getBounds());
    }

    // returns a Rectangle bounds of the ball for collision
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getxCoord() {
        return x;
    }

    public int getyCoord() {
        return y;
    }

    public int getDiameter(){
        return DIAMETER;
    }
}
