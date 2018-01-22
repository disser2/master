package DataStructures;

public class Edge {
	
	private Node start;
	private Node target;

	public Edge(Node start, Node target){
		this.setStart(start);
		this.setTarget(target);
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
	}
	
}
