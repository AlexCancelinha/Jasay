package POO_Heranca;

import java.util.ArrayList;
import java.util.Iterator;

public class Canvas implements Iterable<Figure> {

	private final ArrayList<Figure> figures;

	public Canvas(ArrayList<Figure> figures) {
		super();
		this.figures = figures;
	}

	@Override
	public Iterator<Figure> iterator() {
		return figures.iterator();
	}
	
	public void addFigure(Figure figure) {
		figures.add(figure);
	}

	public void removeFigure(int index) {
		validate();
		figures.remove(index);
	}

	public void removeBiggerArea() {
		validate();
		Figure biggestAreaFigure = figures.get(0);
		for (Figure f : figures) {
			if (f.getArea() > biggestAreaFigure.getArea()) {
				biggestAreaFigure = f;
			}
		}
		figures.remove(biggestAreaFigure);
	}

	public int getAllArea() {
		int sum = 0;
		for(Figure f: figures) {
			sum+= f.getArea();
		}
		return sum;
	}

	private void validate() {
		if(figures.isEmpty())
			throw new IllegalStateException("Invalid Action");
	}
}