package POO_Heranca;

public class Rectangle extends AbstractFigure{

	private final int width;
	private final int height;
	// VARIABLES CREATED TO TEST JAVAPARSER
	private int wo, ow, yo;
	
	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public double getArea() {
		// SCENARIO CREATED TO TEST JAVAPARSER
		int myHeight = height;
		int myWidth = width;
		if (myHeight != myWidth) {
			int aux = 2;
		}
		int area = myWidth * myHeight;
		return area;
	}

	@Override
	public double getPerimeter() {
		// VAR PERIMETER CREATED TO TEST JAVAPARSER
		int perimeter = 2*(width+height);
		return perimeter;
	}
}
