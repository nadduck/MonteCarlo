
public class Point {
    private double x;

    Point(double x) {
        this.x = x;
    }
    
    public double getMagnitude() {
        return Math.abs(x);
    }
    
    public Displacement getDifference(Point point2) {
        return new Displacement(x - point2.getMagnitude());
    }
    
    public void move(Displacement delta) {
        x += delta.getMagnitude();
    }
}
