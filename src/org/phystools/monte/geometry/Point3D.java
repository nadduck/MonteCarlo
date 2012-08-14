package org.phystools.monte.geometry;

public class Point3D implements Point {

	private double[] x = new double[3];

	public Point3D(double[] x) {
		this.x = x;
	}

	public Point3D() {
	}
	
    public Point3D(Point point) {
        this.x = point.toArray();
    }

	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(getMagnitude2());
		return magnitude;
	}

	@Override
	public double getMagnitude2() {
		double magnitude2 = x[0]*x[0] + x[1]*x[1] + x[2]*x[2];
		return magnitude2;
	}

	@Override
	public Displacement getDifference(Point point) {
		double[] difference = new double[3];
		for(int i = 0; i < 3; i++) {
			difference[i] = x[i] - point.toArray()[i];
		}
		return new Displacement3D(difference);
	}

	@Override
	public void move(Displacement delta) {
		for(int i = 0; i < 3; i++) {
			x[i] += delta.toArray()[i];
		}
	}

	@Override
	public double[] toArray() {
		return x;
	}

	@Override
	public double dot(Point point) {
		double product = 0;
		for(int i = 0; i < 3; i++) {
			product += x[i]*point.toArray()[i];
		}
		return product;	
	}

	@Override
	public Point midpoint(Point p2) {
		double[] rmid = new double[3];
		for(int i = 0; i < 3; i++) {
			rmid[i] = 0.5 * (x[i]+p2.toArray()[i]);
		}
		return new Point3D(rmid);
	}

}
