package org.phystools.monte.geometry;

import java.util.Random;

public class Displacement2D implements Displacement {

	private final double[] x = new double[2];
	
	public Displacement2D() {
	}
	
	public Displacement2D(double[] x) {
		this.x[0] = x[0];
        this.x[1] = x[1];
	}

	@Override
	public Displacement newGaussianRand(double sigma, Random rand) {
		Displacement delta = new Displacement2D();
		delta.makeGaussianRandom(sigma, rand);
		return delta;
	}

	@Override
	public Displacement newUniformDisplacement(double delta, Random rand) {
		double[] y = new double[2];
		y[0] = delta * (2 * rand.nextDouble() - 1);
		y[1] = delta * (2 * rand.nextDouble() - 1);
		return new Displacement2D(y);
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
	}

	@Override
	public double getMagnitude2() {
		double magnitude2 = x[0]*x[0]+x[1]*x[1];
		return magnitude2;
	}

	@Override
	public double[] toArray() {
		return x;
	}

}
