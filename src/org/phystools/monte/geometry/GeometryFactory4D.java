package org.phystools.monte.geometry;

public class GeometryFactory4D implements GeometryFactory {

	@Override
	public Point createNewPoint() {
		return new Point4D();
	}

	@Override
	public Point createNewPoint(double[] coord) {
		return new Point4D(coord);
	}

	@Override
	public Point createNewPoint(Point point) {
		return new Point4D(point);
	}

	@Override
	public Displacement createNewDisplacement() {
		return new Displacement4D();
	}

	@Override
	public int getSpaceDimension() {
		return 4;
	}

}
