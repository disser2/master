import java.util.ArrayList;

import DataStructures.*;


public class Testing {

	public static void main(String[] args) {

//		System.out.println("Generiere Kreisgraph");
//		CircleGraph circle = new CircleGraph();
//		PlanarGraph g = circle.getCircleGraph(10);
		
		GraphGenerator gen = new GraphGenerator();
		PlanarGraph g = gen.getK4VertexFaceIncidence();
		//g.print(false);
		
		double delta = Math.pow(2, -10);
		System.out.println("Eingabe-Delta: " + delta);
		
		// Rufe den Algorithmus mit dem Graphen g, der Eulercharakteristik -1 und einem sehr kleinen delta auf
		Mohar test = new Mohar(g, -1, delta);
		
		String output = "output.txt";
		// Um eine neue Zeile in die Ausgabe einzufuegen
		String newLine = System.getProperty("line.separator");
		AusgabeInDatei a = new AusgabeInDatei(output, "Dies ist ein Test" + newLine, false);
		
	}

}
