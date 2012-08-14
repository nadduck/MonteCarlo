package org.phystools.monte.geometry;

import java.util.Random;

public class Displacement3D implements Displacement {

	private double[] x = new double[3];

	public Displacement3D(double[] x) {
		this.x  = x;
	}
	
	public Displacement3D() {
	}
	
	@Override
	public Displacement newGaussianRand(double sigma, Random rand) {
		Displacement delta = new Displacement3D();
		delta.makeGaussianRandom(sigma, rand);
		return delta;
	}

	@Override
	public Displacement newUniformDisplacement(double delta, Random rand) {
		double[] y = new double[3];
		for(int i = 0; i < 3; i++) {
			y[i] = delta * (2 * rand.nextDouble() - 1);
		}
		return new Displacement3D(y);
	}

	@Override
	public double getMagnitude() {
		double magnitude = Math.sqrt(getMagnitude2());
		return magnitude;
	}

	@Override
	public void makeGaussianRandom(double sigma, Random rand) {
		for(int i = 0; i < 3; i++) {
			x[i] = sigma * rand.nextGaussian();
		}
	}

	@Override
	public double getMagnitude2() {
		double magnitude2 = x[0]*x[0]+x[1]*x[1]+x[2]*x[2];
		return magnitude2;
	}

	@Override
	public double[] toArray() {
		return x;
	}

}
