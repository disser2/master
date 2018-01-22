import java.util.ArrayList;
import java.util.Collections;

import DataStructures.*;


public class Mohar {
	
	/*
	 * Attribute
	 */
	private PlanarGraph map;
	private int eulerchar;
	private double sigma_r;
	private ArrayList<Node> s_r;
	
	/**
	 * Der Konstruktor der Klasse Mohar, welcher (momentan) direkt den Algorithmus ausfuehrt
	 * @param map Die reduzierte Karte M_0
	 * @param eulerchar Die Euler-Charakteristik chi(Sigma) der Flaeche Sigma auf der wir den Graphen M_0 betrachten
	 * @param delta Die Fehlertoleranz in der Ausgabe
	 */
	public Mohar(PlanarGraph map_input, int eulerchar, double delta){
		this.map = map_input;
		this.eulerchar = eulerchar;
		
		/*
		 * TODO: 1. Konstruiere M. Hier muss ich mir ueberlegen wie man einen Graphen mit fester 
		 * planarer Einbettung empfaengt und die Flaechen-Knoten-Inzidenzmatrix berechnet
		 */
		
		/*
		 * 2. Berechne delta1 und epsilon
		 */
		
		//TODO: Testing
		System.out.print("Graph-Eingabe: ");
		
		int n = map.getN();
		int m = map.getM();
		System.out.println("n: " + n + ", m: " + m);
		
		System.out.println();
		System.out.println("Erstberechnung von delta1 und epsilon");
		double delta1 = (Math.pow(2, (-2*n)-4) * Math.pow(m,(-2*n)-5) * delta);
		double epsilon = (Math.pow(2, -4*n) * Math.pow(m, -4*n -18) * Math.pow(delta1, 2));
		
		// Testing
		System.out.println("Delta1: " + delta1 + ", Epsilon: " + epsilon);
		
		/*
		 * 3. Berechne p
		 */
		double p = 20 * map.getN() * Math.ceil(Math.log(map.getM()) / Math.log(2)) + Math.ceil(Math.log(1/epsilon) / Math.log(2));
	
		// Testing
		System.out.println("Anzahl der Digits p (Berechnung vor dem 1. Durchlauf): " + p);
		
		/*
		 * 4. Berechne r_v fuer alle v aus V
		 */
		double rv = Math.cosh(Math.atan((map.getN()*Math.PI)/(2*map.getM())));
		System.out.println("Initialer Radius aller Knoten: r_v = " + rv);
		for (Node v : map.getNodes()){
			v.setRadius(rv);
		}
		
		/*
		 * 5. Schleife
		 */
		// Wir berechnen die Abweichung von der Zielvorgabe mit Hilfe von mu(r); Zuerst muss fuer alle Knoten der Wert psi_v berechnet werden
		double mu_r = 0;
		for (Node v: map.getNodes()){
			v.calculateTheta(eulerchar);
			mu_r += Math.pow(v.getTheta(), 2);
		}
		System.out.println("Initialer Wert von mu(r): " + mu_r);
		
		// Hier mit zaehlen wir die Anzahl der Iterationen
		int itCount = 0;
		
		System.out.println();
		System.out.println("____________________");
		System.out.println("Ende Initialisierung");
		System.out.println("____________________");
		System.out.println();
		
		// Die Hauptschleife des Algorithmus
		while (mu_r > (epsilon/2)){
			
			System.out.println("LOG: Beginne Schleifendurchlauf Nr. " + (itCount+1));
			
			/*
			 * 5.1. Bestimme sigma_r und die Menge S(r)
			 */
			sigma_r = 0;
			s_r = null;
			s_r = calculateSigma(map.getNodes());
			
			// TODO: Test-Ausgabe rausnehmen
			map.print(false);
			
			/*
			 * TODO: 5.2. Finde ein zulaessiges Paar (beta, gamma) mit Hilfe des Bisektionsverfahren
			 */
			double beta;
			double gamma;
			
			/*
			double[] betagamma = bisection();
			beta = betagamma[0];
			gamma = betagamma[1];
			*/
			
			// TODO: Entfernen!
			// Testwert, testausgabe
			{
				beta = 1.15;
				gamma = 0.8;
				System.out.println("BetaGamma: " + beta + ", " + gamma);
				System.out.println();
			}
			
			System.out.println("isSuitable: " + isSuitable(beta, gamma));
			
			/*
			 * 8. Aktualisiere r
			 */
			for (Node v : map.getNodes()){
				if (s_r.contains(v)){
					v.setRadius(beta * v.getRadius());
				}else{
					v.setRadius(gamma * v.getRadius());
				}
			}
			System.out.println("r wurde aktualisiert");
			System.out.println();
			
			// TODO: Diese Schleife wieder entfernen
			for (Node v : map.getNodes()){
				v.calculateNewTheta(0);
			}
			
			System.out.println("theta wurde neu berechnet");
			System.out.println();
			
			//map.print(true);			
			
			// Hier sollte noch einmal ueberprueft werden, dass die Radii normalisiert sind
			if (isNormalized(map.getNodes()))
				System.out.println("Radii sind normalisiert");
			else
				System.out.println("Fehler! Radii sind nicht normalisiert");
			
			/*
			 * Wir muessen mu(r) aktualisieren, damit die Schleife irgendwann abbricht. Sollte genauso aussehen wie der obige Abschnitt vor der Schleife
			 */
			mu_r = 0;
			for (Node v: map.getNodes()){
				v.calculateTheta(eulerchar);
				mu_r += Math.pow(v.getTheta(), 2);
			}
			System.out.println("Testausgabe von mu(r): " + mu_r);
			
			// Ausgabe des Iterationszaehlers
			System.out.println("Iteration Nr. " + itCount);
			itCount++;
			
			// TODO: break entfernen
			break;
		}
		
		/*
		 * TODO: 10. Berechne P_v fuer alle v aus V
		 */
		
		/*
		 * TODO: 11. Gebe r_v und P_v fuer alle v aus V aus
		 */
	
		output();
	}
	
	/**
	 * Berechnet mit Hilfe des Bisektionsverfahrens die Parameter beta, gamma in jedem Durchlauf der Schleife des Algorithmus
	 * @return Ein Array mit den Werten beta, gamma
	 */
	public double[] bisection(){
		// Hier speichern wir die beiden Parameter beta und gamma in dieser Reihenfolge
		double[] betagamma = new double[2];
		double gamma = 0;
		double beta = 1;
		
		int e_s = calculate_E_s(s_r);
		
		// Intervallgrenzen fuer gamma
		double gamma_min;
		double gamma_max;
		
		// Fallunterscheidung
		if((e_s + 2 * eulerchar) >= 0){
			// TODO: Bisektion fuer gamma auf dem Intervall [0,1]
			gamma_min = 0;
			gamma_max = 1;
			gamma = (gamma_min + gamma_max)/2;
			
			// TODO: Variable, die angibt ob wir gamma bisher zu tief angesetzt haben
			boolean too_low = false;
			
		
			// TODO: Testen
			// Schleife, die solange gamma anpasst, bis die Funktion f gross genug ist
			while(!isNormalized(map.getNodes()) || !isSuitable(beta, gamma)){
				
				// Je nachdem ob wir zu niedrig oder zu hoch waren, wird gamma per Bisektion angepasst. Dafuer verwenden wir einen Dreieckstausch
				if (too_low){
					double help = gamma_min;
					gamma_min = gamma;
					gamma = (help + gamma_max)/2;
				}else{
					double help = gamma_max;
					gamma_max = gamma;
					gamma = (help + gamma_min)/2;
				}
				
				// Die NewRadien der Knoten werden aktualisiert
				for (Node v : map.getNodes()){
					if (!s_r.contains(v)){
						v.setNewradius(gamma * v.getRadius());
					}
				}
				
				// NewTheta wird fuer jeden Knoten berechnet
				for (Node v : map.getNodes()){
					v.calculateNewTheta(eulerchar);
				}
				
				// TODO: Hier muss too_low aktualisiert werden!!!
			}
			
			
			// TODO: Bisektion fuer beta auf dem Intervall [1,?]
			
		}
		else{
			// TODO: Bisektion fuer beta auf dem Intervall [1,beta_max] wobei beta_max = 2m^n*ceil(log m)
			// TODO: WICHTIG!!! EVENTUELL MUSS HIER EINE ANDERE BASIS FUER DEN LOGARITHMUS VERWENDET WERDEN!
			double beta_max = 2 * Math.pow(map.getM(),map.getN()) * Math.ceil(Math.log(map.getM())/Math.log(2));
			
			// TODO: Bisektion fuer gamma auf dem Intervall [?,?]
			
		}
		
		betagamma[0] = beta;
		betagamma[1] = gamma;
		
		// TODO: Testing
		System.out.println("Beta und Gamma wurden passend berechnet: Beta: " + beta + ", Gamma: " + gamma);
		
		return betagamma;
	}
	
	/**
	 * Die Methode prueft, ob die Menge der Radien der Knoten normalisiert ist
	 * @param nodes
	 * @return true if this set of nodes ist normalized, otherwise false
	 */
	public boolean isNormalized(ArrayList<Node> nodes){
		double sum = 0;
		
		for (Node v : nodes){
			System.out.println("Newtheta von " + v.getName() + ": " + v.getNewtheta());
			sum += v.getNewtheta();
		}
		
		System.out.println("Newtheta-Summe: " + sum);
		System.out.println("Pi: " + Math.PI);
		
		if (sum == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Berechnet den Wert E(S), also die Anzahl Kanten, die beide Knoten in S haben
	 * @param s_r
	 * @return
	 */
	public int calculate_E_s(ArrayList<Node> s_r){
		int count = 0;
		
		for (Node v : s_r){
			for (Node w : v.getNeighbours()){
				if (s_r.contains(w)){
					count++;
				}
			}
		}
		
		// Jede Kante in E(S) wird bis hierher doppelt gezaehlt, einmal aus der Sicht von v, einmal aus der Sicht von w
		count /= 2;
		
		// TODO: Test rausnehmen
		System.out.println("Wert von E(S): " + count);
		
		return count;
	}
	
	/**
	 * Berechnet sigma(r) für eine Menge von Knoten mit jeweils dem Wert theta(v)
	 * @param nodes
	 * @return sigma(r), d.h. den maximalen Abstand aufeinander folgenden negativen theta-Werte zweier Knoten
	 */
	public ArrayList<Node> calculateSigma(ArrayList<Node> nodes){
		Collections.sort(nodes);
		
		// Test-Ausgabe, ob die Knoten richtig sortiert sind
		/*
		for (int i = 0; i < nodes.size(); i++){
			System.out.println(nodes.get(i).getName() + ", " + nodes.get(i).getTheta());
		}
		*/
		
		double sigma = 0;
		for(int i = 0; i < nodes.size() - 1; i++){
			
			double newsigma;
			
			if (nodes.get(i).getTheta() > 0 && nodes.get(i+1).getTheta() > 0)
				continue;
			else if (nodes.get(i).getTheta() > 0)
				newsigma = 0 - nodes.get(i+1).getTheta();
			else
				newsigma = nodes.get(i).getTheta() - nodes.get(i+1).getTheta();
			
			if (newsigma > sigma)
				sigma = newsigma;
		}
		
		// Aktualisiere das globale Attribut
		sigma_r = sigma;
		
		// Test-Ausgabe von sigma
		System.out.println("Sigma: " + sigma);
		
		ArrayList<Node> s_r = new ArrayList<Node>();
		
		if (nodes.get(nodes.size() - 1).getTheta() >= 0){
			for (Node v: nodes){
				s_r.add(v);
			}
		} else {
		
			for (int i = 0; i < nodes.size(); i++){
				if (nodes.get(i).getTheta() > 0 && nodes.get(i+1).getTheta() > 0)
					s_r.add(nodes.get(i));
				else if (nodes.get(i).getTheta() > 0 && nodes.get(i+1).getTheta() < 0){
					s_r.add(nodes.get(i));
					// Falls wir zum ersten Mal die Differenz sigma erreichen, brechen wir die Schleife ab
					if (0 - nodes.get(i+1).getTheta() == sigma)
						break;
				}
				else if (nodes.get(i).getTheta() < 0 && nodes.get(i+1).getTheta() < 0){
					s_r.add(nodes.get(i));
					// Falls wir zum ersten Mal die Differenz sigma erreichen, brechen wir die Schleife ab
					if (nodes.get(i).getTheta() - nodes.get(i+1).getTheta() == sigma)
						break;
				}
			}
		}
		
		// TODO: Test-Ausgabe entfernen
		System.out.println("Elemente aus s_r:");
		for(Node v : s_r){
			System.out.println(v.getName() + ": " + v.getTheta());
		}
		System.out.println();
		
		
		return s_r;
	}

	/**
	 * TODO: testen
	 * Berechnet, ob die beiden Parameter beta und gamma zusammen zulaessig sind
	 * @param beta
	 * @param gamma
	 * @return true, wenn beta und gamma zulaessig sind
	 */
	public boolean isSuitable(double beta, double gamma){
		// Untersuche S(r) und pruefe Bedingung 1
		// TODO: Hier getTheta oder getNewTheta()?
		double minS = s_r.get(0).getNewtheta();
		for (Node v : s_r){
			if (v.getNewtheta() < minS){
				minS = v.getNewtheta();
			}
		}
		
		for (Node u : map.getNodes()){
			if (!s_r.contains(u) && u.getNewtheta() > minS)
				return false;
		}
		
		double f = calculateFBetaGamma(beta, gamma);
		
		for (Node v : map.getNodes()){
			System.out.println("[" + v.getName() + "]: Theta: " + v.getTheta() + ", NewTheta: " + v.getNewtheta());
		}
		System.out.println("Funktionswert f(" + beta + "," + gamma + ") = " + f);
		
		return (f >= sigma_r/6);
	}
	
	/**
	 * Hier berechnen wir den Funktionswert von f(beta, gamma)
	 * @return f(beta, gamma)
	 */
	public double calculateFBetaGamma(double beta, double gamma){
		System.out.println("Starte Berechnung von f(beta, gamma)");
		
		for (Node v : map.getNodes()){
			if (s_r.contains(v)){
				v.setNewradius(beta * v.getRadius());
			}else{
				v.setNewradius(gamma * v.getRadius());
			}
		}
		
		System.out.println("LOG: Methode: calculateFBetaGamma; neue Radien wurden gesetzt");
		
		for (Node v : map.getNodes()){
			v.calculateNewTheta(eulerchar);
		}
		
		double sum = 0;
		for (Node v : map.getNodes()){
			sum += (v.getTheta() - v.getNewtheta());
		}
		
		return sum;
		
	}
	
	/**
	 * TODO: Hier soll der Graph mit seinen Knoten, deren Radii und Position auf der Konsole ausgegeben werden.
	 * Eventuell mit einem boolean als Parameter entscheiden, ob auf der Konsole oder in eine Datei
	 */
	public void output(){
		System.out.println("Ausgabe des Graphen nachdem alles berechnet wurde: ");
		for (int i = 0; i < map.getNodes().size(); i++){
			Node v = map.getNodes().get(i);
			System.out.println("Knoten " + v.getName() + ", Radius: " + v.getRadius() + ", Position: x=" + v.getXpos() + ", y=" + v.getYpos());
		}
	}
	
	/**
	 * TODO: Die Methode berechnet fuer eine gegebene Karte M_0 dessen Knotenflaecheninzidenzkarte M
	 * @param M_0 Karte
	 * @return Knotenflaecheninzidenzkarte M von M_0
	 */
	public PlanarGraph constructM(PlanarGraph M_0){
		
		
		// TODO: Fix return
		return null;
	}

}
