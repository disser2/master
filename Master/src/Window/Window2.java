package Window;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import DataStructures.*;

public class Window2 extends Frame implements ActionListener{
	
	private static int sizeX = 1000;
	private static int sizeY = 600;
	private static ArrayList<Circle> circles;
	Timer tm = new Timer(15, this);
	
	
	public Window2(){
		tm.start();
		
		int counter = 0;
		int target = 200;
		
		while (counter < target){
			//int rndR = (int) (Math.random()*sizeX/15);
			int rndR = 1;
			int rndX = rndR + (int) (Math.random()*(sizeX - 2*rndR));
			int rndY = rndR + (int) (Math.random()*(sizeY - 2*rndR));
		
			// Pruefung
			boolean outside = true;
			for (Circle c : circles){
				if (distance(c.getX(),c.getY(),rndX,rndY) < (c.getR() + rndR)){
					outside = false;
					break;
				}
			}
			
			if (rndX - rndR < 0 || rndY - rndR < 20)
				outside = false;
			
			if (outside){
				circles.add(new Circle(rndX, rndY, rndR));
				counter++;
				//System.out.println(rndX + ", " + rndY + ", " + rndR);
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D ga = (Graphics2D) g;
		
		// Alle Kreise zeichnen
		for (Circle c : circles){
			
			boolean grow = true;
			for (Circle d : circles){
				if (c == d)
					continue;
				
				if (distance(c.getX(),c.getY(),d.getX(),d.getY()) < (c.getR() + d.getR()) + 1){
					grow = false;
					break;
				}
			}
			
			// Am Rand
			if (c.getX() + c.getR() > sizeX - 2 || c.getX() - c.getR() < 2)
				grow = false;
			if (c.getY() + c.getR() > sizeY - 2 || c.getY() - c.getR() < 30)
				grow = false;
			
			// Growing
			if (grow){
				c.setR(c.getR() + 1);
			}
			drawCenteredCircle(ga,c.getX(),c.getY(),c.getR());
		}
		
		ga.setPaint(Color.gray);
		//ga.fill(circle);
	}

	public static void main(String args[]) {
		circles = new ArrayList<Circle>();
		Frame frame = new Window2();
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		frame.setSize(sizeX, sizeY);
		frame.setVisible(true);
	}
	
	/*
	 * Zeichnet einen Kreis mit Mittelpunkt (x,y) und Radius r
	 */
	public void drawCenteredCircle(Graphics2D ga, double x, double y, double r) {
		  x = x-(r);
		  y = y-(r);
		  Shape circle = new Ellipse2D.Double(x,y,2*r,2*r);
		  ga.draw(circle);
		  
//		  Random rand = new Random();
//		  float rr = rand.nextFloat();
//		  float g = rand.nextFloat();
//		  float b = rand.nextFloat();
//		  ga.setPaint(new Color(rr, g, b));
//		  ga.fill(circle);
	}
	
	/*
	 * Berechnet den Abstand zwischen zwei Punkten
	 */
	public static double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {

        //grow();
        repaint();

    }
}