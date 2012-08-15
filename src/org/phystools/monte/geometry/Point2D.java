package org.phystools.monte.geometry;

public class Point2D implements Point {
	
	private final double[] x = new double[2];
	
    public Point2D(double[] x) {
        this.x[0] = x[0];
        this.x[1] = x[1];
    }
    
    public Point2D() {
    }

    public Point2D(Point point) {
        this.x[0] = point.toArray()[0];
        this.x[1] = point.toArray()[1];
    }
    
	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(getMagnitude2());
		return magnitude;
	}

	@Override
	public double getMagnitude2() {
		double magnitude2 = x[0]*x[0]+x[1]*x[1];
		return magnitude2;
	}

	@Override
	public Displacement getDifference(Point point) {
		double[] difference = new double[2];
		difference[0] = x[0] - point.toArray()[0];
		difference[1] = x[1] - point.toArray()[1];
		return new Displacement2D(difference);
	}

	@Override
	public void move(Displacement delta) {
		x[0] += delta.toArray()[0];
		x[1] += delta.toArray()[1];
	}

	@Override
	public double[] toArray() {
		return x;
	}

	@Override
	public double dot(Point point) {
		double product = 0;
		product += x[0]*point.toArray()[0];
		product += x[1]*point.toArray()[1];
		return product;	
	}

	@Override
	public Point midpoint(Point p2) {
		double[] rmid = new double[2];
		rmid[0] = 0.5 * (x[0]+p2.toArray()[0]);
		rmid[1] = 0.5 * (x[0]+p2.toArray()[1]);
		return new Point2D(rmid);
	}
}
