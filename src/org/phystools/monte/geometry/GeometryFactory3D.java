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

    @Override
    public Point createNewPoint(Point xmid) {
        return new Point3D(xmid);
    }

    @Override
    public Displacement createNewDisplacement() {
        return new Displacement3D();
    }

    @Override
    public int getSpaceDimension() {
        return 3;
    }

}
