package DataStructures;

import java.util.ArrayList;

/**
 * @author Dario
 * Diese Klasse beschreibt einen planaren Graphen und dessen Attribute
 */
public class PlanarGraph {
	
	private int n;
	private int m;
	private ArrayList<Node> nodes;
	private int k;
	
	public PlanarGraph(ArrayList<Node> nodes){
		this.setNodes(nodes);
		this.n = nodes.size();
		int edges = 0;
		for (int i = 0; i < nodes.size(); i++){
			edges += nodes.get(i).getNeighbours().size();
		}
		this.m = edges;
	}
	
	public PlanarGraph(){
		this.n = 0;
		this.m = 0;
		this.k = 0;
		this.nodes = new ArrayList<Node>();
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
		
		// Hiermit wird die Anzahl an Knoten aktualisiert
		this.n = nodes.size();
		
		// Hiermit wird die Anzahl an Kanten aktualisiert
		int edges = 0;
		for (int i = 0; i < nodes.size(); i++){
			edges += nodes.get(i).getNeighbours().size();
		}
		
		// Jede Kante wird doppelt gezaehlt
		this.m = edges/2;
	}
	
	
	public void update(){
		// Hiermit wird die Anzahl an Knoten aktualisiert
		this.n = nodes.size();
		
		// Hiermit wird die Anzahl an Kanten aktualisiert
		int edges = 0;
		for (int i = 0; i < nodes.size(); i++){
			edges += nodes.get(i).getNeighbours().size();
		}
		this.m = edges;
	}
	
	/**
	 * Hiermit wird eine Knoten-Inzidenzliste des Graphen auf der Konsole ausgegeben
	 */
	public void print(boolean neu){
		System.out.println("____________________");
		System.out.println("Ausgabe des Graphen: ");
		for (int i = 0; i < nodes.size(); i++){
			System.out.print("[" + nodes.get(i).getName() + "]: ");
			for (int j = 0; j < nodes.get(i).getNeighbours().size(); j++){
				System.out.print(nodes.get(i).getNeighbours().get(j).getName() + " ");
			}
			if (!neu){
				System.out.print(" Radius: " + nodes.get(i).getRadius() + ", Theta: " + nodes.get(i).getTheta());
			}
			else{
				System.out.print(" Radius: " + nodes.get(i).getRadius() + ", Theta: " + nodes.get(i).getNewtheta());
			}
			System.out.println();
		}
		System.out.println("____________________");
	}

	/**
	 * @return Anzahl Knoten k an der Aussenflaeche
	 */
	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
}
