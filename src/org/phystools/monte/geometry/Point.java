package org.phystools.monte.geometry;
public class Point {
    private final double[] x = new double[1];

    public Point(double x) {
        this.x[0] = x;
    }
    
    public Point() {
    }

    public Point(Point point) {
        this.x[0] = point.x[0];
    }

    public double getMagnitude() {
        return Math.abs(x[0]);
    }
    
    public double getMagnitude2() {
        return x[0] * x[0];
    }

    public Displacement getDifference(Point point2) {
        return new Displacement(x[0] - point2.x[0]);
    }
    
    public void move(Displacement delta) {
        x[0] += delta.x;
    }

    public double[] toArray() {
        return x;
    }

    public double dot(Point point) {
        return x[0] * point.x[0];
    }

    public static Point midpoint(Point p1, Point p2) {
        return new Point(0.5*(p1.x[0] + p2.x[0]));
    }
}
