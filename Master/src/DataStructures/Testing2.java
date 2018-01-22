package DataStructures;

import java.util.*;

public class Testing2 {
	
	public static void main(String[] args){
		
		// Ein Quadrat mit einer Diagonalen
//		Node v1 = new Node("v1");
//		Node v2 = new Node("v2");
//		Node v3 = new Node("v3");
//		Node v4 = new Node("v4");
//		LinkedList<Node> v1n = new LinkedList<Node>();
//		LinkedList<Node> v2n = new LinkedList<Node>();
//		LinkedList<Node> v3n = new LinkedList<Node>();
//		LinkedList<Node> v4n = new LinkedList<Node>();
//		v1n.add(v2);
//		v1n.add(v4);
//		v2n.add(v1);
//		v2n.add(v3);
//		v2n.add(v4);
//		v3n.add(v2);
//		v3n.add(v4);
//		v4n.add(v1);
//		v4n.add(v2);
//		v4n.add(v3);
//		v1.setNeighbours(v1n);
//		v2.setNeighbours(v2n);
//		v3.setNeighbours(v3n);
//		v4.setNeighbours(v4n);
//		
//		LinkedList<Node> nodes = new LinkedList<Node>();
//		nodes.add(v1);
//		nodes.add(v2);
//		nodes.add(v3);
//		nodes.add(v4);
//		
//		PlanarGraph g = new PlanarGraph(nodes);
//		g.print();
		
//		CircleGraph circle = new CircleGraph();
//		PlanarGraph g = circle.getCircleGraph(100);
//		g.print();
		
		// Hiermit testen wir, ob das Sortieren der Knoten nach ihrem Radius funktioniert
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		Node v1 = new Node("v1");
		v1.setTheta(30);
		nodes.add(v1);
		
		Node v2 = new Node("v2");
		v2.setTheta(20);
		nodes.add(v2);
		
		Node v3 = new Node("v3");
		v3.setTheta(12);
		nodes.add(v3);
		
		Node v4 = new Node("v4");
		v4.setTheta(10);
		nodes.add(v4);
		
		Node v5 = new Node("v5");
		v5.setTheta(16);
		nodes.add(v5);
		
		Node v6 = new Node("v6");
		v6.setTheta(-21);
		nodes.add(v6);
		
		Node v7 = new Node("v7");
		v7.setTheta(-23);
		nodes.add(v7);
		
		Collections.sort(nodes);
		
		System.out.println("Sortiert:");
		for(Node v : nodes){
			System.out.println(v.getName() + ": " + v.getTheta());
		}
		System.out.println();
		
		double sigma = 0;
		for(int i = 0; i < nodes.size() - 1; i++){
			
			double newsigma;
			
			if (nodes.get(i).getTheta() > 0 && nodes.get(i+1).getTheta() > 0)
				continue;
			else if (nodes.get(i).getTheta() > 0)
				newsigma = 0 - nodes.get(i+1).getTheta();
			else
				newsigma = nodes.get(i).getTheta() - nodes.get(i+1).getTheta();
			
			if (newsigma > sigma)
				sigma = newsigma;
		}
		
		System.out.println("Sigma: " + sigma);
		
		ArrayList<Node> s_r = new ArrayList<Node>();
		
		if (nodes.get(nodes.size() - 1).getTheta() >= 0){
			for (Node v: nodes){
				s_r.add(v);
			}
		} else{
		
			for (int i = 0; i < nodes.size(); i++){
				if (nodes.get(i).getTheta() > 0 && nodes.get(i+1).getTheta() > 0)
					s_r.add(nodes.get(i));
				else if (nodes.get(i).getTheta() > 0 && nodes.get(i+1).getTheta() < 0){
					s_r.add(nodes.get(i));
					// Falls wir zum ersten Mal die Differenz sigma erreichen, brechen wir die Schleife ab
					if (0 - nodes.get(i+1).getTheta() == sigma)
						break;
				}
				else if (nodes.get(i).getTheta() < 0 && nodes.get(i+1).getTheta() < 0){
					s_r.add(nodes.get(i));
					// Falls wir zum ersten Mal die Differenz sigma erreichen, brechen wir die Schleife ab
					if (nodes.get(i).getTheta() - nodes.get(i+1).getTheta() == sigma)
						break;
				}
			}
		}
		
		System.out.println("Elemente aus s_r:");
		for(Node v : s_r){
			System.out.println(v.getName() + ": " + v.getTheta());
		}
		System.out.println();
	}

}
