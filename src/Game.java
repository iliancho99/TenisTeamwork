import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel { 
	Ball ball = new Ball(this);
	Player1 player1 = new Player1(this);
	Player2 player2 = new Player2(this);
	 
	  
	public Game() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player1.keyReleased(e);
				player2.keyReleased(e);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				player1.keyPressed(e);
				player2.keyPressed(e);

			}
		});
		setFocusable(true);
	}

	private void move() {
		ball.move();
		player1.move();
		player2.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon cook = new ImageIcon("cook.png");
		ImageIcon chef = new ImageIcon("chef.png");
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.WHITE);
		g2d.draw(new Line2D.Float(0, 70, 1200, 70));
		g.setFont(new Font("TimesRoman", Font.ITALIC, 24));
		g2d.drawString("Cook Student", 405, 35);
		String pl1Score = Integer.toString(player1.getPl1Score());
		g2d.drawString(pl1Score, 570, 35);
		String pl2Score = Integer.toString(player2.getPl2Score());
		g2d.drawString(pl2Score, 610, 35);
		g2d.drawString(":", 590, 35);
		g2d.drawString("Chef Nakov", 650, 35);
		player1.paint(g2d);
		player2.paint(g2d);
		ball.paint(g2d);
		g2d.drawImage(cook.getImage(), 770, 10, 50, 42, null);
		g2d.drawImage(chef.getImage(), 350, 7, 53, 50, null);
	}

	public void pointMade() {
		JOptionPane.showMessageDialog(this, "Ooops the pepper is out of the pan!", "Scooore!",
				JOptionPane.YES_NO_OPTION);
		ball = new Ball(this);
		repaint();
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Bell Pepper Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(1200, 700);
		game.setBackground(new Color(31, 107, 2));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(5);
		}

	}

}
