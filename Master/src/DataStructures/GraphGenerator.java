package DataStructures;

import java.util.ArrayList;

/**
 * @author Dario
 * Generiert mehrere Typen von Graphen zum Testen
 */
public class GraphGenerator {

	public GraphGenerator(){
		
	}
	
	/**
	 * @return Ein Objekt vom Typ PlanarGraph, bestehend aus 7 Knoten angeordnet in einer Bienenwabenstruktur, 
	 * d.h. ein Knoten in der Mitte und 6 Knoten angrenzend um ihn herum
	 */
	public PlanarGraph getHoneycomb(){
		System.out.println("Generiere neuen Honeycomb-Graphen");
		PlanarGraph g = new PlanarGraph();
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node v0 = new Node("v0");
		Node v1 = new Node("v1");
		Node v2 = new Node("v2");
		Node v3 = new Node("v3");
		Node v4 = new Node("v4");
		Node v5 = new Node("v5");
		Node v6 = new Node("v6");
		nodes.add(v0);
		nodes.add(v1);
		nodes.add(v2);
		nodes.add(v3);
		nodes.add(v4);
		nodes.add(v5);
		nodes.add(v6);
		v0.getNeighbours().add(v1);
		v0.getNeighbours().add(v3);
		v0.getNeighbours().add(v6);
		v1.getNeighbours().add(v2);
		v1.getNeighbours().add(v3);
		v1.getNeighbours().add(v0);
		v2.getNeighbours().add(v4);
		v2.getNeighbours().add(v3);
		v2.getNeighbours().add(v1);
		v3.getNeighbours().add(v0);
		v3.getNeighbours().add(v1);
		v3.getNeighbours().add(v2);
		v3.getNeighbours().add(v4);
		v3.getNeighbours().add(v5);
		v3.getNeighbours().add(v6);
		v4.getNeighbours().add(v5);
		v4.getNeighbours().add(v3);
		v4.getNeighbours().add(v2);
		v5.getNeighbours().add(v6);
		v5.getNeighbours().add(v3);
		v5.getNeighbours().add(v4);
		v6.getNeighbours().add(v0);
		v6.getNeighbours().add(v3);
		v6.getNeighbours().add(v5);
		
		g.setNodes(nodes);
		//TODO: Die Zahl muss eigentlich automatisch berechnet werden
		g.setK(6);
		
		return g;
	}
	
	public PlanarGraph getK3(){
		PlanarGraph g = new PlanarGraph();
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node v0 = new Node("v0");
		Node v1 = new Node("v1");
		Node v2 = new Node("v2");
		nodes.add(v0);
		nodes.add(v1);
		nodes.add(v2);
		v0.getNeighbours().add(v1);
		v0.getNeighbours().add(v2);
		v1.getNeighbours().add(v0);
		v1.getNeighbours().add(v2);
		v2.getNeighbours().add(v0);
		v2.getNeighbours().add(v1);
		
		g.setNodes(nodes);
		
		return g;
	}
	
	public PlanarGraph getK4(){
		PlanarGraph g = new PlanarGraph();
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node v0 = new Node("v0");
		Node v1 = new Node("v1");
		Node v2 = new Node("v2");
		Node v3 = new Node("v3");
		nodes.add(v0);
		nodes.add(v1);
		nodes.add(v2);
		nodes.add(v3);
		v0.getNeighbours().add(v1);
		v0.getNeighbours().add(v2);
		v0.getNeighbours().add(v3);
		v1.getNeighbours().add(v3);
		v1.getNeighbours().add(v2);
		v1.getNeighbours().add(v0);
		v2.getNeighbours().add(v0);
		v2.getNeighbours().add(v1);
		v2.getNeighbours().add(v3);
		v3.getNeighbours().add(v0);
		v3.getNeighbours().add(v2);
		v3.getNeighbours().add(v1);
		
		v1.setBorderNode(true);
		v2.setBorderNode(true);
		v3.setBorderNode(true);
		
		g.setNodes(nodes);
		
		//TODO: Die Zahl muss eigentlich automatisch berechnet werden
		g.setK(3);
		
		return g;
	}
	
	/**
	 * Der Knoten-Facetten-Inzidenzgraph des K_4
	 * Die Knoten sind bereits im Uhrzeigersinn sortiert
	 * @return Den Graphen als Objekt der Klasse PlanarGraph
	 */
	public PlanarGraph getK4VertexFaceIncidence(){
		System.out.println("Generiere den VertexFaceIncidence-Graphen des K_4");
		PlanarGraph g = new PlanarGraph();
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node v0 = new Node("v0");
		Node v1 = new Node("v1");
		Node v2 = new Node("v2");
		Node v3 = new Node("v3");
		Node v4 = new Node("v4");
		Node v5 = new Node("v5");
		Node v6 = new Node("v6");
		Node v7 = new Node("v7");
		nodes.add(v0);
		nodes.add(v1);
		nodes.add(v2);
		nodes.add(v3);
		nodes.add(v4);
		nodes.add(v5);
		nodes.add(v6);
		nodes.add(v7);
		v0.getNeighbours().add(v4);
		v0.getNeighbours().add(v6);
		v0.getNeighbours().add(v5);
		v1.getNeighbours().add(v5);
		v1.getNeighbours().add(v6);
		v1.getNeighbours().add(v7);
		v2.getNeighbours().add(v4);
		v2.getNeighbours().add(v5);
		v2.getNeighbours().add(v7);
		v3.getNeighbours().add(v4);
		v3.getNeighbours().add(v7);
		v3.getNeighbours().add(v6);
		v4.getNeighbours().add(v3);
		v4.getNeighbours().add(v0);
		v4.getNeighbours().add(v2);
		v5.getNeighbours().add(v0);
		v5.getNeighbours().add(v1);
		v5.getNeighbours().add(v2);
		v6.getNeighbours().add(v0);
		v6.getNeighbours().add(v3);
		v6.getNeighbours().add(v1);
		v7.getNeighbours().add(v1);
		v7.getNeighbours().add(v3);
		v7.getNeighbours().add(v2);
		
		g.setNodes(nodes);
		
		return g;
	}
	
	/**
	 * TODO: Hier berechnen wir einen zufaelligen planaren Graphen
	 * @return
	 */
	public PlanarGraph getRandomPlanarGraph(int size){
		
		// zu kleiner Graph
		if (size < 4)
			return null;
		
		PlanarGraph g = new PlanarGraph();
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		// initiales Dreieck
		Node v0 = new Node("v0");
		Node v1 = new Node("v1");
		Node v2 = new Node("v2");
		nodes.add(v0);
		nodes.add(v1);
		nodes.add(v2);
		v0.getNeighbours().add(v1);
		v0.getNeighbours().add(v2);
		v1.getNeighbours().add(v2);
		v1.getNeighbours().add(v0);
		v2.getNeighbours().add(v0);
		v2.getNeighbours().add(v1);
		
		// Enthaelt immer die Knoten auf dem Rand in korrekter Reihenfolge
		ArrayList<Node> border = new ArrayList<Node>();
		for(Node v : nodes)
			border.add(v);
		
		int count = 3;
		
		while (count < size){
			// Erstelle neuen Knoten
			String name = "v" + (count);
			Node v = new Node(name);
			nodes.add(v);
			
			// Waehle zwei verschiedene existierende Knoten
			int index1 = (int) (Math.random() * border.size());
			int index2 = index1;
			while (index2 == index1){
				index2 = (int) (Math.random() * border.size());
			}
			
			// Vertausche so, dass index1 < index2
			if (index2 < index1){
				int h = index1;
				index1 = index2;
				index2 = h;
			}
			
			Node u = border.get(index1);
			v.getNeighbours().add(u);
			u.getNeighbours().add(v);
			Node w = border.get(index2);
			
			// Betrachte alle Knoten zwischen index1 und index2 auf dem Rand
			int indexNow = index1 + 1;
			while (indexNow < index2){
				Node x = border.get(indexNow);
				
				int h = x.getNeighbours().indexOf(border.get(indexNow - 1));
				x.getNeighbours().add(h, v);
				v.getNeighbours().add(x);
				
				// Entferne die aktuellen Knoten aus dem Rand
				x.setBorder(false);
				border.remove(x);
				
				indexNow++;
			}
			
			v.getNeighbours().add(w);
			w.getNeighbours().add(v);
			
			// fuege den neuen Randknoten ein
			border.add(index1 + 1, v);
			
			count++;
		}
		
		g.setNodes(nodes);
		return g;
	}
}
