package org.phystools.monte.geometry;

public class GeometryFactory3D implements GeometryFactory {

	@Override
	public Point createNewPoint() {
		return new Point3D();
	}

	@Override
	public Point createNewPoint(double[] coord) {
		return new Point3D(coord);
	}

}
