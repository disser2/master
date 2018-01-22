package DataStructures;

import java.util.LinkedList;

public class Face {
	
	private LinkedList<Edge> edges;
	
	public Face(LinkedList<Edge> edges){
		this.setEdges(edges);
	}

	public LinkedList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}
	
}
