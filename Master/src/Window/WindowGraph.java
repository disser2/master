package Window;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import DataStructures.*;
import Placement.*;

public class WindowGraph extends Frame implements ActionListener{
	
	private static int sizeX = 1000;
	private static int sizeY = 600;
	private static ArrayList<Circle> circles;
	private static PlanarGraph g;
	Timer tm = new Timer(15, this);
	
	public WindowGraph(PlanarGraph g){
		this.g = g;
		tm.start();
	}

	public void paint(Graphics g) {
		Graphics2D ga = (Graphics2D) g;
		
		// Alle Kreise zeichnen
		for (Circle c : circles){
			drawCenteredCircle(ga,c.getX(),c.getY(),c.getR());
		}
	}

	public static void main(String args[]) {
		circles = new ArrayList<Circle>();
		
		// Generiere einen neuen Graphen
		GraphGenerator gen = new GraphGenerator();
		PlanarGraph graph = gen.getHoneycomb();
		
		// TODO: Berechnung der Radien
		
		// Berechne Platzierung
		Placement p = new Placement(graph);
		p.calculating();
		
		for (Node v : graph.getNodes()){
			v.setRadius(Math.random()*30);
			circles.add(new Circle(v.getXpos() + (sizeX/2),v.getYpos() + (sizeY/2),v.getRadius()));
		}
		
		Frame frame = new WindowGraph(graph);
		
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
		  System.out.println("drew Circle");
	}
	
	/*
	 * Berechnet den Abstand zwischen zwei Punkten
	 */
	public static double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}