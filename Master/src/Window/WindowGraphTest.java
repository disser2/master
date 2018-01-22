package Window;

import DataStructures.*;

public class WindowGraphTest {

	public static void main(String[] args) {

		GraphGenerator gen = new GraphGenerator();
		PlanarGraph graph = gen.getHoneycomb();
		for (Node v : graph.getNodes()){
			v.setRadius(Math.random()*30);
		}
		Window2 w = new Window2();
		System.out.println("Finished");
		
	}

}
