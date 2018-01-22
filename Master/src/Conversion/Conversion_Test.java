package Conversion;

import java.util.*;
import DataStructures.*;

public class Conversion_Test {

	public static void main(String[] args) {

		Uhrzeigerliste uhr = new Uhrzeigerliste();
		ArrayList<Node> graph = new ArrayList<Node>();
		
		Node v1 = new Node("v1");
		Node v2 = new Node("v2");
		Node v3 = new Node("v3");
		Node v4 = new Node("v4");
		Edge e1 = new Edge(v1,v2);
		Edge e2 = new Edge(v1,v4);
		Edge e3 = new Edge(v2,v4);
		Edge e4 = new Edge(v2,v3);
		Edge e5 = new Edge(v4,v3);
		
		LinkedList<Edge> v1edges = new LinkedList<Edge>();
		v1edges.add(e1);
		v1edges.add(e2);
		v1.setEdges(v1edges);
		
		LinkedList<Edge> v2edges = new LinkedList<Edge>();
		v2edges.add(e1);
		v2edges.add(e4);
		v2edges.add(e3);
		v2.setEdges(v2edges);
		
		LinkedList<Edge> v3edges = new LinkedList<Edge>();
		v3edges.add(e4);
		v3edges.add(e5);
		v3.setEdges(v3edges);
		
		LinkedList<Edge> v4edges = new LinkedList<Edge>();
		v4edges.add(e2);
		v4edges.add(e3);
		v4edges.add(e5);
		v4.setEdges(v4edges);
		
		graph.add(v1);
		graph.add(v2);
		graph.add(v3);
		graph.add(v4);
		
		// graph ausgeben
		for (int i = 0; i < graph.size(); i++){
			Node v = graph.get(i);
			System.out.print("[" + v.getName() + "]: ");
			for (int j = 0; j < v.getEdges().size(); j++){
				System.out.print("(" + v.getEdges().get(j).getStart().getName() + "," + v.getEdges().get(j).getTarget().getName() + "),");
			}
			System.out.println();
		}
		
		ArrayList<Face> graph2 = uhr.convert_kul_ful(graph);
	
		// TODO: graph2 ausgeben
		for (int i = 0; i < graph2.size(); i++){
			Face f = graph2.get(i);
			System.out.print(i + ": ");
			for (int j = 0; j < f.getEdges().size(); j++){
				System.out.print("(" + f.getEdges().get(j).getStart().getName() + "," + f.getEdges().get(j).getTarget().getName() + "),");
			}
			System.out.println();
		}
				
		System.out.println("Ende Ausgabe Graph2");
	}

}
