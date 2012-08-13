package org.phystools.monte.geometry;

import java.util.Random;

public interface Displacement {

	public abstract Displacement newGaussianRand(double sigma, Random rand);

	public abstract Displacement newUniformDisplacement(double delta,
			Random rand);

	public abstract double getMagnitude();

	public abstract void makeGaussianRandom(double sigma, Random rand);

	public abstract double getMagnitude2();

	public abstract double[] toArray();
}