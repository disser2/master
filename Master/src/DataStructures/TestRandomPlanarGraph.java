package DataStructures;

public class TestRandomPlanarGraph {

	public static void main(String[] args) {
		
		GraphGenerator gen = new GraphGenerator();
		PlanarGraph g = gen.getRandomPlanarGraph(8);
		g.print(false);

	}

}
