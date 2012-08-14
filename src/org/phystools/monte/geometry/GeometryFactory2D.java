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

    @Override
    public Point createNewPoint(Point xmid) {
        return new Point2D(xmid);
    }

    @Override
    public Displacement createNewDisplacement() {
        return new Displacement2D();
    }

    @Override
    public int getSpaceDimension() {
        return 2;
    }

}
