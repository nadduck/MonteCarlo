package org.phystools.monte.geometry;

public interface GeometryFactory {

    public Point createNewPoint();

    public Point createNewPoint(double[] coord);

    public Point createNewPoint(Point xmid);

    public Displacement createNewDisplacement();

    public int getSpaceDimension();
}

