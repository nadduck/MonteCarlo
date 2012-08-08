public class Point {
    public double x;

    Point(double x) {
        this.x = x;
    }
    
    public Point() {
    }

    public Point(Point point) {
        this.x = point.x;
    }

    public double getMagnitude() {
        return Math.abs(x);
    }
    
    public double getMagnitude2() {
        return x * x;
    }

    public Displacement getDifference(Point point2) {
        return new Displacement(x - point2.x);
    }
    
    public void move(Displacement delta) {
        x += delta.x;
    }

    public double[] toArray() {
        double[] array = {x};
        return array;
    }

    public double dot(Point point) {
        return x * point.x;
    }

    public static Point midpoint(Point p1, Point p2) {
        return new Point(0.5*(p1.x + p2.x));
    }
}
