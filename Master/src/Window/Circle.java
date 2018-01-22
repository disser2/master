package Window;

public class Circle {
	
	private double r;
	private double x;
	private double y;
	
	public Circle(double x, double y, double r){
		this.r = r;
		this.x = x;
		this.y = y;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
