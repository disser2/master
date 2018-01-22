import DataStructures.*;

/**
 * @author Dario Antweiler
 * Dieser Kreispackungs-Algorithmus macht folgendes: Setze alle Radien auf einen festen Wert. Pruefe, ob ein Knoten die Winkelsummenbedingung verletzt. 
 * Falls ja, verkleinere bzw. vergroessere seinen Radies relativ zum Grad des Abweichens.
 */
public class SimpleAlgorithm {
	
	private PlanarGraph g;
	private double epsilon;
	
	/**
	 * Konstruktor
	 * @param g
	 * @param epsilon Die Genauigkeit innerhalb der Winkelsummen-Pruefung
	 */
	public SimpleAlgorithm(PlanarGraph g, double epsilon){
		this.g = g;
		this.epsilon = epsilon;
	}
	
	public void run(){
		// Initialisiere Radien
		for (Node v : g.getNodes()){
			v.setRadius(1);
		}
		
		// Berechne theta
		for (Node v : g.getNodes()){
			v.calculateTheta(2);
		}
		
		boolean done = true;
		
		// Pruefe ob Winkelsummenbedingung erfuellt ist
		for (Node v : g.getNodes()){
			if (Math.abs(v.getTheta()) > epsilon){
				v.setSatisfied(false);
				done = false;
			}else{
				v.setSatisfied(true);
			}
		}
		
		//Zaehler
		int counter = 0;
		
		while(!done){
			
			counter++;
			System.out.println("Runde: " + counter);
			
			Node currentNode = null;
			
			// Waehle einen Knoten aus, der die Bedigung nicht erfuellt
			for (Node v : g.getNodes()){
				if (!v.isSatisfied()  && !v.isBorderNode()){
					currentNode = v;
					break;
				}
			}
			
			// Erhoehe den Radius
			if (currentNode.getTheta() > 0){
				double oldRadius = currentNode.getRadius();
				currentNode.setRadius(currentNode.getRadius() * (1 + 1/(Math.PI) * currentNode.getTheta()));
				double newRadius = currentNode.getRadius();
				System.out.println("Alter Radius: " + oldRadius + ", Neuer Radius: " + newRadius);
			}
			
			// Vermindere den Radius
			if (currentNode.getTheta() < 0){
				double oldRadius = currentNode.getRadius();
				currentNode.setRadius(currentNode.getRadius() * (1 + 1/(Math.PI) * currentNode.getTheta()));
				double newRadius = currentNode.getRadius();
				System.out.println("Alter Radius: " + oldRadius + ", Neuer Radius: " + newRadius);
			}
			
			// Theta aktualisieren
			for (Node v : g.getNodes()){
				v.calculateTheta(2);
			}
			
			// Test-Ausgabe der Radien
			for (Node v : g.getNodes()){
				System.out.println("[" + v.getName() + "]: Radius = " + v.getRadius());
			}
			
			done = true;
			
			// Pruefe ob Winkelsummenbedingung erfuellt ist
			for (Node v : g.getNodes()){
				if (Math.abs(v.getTheta()) > (epsilon) &&  !v.isBorderNode()){
					v.setSatisfied(false);
					done = false;
				}else{
					v.setSatisfied(true);
				}
			}
			
			if (counter > 1000){
				done = true;
				System.out.println("Algorithmus bricht ab, da counter > 1000");
			}
		}
		
		System.out.println("SimpleAlgorithm ist fertig!");
		System.out.println("Counter: " + counter);
		System.out.println("Finale Werte für theta:");
		for (Node v : g.getNodes()){
			System.out.println("[" + v.getName() + "]: " + v.getTheta());
		}
		
	}

}
