package Placement;
import DataStructures.*;


public class PlacementTesting {

	public static void main(String[] args) {
		
		//Generating Testgraph
		GraphGenerator graphGen = new GraphGenerator();
		
		PlanarGraph g = graphGen.getHoneycomb();
		// Setze alle Radien auf den gleichen Wert
		for (Node v : g.getNodes()){
			v.setRadius(1);
			v.setPos(0, 0);
		}
		
//		PlanarGraph g = graphGen.getK4();
//		// Setze alle Radien auf den gleichen Wert
//		for (Node v : g.getNodes()){
//			if (v.getName() == "v2"){
//				double a = 5;
//				double b = 32;
//				v.setRadius(a/b);
//				v.setPos(0, 0);
//			}else{
//				v.setRadius(1);
//				v.setPos(0, 0);
//			}
//		}
		
		Placement p = new Placement(g);
		p.calculating();
		
		System.out.println("Calculating done");
		p.printCoords();
		p.printIpeCoords("test.ipe");
	}

}
