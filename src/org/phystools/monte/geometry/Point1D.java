package org.phystools.monte.geometry;
public class Point1D implements Point {
	
    private final double[] x = new double[1];

    public Point1D(double x) {
        this.x[0] = x;
    }
    
    public Point1D() {
    }

    public Point1D(Point point) {
        this.x[0] = point.toArray()[0];
    }

    @Override
    public double getMagnitude() {
        return Math.abs(x[0]);
    }
    
    @Override
    public double getMagnitude2() {
        return x[0] * x[0];
    }

    @Override
    public Displacement getDifference(Point point) {
        return new Displacement1D(x[0] - point.toArray()[0]);
    }
    
    @Override
    public void move(Displacement delta) {
        x[0] += delta.toArray()[0];
    }

    @Override
    public double[] toArray() {
        return x;
    }

    @Override
    public double dot(Point point) {
        return x[0] * point.toArray()[0];
    }

    @Override
    public Point midpoint(Point p2) {
        return new Point1D(0.5*(x[0] + p2.toArray()[0]));
    }
}
