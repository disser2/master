
public class Bisection {
	
	private static double number;
	private static double epsilon;
	private static int count;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		int top = 10000;

		
		number = Math.random()*top;
		System.out.println("Startzahl: " + number);
		
		epsilon = 0.0000001;
		
		count = 0;
		
		double erg = bisection(0,top);
		
		System.out.println("Zielzahl:  " + erg);
		System.out.println("Differenz: " + Math.abs(number - erg));
		System.out.println("Genauigkeit: " + epsilon);
		System.out.println("Runden: " + count);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Laufzeit in ms: " + totalTime);
	}
	
	public static double bisection(double a, double b){
		count++;
		
		// Falls falsch herum, vertausche die Zahlen
		if (a > b){
			double h = a;
			a = b;
			b = h;
		}
		
		if (Math.abs((a+b)/2 - number) < epsilon)
			return Math.abs((a+b)/2);
		
		if ((a+b)/2 > number){
			return bisection(a, (a+b)/2);
		}
		
		if ((a+b)/2 < number){
			return bisection((a+b)/2,b);
		}
		
		return 0;
		
	}

}
