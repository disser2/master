package DataStructures;
import java.util.LinkedList;

/**
 * @author Dario
 * Diese Klasse erzeugt den Kreis-Graphen C_n fuer ein gegebenes n
 */
public class CircleGraph {

	public CircleGraph(){
	}
	
	public PlanarGraph getCircleGraph(int n){
		
		if (n <= 1){
			return null;
		}
		
		PlanarGraph c = new PlanarGraph();
		
		// Erzeuge die Knoten und fuege sie dem Graphen hinzu
		for (int i = 0; i < n; i++){
			Node v = new Node("" + i);
			c.getNodes().add(v);
		}
		
		// Setze den jeweils naechsten Knoten als Nachbarn
		for (int i = 0; i < c.getNodes().size() - 1; i++){
			LinkedList<Node> neighbours = new LinkedList<Node>();
			neighbours.add(c.getNodes().get(i+1));
			c.getNodes().get(i).setNeighbours(neighbours);
		}
		LinkedList<Node> neighbours = new LinkedList<Node>();
		neighbours.add(c.getNodes().get(0));
		c.getNodes().get(c.getNodes().size() - 1).setNeighbours(neighbours);
		
		c.update();
		
		return c;
	}
	
}
