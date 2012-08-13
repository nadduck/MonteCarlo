package org.phystools.monte.geometry;

public class GeometryFactory2D implements GeometryFactory {

	@Override
	public Point createNewPoint() {
		return new Point2D();
	}

	@Override
	public Point createNewPoint(double[] coord) {
		return new Point2D(coord);
	}

}
