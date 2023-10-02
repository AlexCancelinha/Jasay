package POO_Heranca;

public abstract class AbstractFigure implements Figure{

	// LOCATION OF THE FIGURE
	private final int x;
	private final int y;

	public AbstractFigure(int x, int y) {
		// Assign the X & Y Positive values Values
		this.x = Math.abs(x);
		this.y = Math.abs(y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}