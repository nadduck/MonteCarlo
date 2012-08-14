package org.phystools.monte.geometry;

public class GeometryFactory1D implements GeometryFactory {

    @Override
    public Point createNewPoint() {
        return new Point1D();
    }

    @Override
    public Point createNewPoint(double[] coord) {
        return new Point1D(coord[0]);
    }

    @Override
    public Point createNewPoint(Point xmid) {
        return new Point1D(xmid);
    }

    @Override
    public Displacement createNewDisplacement() {
        return new Displacement1D();
    }

    @Override
    public int getSpaceDimension() {
        return 1;
    }

}
