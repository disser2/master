package DataStructures;

import java.util.LinkedList;

public class Node implements Comparable<Node>{
	
	// Attribute eines Knotens
	private LinkedList<Node> neighbours;
	private LinkedList<Edge> edges;
	private String name;
	
	// Geometrische Lage des Knoten
	private double xpos;
	private double ypos;
	
	// Aktueller Radius des Knoten
	private double radius;
	private double newradius;
	
	// Winkelsumme wie aus dem Paper
	private double theta;
	
	// Hier wird temporaer das neue theta gespeichert, welches sich durch die berechneten beta, gamma ergibt
	private double newtheta;
	
	// Fuer das Placement
	private boolean placed;
	
	// fuer simpleAlgorithm
	private boolean satisfied;
	private boolean borderNode;
	
	// fuer RandomPlanarGraph
	private boolean border;

	public Node(LinkedList<Node> neighbours, double xpos, double ypos, double radius){
		this.neighbours = neighbours;
		this.setXpos(xpos);
		this.setYpos(ypos);
		this.setRadius(radius);
		this.setNewradius(0);
		this.setTheta(0);
		this.setNewtheta(0);
		this.placed = false;
		this.satisfied = false;
		this.borderNode = false;
		this.border = true;
	}
	
	public Node(String name){
		this.neighbours = new LinkedList<Node>();
		this.setXpos(0);
		this.setYpos(0);
		this.setRadius(0);
		this.setNewradius(0);
		this.setName(name);
		this.setTheta(0);
		this.setNewtheta(0);
		this.placed = false;
		this.satisfied = false;
		this.borderNode = false;
		this.border = true;
	}

	// Hiermit kann man zwei Knoten anhand von ihrem Theta vergleichen und eine Liste nach diesem Parameter sortieren
	// ACHTUNG: Wir sortieren hier ABSTEIGEND, also beginnend mit dem groessten Theta
	@Override
    public int compareTo(Node node) {        
       if(this.getTheta() < node.getTheta())
            return 1;
      else if(node.getTheta() < this.getTheta())
            return -1;
      return 0;
    }
	
	public LinkedList<Node> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(LinkedList<Node> neighbours) {
		this.neighbours = neighbours;
	}

	public double getXpos() {
		return xpos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	public void setPos(double xpos, double ypos){
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	
	/**
	 * Diese Funktion berechnet in Abhaengigkeit der Euler-Charakteristik den Wert theta(v), welcher die Winkelsummen-Differenz angibt
	 */
	public void calculateTheta(int euler){
		double phi = 0;
		for (Node u : this.neighbours){
			switch(euler){
				// Euklidischer Fall
				case 2:
					phi += Math.atan((u.getRadius())/(this.getRadius()));
					break;
				// Sphaerischer Fall	
				case 1:
					phi += Math.atan((Math.tan(u.getRadius()))/(Math.sin(this.getRadius())));
					break;
				// Hyperbolischer Fall	
				case -1:
					phi += Math.atan((Math.tanh(u.getRadius()))/(Math.sinh(this.getRadius())));
					break;
				default:
					// TODO: Weitere Faelle implementieren
					System.out.println("Euler-Charakteristik ist nicht 0/1/-1. Berechnung von theta wird abgebrochen");
			}
		}
		this.setTheta(phi - Math.PI);
		//System.out.println("Berechnetes theta fuer " + this.getName() + ": " + this.getTheta());
	}
	
	/**
	 * Diese Funktion berechnet in Abhaengigkeit der Euler-Charakteristik den Wert newTheta(v), welcher die Winkelsummen-Differenz nach Aktualisierung der Parameter angibt
	 */
	public void calculateNewTheta(int euler){
		double newtheta = 0;
		for (Node u : this.neighbours){
			switch(euler){
				// Euklidischer Fall
				case 0:
					newtheta += Math.atan((u.getNewradius())/(this.getNewradius()));
					break;
				// Sphaerischer Fall	
				case 1:
					newtheta += Math.atan((Math.tan(u.getNewradius()))/(Math.sin(this.getNewradius())));
					break;
				// Hyperbolischer Fall	
				case -1:
					newtheta += Math.atan((Math.tanh(u.getNewradius()))/(Math.sinh(this.getNewradius())));
					break;
				default:
					// TODO: Weitere Faelle implementieren
					System.out.println("Euler-Charakteristik ist nicht 0/1/-1. Berechnung von newtheta wird abgebrochen");
			}
		}
		this.setNewtheta(newtheta - Math.PI);
	}

	public double getNewtheta() {
		return newtheta;
	}

	public void setNewtheta(double newtheta) {
		this.newtheta = newtheta;
	}

	public double getNewradius() {
		return newradius;
	}

	public void setNewradius(double newradius) {
		this.newradius = newradius;
	}

	public LinkedList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}

	public boolean isPlaced() {
		return placed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	public boolean isSatisfied() {
		return satisfied;
	}

	public void setSatisfied(boolean satisfied) {
		this.satisfied = satisfied;
	}

	public boolean isBorderNode() {
		return borderNode;
	}

	public void setBorderNode(boolean borderNode) {
		this.borderNode = borderNode;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

}
