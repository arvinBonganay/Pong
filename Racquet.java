import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;	


public class Racquet{
	private int x; // postion of the racquet
	private int y;
	private int ySpeed = 0; // Vertical speed of racquet
	private int width = 20;
	private int height = 80;
	private int key_up;
	private  int key_down;
	private Game game;

	public Racquet(Game game, int key_up, int key_down){
		this.game = game;
		this.key_down = key_down;
		this.key_up = key_up;
	}

	public Racquet(Game game){
		this.game = game;
	}

	
	public void move(){
		if (y + ySpeed > 0 && y + ySpeed < game.getHeight() - height){
			y += ySpeed;
		}

	}

	public void paint(Graphics2D g){
		g.setColor(new Color(92, 47, 182));
		g.fillRect(x, y, width, height); 
	}

	public void keyReleased(KeyEvent e){
		ySpeed = 0;
	}

	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == key_up){
			ySpeed = -3;
		}
		if (e.getKeyCode() ==  key_down){
			ySpeed = 3;
		}
	}


	// returns a Rectangle bounds of the Racquet for collision
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	public int getTop(){
		return x;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
}


