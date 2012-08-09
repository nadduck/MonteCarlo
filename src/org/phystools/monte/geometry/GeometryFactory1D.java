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

}
