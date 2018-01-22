import DataStructures.GraphGenerator;
import DataStructures.PlanarGraph;
import Placement.Placement;


public class TestingSimpleAlgorithm {

	public static void main(String[] args) {
		GraphGenerator gen = new GraphGenerator();
		PlanarGraph g = gen.getK4();
		double e = Math.pow(10,-6);
		double d = Math.pow(10,-2);
		System.out.println("Parameter: e = " + e + ", d = " + d);
		SimpleAlgorithm s = new SimpleAlgorithm(g,e);
		s.run();

		Placement p = new Placement(g);
		p.calculating();
		
		System.out.println("Calculating done");
		p.printCoords();
		p.printIpeCoords("test.ipe");
	}

}
