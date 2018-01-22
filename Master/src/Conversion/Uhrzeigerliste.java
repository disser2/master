package Conversion;
import java.util.ArrayList;
import java.util.LinkedList;

import DataStructures.*;

public class Uhrzeigerliste {
	
	public Uhrzeigerliste(){
		
	}

	/**
	 * TODO: Diese Methode berechnet ausgehend von einer Darstellung eines planaren Graphen durch Knotenuhrzeigerlisten den selben Graphen dargestellt durch Flaechenuhrzeigerlisten
	 * @param in_graph
	 * @return
	 */
	public ArrayList<Face> convert_kul_ful(ArrayList<Node> graph){
		
		// Erstelle neue leere Flaechenuhrzeigerliste
		ArrayList<Face> out_graph = new ArrayList<Face>();
		
		LinkedList<Edge> edges = new LinkedList<Edge>();
		Face f = new Face(edges);
		out_graph.add(f);
		Node v = graph.get(0);
		Edge e = v.getEdges().get(0);
		
		Node v2 = null;
		if (e.getStart() == v){
			v2 = e.getTarget();
		}else{
			v2 = e.getStart();	
		}
		
		edges.add(e);
		
		int index = 0;
		// Finde heraus an welcher Stelle die Kante e in der Liste von v2 ist.
		for (int i = 0; i <= v2.getEdges().size(); i++){
			if (v2.getEdges().get(i) == e){
				index = i;
				break;
			}
		}
		
		Edge e2 = null;
		
		// Betrachte die Kante eins vorher (zyklisch permutiert)
		if (index > 0)
			e2 = v2.getEdges().get(index - 1);
		else 
			e2 = v2.getEdges().get(v2.getEdges().size() - 1);
		
		// Solange wir bei der Startkante nicht angekommen sind, machen wir weiter
		while (e2 != e){
			edges.add(e2);
			
			Node v3 = null;
			if (e2.getStart() == v2){
				v3 = e2.getTarget();
			}else{
				v3 = e2.getStart();	
			}
			
			index = 0;
			// Finde heraus an welcher Stelle die Kante e in der Liste von v3 ist.
			for (int i = 0; i <= v3.getEdges().size(); i++){
				if (v3.getEdges().get(i) == e2){
					index = i;
					break;
				}
			}
						
			// Betrachte die Kante eins vorher (zyklisch permutiert)
			if (index > 0)
				e2 = v3.getEdges().get(index - 1);
			else 
				e2 = v3.getEdges().get(v3.getEdges().size() - 1);
			
		}
		
		// TODO: Momentan wird nur eine Flaeche gemacht, wir wollen aber alle!!!
		
		// Ausgabe des Graphen als Flaechenuhrzeigerliste
		return out_graph;
	}

}
