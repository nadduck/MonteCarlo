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
		for(int i = 0; i < 2; i++) {
			y[i] = delta * (2 * rand.nextDouble() - 1);
		}
		return new Displacement2D(y);
	}

	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(getMagnitude2());
		return magnitude;
	}

	@Override
	public void makeGaussianRandom(double sigma, Random rand) {
		for(int i = 0; i < 2; i++) {
			x[i] = sigma * rand.nextGaussian();
		}
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
