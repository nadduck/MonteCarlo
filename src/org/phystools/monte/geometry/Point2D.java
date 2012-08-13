package org.phystools.monte.geometry;

public class Point2D implements Point {
	private double[] x = new double[2];
	
    public Point2D(double[] x) {
        this.x = x;
    }
    
    public Point2D() {
    }

    public Point2D(Point point) {
        this.x = point.toArray();
    }
    
	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(x[0]*x[0]+x[1]*x[1]);
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
		for(int i = 0; i < 2; i++) {
			difference[i] = x[i] - point.toArray()[i];
		}
		return new Displacement2D(difference);
	}

	@Override
	public void move(Displacement delta) {
		for(int i = 0; i < 2; i++) {
			x[i] += delta.toArray()[i];
		}
	}

	@Override
	public double[] toArray() {
		return x;
	}

	@Override
	public double dot(Point point) {
		double product = x[0]*point.toArray()[0]+x[1]*point.toArray()[1];
		return product;
	}

	@Override
	public Point midpoint(Point p2) {
		double[] rmid = new double[2];
		rmid[0] = 0.5 * (x[0]+p2.toArray()[0]);
		rmid[1] = 0.5 * (x[1]+p2.toArray()[1]);
		return new Point2D(rmid);
	}
}
