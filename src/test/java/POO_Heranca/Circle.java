package POO_Heranca;

public class Circle extends AbstractFigure{

	private final int radius;

	public Circle(int x, int y, int radius) {
		super(x,y);
		this.radius = radius;
	}

	@Override
	public double getArea() {
		// TEMPORARY DO-WHILE TO TEST JAVAPARSER
		double result = Math.PI*radius*radius;
		do {
			String hope = "";
		} while (result<0);
		return result;
	}

	@Override
	public double getPerimeter() {
		// TEMPORARY TRY-CATCH-FINALLY TO TEST JAVAPARSER
		try {
			String attempt = "";
		} catch (Exception e) {
			String fault = "asd";
		} finally {
			String granFinale = "muahaha";
		}
		return 2*Math.PI*radius;
	}
}
