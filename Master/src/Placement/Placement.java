package Placement;
import java.util.ArrayList;
import DataStructures.*;

/**
 * Diese Klasse platziert fuer eine gegebene Menge an Radien und Adjazenzen alle Kreise in der euklidischen Ebene
 */
public class Placement {
	
	private PlanarGraph g;

	public Placement(PlanarGraph graph){
		this.g = graph;
	}
	
	public void calculating(){
		
		// Hier kann die Richtung eingestellt werden
		boolean clockwise = false;
		
		ArrayList<Node> notYetPlaced = new ArrayList<Node>();
		notYetPlaced.addAll(g.getNodes());
		
		Node v = g.getNodes().get(0);
		
		System.out.println();
		System.out.println("|||||||||||||||||||||||||");
		System.out.println("Erster Knoten: " + v.getName());
		
		// Setze den ersten Knoten in den Ursprung
		v.setPos(0, 0);
		
		// Der zweite Knoten wird neben den ersten gesetzt
		Node v2 = v.getNeighbours().get(0);
		System.out.println("Erster Nachbar: " + v2.getName());
		v2.setPos(v.getRadius() + v2.getRadius(),0);
		
		v.setPlaced(true);
		v2.setPlaced(true);
		
		boolean done = false;
		
		while(!done){
			done = true;
			for(Node x : g.getNodes()){
				for (Node y : g.getNodes()){
					for (Node z : g.getNodes()){
												
						// Nicht identisch
						if (x == y){
							continue;
						}
						
						// benachbart
						if (!x.getNeighbours().contains(y)){
							continue;
						}
						
						// x bearbeitet
						if (!x.isPlaced()){
							continue;
						}
						
						// y bearbeitet
						if (!y.isPlaced()){
							continue;
						}
						
						// z nicht bearbeitet
						if (z.isPlaced()){
							continue;
						}
						
						if (x.getNeighbours().contains(z) && y.getNeighbours().contains(z)){
							double alpha = calculateAlpha(x.getRadius(),y.getRadius(),z.getRadius());
							
							System.out.println("Knoten-Tripel: " + x.getName() + ", " + y.getName() + ", " + z.getName());
							System.out.println("Alpha: " + alpha);
														
							double dx = y.getXpos() - x.getXpos();
							double dy = y.getYpos() - x.getYpos();
							double a_rad = alpha;
							
							// Hier wird entschieden, auf welcher der beiden moeglichen Positionen der Kreis gesetzt wird (Orientierung des Tripels)
							if (x.getNeighbours().indexOf(y) < x.getNeighbours().indexOf(z)){
								clockwise = true;
							}else{
								clockwise = false;
							}
							double qx;
							double qy;
							if (clockwise){
								qx = Math.cos(a_rad) * dx + Math.sin(a_rad) * dy;
								qy = (-1) * Math.sin(a_rad) * dx + Math.cos(a_rad) * dy;
							}else{
								qx = Math.cos(a_rad) * dx + (-1) * Math.sin(a_rad) * dy;
								qy = Math.sin(a_rad) * dx + Math.cos(a_rad) * dy;
							}
							
							
							System.out.println("Wir gehen von Knoten " + x.getName() + " in Richtung des Vektors (" + qx + "/" + qy + ")");
							double qxn = qx / Math.sqrt(Math.pow(qx, 2) + Math.pow(qy, 2));
							double qyn = qy / Math.sqrt(Math.pow(qx, 2) + Math.pow(qy, 2));
							qxn = qxn * (x.getRadius() + z.getRadius()) + x.getXpos();
							qyn = qyn * (x.getRadius() + z.getRadius()) + x.getYpos();
							System.out.println("Berechneter Punkt: " + qxn + "/" + qyn);
							z.setPos(qxn, qyn);
							
							System.out.println("Knoten " + z.getName() + " wurde platziert");
							z.setPlaced(true);
							done = false;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Berechnet fuer drei gegebene Radien den Winkel alpha
	 * @param r
	 * @param r2
	 * @param r3
	 * @return
	 */
	public double calculateAlpha(double r, double r2, double r3){
		return Math.acos((Math.pow((r+r2), 2) + Math.pow((r+r3), 2) - Math.pow((r2+r3), 2))/(2*(r+r2)*(r+r3)));
	}
	
	public void printCoords(){
		for (Node v : g.getNodes()){
			System.out.print("Knoten: ");
			System.out.println("[" + v.getName() + "]: " + v.getXpos() + "/" + v.getYpos() + " mit Radius " + v.getRadius());
		}
	}
	
	public void printIpeCoords(String fileName){
		
		AusgabeInDatei ausgabe = new AusgabeInDatei();
		
		// Lege den Urpsrung fest
		int xnull = 192;
		int ynull = 704;
		
		// Lege die Skalierung fest
		int scale = 32;
		
		System.out.println("Ipe-Datei wird geschrieben");

		ausgabe.write(fileName, "<?xml version=\"1.0\"?>", true);
		ausgabe.write(fileName, "<!DOCTYPE ipe SYSTEM \"ipe.dtd\">", true);
		ausgabe.write(fileName, "<ipe version=\"70107\" creator=\"Ipe 7.1.10\">", true);
		ausgabe.write(fileName, "<info created=\"D:20171129205900\" modified=\"D:20171129213526\"/>", true);
		ausgabe.write(fileName, "<ipestyle name=\"basic\">", true);
		ausgabe.write(fileName, "<pen name=\"fat\" value=\"1.2\"/>", true);
		ausgabe.write(fileName, "</ipestyle>", true);
		ausgabe.write(fileName, "<page>", true);
		ausgabe.write(fileName, "<layer name=\"alpha\"/>", true);
		ausgabe.write(fileName, "<view layers=\"alpha\" active=\"alpha\"/>", true);
		
		for (Node v : g.getNodes()){
			
			ausgabe.write(fileName, "<path layer=\"alpha\" stroke=\"black\" pen=\"fat\">", true);
			ausgabe.write(fileName, scale*v.getRadius() + " 0 0 " + scale*v.getRadius() + " " + (xnull + scale*v.getXpos()) + " " +  (ynull + scale*v.getYpos()) + " e", true);
			ausgabe.write(fileName, "</path>", true);
			ausgabe.write(fileName, "<text transformations=\"translations\" pos=\"" + (xnull + scale*v.getXpos()) + " " + (ynull + scale*v.getYpos()) + "\" stroke=\"black\" type=\"label\" width=\"10.168\" height=\"6.42\" depth=\"0\" valign=\"baseline\">$" + v.getName() + "$</text>", true);
		}
		
		ausgabe.write(fileName, "</page>", true);
		ausgabe.write(fileName, "</ipe>", true);
	}
	
}
