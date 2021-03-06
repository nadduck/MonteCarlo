package org.phystools.monte.geometry;

import java.util.Random;

public class Displacement4D implements Displacement {
	
	private final double[] x = new double[4];

	public Displacement4D(double[] x) {
		this.x[0] = x[0];
		this.x[1] = x[1];
		this.x[2] = x[2];
		this.x[3] = x[3];
	}

	public Displacement4D() {
	}

	@Override
	public Displacement newGaussianRand(double sigma, Random rand) {
		Displacement delta = new Displacement4D();
		delta.makeGaussianRandom(sigma, rand);
		return delta;
	}

	@Override
	public Displacement newUniformDisplacement(double delta, Random rand) {
		double[] y = new double[4];
		y[0] = delta * (2 * rand.nextDouble() - 1);
		y[1] = delta * (2 * rand.nextDouble() - 1);
		y[2] = delta * (2 * rand.nextDouble() - 1);
		y[3] = delta * (2 * rand.nextDouble() - 1);
		return new Displacement4D(y);
	}

	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(getMagnitude2());
		return magnitude;
	}

	@Override
	public void makeGaussianRandom(double sigma, Random rand) {
		x[0] = sigma * rand.nextGaussian();
		x[1] = sigma * rand.nextGaussian();
		x[2] = sigma * rand.nextGaussian();
		x[3] = sigma * rand.nextGaussian();
	}

	@Override
	public double getMagnitude2() {
		double magnitude2 = x[0]*x[0] + x[1]*x[1] + x[2]*x[2] + x[3]*x[3];
		return magnitude2;
	}

	@Override
	public double[] toArray() {
		return x;
	}

}
