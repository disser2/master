package Window;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements MouseListener{

	
	public static void main(String[] args) {
		JFrame jf = new JFrame("Test");
		Window w = new Window();
		jf.setSize(600, 400);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		//panel.setBackground(Color.gray);
		jf.add(panel);	
		
		jf.setVisible(true);

	}
	
	Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f);
	  Shape square = new Rectangle2D.Double(100, 100,100, 100);
	  public void paint(Graphics g) {
	  Graphics2D ga = (Graphics2D)g;
	  ga.draw(circle);
	  ga.setPaint(Color.green);
	  ga.fill(circle);
	  ga.setPaint(Color.red);
	  ga.draw(square);
	  }
	
	public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.fillOval(x,y,r,r);
	}
	
	public void mouseReleased(MouseEvent e ) { }
	  public void mouseEntered(MouseEvent e)   { }
	  public void mouseExited(MouseEvent e)    { }
	  public void mousePressed(MouseEvent e)   { }

	  public void mouseClicked(MouseEvent e) { 
	    repaint();    //calls paint()
	  }

}
