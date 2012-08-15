package org.phystools.monte.geometry;

public class Point4D implements Point {

	private final double[] x = new double[4];

	public Point4D(double[] x) {
		this.x[0] = x[0];
		this.x[1] = x[1];
		this.x[2] = x[2];
		this.x[3] = x[3];
	}

	public Point4D() {
	}

	public Point4D(Point point) {
        x[0] = point.toArray()[0];
        x[1] = point.toArray()[1];
        x[2] = point.toArray()[2];
        x[3] = point.toArray()[3];
    }

	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(getMagnitude2());
		return magnitude;
	}

	@Override
	public double getMagnitude2() {
		double magnitude2 = x[0]*x[0] + x[1]*x[1] + x[2]*x[2] + x[3]*x[3];
		return magnitude2;
	}

	@Override
	public Displacement getDifference(Point point) {
		double[] difference = new double[4];
		difference[0] = x[0] - point.toArray()[0];
		difference[1] = x[1] - point.toArray()[1];
		difference[2] = x[2] - point.toArray()[2];
		difference[3] = x[3] - point.toArray()[3];
		return new Displacement4D(difference);
	}

	@Override
	public void move(Displacement delta) {
		x[0] += delta.toArray()[0];
		x[1] += delta.toArray()[1];
		x[2] += delta.toArray()[2];
		x[3] += delta.toArray()[3];
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
		product += x[2]*point.toArray()[2];
		product += x[3]*point.toArray()[3];
		return product;	
	}

	@Override
	public Point midpoint(Point p2) {
		double[] rmid = new double[4];
		rmid[0] = 0.5 * (x[0]+p2.toArray()[0]);
		rmid[1] = 0.5 * (x[1]+p2.toArray()[1]);
		rmid[2] = 0.5 * (x[2]+p2.toArray()[2]);
		rmid[3] = 0.5 * (x[3]+p2.toArray()[3]);
		return new Point4D(rmid);
	}

}
